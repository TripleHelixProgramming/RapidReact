package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class FourBallPartThree extends Path {
   private final static double[][] points = {
       {0,6.15,0.15,-2.1,-0.0,0.0,0.0},
       {0.0182,6.15,0.15,-2.1,-0.0414,0.0194,-0.001},
       {0.0363,6.1492,0.1504,-2.1,-0.0829,0.0388,-0.0021},
       {0.0545,6.1477,0.1511,-2.1001,-0.1245,0.058,-0.0031},
       {0.0727,6.1455,0.1521,-2.1001,-0.1661,0.0771,-0.0041},
       {0.0909,6.1425,0.1535,-2.1002,-0.2077,0.0962,-0.0051},
       {0.109,6.1387,0.1553,-2.1003,-0.2494,0.1151,-0.0061},
       {0.1272,6.1342,0.1574,-2.1004,-0.2911,0.1339,-0.0071},
       {0.1454,6.1289,0.1598,-2.1005,-0.3329,0.1527,-0.0081},
       {0.1635,6.1228,0.1626,-2.1007,-0.3747,0.1713,-0.0091},
       {0.1817,6.116,0.1657,-2.1008,-0.4165,0.1898,-0.01},
       {0.1999,6.1084,0.1691,-2.101,-0.4585,0.2082,-0.011},
       {0.218,6.1001,0.1729,-2.1012,-0.5004,0.2264,-0.0119},
       {0.2362,6.091,0.177,-2.1014,-0.5425,0.2445,-0.0128},
       {0.2544,6.0812,0.1815,-2.1017,-0.5846,0.2625,-0.0137},
       {0.2726,6.0705,0.1862,-2.1019,-0.6267,0.2804,-0.0146},
       {0.2907,6.0592,0.1913,-2.1022,-0.6689,0.2981,-0.0155},
       {0.3089,6.047,0.1967,-2.1025,-0.7112,0.3157,-0.0164},
       {0.3271,6.0341,0.2025,-2.1028,-0.7535,0.3331,-0.0173},
       {0.3452,6.0204,0.2085,-2.1031,-0.7959,0.3504,-0.0181},
       {0.3634,6.0059,0.2149,-2.1034,-0.8384,0.3675,-0.0189},
       {0.3816,5.9907,0.2216,-2.1037,-0.8809,0.3844,-0.0198},
       {0.3998,5.9747,0.2286,-2.1041,-0.9235,0.4012,-0.0206},
       {0.4179,5.9579,0.2359,-2.1045,-0.9661,0.4178,-0.0213},
       {0.4361,5.9403,0.2434,-2.1049,-1.0089,0.4342,-0.0221},
       {0.4543,5.922,0.2513,-2.1053,-1.0517,0.4504,-0.0229},
       {0.4724,5.9029,0.2595,-2.1057,-1.0946,0.4665,-0.0236},
       {0.4906,5.883,0.268,-2.1061,-1.1375,0.4823,-0.0243},
       {0.5088,5.8623,0.2768,-2.1066,-1.1806,0.4978,-0.025},
       {0.5269,5.8409,0.2858,-2.107,-1.2237,0.5132,-0.0257},
       {0.5451,5.8187,0.2951,-2.1075,-1.2669,0.5283,-0.0263},
       {0.5633,5.7956,0.3047,-2.108,-1.3102,0.5432,-0.027},
       {0.5815,5.7718,0.3146,-2.1084,-1.3535,0.5579,-0.0276},
       {0.5996,5.7472,0.3247,-2.1089,-1.397,0.5722,-0.0281},
       {0.6178,5.7219,0.3351,-2.1095,-1.4406,0.5863,-0.0287},
       {0.636,5.6957,0.3458,-2.11,-1.4842,0.6001,-0.0292},
       {0.6541,5.6687,0.3567,-2.1105,-1.528,0.6136,-0.0297},
       {0.6723,5.6409,0.3678,-2.111,-1.5718,0.6267,-0.0302},
       {0.6905,5.6124,0.3792,-2.1116,-1.6158,0.6395,-0.0307},
       {0.7087,5.583,0.3908,-2.1122,-1.6598,0.652,-0.0311},
       {0.7268,5.5529,0.4027,-2.1127,-1.704,0.6641,-0.0314},
       {0.745,5.5219,0.4148,-2.1133,-1.7482,0.6758,-0.0318},
       {0.7632,5.4901,0.427,-2.1139,-1.7926,0.687,-0.0321},
       {0.7813,5.4576,0.4395,-2.1145,-1.8371,0.6979,-0.0324},
       {0.7995,5.4242,0.4522,-2.115,-1.8816,0.7082,-0.0326},
       {0.8177,5.39,0.4651,-2.1156,-1.9263,0.7181,-0.0328},
       {0.8358,5.355,0.4781,-2.1162,-1.9711,0.7275,-0.0329},
       {0.854,5.3192,0.4913,-2.1168,-2.0161,0.7363,-0.033},
       {0.8722,5.2825,0.5047,-2.1174,-2.0611,0.7445,-0.033},
       {0.8904,5.2451,0.5182,-2.118,-2.1062,0.7521,-0.033},
       {0.9085,5.2068,0.5319,-2.1186,-2.1515,0.759,-0.0329},
       {0.9267,5.1677,0.5457,-2.1192,-2.1968,0.7652,-0.0327},
       {0.9449,5.1278,0.5596,-2.1198,-2.2423,0.7707,-0.0325},
       {0.963,5.0871,0.5736,-2.1204,-2.2878,0.7753,-0.0322},
       {0.9812,5.0455,0.5877,-2.121,-2.3334,0.7791,-0.0319},
       {0.9994,5.0031,0.6019,-2.1216,-2.3791,0.782,-0.0314},
       {1.0176,4.9599,0.6161,-2.1221,-2.4249,0.7838,-0.0309},
       {1.0357,4.9158,0.6303,-2.1227,-2.4706,0.7846,-0.0302},
       {1.0539,4.8709,0.6446,-2.1233,-2.5164,0.7842,-0.0295},
       {1.0721,4.8252,0.6588,-2.1238,-2.5622,0.7825,-0.0286},
       {1.0902,4.7786,0.673,-2.1243,-2.6078,0.7795,-0.0276},
       {1.1084,4.7312,0.6872,-2.1248,-2.6534,0.7751,-0.0265},
       {1.1266,4.683,0.7013,-2.1253,-2.6988,0.7691,-0.0252},
       {1.1447,4.634,0.7153,-2.1257,-2.7439,0.7614,-0.0237},
       {1.1629,4.5841,0.7291,-2.1262,-2.7887,0.7519,-0.0221},
       {1.1811,4.5335,0.7428,-2.1266,-2.833,0.7405,-0.0203},
       {1.1993,4.482,0.7562,-2.1269,-2.8767,0.7269,-0.0183},
       {1.2174,4.4297,0.7694,-2.1273,-2.9197,0.7112,-0.0161},
       {1.2356,4.3767,0.7823,-2.1276,-2.9618,0.6932,-0.0137},
       {1.2538,4.3228,0.7949,-2.1278,-3.0027,0.6727,-0.011},
       {1.2719,4.2683,0.8072,-2.128,-3.0423,0.6496,-0.0081},
       {1.2901,4.213,0.819,-2.1282,-3.0802,0.624,-0.0049},
       {1.3083,4.157,0.8303,-2.1283,-3.1162,0.5958,-0.0015},
       {1.3265,4.1004,0.8411,-2.1283,-3.1501,0.565,0.0022},
       {1.3446,4.0432,0.8514,-2.1282,-3.1815,0.5317,0.0061},
       {1.3628,3.9854,0.8611,-2.1281,-3.2101,0.496,0.0102},
       {1.381,3.927,0.8701,-2.128,-3.2359,0.4582,0.0145},
       {1.3991,3.8682,0.8784,-2.1277,-3.2587,0.4185,0.0189},
       {1.4173,3.809,0.886,-2.1273,-3.2782,0.3771,0.0234},
       {1.4355,3.7494,0.8928,-2.1269,-3.2946,0.3344,0.028},
       {1.4536,3.6896,0.8989,-2.1264,-3.3078,0.2905,0.0326},
       {1.4718,3.6295,0.9042,-2.1258,-3.3179,0.2459,0.0372},
       {1.49,3.5692,0.9087,-2.1251,-3.325,0.2007,0.0418},
       {1.5082,3.5088,0.9123,-2.1244,-3.3293,0.1551,0.0463},
       {1.5263,3.4483,0.9151,-2.1235,-3.3309,0.1094,0.0508},
       {1.5445,3.3878,0.9171,-2.1226,-3.3301,0.0636,0.0552},
       {1.5627,3.3272,0.9183,-2.1216,-3.327,0.018,0.0595},
       {1.5808,3.2668,0.9186,-2.1205,-3.3217,-0.0275,0.0638},
       {1.599,3.2064,0.9181,-2.1194,-3.3145,-0.0727,0.0679},
       {1.6172,3.1462,0.9168,-2.1181,-3.3055,-0.1176,0.072},
       {1.6354,3.0861,0.9146,-2.1168,-3.2948,-0.1621,0.076},
       {1.6535,3.0263,0.9117,-2.1155,-3.2827,-0.2062,0.0799},
       {1.6717,2.9666,0.908,-2.114,-3.2692,-0.2499,0.0837},
       {1.6899,2.9072,0.9034,-2.1125,-3.2544,-0.2933,0.0874},
       {1.708,2.8481,0.8981,-2.1109,-3.2384,-0.3362,0.0911},
       {1.7262,2.7892,0.892,-2.1092,-3.2214,-0.3786,0.0947},
       {1.7444,2.7307,0.8851,-2.1075,-3.2034,-0.4207,0.0982},
       {1.7625,2.6725,0.8774,-2.1057,-3.1845,-0.4624,0.1017},
       {1.7807,2.6146,0.869,-2.1039,-3.1647,-0.5037,0.1051},
       {1.7989,2.5571,0.8599,-2.102,-3.1442,-0.5446,0.1085},
       {1.8171,2.5,0.85,-2.1,-3.1229,-0.5851,0.1118},
       {1.8317,2.4544,0.8415,-2.0984,-3.105,-0.6172,0.1144},
       {1.8463,2.409,0.8324,-2.0967,-3.0861,-0.6488,0.1169},
       {1.8609,2.364,0.823,-2.095,-3.0663,-0.6798,0.1194},
       {1.8755,2.3192,0.813,-2.0932,-3.0456,-0.7102,0.1217},
       {1.8901,2.2747,0.8027,-2.0915,-3.024,-0.74,0.124},
       {1.9047,2.2306,0.7919,-2.0897,-3.0014,-0.769,0.1262},
       {1.9193,2.1867,0.7806,-2.0878,-2.9779,-0.7973,0.1283},
       {1.9339,2.1432,0.769,-2.0859,-2.9535,-0.8248,0.1304},
       {1.9485,2.1001,0.7569,-2.084,-2.9281,-0.8515,0.1323},
       {1.9631,2.0573,0.7445,-2.0821,-2.9018,-0.8772,0.1341},
       {1.9777,2.015,0.7317,-2.0801,-2.8746,-0.902,0.1359},
       {1.9923,1.973,0.7185,-2.0782,-2.8466,-0.9258,0.1375},
       {2.0069,1.9314,0.705,-2.0762,-2.8177,-0.9485,0.139},
       {2.0215,1.8903,0.6911,-2.0741,-2.7879,-0.9702,0.1404},
       {2.0361,1.8496,0.677,-2.0721,-2.7574,-0.9908,0.1417},
       {2.0507,1.8093,0.6625,-2.07,-2.7262,-1.0102,0.1429},
       {2.0653,1.7695,0.6478,-2.0679,-2.6942,-1.0284,0.144},
       {2.0799,1.7301,0.6327,-2.0658,-2.6616,-1.0454,0.145},
       {2.0945,1.6913,0.6175,-2.0637,-2.6284,-1.0612,0.1458},
       {2.1091,1.6529,0.602,-2.0616,-2.5946,-1.0758,0.1466},
       {2.1237,1.615,0.5863,-2.0594,-2.5603,-1.0891,0.1472},
       {2.1383,1.5776,0.5704,-2.0573,-2.5255,-1.1012,0.1477},
       {2.1529,1.5407,0.5543,-2.0551,-2.4904,-1.112,0.1481},
       {2.1676,1.5043,0.538,-2.053,-2.4549,-1.1216,0.1484},
       {2.1822,1.4685,0.5217,-2.0508,-2.419,-1.1299,0.1486},
       {2.1968,1.4332,0.5052,-2.0486,-2.3829,-1.1371,0.1487},
       {2.2114,1.3984,0.4886,-2.0464,-2.3466,-1.143,0.1486},
       {2.226,1.3641,0.4719,-2.0443,-2.3102,-1.1478,0.1485},
       {2.2406,1.3304,0.4551,-2.0421,-2.2736,-1.1515,0.1482},
       {2.2552,1.2972,0.4383,-2.0399,-2.2369,-1.154,0.1479},
       {2.2698,1.2645,0.4214,-2.0378,-2.2001,-1.1555,0.1475},
       {2.2844,1.2324,0.4046,-2.0356,-2.1633,-1.1559,0.1469},
       {2.299,1.2008,0.3877,-2.0335,-2.1265,-1.1552,0.1463},
       {2.3136,1.1697,0.3708,-2.0313,-2.0898,-1.1537,0.1456},
       {2.3282,1.1392,0.354,-2.0292,-2.0531,-1.1511,0.1448},
       {2.3428,1.1092,0.3371,-2.0271,-2.0164,-1.1477,0.1439},
       {2.3574,1.0798,0.3204,-2.025,-1.9799,-1.1433,0.1429},
       {2.372,1.0509,0.3037,-2.0229,-1.9435,-1.1381,0.1419},
       {2.3866,1.0225,0.2871,-2.0208,-1.9072,-1.1321,0.1408},
       {2.4012,0.9946,0.2705,-2.0188,-1.871,-1.1254,0.1396},
       {2.4158,0.9673,0.2541,-2.0168,-1.835,-1.1178,0.1383},
       {2.4304,0.9405,0.2378,-2.0147,-1.7992,-1.1096,0.137},
       {2.445,0.9142,0.2216,-2.0127,-1.7635,-1.1006,0.1356},
       {2.4596,0.8885,0.2055,-2.0108,-1.728,-1.091,0.1341},
       {2.4742,0.8632,0.1896,-2.0088,-1.6926,-1.0808,0.1326},
       {2.4888,0.8385,0.1738,-2.0069,-1.6575,-1.0699,0.1311},
       {2.5034,0.8143,0.1582,-2.0049,-1.6225,-1.0585,0.1294},
       {2.518,0.7906,0.1427,-2.0031,-1.5877,-1.0465,0.1277},
       {2.5326,0.7674,0.1274,-2.0012,-1.5531,-1.034,0.126},
       {2.5472,0.7447,0.1123,-1.9993,-1.5187,-1.0209,0.1242},
       {2.5619,0.7226,0.0974,-1.9975,-1.4845,-1.0074,0.1224},
       {2.5765,0.7009,0.0827,-1.9957,-1.4505,-0.9934,0.1205},
       {2.5911,0.6797,0.0682,-1.994,-1.4167,-0.9789,0.1186},
       {2.6057,0.659,0.0539,-1.9922,-1.3831,-0.964,0.1167},
       {2.6203,0.6388,0.0398,-1.9905,-1.3496,-0.9487,0.1147},
       {2.6349,0.6191,0.026,-1.9889,-1.3163,-0.933,0.1126},
       {2.6495,0.5999,0.0123,-1.9872,-1.2833,-0.9169,0.1106},
       {2.6641,0.5811,-0.0011,-1.9856,-1.2504,-0.9004,0.1085},
       {2.6787,0.5629,-0.0142,-1.984,-1.2177,-0.8836,0.1063},
       {2.6933,0.5451,-0.0271,-1.9825,-1.1851,-0.8665,0.1041},
       {2.7079,0.5278,-0.0398,-1.981,-1.1528,-0.849,0.1019},
       {2.7225,0.511,-0.0522,-1.9795,-1.1206,-0.8311,0.0997},
       {2.7371,0.4946,-0.0643,-1.978,-1.0886,-0.813,0.0974},
       {2.7517,0.4787,-0.0762,-1.9766,-1.0567,-0.7946,0.0952},
       {2.7663,0.4633,-0.0878,-1.9752,-1.025,-0.7759,0.0928},
       {2.7809,0.4483,-0.0991,-1.9738,-0.9935,-0.757,0.0905},
       {2.7955,0.4338,-0.1102,-1.9725,-0.9621,-0.7377,0.0881},
       {2.8101,0.4197,-0.1209,-1.9712,-0.9309,-0.7183,0.0857},
       {2.8247,0.4061,-0.1314,-1.97,-0.8999,-0.6985,0.0833},
       {2.8393,0.393,-0.1416,-1.9688,-0.869,-0.6786,0.0809},
       {2.8539,0.3803,-0.1515,-1.9676,-0.8382,-0.6584,0.0784},
       {2.8685,0.3681,-0.1611,-1.9664,-0.8076,-0.638,0.0759},
       {2.8831,0.3563,-0.1705,-1.9653,-0.7772,-0.6174,0.0734},
       {2.8977,0.3449,-0.1795,-1.9643,-0.7468,-0.5965,0.0709},
       {2.9123,0.334,-0.1882,-1.9632,-0.7166,-0.5755,0.0684},
       {2.9269,0.3236,-0.1966,-1.9622,-0.6866,-0.5543,0.0658},
       {2.9415,0.3135,-0.2047,-1.9613,-0.6567,-0.5329,0.0632},
       {2.9562,0.3039,-0.2125,-1.9603,-0.6269,-0.5113,0.0606},
       {2.9708,0.2948,-0.2199,-1.9595,-0.5972,-0.4895,0.058},
       {2.9854,0.2861,-0.2271,-1.9586,-0.5677,-0.4676,0.0554},
       {3.0,0.2778,-0.2339,-1.9578,-0.5383,-0.4455,0.0527},
       {3.0146,0.2699,-0.2404,-1.957,-0.509,-0.4233,0.0501},
       {3.0292,0.2625,-0.2466,-1.9563,-0.4798,-0.4008,0.0474},
       {3.0438,0.2555,-0.2525,-1.9556,-0.4508,-0.3783,0.0447},
       {3.0584,0.2489,-0.258,-1.955,-0.4218,-0.3556,0.042},
       {3.073,0.2427,-0.2632,-1.9543,-0.393,-0.3327,0.0393},
       {3.0876,0.237,-0.268,-1.9538,-0.3643,-0.3097,0.0366},
       {3.1022,0.2317,-0.2726,-1.9532,-0.3357,-0.2866,0.0338},
       {3.1168,0.2268,-0.2767,-1.9527,-0.3072,-0.2634,0.0311},
       {3.1314,0.2223,-0.2806,-1.9523,-0.2787,-0.24,0.0283},
       {3.146,0.2182,-0.2841,-1.9519,-0.2504,-0.2165,0.0255},
       {3.1606,0.2145,-0.2873,-1.9515,-0.2222,-0.1929,0.0227},
       {3.1752,0.2113,-0.2901,-1.9512,-0.1941,-0.1692,0.0199},
       {3.1898,0.2085,-0.2925,-1.9509,-0.1661,-0.1453,0.0171},
       {3.2044,0.206,-0.2947,-1.9506,-0.1382,-0.1213,0.0143},
       {3.219,0.204,-0.2964,-1.9504,-0.1104,-0.0973,0.0114},
       {3.2336,0.2024,-0.2979,-1.9503,-0.0827,-0.0731,0.0086},
       {3.2482,0.2012,-0.2989,-1.9501,-0.055,-0.0488,0.0057},
       {3.2628,0.2004,-0.2996,-1.95,-0.0275,-0.0245,0.0029},
       {3.2774,0.2,-0.3,-1.95,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
