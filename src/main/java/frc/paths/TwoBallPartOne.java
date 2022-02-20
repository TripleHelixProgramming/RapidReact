package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class TwoBallPartOne extends Path {
   private final static double[][] points = {
       {0,0.0,0.0,2.32,-0.0,-0.0,0.0},
       {0.0169,-0.0,-0.0,2.32,0.0284,-0.0,0.007},
       {0.0338,0.0005,-0.0,2.3201,0.0568,-0.0,0.0139},
       {0.0508,0.0014,-0.0,2.3204,0.0851,-0.0,0.0208},
       {0.0677,0.0029,-0.0,2.3207,0.1135,-0.0,0.0278},
       {0.0846,0.0048,-0.0,2.3212,0.1419,-0.0,0.0347},
       {0.1015,0.0072,-0.0,2.3218,0.1703,-0.0,0.0417},
       {0.1184,0.0101,-0.0,2.3225,0.1986,-0.0,0.0486},
       {0.1353,0.0134,-0.0,2.3233,0.227,-0.0,0.0556},
       {0.1523,0.0173,-0.0,2.3242,0.2554,-0.0,0.0625},
       {0.1692,0.0216,-0.0,2.3253,0.2838,-0.0,0.0695},
       {0.1861,0.0264,-0.0,2.3265,0.3122,-0.0,0.0764},
       {0.203,0.0317,-0.0,2.3278,0.3405,-0.0,0.0834},
       {0.2199,0.0374,-0.0,2.3292,0.3689,-0.0,0.0903},
       {0.2368,0.0437,-0.0,2.3307,0.3973,-0.0,0.0973},
       {0.2538,0.0504,-0.0,2.3323,0.4257,-0.0,0.1042},
       {0.2707,0.0576,-0.0,2.3341,0.4541,-0.0,0.1112},
       {0.2876,0.0653,-0.0,2.336,0.4824,-0.0,0.1181},
       {0.3045,0.0735,-0.0,2.338,0.5108,-0.0,0.1251},
       {0.3214,0.0821,-0.0,2.3401,0.5392,-0.0,0.132},
       {0.3384,0.0912,-0.0,2.3423,0.5676,-0.0,0.139},
       {0.3553,0.1008,-0.0,2.3447,0.5959,-0.0,0.1459},
       {0.3722,0.1109,-0.0,2.3472,0.6243,-0.0,0.1529},
       {0.3891,0.1215,-0.0,2.3497,0.6527,-0.0,0.1599},
       {0.406,0.1325,-0.0,2.3524,0.6811,-0.0,0.1668},
       {0.4229,0.144,-0.0,2.3553,0.7095,-0.0,0.1738},
       {0.4399,0.156,-0.0,2.3582,0.7378,-0.0,0.1808},
       {0.4568,0.1685,-0.0,2.3613,0.7662,-0.0,0.1878},
       {0.4737,0.1815,-0.0,2.3644,0.7946,-0.0,0.1947},
       {0.4906,0.1949,-0.0,2.3677,0.823,-0.0,0.2017},
       {0.5075,0.2088,-0.0,2.3712,0.8514,-0.0,0.2087},
       {0.5245,0.2232,-0.0,2.3747,0.8797,-0.0,0.2157},
       {0.5414,0.2381,-0.0,2.3783,0.9081,-0.0,0.2227},
       {0.5583,0.2535,-0.0,2.3821,0.9365,-0.0,0.2297},
       {0.5752,0.2693,-0.0,2.386,0.9649,-0.0,0.2367},
       {0.5921,0.2857,-0.0,2.39,0.9932,-0.0,0.2437},
       {0.609,0.3025,-0.0,2.3941,1.0216,-0.0,0.2507},
       {0.626,0.3197,-0.0,2.3984,1.05,-0.0,0.2577},
       {0.6429,0.3375,-0.0,2.4027,1.0784,-0.0,0.2647},
       {0.6598,0.3558,-0.0,2.4072,1.1068,-0.0,0.2718},
       {0.6767,0.3745,-0.0,2.4118,1.1351,-0.0,0.2788},
       {0.6936,0.3937,-0.0,2.4165,1.1635,-0.0,0.2858},
       {0.7105,0.4134,-0.0,2.4213,1.1919,-0.0,0.2929},
       {0.7275,0.4335,-0.0,2.4263,1.2203,-0.0,0.2999},
       {0.7444,0.4542,-0.0,2.4314,1.2486,-0.0,0.307},
       {0.7613,0.4753,-0.0,2.4366,1.277,-0.0,0.3141},
       {0.7782,0.4969,-0.0,2.4419,1.3054,-0.0,0.3211},
       {0.7951,0.519,-0.0,2.4473,1.3338,-0.0,0.3282},
       {0.8121,0.5416,-0.0,2.4529,1.3622,-0.0,0.3353},
       {0.829,0.5646,-0.0,2.4585,1.3905,-0.0,0.3425},
       {0.8459,0.5881,-0.0,2.4643,1.4186,0.0,0.3596},
       {0.8628,0.6121,-0.0,2.4704,1.3902,0.0,0.3525},
       {0.8797,0.6356,-0.0,2.4764,1.3618,0.0,0.3455},
       {0.8966,0.6587,-0.0,2.4822,1.3335,0.0,0.3384},
       {0.9136,0.6812,-0.0,2.4879,1.3051,0.0,0.3313},
       {0.9305,0.7033,-0.0,2.4935,1.2767,0.0,0.3242},
       {0.9474,0.7249,-0.0,2.499,1.2483,0.0,0.317},
       {0.9643,0.746,-0.0,2.5044,1.22,0.0,0.3099},
       {0.9812,0.7667,-0.0,2.5096,1.1916,0.0,0.3028},
       {0.9981,0.7868,-0.0,2.5148,1.1632,0.0,0.2957},
       {1.0151,0.8065,-0.0,2.5198,1.1348,0.0,0.2885},
       {1.032,0.8257,-0.0,2.5246,1.1064,0.0,0.2814},
       {1.0489,0.8444,-0.0,2.5294,1.0781,0.0,0.2742},
       {1.0658,0.8627,-0.0,2.534,1.0497,0.0,0.267},
       {1.0827,0.8804,-0.0,2.5386,1.0213,0.0,0.2599},
       {1.0997,0.8977,-0.0,2.543,0.9929,0.0,0.2527},
       {1.1166,0.9145,-0.0,2.5472,0.9646,0.0,0.2455},
       {1.1335,0.9308,-0.0,2.5514,0.9362,0.0,0.2384},
       {1.1504,0.9467,-0.0,2.5554,0.9078,0.0,0.2312},
       {1.1673,0.962,-0.0,2.5593,0.8794,0.0,0.224},
       {1.1842,0.9769,-0.0,2.5631,0.8511,0.0,0.2168},
       {1.2012,0.9913,-0.0,2.5668,0.8227,0.0,0.2096},
       {1.2181,1.0052,-0.0,2.5703,0.7943,0.0,0.2024},
       {1.235,1.0187,-0.0,2.5738,0.7659,0.0,0.1952},
       {1.2519,1.0316,-0.0,2.5771,0.7375,0.0,0.188},
       {1.2688,1.0441,-0.0,2.5802,0.7092,0.0,0.1808},
       {1.2858,1.0561,-0.0,2.5833,0.6808,0.0,0.1735},
       {1.3027,1.0676,-0.0,2.5862,0.6524,0.0,0.1663},
       {1.3196,1.0786,-0.0,2.589,0.624,0.0,0.1591},
       {1.3365,1.0892,-0.0,2.5917,0.5957,0.0,0.1519},
       {1.3534,1.0993,-0.0,2.5943,0.5673,0.0,0.1447},
       {1.3703,1.1089,-0.0,2.5968,0.5389,0.0,0.1374},
       {1.3873,1.118,-0.0,2.5991,0.5105,0.0,0.1302},
       {1.4042,1.1266,-0.0,2.6013,0.4821,0.0,0.123},
       {1.4211,1.1348,-0.0,2.6034,0.4538,0.0,0.1157},
       {1.438,1.1425,-0.0,2.6053,0.4254,0.0,0.1085},
       {1.4549,1.1497,-0.0,2.6072,0.397,0.0,0.1013},
       {1.4718,1.1564,-0.0,2.6089,0.3686,0.0,0.094},
       {1.4888,1.1626,-0.0,2.6105,0.3403,0.0,0.0868},
       {1.5057,1.1684,-0.0,2.6119,0.3119,0.0,0.0796},
       {1.5226,1.1736,-0.0,2.6133,0.2835,0.0,0.0723},
       {1.5395,1.1784,-0.0,2.6145,0.2551,0.0,0.0651},
       {1.5564,1.1828,-0.0,2.6156,0.2268,0.0,0.0578},
       {1.5734,1.1866,-0.0,2.6166,0.1984,0.0,0.0506},
       {1.5903,1.1899,-0.0,2.6174,0.17,0.0,0.0434},
       {1.6072,1.1928,-0.0,2.6182,0.1416,0.0,0.0361},
       {1.6241,1.1952,-0.0,2.6188,0.1132,0.0,0.0289},
       {1.641,1.1971,-0.0,2.6193,0.0849,0.0,0.0216},
       {1.6579,1.1986,-0.0,2.6196,0.0565,0.0,0.0144},
       {1.6749,1.1995,-0.0,2.6199,0.0281,0.0,0.0072},
       {1.6918,1.2,0.0,2.62,-0.0003,-0.0,-0.0001},
       {1.7087,1.2,-0.0,2.62,-0.0286,-0.0,-0.0073},
       {1.7256,1.1995,-0.0,2.6199,-0.057,-0.0,-0.0146},
       {1.7425,1.1985,-0.0,2.6196,-0.0854,-0.0,-0.0218},
       {1.7594,1.1971,-0.0,2.6193,-0.1138,-0.0,-0.029},
       {1.7764,1.1952,-0.0,2.6188,-0.1421,-0.0,-0.0363},
       {1.7933,1.1928,-0.0,2.6182,-0.1705,-0.0,-0.0435},
       {1.8102,1.1899,-0.0,2.6174,-0.1989,-0.0,-0.0508},
       {1.8271,1.1865,-0.0,2.6166,-0.2272,-0.0,-0.058},
       {1.844,1.1827,-0.0,2.6156,-0.2556,-0.0,-0.0652},
       {1.8609,1.1784,-0.0,2.6145,-0.284,-0.0,-0.0725},
       {1.8778,1.1736,-0.0,2.6132,-0.3124,-0.0,-0.0797},
       {1.8948,1.1683,-0.0,2.6119,-0.3407,-0.0,-0.087},
       {1.9117,1.1625,-0.0,2.6104,-0.3691,-0.0,-0.0942},
       {1.9286,1.1563,-0.0,2.6088,-0.3975,-0.0,-0.1014},
       {1.9455,1.1495,-0.0,2.6071,-0.4258,-0.0,-0.1087},
       {1.9624,1.1423,-0.0,2.6053,-0.4542,-0.0,-0.1159},
       {1.9793,1.1347,-0.0,2.6033,-0.4826,-0.0,-0.1232},
       {1.9962,1.1265,-0.0,2.6012,-0.511,-0.0,-0.1304},
       {2.0132,1.1179,-0.0,2.599,-0.5393,-0.0,-0.1376},
       {2.0301,1.1087,-0.0,2.5967,-0.5677,-0.0,-0.1449},
       {2.047,1.0991,-0.0,2.5943,-0.5961,-0.0,-0.1521},
       {2.0639,1.089,-0.0,2.5917,-0.6244,-0.0,-0.1593},
       {2.0808,1.0785,-0.0,2.589,-0.6528,-0.0,-0.1665},
       {2.0977,1.0674,-0.0,2.5862,-0.6812,-0.0,-0.1738},
       {2.1146,1.0559,-0.0,2.5832,-0.7096,-0.0,-0.181},
       {2.1316,1.0439,-0.0,2.5802,-0.7379,-0.0,-0.1882},
       {2.1485,1.0314,-0.0,2.577,-0.7663,-0.0,-0.1954},
       {2.1654,1.0185,-0.0,2.5737,-0.7947,-0.0,-0.2026},
       {2.1823,1.005,-0.0,2.5703,-0.8231,-0.0,-0.2098},
       {2.1992,0.9911,-0.0,2.5667,-0.8514,-0.0,-0.217},
       {2.2161,0.9767,-0.0,2.563,-0.8798,-0.0,-0.2242},
       {2.233,0.9618,-0.0,2.5592,-0.9082,-0.0,-0.2314},
       {2.25,0.9465,-0.0,2.5553,-0.9365,-0.0,-0.2386},
       {2.2669,0.9306,-0.0,2.5513,-0.9649,-0.0,-0.2458},
       {2.2838,0.9143,-0.0,2.5471,-0.9933,-0.0,-0.253},
       {2.3007,0.8975,-0.0,2.5429,-1.0217,-0.0,-0.2602},
       {2.3176,0.8802,-0.0,2.5385,-1.05,-0.0,-0.2673},
       {2.3345,0.8625,-0.0,2.5339,-1.0784,-0.0,-0.2745},
       {2.3514,0.8442,-0.0,2.5293,-1.1068,-0.0,-0.2817},
       {2.3684,0.8255,-0.0,2.5245,-1.1351,-0.0,-0.2888},
       {2.3853,0.8063,-0.0,2.5196,-1.1635,-0.0,-0.296},
       {2.4022,0.7866,-0.0,2.5146,-1.1919,-0.0,-0.3031},
       {2.4191,0.7665,-0.0,2.5095,-1.2203,-0.0,-0.3103},
       {2.436,0.7458,-0.0,2.5043,-1.2486,-0.0,-0.3174},
       {2.4529,0.7247,-0.0,2.4989,-1.277,-0.0,-0.3245},
       {2.4698,0.7031,-0.0,2.4934,-1.3054,-0.0,-0.3316},
       {2.4868,0.681,-0.0,2.4878,-1.3338,-0.0,-0.3388},
       {2.5037,0.6585,-0.0,2.4821,-1.3621,-0.0,-0.3458},
       {2.5206,0.6354,-0.0,2.4762,-1.3905,-0.0,-0.3529},
       {2.5375,0.6119,-0.0,2.4702,-1.4186,0.0,-0.3496},
       {2.5544,0.5879,-0.0,2.4643,-1.3903,0.0,-0.3425},
       {2.5713,0.5644,-0.0,2.4585,-1.3619,0.0,-0.3354},
       {2.5883,0.5413,-0.0,2.4529,-1.3335,0.0,-0.3283},
       {2.6052,0.5188,-0.0,2.4473,-1.3052,0.0,-0.3212},
       {2.6221,0.4967,-0.0,2.4419,-1.2768,0.0,-0.3141},
       {2.639,0.4751,-0.0,2.4366,-1.2484,0.0,-0.3071},
       {2.6559,0.454,-0.0,2.4314,-1.22,0.0,-0.3},
       {2.6728,0.4334,-0.0,2.4263,-1.1917,0.0,-0.293},
       {2.6897,0.4132,-0.0,2.4213,-1.1633,0.0,-0.2859},
       {2.7067,0.3935,-0.0,2.4165,-1.1349,0.0,-0.2789},
       {2.7236,0.3743,-0.0,2.4118,-1.1065,0.0,-0.2718},
       {2.7405,0.3556,-0.0,2.4072,-1.0782,0.0,-0.2648},
       {2.7574,0.3374,-0.0,2.4027,-1.0498,0.0,-0.2578},
       {2.7743,0.3196,-0.0,2.3983,-1.0214,0.0,-0.2508},
       {2.7912,0.3023,-0.0,2.3941,-0.9931,0.0,-0.2438},
       {2.8081,0.2856,-0.0,2.39,-0.9647,0.0,-0.2367},
       {2.8251,0.2692,-0.0,2.386,-0.9363,0.0,-0.2297},
       {2.842,0.2534,-0.0,2.3821,-0.9079,0.0,-0.2227},
       {2.8589,0.238,-0.0,2.3783,-0.8796,0.0,-0.2157},
       {2.8758,0.2232,-0.0,2.3747,-0.8512,0.0,-0.2087},
       {2.8927,0.2088,-0.0,2.3711,-0.8228,0.0,-0.2018},
       {2.9096,0.1948,-0.0,2.3677,-0.7944,0.0,-0.1948},
       {2.9265,0.1814,-0.0,2.3644,-0.7661,0.0,-0.1878},
       {2.9435,0.1685,-0.0,2.3613,-0.7377,0.0,-0.1808},
       {2.9604,0.156,-0.0,2.3582,-0.7093,0.0,-0.1738},
       {2.9773,0.144,-0.0,2.3553,-0.681,0.0,-0.1669},
       {2.9942,0.1325,-0.0,2.3524,-0.6526,0.0,-0.1599},
       {3.0111,0.1214,-0.0,2.3497,-0.6242,0.0,-0.1529},
       {3.028,0.1109,-0.0,2.3471,-0.5958,0.0,-0.146},
       {3.0449,0.1008,-0.0,2.3447,-0.5675,0.0,-0.139},
       {3.0619,0.0912,-0.0,2.3423,-0.5391,0.0,-0.132},
       {3.0788,0.0821,-0.0,2.3401,-0.5107,0.0,-0.1251},
       {3.0957,0.0734,-0.0,2.338,-0.4823,0.0,-0.1181},
       {3.1126,0.0653,-0.0,2.336,-0.454,0.0,-0.1112},
       {3.1295,0.0576,-0.0,2.3341,-0.4256,0.0,-0.1042},
       {3.1464,0.0504,-0.0,2.3323,-0.3972,0.0,-0.0973},
       {3.1633,0.0437,-0.0,2.3307,-0.3689,0.0,-0.0903},
       {3.1803,0.0374,-0.0,2.3292,-0.3405,0.0,-0.0834},
       {3.1972,0.0317,-0.0,2.3278,-0.3121,0.0,-0.0764},
       {3.2141,0.0264,-0.0,2.3265,-0.2837,0.0,-0.0695},
       {3.231,0.0216,-0.0,2.3253,-0.2554,0.0,-0.0625},
       {3.2479,0.0173,-0.0,2.3242,-0.227,0.0,-0.0556},
       {3.2648,0.0134,-0.0,2.3233,-0.1986,0.0,-0.0486},
       {3.2818,0.0101,-0.0,2.3225,-0.1702,0.0,-0.0417},
       {3.2987,0.0072,-0.0,2.3218,-0.1419,0.0,-0.0347},
       {3.3156,0.0048,-0.0,2.3212,-0.1135,0.0,-0.0278},
       {3.3325,0.0029,-0.0,2.3207,-0.0851,0.0,-0.0208},
       {3.3494,0.0014,-0.0,2.3204,-0.0567,0.0,-0.0139},
       {3.3663,0.0005,-0.0,2.3201,-0.0284,0.0,-0.0069},
       {3.3832,0.0,0.0,2.32,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
