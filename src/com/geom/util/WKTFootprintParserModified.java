package com.geom.util;


import com.esri.core.geometry.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WKTFootprintParserModified
{
    /**
     * Check WKT Footprint validity.
     *
     * @param wkt to check.
     * @return corrected WKT footprint or null.
     */
    public static String reformatWKTFootprint(String wkt)
    {
        int poleThreshold = 100;

        Geometry geometry = OperatorImportFromWkt.local()
                .execute(WktImportFlags.wktImportDefaults, Geometry.Type.Unknown, wkt, null);

        List<Geometry> out = new ArrayList<>(5);
        Geometry tmp = null;
        if (geometry instanceof Polygon)
        {
            tmp = parsePolygon((Polygon) geometry.copy(), poleThreshold, true, true);
            if (tmp != null)
            {
                out.add(tmp);
            }
            tmp = parsePolygon((Polygon) geometry.copy(), poleThreshold, false, true);
            if (tmp != null)
            {
                out.add(tmp);
            }
            tmp = parsePolygon((Polygon) geometry.copy(), poleThreshold, true, true);
            if (tmp != null)
            {
                out.add(tmp);
            }
            tmp = parsePolygon((Polygon) geometry.copy(), poleThreshold, false, false);
            if (tmp != null)
            {
                out.add(tmp);
            }
        }
        else if (geometry instanceof Polyline)
        {
            tmp = parseLinestring((Polyline) geometry.copy());
        }

        out = out.stream().filter(g -> ((Polygon) g).getPathCount() <= 2)
                .sorted(Comparator.comparingDouble(Geometry::calculateArea2D))
                .collect(Collectors.toList());

        if (!out.isEmpty())
        {
            tmp = out.get(0);
        }
        else if (tmp == null)
        {
            tmp = geometry;
        }

        tmp = OperatorDensifyByLength.local().execute(tmp, 15, null);

        return OperatorExportToWkt.local().execute(WktExportFlags.wktExportDefaults, tmp, null);
    }

    private static Geometry parseLinestring(Polyline polyline)
    {
        SpatialReference wgs84 = SpatialReference.create(4326);

        Point prev = null;
        int crossing = 0;
        Transformation2D cr = new Transformation2D();
        cr.shift(0, 0);
        for (int i = 0; i < polyline.getPointCount(); i++)
        {
            Point p = polyline.getPoint(i);
            if (prev == null)
            {
                prev = p;
            }
            else
            {
                if (prev.getX() - p.getX() - crossing * 360 > 300)
                {
                    // east west crossing
                    crossing++;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }
                else if (prev.getX() - p.getX() - crossing * 360 < -300)
                {
                    // west east
                    crossing--;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }

                p.applyTransformation(cr);
                polyline.setPoint(i, p);

                prev = p;
            }
        }

        Envelope2D envelope = new Envelope2D(-180, -85, 180, 85);
        Polyline firstClip = (Polyline) OperatorClip.local().execute(polyline, envelope, wgs84, null);

        envelope = new Envelope2D(180, -85, 180 + 360, 85);
        Polyline secondClip = (Polyline) OperatorClip.local().execute(polyline, envelope, wgs84, null);

        Transformation2D trans = new Transformation2D();
        trans.shift(-360, 0);
        secondClip.applyTransformation(trans);

        Polyline union = (Polyline) OperatorUnion.local().execute(firstClip, secondClip, wgs84, null);

        if (!OperatorSimplifyOGC.local().isSimpleOGC(polyline, wgs84, true, null, null))
        {
            union = (Polyline) OperatorSimplifyOGC.local().execute(union, wgs84, true, null);
        }

        return union;
    }

    private static Geometry parsePolygon(Polygon polygon, double poleThreshold,
                                         boolean goBack, boolean goForward)
    {
        SpatialReference wgs84 = SpatialReference.create(4326);

        /*Point prev = null;
        int crossing = 0;
        Transformation2D cr = new Transformation2D();
        cr.shift(0, 0);
        for (int i = 0; i < polygon.getPointCount(); i++)
        {
            Point p = polygon.getPoint(i);

            *//*if (Math.abs(p.getY()) > 86.5)
            {
                polygon.removePoint(polygon.getPathIndexFromPointIndex(i), i);
                i--;
                continue;
            }*//*
            if (prev == null)
            {
                prev = p;
            }
            else
            {
                if (crossing < 1 && prev.getX() - p.getX() - crossing * 360 > 300)
                {
                    // east west crossing
                    crossing++;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }
                else if (prev.getX() - p.getX() - crossing * 360 < -300)
                {
                    // west east
                    crossing--;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }
                else if (crossing > 0 && goBack && prev.getX() - p.getX() - crossing * 360 < -poleThreshold)
                {
                    // west east
                    crossing--;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }
                else if (goForward && prev.getX() - p.getX()
                        - crossing * 360 > poleThreshold)
                {
                    // east west
                    crossing++;
                    cr = new Transformation2D();
                    cr.shift(crossing * 360, 0);
                }

                p.applyTransformation(cr);
                polygon.setPoint(i, p);

                prev = p;
            }
        }

        if (crossing == 1)
        {
            crossing--;
            cr = new Transformation2D();
            cr.shift(crossing * 360, 0);
            prev.applyTransformation(cr);
            polygon.setPoint(polygon.getPointCount() - 1, prev);
        }
        else if (crossing == -1)
        {
            crossing++;
            cr = new Transformation2D();
            cr.shift(crossing * 360, 0);
            prev.applyTransformation(cr);
            polygon.setPoint(polygon.getPointCount() - 1, prev);
        }
        if (crossing != 0)
        {
            return null;
        }*/

        Geometry geom = Main.getNorthPoleCut(polygon);
        if(geom != null)
            polygon = (Polygon) geom;
        else {
            geom = Main.getSouthPoleCut(polygon);
            if(geom !=null) {
                polygon = (Polygon) geom;
            }
        }

        System.out.println("***** Footprint is " + OperatorExportToWkt.local().execute(WktExportFlags.wktExportDefaults, polygon, null));

        Envelope2D envelope = new Envelope2D(-180, -85, 180, 85);
        Polygon firstClip = (Polygon) OperatorClip.local().execute(polygon, envelope, wgs84, null);

        envelope = new Envelope2D(180, -85, 180 + 360, 85);
        Polygon secondClip = (Polygon) OperatorClip.local().execute(polygon, envelope, wgs84, null);

        envelope = new Envelope2D(-180 - 360, -85, -180, 85);
        Polygon thirdClip = (Polygon) OperatorClip.local().execute(polygon, envelope, wgs84, null);

        Transformation2D trans = new Transformation2D();
        trans.shift(-360, 0);
        secondClip.applyTransformation(trans);

        trans = new Transformation2D();
        trans.shift(+360, 0);
        thirdClip.applyTransformation(trans);

        Polygon multipolygon = (Polygon) OperatorUnion.local().execute(firstClip, secondClip, wgs84, null);
        multipolygon = (Polygon) OperatorUnion.local().execute(multipolygon, thirdClip, wgs84, null);

        if (!OperatorSimplifyOGC.local().isSimpleOGC(polygon, wgs84, true, null, null))
        {
            multipolygon = (Polygon) OperatorSimplifyOGC.local().execute(multipolygon, wgs84, true, null);
        }

        return multipolygon;
    }
}
