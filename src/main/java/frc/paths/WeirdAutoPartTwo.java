package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class WeirdAutoPartTwo extends Path {
   private final static double[][] points = {
       {0,0.0,0.0,-1.95,0.0,0.0,-0.0},
       {0.0168,0.0,0.0,-1.95,-0.0298,0.0292,0.0303},
       {0.0335,-0.0005,0.0005,-1.9495,-0.0597,0.0585,0.0606},
       {0.0503,-0.0015,0.0015,-1.9485,-0.0895,0.0877,0.0909},
       {0.0671,-0.003,0.0029,-1.947,-0.1193,0.117,0.1212},
       {0.0838,-0.005,0.0049,-1.9449,-0.1491,0.1462,0.1514},
       {0.1006,-0.0075,0.0074,-1.9424,-0.179,0.1755,0.1817},
       {0.1174,-0.0105,0.0103,-1.9393,-0.2088,0.2047,0.212},
       {0.1341,-0.014,0.0137,-1.9358,-0.2386,0.234,0.2422},
       {0.1509,-0.018,0.0177,-1.9317,-0.2685,0.2632,0.2725},
       {0.1677,-0.0225,0.0221,-1.9272,-0.2983,0.2925,0.3028},
       {0.1844,-0.0275,0.027,-1.9221,-0.3281,0.3217,0.333},
       {0.2012,-0.033,0.0324,-1.9165,-0.3579,0.351,0.3633},
       {0.218,-0.039,0.0383,-1.9104,-0.3878,0.3802,0.3936},
       {0.2347,-0.0455,0.0446,-1.9038,-0.4176,0.4095,0.4238},
       {0.2515,-0.0525,0.0515,-1.8967,-0.4474,0.4387,0.4541},
       {0.2683,-0.06,0.0588,-1.8891,-0.4773,0.468,0.4844},
       {0.285,-0.068,0.0667,-1.881,-0.5071,0.4972,0.5147},
       {0.3018,-0.0765,0.075,-1.8723,-0.5369,0.5265,0.5449},
       {0.3186,-0.0855,0.0839,-1.8632,-0.5667,0.5558,0.5752},
       {0.3353,-0.095,0.0932,-1.8535,-0.5966,0.585,0.6056},
       {0.3521,-0.105,0.103,-1.8434,-0.6264,0.6143,0.6359},
       {0.3689,-0.1155,0.1133,-1.8327,-0.6562,0.6435,0.6662},
       {0.3856,-0.1265,0.1241,-1.8216,-0.686,0.6728,0.6966},
       {0.4024,-0.138,0.1354,-1.8099,-0.7158,0.702,0.727},
       {0.4192,-0.15,0.1471,-1.7977,-0.7457,0.7313,0.7574},
       {0.4359,-0.1625,0.1594,-1.785,-0.7755,0.7606,0.7878},
       {0.4527,-0.1755,0.1721,-1.7718,-0.8053,0.7898,0.8183},
       {0.4695,-0.189,0.1854,-1.7581,-0.8351,0.8191,0.8488},
       {0.4862,-0.203,0.1991,-1.7438,-0.8649,0.8483,0.8794},
       {0.503,-0.2175,0.2133,-1.7291,-0.8947,0.8776,0.91},
       {0.5198,-0.2325,0.2281,-1.7138,-0.9246,0.9069,0.9407},
       {0.5365,-0.248,0.2433,-1.6981,-0.9544,0.9361,0.9714},
       {0.5533,-0.264,0.259,-1.6818,-0.9842,0.9654,1.0022},
       {0.5701,-0.2806,0.2751,-1.665,-1.014,0.9947,1.0331},
       {0.5868,-0.2976,0.2918,-1.6477,-1.0438,1.0239,1.0641},
       {0.6036,-0.3151,0.309,-1.6298,-1.0736,1.0532,1.0953},
       {0.6204,-0.3331,0.3266,-1.6114,-1.1034,1.0825,1.1265},
       {0.6371,-0.3516,0.3448,-1.5926,-1.1332,1.1117,1.1579},
       {0.6539,-0.3706,0.3634,-1.5731,-1.1629,1.141,1.1894},
       {0.6707,-0.39,0.3826,-1.5532,-1.1927,1.1702,1.2212},
       {0.6874,-0.41,0.4022,-1.5327,-1.2225,1.1995,1.2531},
       {0.7042,-0.4305,0.4223,-1.5117,-1.2523,1.2287,1.2853},
       {0.721,-0.4515,0.4429,-1.4902,-1.282,1.258,1.3178},
       {0.7377,-0.473,0.464,-1.4681,-1.3118,1.2872,1.3507},
       {0.7545,-0.495,0.4856,-1.4454,-1.3416,1.3165,1.384},
       {0.7713,-0.5175,0.5076,-1.4222,-1.3713,1.3457,1.4179},
       {0.788,-0.5405,0.5302,-1.3984,-1.401,1.3749,1.4527},
       {0.8048,-0.564,0.5533,-1.3741,-1.4307,1.4041,1.4888},
       {0.8216,-0.588,0.5768,-1.3491,-1.4604,1.4332,1.5275},
       {0.8383,-0.6125,0.6008,-1.3235,-1.4898,1.462,1.5747},
       {0.8551,-0.6375,0.6253,-1.2971,-1.4611,1.4323,1.6205},
       {0.8719,-0.662,0.6494,-1.2699,-1.4312,1.4028,1.5995},
       {0.8886,-0.686,0.6729,-1.2431,-1.4013,1.3734,1.5731},
       {0.9054,-0.7094,0.6959,-1.2168,-1.3715,1.344,1.5446},
       {0.9222,-0.7324,0.7184,-1.1909,-1.3417,1.3147,1.5149},
       {0.9389,-0.7549,0.7405,-1.1655,-1.3119,1.2853,1.4844},
       {0.9557,-0.7769,0.762,-1.1406,-1.2821,1.256,1.4533},
       {0.9725,-0.7984,0.7831,-1.1162,-1.2523,1.2267,1.4218},
       {0.9892,-0.8194,0.8037,-1.0924,-1.2225,1.1974,1.3899},
       {1.006,-0.8399,0.8237,-1.0691,-1.1927,1.1681,1.3577},
       {1.0228,-0.8599,0.8433,-1.0463,-1.1629,1.1389,1.3252},
       {1.0395,-0.8794,0.8624,-1.0241,-1.1331,1.1096,1.2924},
       {1.0563,-0.8984,0.881,-1.0024,-1.1033,1.0803,1.2595},
       {1.0731,-0.9169,0.8991,-0.9813,-1.0735,1.0511,1.2263},
       {1.0898,-0.9349,0.9168,-0.9607,-1.0437,1.0218,1.193},
       {1.1066,-0.9524,0.9339,-0.9407,-1.0139,0.9926,1.1596},
       {1.1234,-0.9694,0.9505,-0.9213,-0.9841,0.9633,1.126},
       {1.1401,-0.9859,0.9667,-0.9024,-0.9543,0.9341,1.0923},
       {1.1569,-1.0019,0.9823,-0.8841,-0.9246,0.9048,1.0585},
       {1.1737,-1.0174,0.9975,-0.8663,-0.8948,0.8756,1.0246},
       {1.1904,-1.0324,1.0122,-0.8492,-0.865,0.8464,0.9907},
       {1.2072,-1.0469,1.0264,-0.8326,-0.8352,0.8172,0.9566},
       {1.224,-1.0609,1.0401,-0.8165,-0.8053,0.788,0.9226},
       {1.2407,-1.0744,1.0533,-0.801,-0.7755,0.7587,0.8885},
       {1.2575,-1.0874,1.066,-0.7862,-0.7457,0.7295,0.8543},
       {1.2743,-1.0999,1.0782,-0.7718,-0.7159,0.7003,0.8202},
       {1.291,-1.1119,1.09,-0.7581,-0.6861,0.6711,0.786},
       {1.3078,-1.1234,1.1012,-0.7449,-0.6563,0.6419,0.7518},
       {1.3246,-1.1344,1.112,-0.7323,-0.6265,0.6127,0.7176},
       {1.3413,-1.1449,1.1223,-0.7203,-0.5967,0.5835,0.6834},
       {1.3581,-1.1549,1.1321,-0.7088,-0.5668,0.5544,0.6492},
       {1.3749,-1.1645,1.1414,-0.6979,-0.537,0.5252,0.6149},
       {1.3916,-1.1735,1.1502,-0.6876,-0.5072,0.496,0.5807},
       {1.4084,-1.182,1.1585,-0.6779,-0.4774,0.4668,0.5465},
       {1.4252,-1.19,1.1663,-0.6687,-0.4475,0.4376,0.5123},
       {1.4419,-1.1975,1.1736,-0.6601,-0.4177,0.4084,0.4781},
       {1.4587,-1.2045,1.1805,-0.6521,-0.3879,0.3792,0.444},
       {1.4755,-1.211,1.1869,-0.6447,-0.3581,0.3501,0.4098},
       {1.4922,-1.217,1.1927,-0.6378,-0.3282,0.3209,0.3756},
       {1.509,-1.2225,1.1981,-0.6315,-0.2984,0.2917,0.3415},
       {1.5258,-1.2275,1.203,-0.6258,-0.2686,0.2625,0.3073},
       {1.5425,-1.232,1.2074,-0.6206,-0.2387,0.2334,0.2732},
       {1.5593,-1.236,1.2113,-0.616,-0.2089,0.2042,0.239},
       {1.5761,-1.2395,1.2147,-0.612,-0.179,0.175,0.2049},
       {1.5928,-1.2425,1.2177,-0.6086,-0.1492,0.1458,0.1707},
       {1.6096,-1.245,1.2201,-0.6057,-0.1194,0.1167,0.1366},
       {1.6264,-1.247,1.2221,-0.6034,-0.0895,0.0875,0.1025},
       {1.6431,-1.2485,1.2235,-0.6017,-0.0597,0.0583,0.0683},
       {1.6599,-1.2495,1.2245,-0.6006,-0.0298,0.0292,0.0342},
       {1.6767,-1.25,1.225,-0.6,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
