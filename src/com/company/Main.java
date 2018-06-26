package com.company;

import com.esri.core.geometry.*;

import java.awt.geom.Area;

public class Main {

    private static String processPoints(String points, String pattern, String coordSeparator, String pointSeparator){
        String[] pointsArray = points.split(" ");
        String coords = "";
        for(int i = 0; i < pointsArray.length; i=i+2)
        {
            coords += String.format(pattern, pointsArray[i],pointsArray[i+1],coordSeparator,((i!=pointsArray.length-2)?pointSeparator:""));
        }
        System.out.println("Retrieved Coordinates: "+coords);
        return coords;
    }


    public static String points2GML(String pointsString){
        System.out.println("----------Input coordinates GML Coordinates: "+pointsString);
        return Main.processPoints(pointsString,"%1$s%3$s%2$s%4$s",","," ");
    }

    public static String points2JTS(String pointsString){
        System.out.println("----------Input coordinates JTS : "+pointsString);
        return Main.processPoints(pointsString,"%2$s%3$s%1$s%4$s"," ",",");
    }

    public static void main(String[] args) {
	// write your code here
        // this is a satellite orbit with its corresponding swath, it goes from north pole to north pole going around the globe
        //String footprint = "POLYGON ((49.5859 89.3816,115.712 89.8297,-163.024 89.4178,-154.723 88.8588,-151.886 88.2905,-150.465 87.7199,-149.612 87.1483,-149.043 86.5762,-148.637 86.0039,-148.333 85.4314,-148.096 84.8587,-147.907 84.286,-147.752 83.7132,-147.623 83.1403,-147.513 82.5674,-147.42 81.9945,-147.338 81.4215,-147.267 80.8485,-147.204 80.2755,-147.148 79.7024,-147.098 79.1293,-147.053 78.5561,-147.012 77.983,-146.975 77.4098,-146.941 76.8366,-146.909 76.2633,-146.881 75.69,-146.854 75.1167,-146.829 74.5433,-146.806 73.97,-146.785 73.3965,-146.765 72.8231,-146.746 72.2496,-146.742 72.1062,-140.744 72.0312,-131.759 71.5596,-123.385 70.6869,-115.843 69.464,-109.209 67.9488,-103.452 66.1973,-98.4598 64.2462,-94.178 62.1563,-90.4715 59.9483,-87.2451 57.6464,-84.4184 55.269,-81.9237 52.8306,-79.7063 50.3423,-77.7213 47.8129,-75.9322 45.2493,-74.3094 42.657,-72.8283 40.0405,-71.4689 37.4035,-70.2145 34.7489,-69.0513 32.0792,-67.9676 29.3965,-66.9538 26.7026,-66.0014 23.999,-65.1035 21.287,-64.254 18.5678,-63.4478 15.8424,-62.6804 13.1117,-61.9478 10.3765,-61.2468 7.63755,-60.5743 4.89547,-59.9278 2.15088,-59.305 -0.595683,-58.704 -3.34371,-58.1228 -6.09275,-57.5601 -8.84237,-57.0144 -11.5922,-56.4845 -14.3419,-55.9695 -17.0911,-55.4682 -19.8396,-54.9801 -22.587,-54.5043 -25.3332,-54.0404 -28.0779,-53.5879 -30.821,-53.1466 -33.5623,-52.7161 -36.3017,-52.2966 -39.0391,-51.8881 -41.7744,-51.491 -44.5075,-51.1058 -47.2385,-50.7336 -49.9673,-50.3755 -52.6939,-50.0336 -55.4184,-49.7104 -58.1407,-49.4099 -60.8611,-49.1377 -63.5794,-48.9022 -66.2958,-48.7161 -69.0103,-48.6002 -71.723,-48.5896 -74.4337,-48.7494 -77.1422,-49.2144 -79.8479,-50.3145 -82.5488,-53.1225 -85.2381,-64.0671 -87.8737,-177.529 -88.9745,149.079 -86.4983,144.207 -83.8225,142.533 -81.1255,141.831 -78.4217,141.551 -75.7142,141.492 -73.0044,141.564 -70.2925,141.72 -67.5787,141.934 -64.863,142.191 -62.1454,142.479 -59.4258,142.792 -56.7042,143.125 -53.9804,143.476 -51.2546,143.842 -48.5265,144.221 -45.7963,144.612 -43.0638,145.016 -40.3292,145.43 -37.5925,145.855 -34.8537,146.292 -32.1129,146.739 -29.3702,147.197 -26.6258,147.667 -23.8799,148.15 -21.1326,148.645 -18.3841,149.153 -15.6348,149.676 -12.8849,150.214 -10.1346,150.769 -7.38438,151.341 -4.63455,151.933 -1.88552,152.545 0.862255,153.181 3.6083,153.841 6.3521,154.528 9.09307,155.246 11.8306,155.996 14.5639,156.784 17.2924,157.613 20.015,158.487 22.7309,159.414 25.439,160.398 28.1381,161.448 30.8266,162.573 33.503,163.783 36.1654,165.092 38.8113,166.514 41.4381,168.068 44.0425,169.776 46.6205,171.666 49.1669,173.77 51.6756,176.13 54.1388,178.795 56.5464,-178.174 58.8857,-174.702 61.1403,-170.701 63.2888,-166.068 65.3035,-160.695 67.1495,-154.483 68.7826,-147.366 70.1504,-139.366 71.1952,-130.632 71.861,-121.463 72.1056,-121.467 72.249,-121.485 72.8225,-121.503 73.396,-121.524 73.9694,-121.545 74.5428,-121.569 75.1161,-121.594 75.6894,-121.621 76.2627,-121.651 76.836,-121.683 77.4092,-121.718 77.9824,-121.757 78.5556,-121.799 79.1287,-121.847 79.7018,-121.9 80.2749,-121.959 80.848,-122.026 81.421,-122.103 81.994,-122.192 82.5669,-122.295 83.1399,-122.417 83.7127,-122.564 84.2856,-122.743 84.8583,-122.966 85.431,-123.254 86.0035,-123.637 86.5759,-124.174 87.1481,-124.98 87.7198,-126.323 88.2906,-129.003 88.8592,-136.854 89.4194,140.518 89.8389,74.0012 89.3843,137.051 87.0571,142.22 84.3624,143.773 81.6485,144.363 78.9292,144.559 76.2071,144.553 73.4831,144.432 70.7575,144.236 68.0304,143.99 65.3018,143.708 62.5716,143.398 59.8399,143.067 57.1065,142.717 54.3713,142.353 51.6345,141.976 48.8958,141.587 46.1555,141.186 43.4134,140.776 40.6696,140.355 37.9241,139.924 35.1771,139.483 32.4287,139.031 29.6789,138.569 26.9279,138.096 24.176,137.611 21.4232,137.115 18.6699,136.605 15.9163,136.081 13.1626,135.543 10.4092,134.989 7.65648,134.417 4.90469,133.827 2.15428,133.217 -0.59433,132.585 -3.34066,131.928 -6.0842,131.245 -8.8244,130.532 -11.5607,129.787 -14.2923,129.006 -17.0186,128.186 -19.7387,127.32 -22.4517,126.404 -25.1566,125.432 -27.8522,124.396 -30.5372,123.288 -33.21,122.096 -35.8687,120.81 -38.5112,119.413 -41.1348,117.889 -43.7365,116.215 -46.3125,114.367 -48.8579,112.311 -51.367,110.01 -53.8323,107.414 -56.2445,104.466 -58.5916,101.092 -60.8579,97.2077 -63.0235,92.7126 -65.0624,87.4973 -66.9411,81.457 -68.6178,74.516 -70.0415,66.6715 -71.1551,58.0434 -71.9015,48.9031 -72.2343,39.6483 -72.1306,30.7067 -71.5978,22.4249 -70.671,15.0014 -69.4035,8.49228 -67.8534,2.85427 -66.0755,-2.00681 -64.1165,-6.19983 -62.0142,-9.83095 -59.7982,-12.9938 -57.4917,-15.7672 -55.1124,-18.2171 -52.674,-20.3969 -50.1872,-22.35 -47.6603,-24.1122 -45.1001,-25.7121 -42.5117,-27.1736 -39.8995,-28.5163 -37.2669,-29.7563 -34.6168,-30.9072 -31.9516,-31.9802 -29.2732,-32.985 -26.5834,-33.9295 -23.8837,-34.8206 -21.1752,-35.6643 -18.4591,-36.4657 -15.7364,-37.229 -13.008,-37.9582 -10.2745,-38.6565 -7.53676,-39.3269 -4.79538,-39.9718 -2.05092,-40.5936 0.696075,-41.1941 3.44512,-41.7752 6.19576,-42.3383 8.94758,-42.8849 11.7002,-43.4161 14.4533,-43.9329 17.2065,-44.4364 19.9595,-44.9274 22.7122,-45.4064 25.4641,-45.8742 28.2152,-46.3312 30.9651,-46.7777 33.7139,-47.214 36.4613,-47.6403 39.2071,-48.0564 41.9514,-48.4623 44.694,-48.8574 47.435,-49.2411 50.1741,-49.6124 52.9116,-49.9698 55.6473,-50.3109 58.3813,-50.6327 61.1136,-50.9304 63.8443,-51.197 66.5734,-51.4215 69.3011,-51.5862 72.0272,-51.6604 74.7667,-51.5853 77.4897,-51.2396 80.2104,-50.3127 82.9276,-47.724 85.6357,-35.6305 88.2935,49.5859 89.3816))";
// this is a polygon that goes near the north pole and cross the dateline
        //String appo = "44.522938,169.98872 46.22109,171.32088 47.899612,172.74696 49.55597,174.27748 51.18739,175.92383 52.790535,177.69911 54.361607,179.61789 55.89623,-178.30334 57.38929,-176.04712 58.834766,-173.59375 60.22597,-170.92282 61.55472,-168.0122 62.811855,-164.84044 63.987373,-161.38802 65.06948,-157.63791 66.045654,-153.57964 66.90239,-149.21252 67.62608,-144.54852 68.203636,-139.61584 68.62305,-134.46054 68.87515,-129.14671 68.95347,-123.751816 68.856415,-118.36168 68.58648,-113.06115 68.14983,-107.92694 67.55651,-103.02109 66.81813,-98.38775 65.948135,-94.05347 64.96032,-90.02853 63.868153,-86.31056 62.683865,-82.888725 61.418957,-79.74581 60.083527,-76.861626 58.68689,-74.21437 57.236313,-71.782845 55.73908,-69.54605 54.200985,-67.48465 52.6267,-65.58147 51.021023,-63.819805 49.387196,-62.185886 47.72878,-60.666386 46.04856,-59.24997 44.34874,-57.92665 42.63155,-56.687275 40.89877,-55.5238 39.151993,-54.42934 37.392773,-53.397175 35.622074,-52.42237 33.84115,-51.499542 32.051014,-50.624115 30.25233,-49.79276 28.44609,-49.001324 26.63294,-48.246838 24.813377,-47.526684 22.988262,-46.83794 21.157784,-46.178604 19.322521,-45.546753 17.483086,-44.940002 15.639606,-44.357246 13.792631,-43.796608 11.942515,-43.256824 10.089407,-42.7367 8.233862,-42.23496 6.3759227,-41.75081 4.516005,-41.28326 2.654333,-40.831245 0.7909649,-40.394665 -1.0735977,-39.97211 -2.9393368,-39.563396 -4.8059945,-39.167892 -6.673411,-38.78519 -8.541258,-38.41468 -10.409539,-38.056232 -12.278092,-37.709568 -14.146573,-37.374073 -16.015045,-37.05018 -17.883303,-36.737217 -19.75118,-36.435387 -21.618439,-36.14445 -23.485199,-35.86484 -25.351164,-35.59629 -27.216297,-35.339157 -29.080423,-35.093483 -30.943531,-34.859653 -32.805435,-34.638283 -34.66612,-34.429752 -36.525425,-34.2346 -38.383266,-34.053673 -40.23961,-33.887867 -42.09434,-33.738384 -43.94734,-33.606243 -45.79859,-33.493347 -47.647934,-33.401493 -49.495308,-33.3331 -51.34061,-33.290184 -53.183643,-33.276623 -55.024406,-33.296642 -56.862633,-33.354916 -58.698135,-33.45724 -60.53066,-33.61163 -62.35989,-33.827816 -64.185524,-34.118015 -66.00688,-34.498604 -67.823326,-34.99068 -69.63386,-35.62357 -71.43707,-36.43752 -73.23107,-37.488056 -75.01286,-38.859333 -76.777985,-40.67526 -77.070145,-41.033268 -77.080696,-40.637653 -77.168495,-33.352737 -76.9001,-21.731657 -76.49998,-14.877884 -76.01412,-9.1141615 -75.598274,-5.172482 -75.14166,-1.4627341 -74.75628,1.3169882 -74.32594,4.135445 -73.95211,6.387315 -73.556366,8.606112 -73.51993,8.802779 -73.12999,10.835362 -72.66041,13.125154 -72.21761,15.147055 -71.657364,17.538492 -71.09829,19.759718 -70.34064,22.534054 -69.51734,25.271645 -68.26598,28.948072 -66.66622,32.936615 -65.21153,36.008907 -65.21153,36.008907 -64.161125,32.21125 -63.015617,28.708488 -61.78637,25.486057 -60.483532,22.525656 -59.11708,19.805395 -57.694485,17.30515 -56.22282,15.004531 -54.708347,12.8839035 -53.156166,10.925441 -51.57073,9.113 -49.95613,7.4316654 -48.31559,5.868526 -46.65206,4.4116697 -44.968006,3.0506847 -43.26571,1.7760633 -41.546963,0.580082 -39.81355,-0.54519093 -38.06674,-1.6056492 -36.30799,-2.6071374 -34.538425,-3.555454 -32.759,-4.4544654 -30.970795,-5.3088384 -29.174324,-6.121799 -27.370493,-6.896607 -25.559904,-7.6364226 -23.743261,-8.343953 -21.920853,-9.021244 -20.093472,-9.670721 -18.261414,-10.294385 -16.424984,-10.893511 -14.584759,-11.4701605 -12.740965,-12.025408 -10.893972,-12.560824 -9.044141,-13.077517 -7.191658,-13.576657 -5.336875,-14.058818 -3.479977,-14.525274 -1.6211689,-14.9765625 0.23922819,-15.413483 2.101067,-15.836812 3.9641368,-16.246895 5.828224,-16.644276 7.693185,-17.029533 9.558817,-17.403046 11.425041,-17.765238 13.291556,-18.116127 15.158373,-18.456264 17.02526,-18.785677 18.892117,-19.1045 20.758907,-19.412716 22.625349,-19.710613 24.491491,-19.998209 26.357042,-20.27521 28.222055,-20.541363 30.08649,-20.796848 31.9501,-21.041103 33.81288,-21.273764 35.674755,-21.494429 37.535645,-21.702543 39.39547,-21.897253 41.254147,-22.077837 43.111633,-22.24319 44.9679,-22.391935 46.822796,-22.522943 48.67633,-22.633932 50.528378,-22.723055 52.37881,-22.787643 54.227627,-22.82398 56.074688,-22.82872 57.91978,-22.796772 59.762936,-22.721983 61.603695,-22.59625 63.441956,-22.409742 65.27723,-22.150183 67.10911,-21.799877 68.93704,-21.336367 70.75991,-20.727577 72.57654,-19.93016 74.3849,-18.878796 76.18198,-17.476175 77.96302,-15.569505 79.72013,-12.907542 81.43843,-9.044301 83.089424,-3.150065 84.61119,6.411733 85.85879,22.70994 86.5232,48.683117 86.27803,77.88885 85.262085,98.780846 83.84591,111.11801 82.24545,118.50443 80.55366,123.20818 78.81169,126.37748 77.03949,128.60823 75.247765,130.22963 73.44258,131.43538 71.627625,132.34523 69.805374,133.03792 67.97737,133.56648 66.14477,133.96704 64.30832,134.26714 62.468628,134.486 60.62598,134.63849 58.780827,134.7358 56.93344,134.78674 55.083946,134.79773 53.23261,134.77455 51.37941,134.72186 51.07039,134.71039 51.05588,134.83908 50.739105,137.38402 50.180855,141.0973 49.785248,143.40266 49.409588,145.4571 49.129387,146.94225 48.848743,148.41144 48.62805,149.56377 48.39535,150.78203 48.2029,151.7946 48.007416,152.82903 47.9898,152.92256 47.804893,153.90765 47.590137,155.05951 47.39441,156.11618 47.15455,157.41748 46.921925,158.68036 46.613697,160.339 46.28274,162.07597 45.77642,164.59404 45.106255,167.62195 44.522938,169.98872 44.522938,169.98872 ";
        String appo = "44.7003,-110.58415 46.396362,-109.24269 48.072445,-107.806046 49.72612,-106.26398 51.354553,-104.60485 52.95425,-102.81506 54.521538,-100.880325 56.05179,-98.783844 57.539925,-96.50795 58.97983,-94.03304 60.364616,-91.33808 61.686035,-88.40142 62.93503,-85.20163 64.10104,-81.71916 65.17254,-77.93789 66.13662,-73.848114 66.980125,-69.4504 67.6891,-64.757835 68.250496,-59.80063 68.65279,-54.62694 68.88688,-49.302265 68.94712,-43.905254 68.83211,-38.522015 68.54468,-33.23675 68.09162,-28.124796 67.482574,-23.246449 66.73029,-18.643984 65.84788,-14.342073 64.84887,-10.349725 63.746883,-6.663373 62.55397,-3.271526 61.281696,-0.15639026 59.939728,2.7020686 58.53752,5.3263254 57.08222,7.7369595 55.58075,9.95476 54.0388,11.998976 52.461487,13.887178 50.852757,15.634958 49.21657,17.256824 47.556095,18.765589 45.873913,20.171904 44.172417,21.486412 42.453705,22.71774 40.7196,23.873877 38.971676,24.961912 37.211277,25.98787 35.439716,26.957499 33.65793,27.87527 31.866966,28.746017 30.067614,29.573324 28.260723,30.360867 26.44714,31.111938 24.627003,31.828743 22.801334,32.51437 20.97048,33.170914 19.13489,33.800304 17.294975,34.404324 15.451352,34.985107 13.604035,35.543564 11.753683,36.081387 9.900449,36.59956 8.044624,37.099525 6.186531,37.581993 4.3264775,38.048023 2.4646633,38.498444 0.60126626,38.93384 -1.2633866,39.354916 -3.1292682,39.76229 -4.99593,40.156734 -6.8633156,40.538265 -8.731316,40.907425 -10.599631,41.264736 -12.468144,41.61029 -14.336697,41.944492 -16.205128,42.267475 -18.073305,42.579308 -19.94112,42.880093 -21.808489,43.169685 -23.675158,43.448326 -25.541138,43.71582 -27.406193,43.97188 -29.27031,44.21638 -31.133295,44.448997 -32.995117,44.66909 -34.85574,44.876385 -36.714966,45.07008 -38.572784,45.249638 -40.429024,45.413868 -42.283684,45.56159 -44.1366,45.691753 -45.987694,45.802753 -47.836967,45.892403 -49.68422,45.958492 -51.529438,45.99851 -53.372337,46.00861 -55.212936,45.985165 -57.051044,45.922733 -58.88632,45.81516 -60.71866,45.655144 -62.54768,45.43185 -64.37296,45.133095 -66.19397,44.742775 -68.00996,44.237263 -69.81991,43.587452 -71.62245,42.75231 -73.41545,41.67347 -75.19586,40.262615 -76.95905,38.391315 -77.25073,38.02121 -77.26174,38.42199 -77.356255,45.807583 -77.09239,57.598793 -76.691284,64.54874 -76.20249,70.38618 -75.783554,74.3731 -75.32325,78.120964 -74.9346,80.92618 -74.50054,83.76767 -74.123436,86.035866 -73.72422,88.268906 -73.68747,88.46674 -73.294106,90.51062 -72.82044,92.81129 -72.37383,94.84118 -71.80884,97.24013 -71.24513,99.46645 -70.48141,102.24462 -69.65184,104.98321 -68.391624,108.65649 -66.781815,112.63567 -65.31914,115.696434 -65.31914,115.696434 -64.27891,111.867355 -63.14231,108.33448 -61.921,105.08367 -60.625175,102.09654 -59.26459,99.352554 -57.847122,96.83072 -56.38005,94.51019 -54.869335,92.37223 -53.32063,90.3978 -51.73828,88.5711 -50.126293,86.87709 -48.488064,85.30242 -46.826595,83.835106 -45.144352,82.4649 -43.44361,81.18209 -41.726273,79.97854 -39.994003,78.84679 -38.2485,77.779915 -36.490734,76.772606 -34.722057,75.8194 -32.94345,74.91555 -31.15589,74.0571 -29.360117,73.24025 -27.556871,72.46184 -25.746803,71.718704 -23.930618,71.00824 -22.108673,70.32807 -20.281603,69.67606 -18.449926,69.0501 -16.613762,68.44874 -14.773811,67.869934 -12.930299,67.31271 -11.083555,66.7752 -9.233888,66.256905 -7.3815804,65.756424 -5.5268874,65.2728 -3.6701517,64.80481 -1.8115053,64.352104 0.048853192,63.913998 1.9105796,63.48953 3.7735097,63.078373 5.6375613,62.67974 7.502506,62.293503 9.368122,61.918842 11.234254,61.555725 13.100817,61.20387 14.967663,60.8628 16.834568,60.53261 18.70144,60.212955 20.568193,59.903954 22.434715,59.60496 24.300814,59.316597 26.166496,59.0388 28.031567,58.771633 29.896023,58.51545 31.75965,58.270096 33.622524,58.036556 35.484432,57.81482 37.3454,57.60567 39.20523,57.40981 41.063953,57.22827 42.92158,57.061302 44.777843,56.911057 46.6328,56.778595 48.486378,56.665894 50.338474,56.57459 52.188984,56.508236 54.037933,56.46864 55.885063,56.46097 57.73028,56.489433 59.573433,56.56037 61.414307,56.68118 63.25263,56.86118 65.08819,57.11349 66.920334,57.45437 68.748436,57.906193 70.57174,58.498734 72.38882,59.276207 74.19792,60.29941 75.99611,61.66193 77.77884,63.50926 79.538574,66.080605 81.26146,69.79394 82.921005,75.42397 84.46001,84.49684 85.74592,99.912575 86.48893,124.90564 86.341545,154.39299 85.38814,176.32146 84.0014,-170.63106 82.41508,-162.8579 80.73084,-157.93832 78.99337,-154.63953 77.22411,-152.32678 75.43438,-150.65009 73.63065,-149.40593 71.816864,-148.46808 69.99558,-147.7537 68.16837,-147.20923 66.33641,-146.7955 64.5005,-146.48521 62.661247,-146.25797 60.81918,-146.09831 58.9745,-145.99525 57.127563,-145.93935 55.27843,-145.92404 53.42742,-145.94331 51.574585,-145.99263 51.265667,-146.00356 51.25124,-145.87422 50.93583,-143.31667 50.378815,-139.58556 49.983524,-137.26955 49.60786,-135.20587 49.327488,-133.71425 49.04654,-132.23877 48.825512,-131.0816 48.59237,-129.85834 48.399475,-128.84166 48.203476,-127.80311 48.18581,-127.709206 48.00034,-126.720245 47.784855,-125.56394 47.588383,-124.503265 47.347507,-123.197105 47.113792,-121.9296 46.80398,-120.26502 46.47117,-118.521996 45.961807,-115.99551 45.2874,-112.95799 44.7003,-110.58415 44.7003,-110.58415";
        String footprint = "POLYGON((" + points2JTS(appo.replaceAll(","," ")) + "))";
        System.out.println("Original WKT is " +  footprint);

        //String footprint = "POLYGON ((80.0522 83.7357,85.3112 83.7546,90.5581 83.7213,95.7078 83.6365,100.684 83.5023,105.426 83.3217,109.891 83.0982,114.054 82.836,117.907 82.539,121.451 82.2113,124.7 81.8566,127.671 81.4782,130.385 81.0792,132.863 80.6622,135.127 80.2294,137.198 79.783,139.096 79.3245,140.837 78.8555,142.438 78.3773,143.914 77.8909,145.276 77.3974,146.536 76.8974,147.705 76.3917,148.792 75.8809,149.804 75.3655,150.748 74.8461,151.63 74.3229,152.457 73.7964,153.234 73.2669,153.963 72.7346,154.651 72.1998,155.299 71.6627,155.912 71.1235,156.06 70.9884,164.137 71.7139,172.839 72.0764,-178.306 72.038,-169.674 71.6014,-169.569 71.7409,-169.131 72.2983,-168.666 72.8546,-168.171 73.4098,-167.643 73.9637,-167.077 74.5162,-166.472 75.0671,-165.821 75.6162,-165.119 76.1635,-164.361 76.7085,-163.54 77.2511,-162.647 77.7909,-161.673 78.3275,-160.607 78.8606,-159.436 79.3894,-158.143 79.9135,-156.712 80.432,-155.119 80.944,-153.339 81.4482,-151.34 81.9433,-149.083 82.4274,-146.526 82.8984,-143.613 83.3533,-140.285 83.7888,-136.471 84.2005,-132.1 84.5828,-127.101 84.9291,-121.421 85.2317,-115.046 85.4816,-108.023 85.6699,-100.486 85.7882,-92.6582 85.8305,-84.8183 85.7945,-72.3555 88.3373,56.9057 88.7863,76.6766 86.2836,80.0522 83.7357))";
// this is a polygon that wrap the south pole
        //String footprint = "POLYGON ((2.18368 -70.4477,1.45577 -70.9684,0.688619 -71.4861,-0.120913 -72.0005,-0.976256 -72.5113,-1.88118 -73.0182,-2.83984 -73.5208,-3.85678 -74.0188,-4.937 -74.5116,-6.086 -74.9989,-7.30979 -75.48,-8.61495 -75.9544,-10.0087 -76.4212,-11.4988 -76.8797,-13.0937 -77.3291,-14.8025 -77.7682,-16.6348 -78.196,-18.6008 -78.6112,-20.7109 -79.0124,-22.9756 -79.3979,-25.4052 -79.766,-28.0091 -80.1148,-30.7954 -80.4422,-33.7701 -80.7458,-36.9358 -81.0233,-40.291 -81.2722,-43.829 -81.49,-47.5368 -81.6742,-51.3944 -81.8226,-55.3753 -81.9331,-59.4462 -82.0043,-63.5689 -82.0351,-67.7022 -82.0249,-68.7323 -82.016,-70.8282 -84.5548,-77.4161 -87.1071,-137.182 -89.2345,135.202 -87.492,132.016 -87.5294,118.591 -87.598,105.152 -87.5319,93.0367 -87.3409,82.9272 -87.0494,74.8366 -86.6837,68.4512 -86.2656,63.3987 -85.8108,59.3589 -85.3299,56.0846 -84.8302,53.3929 -84.3167,51.15 -83.7928,49.2572 -83.2609,47.6416 -82.7228,46.2483 -82.1797,45.0354 -81.6326,43.9708 -81.0823,43.0292 -80.5293,42.1908 -79.974,41.4397 -79.4168,40.763 -78.8579,40.1502 -78.2977,39.5927 -77.7363,39.0833 -77.1738,38.616 -76.6104,38.1858 -76.0462,37.7885 -75.4813,37.4202 -74.9157,37.078 -74.3495,36.7592 -73.7828,36.4613 -73.2156,36.1823 -72.648,35.9204 -72.08,27.0691 -72.2504,18.2473 -72.0184,9.82037 -71.3991,2.18368 -70.4477))";

        Geometry geometry = OperatorImportFromWkt.local()
                .execute(WktImportFlags.wktImportDefaults, Geometry.Type.Unknown, footprint, null);

        System.out.println("Geometry is " + geometry);

        SpatialReference wgs84 = SpatialReference.create(4326);
// clip to exclude parts outside of map

// cut over northpole
        Polyline northpole = new Polyline(new Point(-180, 85), new Point(180, 85));
        Polyline southpole = new Polyline(new Point(-180, -85), new Point(180, -85));
        GeometryCursor cursor = OperatorCut.local().execute(false, geometry, northpole, wgs84, null);
        System.out.println("GeometryCursor for NORTHPOLE is " + cursor);
        Polygon poly = null;
        //System.out.println("next is " + cursor.next());
        int i=0;
        while((poly = (Polygon)cursor.next()) !=null){
            System.out.println("Polygon [ " + i+ " ] is " + poly);
            System.out.println("WKT is " +
                    OperatorExportToWkt.local().execute(WktExportFlags.wktExportDefaults, poly, null));
            i++;
        }

        cursor = OperatorCut.local().execute(true, geometry, southpole, wgs84, null);
        System.out.println("GeometryCursor for SOUTHPOLE is " + cursor);

        //System.out.println("next is " + cursor.next());
        i=0;
        while((poly = (Polygon)cursor.next()) !=null){
            System.out.println("Polygon [ " + i+ " ] is " + poly);
            System.out.println("WKT is " +
                    OperatorExportToWkt.local().execute(WktExportFlags.wktExportDefaults, poly, null));
            i++;
        }









    }
}