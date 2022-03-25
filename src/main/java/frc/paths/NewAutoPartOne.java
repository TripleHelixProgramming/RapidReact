package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class NewAutoPartOne extends Path {
   private final static double[][] points = {
       {0,0.0,0.0,-2.35,0.0,0.0,0.0},
       {0.0116,0.0,0.0,-2.35,0.036,0.0148,-0.0053},
       {0.0232,0.0004,0.0002,-2.3501,0.0721,0.0297,-0.0105},
       {0.0348,0.0013,0.0005,-2.3502,0.1081,0.0445,-0.0158},
       {0.0464,0.0025,0.001,-2.3504,0.1441,0.0593,-0.0211},
       {0.058,0.0042,0.0017,-2.3506,0.1802,0.0741,-0.0264},
       {0.0696,0.0063,0.0026,-2.3509,0.2162,0.0889,-0.0317},
       {0.0812,0.0088,0.0036,-2.3513,0.2523,0.1037,-0.037},
       {0.0928,0.0117,0.0048,-2.3517,0.2883,0.1185,-0.0423},
       {0.1044,0.0151,0.0062,-2.3522,0.3244,0.1332,-0.0476},
       {0.116,0.0188,0.0077,-2.3528,0.3605,0.1479,-0.0529},
       {0.1277,0.023,0.0095,-2.3534,0.3966,0.1627,-0.0582},
       {0.1393,0.0276,0.0113,-2.354,0.4327,0.1774,-0.0635},
       {0.1509,0.0326,0.0134,-2.3548,0.4688,0.192,-0.0689},
       {0.1625,0.0381,0.0156,-2.3556,0.5049,0.2067,-0.0742},
       {0.1741,0.0439,0.018,-2.3564,0.541,0.2213,-0.0796},
       {0.1857,0.0502,0.0206,-2.3574,0.5771,0.236,-0.0849},
       {0.1973,0.0569,0.0233,-2.3584,0.6132,0.2506,-0.0903},
       {0.2089,0.064,0.0262,-2.3594,0.6493,0.2651,-0.0956},
       {0.2205,0.0716,0.0293,-2.3605,0.6855,0.2797,-0.101},
       {0.2321,0.0795,0.0326,-2.3617,0.7217,0.2942,-0.1064},
       {0.2437,0.0879,0.036,-2.3629,0.7578,0.3087,-0.1118},
       {0.2553,0.0967,0.0396,-2.3642,0.794,0.3232,-0.1172},
       {0.2669,0.1059,0.0433,-2.3656,0.8302,0.3376,-0.1227},
       {0.2785,0.1155,0.0472,-2.367,0.8664,0.352,-0.1281},
       {0.2901,0.1256,0.0513,-2.3685,0.9026,0.3663,-0.1336},
       {0.3017,0.1361,0.0556,-2.37,0.9389,0.3807,-0.139},
       {0.3133,0.147,0.06,-2.3717,0.9751,0.3949,-0.1445},
       {0.3249,0.1583,0.0646,-2.3733,1.0114,0.4091,-0.15},
       {0.3365,0.17,0.0693,-2.3751,1.0477,0.4233,-0.1555},
       {0.3481,0.1822,0.0742,-2.3769,1.084,0.4374,-0.1611},
       {0.3598,0.1947,0.0793,-2.3787,1.1204,0.4515,-0.1667},
       {0.3714,0.2077,0.0845,-2.3807,1.1568,0.4654,-0.1723},
       {0.383,0.2212,0.0899,-2.3827,1.1932,0.4793,-0.1779},
       {0.3946,0.235,0.0955,-2.3847,1.2296,0.4931,-0.1835},
       {0.4062,0.2493,0.1012,-2.3869,1.2661,0.5068,-0.1892},
       {0.4178,0.264,0.1071,-2.3891,1.3026,0.5204,-0.195},
       {0.4294,0.2791,0.1131,-2.3913,1.3392,0.5338,-0.2008},
       {0.441,0.2946,0.1193,-2.3937,1.3758,0.5471,-0.2066},
       {0.4526,0.3106,0.1257,-2.3961,1.4125,0.5602,-0.2125},
       {0.4642,0.327,0.1322,-2.3985,1.4493,0.5731,-0.2185},
       {0.4758,0.3438,0.1388,-2.4011,1.4861,0.5857,-0.2246},
       {0.4874,0.3611,0.1456,-2.4037,1.5231,0.598,-0.2308},
       {0.499,0.3787,0.1526,-2.4063,1.5602,0.6099,-0.2371},
       {0.5106,0.3968,0.1597,-2.4091,1.5975,0.6213,-0.2436},
       {0.5222,0.4154,0.1669,-2.4119,1.635,0.6319,-0.2504},
       {0.5338,0.4344,0.1742,-2.4148,1.6727,0.6414,-0.2575},
       {0.5454,0.4538,0.1816,-2.4178,1.7109,0.6492,-0.2651},
       {0.557,0.4736,0.1892,-2.4209,1.7495,0.6541,-0.2735},
       {0.5686,0.4939,0.1968,-2.4241,1.7884,0.6525,-0.2834},
       {0.5802,0.5147,0.2043,-2.4274,1.8214,0.6319,-0.296},
       {0.5918,0.5358,0.2117,-2.4308,1.8134,0.5938,-0.304},
       {0.6035,0.5569,0.2186,-2.4343,1.7877,0.5644,-0.3054},
       {0.6151,0.5776,0.2251,-2.4379,1.7576,0.5398,-0.3043},
       {0.6267,0.598,0.2314,-2.4414,1.7256,0.5175,-0.302},
       {0.6383,0.618,0.2374,-2.4449,1.6926,0.4967,-0.2992},
       {0.6499,0.6377,0.2432,-2.4484,1.6591,0.4768,-0.2958},
       {0.6615,0.6569,0.2487,-2.4518,1.6252,0.4576,-0.2923},
       {0.6731,0.6758,0.254,-2.4552,1.591,0.4389,-0.2885},
       {0.6847,0.6942,0.2591,-2.4585,1.5566,0.4206,-0.2845},
       {0.6963,0.7123,0.264,-2.4618,1.522,0.4026,-0.2804},
       {0.7079,0.73,0.2686,-2.4651,1.4874,0.3848,-0.2762},
       {0.7195,0.7472,0.2731,-2.4683,1.4526,0.3672,-0.2719},
       {0.7311,0.7641,0.2774,-2.4715,1.4177,0.3498,-0.2676},
       {0.7427,0.7805,0.2814,-2.4746,1.3828,0.3326,-0.2632},
       {0.7543,0.7966,0.2853,-2.4776,1.3477,0.3154,-0.2587},
       {0.7659,0.8122,0.2889,-2.4806,1.3127,0.2984,-0.2542},
       {0.7775,0.8275,0.2924,-2.4836,1.2776,0.2815,-0.2497},
       {0.7891,0.8423,0.2957,-2.4865,1.2424,0.2647,-0.2451},
       {0.8007,0.8567,0.2987,-2.4893,1.2072,0.2479,-0.2405},
       {0.8123,0.8707,0.3016,-2.4921,1.172,0.2313,-0.2359},
       {0.8239,0.8843,0.3043,-2.4948,1.1368,0.2147,-0.2312},
       {0.8356,0.8975,0.3068,-2.4975,1.1015,0.1981,-0.2265},
       {0.8472,0.9103,0.3091,-2.5002,1.0662,0.1816,-0.2218},
       {0.8588,0.9227,0.3112,-2.5027,1.0309,0.1651,-0.2171},
       {0.8704,0.9346,0.3131,-2.5053,0.9955,0.1487,-0.2124},
       {0.882,0.9462,0.3148,-2.5077,0.9602,0.1324,-0.2076},
       {0.8936,0.9573,0.3164,-2.5101,0.9248,0.116,-0.2028},
       {0.9052,0.9681,0.3177,-2.5125,0.8894,0.0997,-0.1981},
       {0.9168,0.9784,0.3189,-2.5148,0.854,0.0835,-0.1933},
       {0.9284,0.9883,0.3199,-2.517,0.8185,0.0672,-0.1884},
       {0.94,0.9978,0.3206,-2.5192,0.7831,0.051,-0.1836},
       {0.9516,1.0069,0.3212,-2.5213,0.7477,0.0348,-0.1788},
       {0.9632,1.0155,0.3216,-2.5234,0.7122,0.0187,-0.1739},
       {0.9748,1.0238,0.3219,-2.5254,0.6767,0.0025,-0.1691},
       {0.9864,1.0317,0.3219,-2.5274,0.6412,-0.0136,-0.1642},
       {0.998,1.0391,0.3217,-2.5293,0.6057,-0.0297,-0.1593},
       {1.0096,1.0461,0.3214,-2.5311,0.5702,-0.0457,-0.1545},
       {1.0212,1.0528,0.3208,-2.5329,0.5347,-0.0618,-0.1496},
       {1.0328,1.059,0.3201,-2.5347,0.4992,-0.0778,-0.1447},
       {1.0444,1.0648,0.3192,-2.5364,0.4637,-0.0938,-0.1398},
       {1.056,1.0701,0.3181,-2.538,0.4282,-0.1098,-0.1348},
       {1.0676,1.0751,0.3169,-2.5395,0.3926,-0.1258,-0.1299},
       {1.0793,1.0797,0.3154,-2.541,0.3571,-0.1418,-0.125},
       {1.0909,1.0838,0.3138,-2.5425,0.3215,-0.1578,-0.1201},
       {1.1025,1.0875,0.3119,-2.5439,0.286,-0.1737,-0.1151},
       {1.1141,1.0909,0.3099,-2.5452,0.2504,-0.1896,-0.1102},
       {1.1257,1.0938,0.3077,-2.5465,0.2148,-0.2056,-0.1053},
       {1.1373,1.0963,0.3053,-2.5477,0.1793,-0.2215,-0.1003},
       {1.1489,1.0983,0.3028,-2.5489,0.1437,-0.2374,-0.0954},
       {1.1605,1.1,0.3,-2.55,0.1081,-0.2533,-0.0904},
       {1.1654,1.1005,0.2988,-2.5504,0.0932,-0.2599,-0.0883},
       {1.1702,1.101,0.2975,-2.5509,0.0783,-0.2665,-0.0862},
       {1.1751,1.1014,0.2962,-2.5513,0.0633,-0.2731,-0.0841},
       {1.1799,1.1017,0.2949,-2.5517,0.0484,-0.2797,-0.082},
       {1.1848,1.1019,0.2935,-2.5521,0.0335,-0.2863,-0.0799},
       {1.1897,1.1021,0.2921,-2.5525,0.0185,-0.2929,-0.0778},
       {1.1945,1.1022,0.2907,-2.5529,0.0036,-0.2994,-0.0757},
       {1.1994,1.1022,0.2892,-2.5532,-0.0114,-0.306,-0.0736},
       {1.2042,1.1021,0.2878,-2.5536,-0.0264,-0.3125,-0.0715},
       {1.2091,1.102,0.2862,-2.5539,-0.0414,-0.319,-0.0693},
       {1.214,1.1018,0.2847,-2.5543,-0.0563,-0.3254,-0.0672},
       {1.2188,1.1015,0.2831,-2.5546,-0.0713,-0.3319,-0.065},
       {1.2237,1.1012,0.2815,-2.5549,-0.0864,-0.3383,-0.0628},
       {1.2286,1.1007,0.2798,-2.5552,-0.1014,-0.3447,-0.0607},
       {1.2334,1.1003,0.2782,-2.5555,-0.1164,-0.3511,-0.0585},
       {1.2383,1.0997,0.2765,-2.5558,-0.1315,-0.3574,-0.0563},
       {1.2431,1.099,0.2747,-2.5561,-0.1465,-0.3638,-0.0541},
       {1.248,1.0983,0.273,-2.5563,-0.1616,-0.37,-0.0519},
       {1.2529,1.0976,0.2712,-2.5566,-0.1767,-0.3763,-0.0497},
       {1.2577,1.0967,0.2693,-2.5568,-0.1918,-0.3825,-0.0474},
       {1.2626,1.0958,0.2675,-2.5571,-0.2069,-0.3887,-0.0452},
       {1.2675,1.0948,0.2656,-2.5573,-0.222,-0.3948,-0.0429},
       {1.2723,1.0937,0.2637,-2.5575,-0.2371,-0.4009,-0.0406},
       {1.2772,1.0925,0.2617,-2.5577,-0.2523,-0.407,-0.0383},
       {1.282,1.0913,0.2597,-2.5579,-0.2675,-0.413,-0.036},
       {1.2869,1.09,0.2577,-2.558,-0.2827,-0.4189,-0.0336},
       {1.2918,1.0886,0.2557,-2.5582,-0.2979,-0.4248,-0.0313},
       {1.2966,1.0872,0.2536,-2.5584,-0.3132,-0.4306,-0.0289},
       {1.3015,1.0856,0.2515,-2.5585,-0.3285,-0.4363,-0.0265},
       {1.3064,1.0841,0.2494,-2.5586,-0.3438,-0.442,-0.0241},
       {1.3112,1.0824,0.2473,-2.5588,-0.3591,-0.4476,-0.0216},
       {1.3161,1.0806,0.2451,-2.5589,-0.3745,-0.453,-0.0191},
       {1.3209,1.0788,0.2429,-2.5589,-0.3899,-0.4584,-0.0166},
       {1.3258,1.0769,0.2406,-2.559,-0.4054,-0.4636,-0.014},
       {1.3307,1.0749,0.2384,-2.5591,-0.4209,-0.4687,-0.0113},
       {1.3355,1.0729,0.2361,-2.5592,-0.4365,-0.4737,-0.0087},
       {1.3404,1.0708,0.2338,-2.5592,-0.4521,-0.4784,-0.0059},
       {1.3453,1.0686,0.2315,-2.5592,-0.4678,-0.483,-0.0031},
       {1.3501,1.0663,0.2291,-2.5592,-0.4835,-0.4873,-0.0002},
       {1.355,1.064,0.2268,-2.5592,-0.4993,-0.4913,0.0028},
       {1.3598,1.0615,0.2244,-2.5592,-0.5152,-0.4949,0.0059},
       {1.3647,1.059,0.222,-2.5592,-0.5313,-0.498,0.0091},
       {1.3696,1.0564,0.2196,-2.5592,-0.5474,-0.5006,0.0125},
       {1.3744,1.0538,0.2171,-2.5591,-0.5636,-0.5024,0.0161},
       {1.3793,1.051,0.2147,-2.559,-0.5799,-0.5032,0.0199},
       {1.3842,1.0482,0.2122,-2.5589,-0.5962,-0.5025,0.0241},
       {1.389,1.0453,0.2098,-2.5588,-0.6123,-0.4998,0.0286},
       {1.3939,1.0423,0.2074,-2.5587,-0.6274,-0.4939,0.0335},
       {1.3987,1.0393,0.205,-2.5585,-0.6401,-0.4836,0.0387},
       {1.4036,1.0362,0.2026,-2.5583,-0.6474,-0.469,0.0436},
       {1.4085,1.033,0.2003,-2.5581,-0.648,-0.4527,0.0476},
       {1.4133,1.0299,0.1981,-2.5579,-0.6436,-0.437,0.0504},
       {1.4182,1.0267,0.196,-2.5576,-0.6362,-0.4224,0.0523},
       {1.4231,1.0237,0.1939,-2.5574,-0.6269,-0.409,0.0534},
       {1.4279,1.0206,0.192,-2.5571,-0.6165,-0.3964,0.0541},
       {1.4328,1.0176,0.19,-2.5568,-0.6053,-0.3845,0.0544},
       {1.4376,1.0147,0.1882,-2.5566,-0.5936,-0.3731,0.0543},
       {1.4425,1.0118,0.1863,-2.5563,-0.5814,-0.3622,0.0541},
       {1.4474,1.009,0.1846,-2.5561,-0.569,-0.3516,0.0537},
       {1.4522,1.0062,0.1829,-2.5558,-0.5563,-0.3413,0.0531},
       {1.4571,1.0035,0.1812,-2.5555,-0.5434,-0.3313,0.0525},
       {1.462,1.0008,0.1796,-2.5553,-0.5304,-0.3215,0.0517},
       {1.4668,0.9983,0.178,-2.555,-0.5172,-0.3118,0.0509},
       {1.4717,0.9957,0.1765,-2.5548,-0.5039,-0.3023,0.05},
       {1.4765,0.9933,0.175,-2.5545,-0.4905,-0.293,0.049},
       {1.4814,0.9909,0.1736,-2.5543,-0.4771,-0.2837,0.048},
       {1.4863,0.9886,0.1722,-2.5541,-0.4635,-0.2746,0.0469},
       {1.4911,0.9863,0.1709,-2.5538,-0.4499,-0.2656,0.0458},
       {1.496,0.9841,0.1696,-2.5536,-0.4363,-0.2566,0.0446},
       {1.5009,0.982,0.1684,-2.5534,-0.4226,-0.2477,0.0434},
       {1.5057,0.98,0.1672,-2.5532,-0.4088,-0.239,0.0422},
       {1.5106,0.978,0.166,-2.553,-0.395,-0.2302,0.041},
       {1.5154,0.9761,0.1649,-2.5528,-0.3812,-0.2215,0.0397},
       {1.5203,0.9742,0.1638,-2.5526,-0.3673,-0.2129,0.0384},
       {1.5252,0.9724,0.1628,-2.5524,-0.3534,-0.2044,0.0371},
       {1.53,0.9707,0.1618,-2.5522,-0.3394,-0.1958,0.0357},
       {1.5349,0.9691,0.1608,-2.552,-0.3255,-0.1874,0.0344},
       {1.5398,0.9675,0.1599,-2.5519,-0.3115,-0.1789,0.033},
       {1.5446,0.966,0.159,-2.5517,-0.2975,-0.1705,0.0316},
       {1.5495,0.9645,0.1582,-2.5516,-0.2835,-0.1622,0.0302},
       {1.5543,0.9631,0.1574,-2.5514,-0.2694,-0.1539,0.0288},
       {1.5592,0.9618,0.1567,-2.5513,-0.2554,-0.1456,0.0273},
       {1.5641,0.9606,0.156,-2.5511,-0.2413,-0.1373,0.0259},
       {1.5689,0.9594,0.1553,-2.551,-0.2272,-0.129,0.0244},
       {1.5738,0.9583,0.1547,-2.5509,-0.2131,-0.1208,0.023},
       {1.5787,0.9573,0.1541,-2.5508,-0.1989,-0.1126,0.0215},
       {1.5835,0.9563,0.1535,-2.5507,-0.1848,-0.1045,0.02},
       {1.5884,0.9554,0.153,-2.5506,-0.1706,-0.0963,0.0185},
       {1.5932,0.9546,0.1526,-2.5505,-0.1565,-0.0882,0.017},
       {1.5981,0.9538,0.1521,-2.5504,-0.1423,-0.0801,0.0155},
       {1.603,0.9531,0.1517,-2.5503,-0.1281,-0.072,0.014},
       {1.6078,0.9525,0.1514,-2.5503,-0.1139,-0.064,0.0125},
       {1.6127,0.9519,0.1511,-2.5502,-0.0997,-0.0559,0.0109},
       {1.6176,0.9515,0.1508,-2.5502,-0.0855,-0.0479,0.0094},
       {1.6224,0.951,0.1506,-2.5501,-0.0713,-0.0399,0.0078},
       {1.6273,0.9507,0.1504,-2.5501,-0.057,-0.0319,0.0063},
       {1.6321,0.9504,0.1502,-2.55,-0.0428,-0.0239,0.0047},
       {1.637,0.9502,0.1501,-2.55,-0.0285,-0.0159,0.0031},
       {1.6419,0.9501,0.15,-2.55,-0.0143,-0.0079,0.0016},
       {1.6467,0.95,0.15,-2.55,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}