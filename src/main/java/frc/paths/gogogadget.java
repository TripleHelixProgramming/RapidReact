package frc.paths;

public class gogogadget extends Path {
   private final static double[][] points = {
       {5.0,2.0,-0.0},
       {5.0,2.0,-0.0},
       {5.0014,2.0007,0.0005},
       {5.0043,2.002,0.0014},
       {5.0085,2.0041,0.0029},
       {5.0142,2.0068,0.0048},
       {5.0213,2.0102,0.0071},
       {5.0299,2.0142,0.0099},
       {5.0398,2.019,0.0132},
       {5.0512,2.0244,0.017},
       {5.064,2.0305,0.0211},
       {5.0783,2.0372,0.0258},
       {5.0939,2.0446,0.0308},
       {5.1111,2.0527,0.0363},
       {5.1296,2.0615,0.0423},
       {5.1496,2.0709,0.0487},
       {5.171,2.081,0.0554},
       {5.1938,2.0917,0.0627},
       {5.2181,2.1031,0.0703},
       {5.2438,2.1152,0.0783},
       {5.2709,2.1279,0.0868},
       {5.2996,2.1413,0.0956},
       {5.3297,2.1554,0.1049},
       {5.3612,2.1701,0.1145},
       {5.3942,2.1855,0.1246},
       {5.4287,2.2015,0.1349},
       {5.4646,2.2181,0.1457},
       {5.5019,2.2354,0.1568},
       {5.5407,2.2533,0.1682},
       {5.581,2.2719,0.18},
       {5.6227,2.291,0.192},
       {5.6659,2.3108,0.2044},
       {5.7105,2.3313,0.2171},
       {5.7566,2.3523,0.23},
       {5.8042,2.3739,0.2431},
       {5.8532,2.3961,0.2565},
       {5.9038,2.419,0.2701},
       {5.9558,2.4424,0.2838},
       {6.0093,2.4663,0.2977},
       {6.0643,2.4909,0.3117},
       {6.1208,2.516,0.3257},
       {6.1788,2.5416,0.3398},
       {6.2384,2.5678,0.3538},
       {6.2995,2.5944,0.3677},
       {6.3621,2.6216,0.3815},
       {6.4263,2.6492,0.395},
       {6.492,2.6772,0.4082},
       {6.5592,2.7056,0.421},
       {6.6279,2.7343,0.4332},
       {6.6982,2.7634,0.4446},
       {6.77,2.7926,0.4554},
       {6.8434,2.822,0.4653},
       {6.9182,2.8515,0.4746},
       {6.9947,2.8808,0.4831},
       {7.0726,2.9098,0.491},
       {7.152,2.9383,0.4983},
       {7.2325,2.9657,0.5053},
       {7.3139,2.9919,0.5119},
       {7.3957,3.0165,0.5185},
       {7.4774,3.0395,0.5248},
       {7.5586,3.0611,0.5306},
       {7.6391,3.0812,0.5359},
       {7.7187,3.1001,0.5405},
       {7.7973,3.1176,0.5444},
       {7.8749,3.134,0.5476},
       {7.9515,3.1492,0.55},
       {8.0269,3.1634,0.5516},
       {8.1013,3.1765,0.5525},
       {8.1744,3.1886,0.5526},
       {8.2465,3.1996,0.5518},
       {8.3173,3.2097,0.5503},
       {8.3869,3.2188,0.548},
       {8.4554,3.2269,0.5449},
       {8.5226,3.2341,0.541},
       {8.5885,3.2403,0.5362},
       {8.6533,3.2456,0.5307},
       {8.7168,3.25,0.5244},
       {8.779,3.2535,0.5173},
       {8.84,3.2561,0.5094},
       {8.8998,3.2578,0.5007},
       {8.9582,3.2586,0.4912},
       {9.0154,3.2585,0.4809},
       {9.0713,3.2575,0.4698},
       {9.1259,3.2556,0.4579},
       {9.1792,3.2529,0.4452},
       {9.2312,3.2493,0.4317},
       {9.2819,3.2449,0.4174},
       {9.3313,3.2396,0.4024},
       {9.3795,3.2334,0.3865},
       {9.4263,3.2264,0.3699},
       {9.4718,3.2186,0.3525},
       {9.5161,3.2099,0.3344},
       {9.559,3.2004,0.3155},
       {9.6006,3.1901,0.2958},
       {9.6409,3.179,0.2754},
       {9.6799,3.167,0.2543},
       {9.7175,3.1542,0.2324},
       {9.7539,3.1406,0.2098},
       {9.7889,3.1262,0.1864},
       {9.8226,3.111,0.1624},
       {9.8549,3.095,0.1376},
       {9.886,3.0781,0.1122},
       {9.9157,3.0605,0.0861},
       {9.944,3.042,0.0593},
       {9.971,3.0227,0.0318},
       {9.9966,3.0027,0.0037},
       {10.0207,2.9816,-0.0251},
       {10.0432,2.9598,-0.0546},
       {10.0644,2.9371,-0.0846},
       {10.0843,2.9137,-0.1151},
       {10.1027,2.8895,-0.146},
       {10.1197,2.8645,-0.1773},
       {10.1354,2.8388,-0.2089},
       {10.1496,2.8125,-0.2408},
       {10.1624,2.7854,-0.2729},
       {10.1737,2.7577,-0.3051},
       {10.1835,2.7293,-0.3374},
       {10.1919,2.7003,-0.3697},
       {10.1988,2.6708,-0.4021},
       {10.2041,2.6407,-0.4344},
       {10.208,2.6102,-0.4668},
       {10.2103,2.5792,-0.4991},
       {10.211,2.5479,-0.5314},
       {10.2101,2.5163,-0.5637},
       {10.2077,2.4846,-0.596},
       {10.2036,2.4529,-0.6283},
       {10.198,2.4215,-0.6601},
       {10.191,2.3905,-0.6911},
       {10.1828,2.3604,-0.7206},
       {10.1737,2.3312,-0.7482},
       {10.1638,2.303,-0.7737},
       {10.1535,2.2758,-0.7971},
       {10.1426,2.2498,-0.8185},
       {10.1315,2.2249,-0.8379},
       {10.1201,2.2013,-0.8557},
       {10.1088,2.179,-0.8721},
       {10.0976,2.1582,-0.8872},
       {10.0868,2.1387,-0.9013},
       {10.0763,2.1206,-0.9144},
       {10.0664,2.1037,-0.9265},
       {10.0571,2.0882,-0.9376},
       {10.0483,2.074,-0.9478},
       {10.0402,2.0611,-0.957},
       {10.0328,2.0494,-0.9653},
       {10.0261,2.039,-0.9727},
       {10.0201,2.0298,-0.9791},
       {10.0149,2.0219,-0.9847},
       {10.0104,2.0152,-0.9894},
       {10.0067,2.0097,-0.9932},
       {10.0038,2.0055,-0.9962},
       {10.0017,2.0024,-0.9983},
   };
   public double[][] getPath() {
       return points;
   }
}