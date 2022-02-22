package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class FourBallPartTwo extends Path {
   private final static double[][] points = {
       {0,0.0,0.0,-1.95,-0.0,0.0,0.0},
       {0.0155,-0.0,0.0,-1.95,0.0292,0.0257,0.001},
       {0.0309,0.0005,0.0004,-1.95,0.0586,0.0512,0.002},
       {0.0464,0.0014,0.0012,-1.95,0.0881,0.0766,0.003},
       {0.0618,0.0027,0.0024,-1.9499,0.1177,0.1019,0.004},
       {0.0773,0.0045,0.0039,-1.9498,0.1475,0.127,0.005},
       {0.0927,0.0068,0.0059,-1.9498,0.1774,0.1519,0.0059},
       {0.1082,0.0096,0.0083,-1.9497,0.2074,0.1767,0.0069},
       {0.1236,0.0128,0.011,-1.9496,0.2376,0.2013,0.0078},
       {0.1391,0.0164,0.0141,-1.9494,0.2679,0.2257,0.0087},
       {0.1545,0.0206,0.0176,-1.9493,0.2983,0.25,0.0096},
       {0.17,0.0252,0.0214,-1.9492,0.3289,0.274,0.0105},
       {0.1854,0.0303,0.0257,-1.949,0.3597,0.2979,0.0113},
       {0.2009,0.0358,0.0303,-1.9488,0.3906,0.3216,0.0122},
       {0.2163,0.0419,0.0353,-1.9486,0.4216,0.345,0.013},
       {0.2318,0.0484,0.0406,-1.9484,0.4529,0.3683,0.0138},
       {0.2472,0.0554,0.0463,-1.9482,0.4843,0.3913,0.0146},
       {0.2627,0.0629,0.0523,-1.948,0.5158,0.4141,0.0154},
       {0.2781,0.0708,0.0587,-1.9478,0.5476,0.4366,0.0161},
       {0.2936,0.0793,0.0655,-1.9475,0.5795,0.4589,0.0168},
       {0.309,0.0882,0.0726,-1.9473,0.6116,0.4809,0.0175},
       {0.3245,0.0977,0.08,-1.947,0.6439,0.5027,0.0182},
       {0.3399,0.1076,0.0878,-1.9467,0.6763,0.5241,0.0188},
       {0.3554,0.1181,0.0959,-1.9464,0.709,0.5453,0.0195},
       {0.3708,0.129,0.1043,-1.9461,0.7418,0.5662,0.0201},
       {0.3863,0.1405,0.113,-1.9458,0.7749,0.5867,0.0206},
       {0.4017,0.1525,0.1221,-1.9455,0.8082,0.607,0.0212},
       {0.4172,0.165,0.1315,-1.9452,0.8416,0.6268,0.0217},
       {0.4327,0.178,0.1412,-1.9448,0.8753,0.6464,0.0222},
       {0.4481,0.1915,0.1511,-1.9445,0.9092,0.6655,0.0226},
       {0.4636,0.2055,0.1614,-1.9441,0.9433,0.6842,0.0231},
       {0.479,0.2201,0.172,-1.9438,0.9777,0.7025,0.0235},
       {0.4945,0.2352,0.1829,-1.9434,1.0123,0.7204,0.0238},
       {0.5099,0.2509,0.194,-1.943,1.0471,0.7379,0.0241},
       {0.5254,0.2671,0.2054,-1.9427,1.0821,0.7548,0.0244},
       {0.5408,0.2838,0.2171,-1.9423,1.1174,0.7713,0.0247},
       {0.5563,0.301,0.229,-1.9419,1.1529,0.7873,0.0249},
       {0.5717,0.3189,0.2411,-1.9415,1.1886,0.8027,0.0251},
       {0.5872,0.3372,0.2535,-1.9411,1.2246,0.8175,0.0252},
       {0.6026,0.3561,0.2662,-1.9407,1.2608,0.8318,0.0253},
       {0.6181,0.3756,0.279,-1.9404,1.2973,0.8454,0.0253},
       {0.6335,0.3957,0.2921,-1.94,1.334,0.8584,0.0253},
       {0.649,0.4163,0.3054,-1.9396,1.3709,0.8706,0.0252},
       {0.6644,0.4375,0.3188,-1.9392,1.4081,0.8822,0.0251},
       {0.6799,0.4592,0.3324,-1.9388,1.4455,0.893,0.025},
       {0.6953,0.4816,0.3462,-1.9384,1.4831,0.903,0.0247},
       {0.7108,0.5045,0.3602,-1.938,1.521,0.9121,0.0245},
       {0.7262,0.528,0.3743,-1.9377,1.559,0.9204,0.0241},
       {0.7417,0.5521,0.3885,-1.9373,1.5972,0.9278,0.0237},
       {0.7571,0.5767,0.4028,-1.9369,1.6356,0.9342,0.0233},
       {0.7726,0.602,0.4173,-1.9366,1.6742,0.9396,0.0228},
       {0.788,0.6279,0.4318,-1.9362,1.7128,0.944,0.0222},
       {0.8035,0.6544,0.4464,-1.9359,1.7516,0.9472,0.0216},
       {0.819,0.6814,0.461,-1.9355,1.7905,0.9494,0.0208},
       {0.8344,0.7091,0.4757,-1.9352,1.8294,0.9503,0.0201},
       {0.8499,0.7374,0.4904,-1.9349,1.8683,0.95,0.0192},
       {0.8653,0.7662,0.5051,-1.9346,1.9072,0.9485,0.0183},
       {0.8808,0.7957,0.5197,-1.9343,1.9461,0.9456,0.0173},
       {0.8962,0.8258,0.5343,-1.934,1.9847,0.9413,0.0162},
       {0.9117,0.8564,0.5489,-1.9338,2.0233,0.9357,0.015},
       {0.9271,0.8877,0.5633,-1.9336,2.0615,0.9287,0.0138},
       {0.9426,0.9196,0.5777,-1.9333,2.0995,0.9202,0.0125},
       {0.958,0.952,0.5919,-1.9332,2.1372,0.9102,0.0111},
       {0.9735,0.985,0.606,-1.933,2.1744,0.8988,0.0097},
       {0.9889,1.0186,0.6198,-1.9328,2.2111,0.8858,0.0081},
       {1.0044,1.0528,0.6335,-1.9327,2.2472,0.8714,0.0065},
       {1.0198,1.0875,0.647,-1.9326,2.2828,0.8555,0.0048},
       {1.0353,1.1228,0.6602,-1.9325,2.3176,0.8381,0.0031},
       {1.0507,1.1586,0.6732,-1.9325,2.3517,0.8193,0.0013},
       {1.0662,1.1949,0.6858,-1.9325,2.3849,0.7991,-0.0006},
       {1.0816,1.2318,0.6982,-1.9325,2.4173,0.7775,-0.0026},
       {1.0971,1.2691,0.7102,-1.9325,2.4488,0.7546,-0.0046},
       {1.1125,1.307,0.7219,-1.9326,2.4793,0.7305,-0.0067},
       {1.128,1.3453,0.7331,-1.9327,2.5088,0.7051,-0.0089},
       {1.1434,1.384,0.744,-1.9328,2.5373,0.6785,-0.0111},
       {1.1589,1.4233,0.7545,-1.933,2.5647,0.6509,-0.0134},
       {1.1743,1.4629,0.7646,-1.9332,2.5911,0.6223,-0.0157},
       {1.1898,1.5029,0.7742,-1.9335,2.6163,0.5926,-0.0181},
       {1.2052,1.5433,0.7833,-1.9337,2.6405,0.5621,-0.0205},
       {1.2207,1.5841,0.792,-1.934,2.6636,0.5308,-0.0229},
       {1.2362,1.6253,0.8002,-1.9344,2.6856,0.4987,-0.0254},
       {1.2516,1.6668,0.8079,-1.9348,2.7066,0.4659,-0.028},
       {1.2671,1.7086,0.8151,-1.9352,2.7265,0.4324,-0.0305},
       {1.2825,1.7508,0.8218,-1.9357,2.7453,0.3984,-0.0331},
       {1.298,1.7932,0.828,-1.9362,2.7632,0.3638,-0.0357},
       {1.3134,1.8359,0.8336,-1.9368,2.78,0.3287,-0.0383},
       {1.3289,1.8788,0.8387,-1.9374,2.7959,0.2932,-0.041},
       {1.3443,1.922,0.8432,-1.938,2.8109,0.2573,-0.0436},
       {1.3598,1.9655,0.8472,-1.9387,2.8249,0.221,-0.0463},
       {1.3752,2.0091,0.8506,-1.9394,2.8381,0.1843,-0.049},
       {1.3907,2.053,0.8534,-1.9401,2.8504,0.1474,-0.0517},
       {1.4061,2.097,0.8557,-1.9409,2.8619,0.1102,-0.0544},
       {1.4216,2.1412,0.8574,-1.9418,2.8725,0.0728,-0.0571},
       {1.437,2.1856,0.8586,-1.9427,2.8825,0.0351,-0.0598},
       {1.4525,2.2302,0.8591,-1.9436,2.8916,-0.0027,-0.0625},
       {1.4679,2.2748,0.8591,-1.9445,2.9001,-0.0407,-0.0652},
       {1.4834,2.3197,0.8584,-1.9456,2.9079,-0.0788,-0.0679},
       {1.4988,2.3646,0.8572,-1.9466,2.915,-0.1171,-0.0706},
       {1.5143,2.4096,0.8554,-1.9477,2.9215,-0.1555,-0.0733},
       {1.5297,2.4548,0.853,-1.9488,2.9273,-0.1939,-0.0761},
       {1.5452,2.5,0.85,-1.95,2.9326,-0.2325,-0.0788},
       {1.5585,2.5389,0.8469,-1.951,2.937,-0.2657,-0.0811},
       {1.5718,2.578,0.8434,-1.9521,2.9412,-0.2989,-0.0834},
       {1.585,2.617,0.8394,-1.9532,2.9453,-0.3321,-0.0857},
       {1.5983,2.6561,0.835,-1.9544,2.9493,-0.3653,-0.0881},
       {1.6116,2.6953,0.8302,-1.9555,2.953,-0.3985,-0.0904},
       {1.6249,2.7345,0.8249,-1.9567,2.9566,-0.4318,-0.0927},
       {1.6382,2.7738,0.8191,-1.958,2.96,-0.4651,-0.095},
       {1.6514,2.8131,0.8129,-1.9592,2.9631,-0.4984,-0.0973},
       {1.6647,2.8525,0.8063,-1.9605,2.9661,-0.5317,-0.0996},
       {1.678,2.8919,0.7993,-1.9618,2.9688,-0.565,-0.1019},
       {1.6913,2.9313,0.7918,-1.9632,2.9713,-0.5984,-0.1043},
       {1.7046,2.9707,0.7838,-1.9646,2.9736,-0.6318,-0.1066},
       {1.7178,3.0102,0.7754,-1.966,2.9755,-0.6652,-0.1089},
       {1.7311,3.0498,0.7666,-1.9674,2.9772,-0.6986,-0.1112},
       {1.7444,3.0893,0.7573,-1.9689,2.9785,-0.732,-0.1134},
       {1.7577,3.1289,0.7476,-1.9704,2.9795,-0.7655,-0.1157},
       {1.771,3.1684,0.7374,-1.972,2.9801,-0.7989,-0.118},
       {1.7843,3.208,0.7268,-1.9735,2.9803,-0.8324,-0.1203},
       {1.7975,3.2476,0.7158,-1.9751,2.9801,-0.8658,-0.1226},
       {1.8108,3.2872,0.7043,-1.9768,2.9794,-0.8993,-0.1248},
       {1.8241,3.3267,0.6923,-1.9784,2.9782,-0.9327,-0.1271},
       {1.8374,3.3663,0.6799,-1.9801,2.9764,-0.9661,-0.1293},
       {1.8507,3.4058,0.6671,-1.9818,2.9741,-0.9995,-0.1315},
       {1.8639,3.4453,0.6538,-1.9836,2.971,-1.0328,-0.1337},
       {1.8772,3.4848,0.6401,-1.9853,2.9672,-1.066,-0.1359},
       {1.8905,3.5242,0.6259,-1.9872,2.9625,-1.0992,-0.1381},
       {1.9038,3.5635,0.6114,-1.989,2.9569,-1.1321,-0.1402},
       {1.9171,3.6028,0.5963,-1.9908,2.9503,-1.1649,-0.1423},
       {1.9303,3.642,0.5808,-1.9927,2.9425,-1.1975,-0.1444},
       {1.9436,3.6811,0.5649,-1.9947,2.9334,-1.2297,-0.1464},
       {1.9569,3.72,0.5486,-1.9966,2.9228,-1.2614,-0.1483},
       {1.9702,3.7588,0.5319,-1.9986,2.9106,-1.2925,-0.1502},
       {1.9835,3.7975,0.5147,-2.0006,2.8965,-1.3229,-0.152},
       {1.9968,3.836,0.4971,-2.0026,2.8803,-1.3521,-0.1536},
       {2.01,3.8742,0.4792,-2.0046,2.8617,-1.38,-0.1551},
       {2.0233,3.9122,0.4608,-2.0067,2.8407,-1.4061,-0.1564},
       {2.0366,3.95,0.4422,-2.0088,2.8171,-1.4297,-0.1574},
       {2.0499,3.9874,0.4232,-2.0109,2.7908,-1.4504,-0.1582},
       {2.0632,4.0244,0.4039,-2.013,2.762,-1.4675,-0.1587},
       {2.0764,4.0611,0.3844,-2.0151,2.7312,-1.4805,-0.1588},
       {2.0897,4.0974,0.3648,-2.0172,2.6988,-1.4889,-0.1586},
       {2.103,4.1332,0.345,-2.0193,2.6656,-1.4928,-0.1581},
       {2.1163,4.1686,0.3252,-2.0214,2.6321,-1.4922,-0.1574},
       {2.1296,4.2036,0.3053,-2.0235,2.599,-1.4875,-0.1564},
       {2.1428,4.2381,0.2856,-2.0255,2.5666,-1.4792,-0.1552},
       {2.1561,4.2722,0.2659,-2.0276,2.5351,-1.4678,-0.1539},
       {2.1694,4.3059,0.2464,-2.0296,2.5047,-1.4539,-0.1525},
       {2.1827,4.3391,0.2271,-2.0317,2.4754,-1.4377,-0.1509},
       {2.196,4.372,0.208,-2.0337,2.4472,-1.4197,-0.1493},
       {2.2093,4.4045,0.1892,-2.0357,2.42,-1.4003,-0.1475},
       {2.2225,4.4367,0.1706,-2.0376,2.3938,-1.3795,-0.1458},
       {2.2358,4.4684,0.1523,-2.0396,2.3684,-1.3576,-0.1439},
       {2.2491,4.4999,0.1342,-2.0415,2.3439,-1.3349,-0.1421},
       {2.2624,4.531,0.1165,-2.0434,2.3202,-1.3113,-0.1401},
       {2.2757,4.5618,0.0991,-2.0452,2.2971,-1.2871,-0.1382},
       {2.2889,4.5924,0.082,-2.047,2.2747,-1.2622,-0.1362},
       {2.3022,4.6226,0.0652,-2.0489,2.2528,-1.2369,-0.1342},
       {2.3155,4.6525,0.0488,-2.0506,2.2316,-1.2111,-0.1322},
       {2.3288,4.6821,0.0327,-2.0524,2.2107,-1.1849,-0.1302},
       {2.3421,4.7115,0.017,-2.0541,2.1904,-1.1583,-0.1281},
       {2.3553,4.7406,0.0016,-2.0558,2.1705,-1.1315,-0.1261},
       {2.3686,4.7694,-0.0134,-2.0575,2.1509,-1.1043,-0.124},
       {2.3819,4.798,-0.0281,-2.0591,2.1318,-1.0769,-0.1219},
       {2.3952,4.8263,-0.0424,-2.0608,2.1129,-1.0492,-0.1198},
       {2.4085,4.8543,-0.0563,-2.0624,2.0944,-1.0214,-0.1177},
       {2.4217,4.8822,-0.0699,-2.0639,2.0762,-0.9933,-0.1156},
       {2.435,4.9097,-0.0831,-2.0655,2.0583,-0.9651,-0.1135},
       {2.4483,4.9371,-0.0959,-2.067,2.0406,-0.9367,-0.1114},
       {2.4616,4.9642,-0.1084,-2.0684,2.0231,-0.9081,-0.1092},
       {2.4749,4.991,-0.1204,-2.0699,2.0059,-0.8795,-0.1071},
       {2.4882,5.0177,-0.1321,-2.0713,1.9889,-0.8506,-0.1049},
       {2.5014,5.0441,-0.1434,-2.0727,1.9721,-0.8217,-0.1028},
       {2.5147,5.0703,-0.1543,-2.0741,1.9555,-0.7927,-0.1006},
       {2.528,5.0963,-0.1648,-2.0754,1.939,-0.7635,-0.0985},
       {2.5413,5.122,-0.175,-2.0767,1.9228,-0.7343,-0.0963},
       {2.5546,5.1475,-0.1847,-2.078,1.9067,-0.705,-0.0941},
       {2.5678,5.1729,-0.1941,-2.0793,1.8907,-0.6756,-0.092},
       {2.5811,5.198,-0.2031,-2.0805,1.8749,-0.6461,-0.0898},
       {2.5944,5.2229,-0.2116,-2.0817,1.8593,-0.6165,-0.0876},
       {2.6077,5.2476,-0.2198,-2.0828,1.8437,-0.5869,-0.0855},
       {2.621,5.2721,-0.2276,-2.084,1.8283,-0.5572,-0.0833},
       {2.6342,5.2963,-0.235,-2.0851,1.813,-0.5274,-0.0811},
       {2.6475,5.3204,-0.242,-2.0861,1.7979,-0.4976,-0.0789},
       {2.6608,5.3443,-0.2486,-2.0872,1.7828,-0.4677,-0.0767},
       {2.6741,5.368,-0.2548,-2.0882,1.7679,-0.4378,-0.0745},
       {2.6874,5.3915,-0.2607,-2.0892,1.753,-0.4078,-0.0723},
       {2.7007,5.4147,-0.2661,-2.0902,1.7383,-0.3778,-0.0702},
       {2.7139,5.4378,-0.2711,-2.0911,1.7236,-0.3477,-0.068},
       {2.7272,5.4607,-0.2757,-2.092,1.7091,-0.3176,-0.0658},
       {2.7405,5.4834,-0.2799,-2.0929,1.6946,-0.2874,-0.0636},
       {2.7538,5.5059,-0.2837,-2.0937,1.6802,-0.2572,-0.0614},
       {2.7671,5.5282,-0.2872,-2.0945,1.6659,-0.227,-0.0592},
       {2.7803,5.5504,-0.2902,-2.0953,1.6516,-0.1967,-0.057},
       {2.7936,5.5723,-0.2928,-2.0961,1.6375,-0.1664,-0.0548},
       {2.8069,5.5941,-0.295,-2.0968,1.6234,-0.1361,-0.0526},
       {2.8202,5.6156,-0.2968,-2.0975,1.6094,-0.1057,-0.0504},
       {2.8335,5.637,-0.2982,-2.0982,1.5954,-0.0753,-0.0482},
       {2.8467,5.6582,-0.2992,-2.0988,1.5815,-0.0448,-0.046},
       {2.86,5.6792,-0.2998,-2.0994,1.5677,-0.0144,-0.0437},
       {2.8733,5.7,-0.3,-2.1,1.5539,0.0161,-0.0415},
       {2.8814,5.7126,-0.2999,-2.1003,1.5454,0.0346,-0.0402},
       {2.8895,5.7251,-0.2996,-2.1007,1.5366,0.053,-0.0388},
       {2.8976,5.7375,-0.2992,-2.101,1.5276,0.0713,-0.0375},
       {2.9057,5.7499,-0.2986,-2.1013,1.5183,0.0895,-0.0362},
       {2.9138,5.7622,-0.2979,-2.1016,1.5089,0.1076,-0.0348},
       {2.9219,5.7744,-0.297,-2.1019,1.4992,0.1255,-0.0335},
       {2.93,5.7866,-0.296,-2.1021,1.4893,0.1434,-0.0322},
       {2.9381,5.7986,-0.2948,-2.1024,1.4791,0.161,-0.0309},
       {2.9462,5.8106,-0.2935,-2.1026,1.4687,0.1786,-0.0295},
       {2.9543,5.8225,-0.2921,-2.1029,1.4581,0.196,-0.0282},
       {2.9624,5.8343,-0.2905,-2.1031,1.4472,0.2132,-0.0269},
       {2.9705,5.846,-0.2887,-2.1033,1.436,0.2303,-0.0256},
       {2.9786,5.8576,-0.2869,-2.1035,1.4245,0.2471,-0.0243},
       {2.9867,5.8692,-0.2849,-2.1037,1.4128,0.2638,-0.023},
       {2.9948,5.8806,-0.2827,-2.1039,1.4007,0.2803,-0.0217},
       {3.0029,5.8919,-0.2805,-2.1041,1.3884,0.2965,-0.0204},
       {3.011,5.9032,-0.2781,-2.1043,1.3758,0.3125,-0.0192},
       {3.019,5.9143,-0.2755,-2.1044,1.3629,0.3283,-0.0179},
       {3.0271,5.9254,-0.2729,-2.1046,1.3496,0.3438,-0.0166},
       {3.0352,5.9363,-0.2701,-2.1047,1.336,0.359,-0.0154},
       {3.0433,5.9471,-0.2672,-2.1048,1.3221,0.374,-0.0142},
       {3.0514,5.9578,-0.2642,-2.1049,1.3079,0.3886,-0.0129},
       {3.0595,5.9684,-0.261,-2.105,1.2934,0.4029,-0.0117},
       {3.0676,5.9789,-0.2578,-2.1051,1.2785,0.4168,-0.0105},
       {3.0757,5.9892,-0.2544,-2.1052,1.2633,0.4304,-0.0093},
       {3.0838,5.9994,-0.2509,-2.1053,1.2477,0.4436,-0.0082},
       {3.0919,6.0095,-0.2473,-2.1054,1.2318,0.4564,-0.007},
       {3.1,6.0195,-0.2436,-2.1054,1.2156,0.4687,-0.0059},
       {3.1081,6.0294,-0.2398,-2.1055,1.199,0.4806,-0.0047},
       {3.1162,6.0391,-0.2359,-2.1055,1.1821,0.492,-0.0036},
       {3.1243,6.0486,-0.2319,-2.1055,1.1648,0.5029,-0.0025},
       {3.1324,6.0581,-0.2279,-2.1055,1.1473,0.5133,-0.0015},
       {3.1405,6.0674,-0.2237,-2.1056,1.1294,0.5231,-0.0004},
       {3.1486,6.0765,-0.2195,-2.1056,1.1112,0.5323,0.0006},
       {3.1567,6.0855,-0.2152,-2.1056,1.0928,0.541,0.0016},
       {3.1648,6.0944,-0.2108,-2.1055,1.074,0.5491,0.0026},
       {3.1729,6.103,-0.2063,-2.1055,1.055,0.5565,0.0035},
       {3.181,6.1116,-0.2018,-2.1055,1.0358,0.5633,0.0044},
       {3.1891,6.12,-0.1973,-2.1055,1.0163,0.5694,0.0053},
       {3.1972,6.1282,-0.1927,-2.1054,0.9967,0.5748,0.0062},
       {3.2053,6.1363,-0.188,-2.1054,0.9769,0.5796,0.007},
       {3.2134,6.1442,-0.1833,-2.1053,0.9569,0.5836,0.0078},
       {3.2215,6.1519,-0.1786,-2.1052,0.9367,0.587,0.0086},
       {3.2296,6.1595,-0.1738,-2.1052,0.9165,0.5896,0.0093},
       {3.2377,6.1669,-0.1691,-2.1051,0.8962,0.5915,0.01},
       {3.2458,6.1742,-0.1643,-2.105,0.8758,0.5927,0.0106},
       {3.2539,6.1813,-0.1595,-2.1049,0.8555,0.5932,0.0113},
       {3.2619,6.1882,-0.1547,-2.1048,0.8351,0.593,0.0118},
       {3.27,6.195,-0.1499,-2.1047,0.8147,0.5921,0.0124},
       {3.2781,6.2016,-0.1451,-2.1046,0.7943,0.5905,0.0129},
       {3.2862,6.208,-0.1403,-2.1045,0.7741,0.5882,0.0134},
       {3.2943,6.2143,-0.1355,-2.1044,0.7539,0.5853,0.0138},
       {3.3024,6.2204,-0.1308,-2.1043,0.7338,0.5817,0.0142},
       {3.3105,6.2263,-0.1261,-2.1042,0.7138,0.5775,0.0145},
       {3.3186,6.2321,-0.1214,-2.1041,0.694,0.5727,0.0148},
       {3.3267,6.2377,-0.1168,-2.104,0.6744,0.5673,0.0151},
       {3.3348,6.2432,-0.1122,-2.1039,0.6548,0.5614,0.0153},
       {3.3429,6.2485,-0.1076,-2.1037,0.6355,0.5549,0.0155},
       {3.351,6.2536,-0.1031,-2.1036,0.6164,0.5478,0.0157},
       {3.3591,6.2586,-0.0987,-2.1035,0.5974,0.5403,0.0158},
       {3.3672,6.2634,-0.0943,-2.1033,0.5787,0.5323,0.0159},
       {3.3753,6.2681,-0.09,-2.1032,0.5601,0.5238,0.0159},
       {3.3834,6.2727,-0.0858,-2.1031,0.5418,0.5149,0.0159},
       {3.3915,6.2771,-0.0816,-2.103,0.5236,0.5055,0.0159},
       {3.3996,6.2813,-0.0775,-2.1028,0.5057,0.4957,0.0159},
       {3.4077,6.2854,-0.0735,-2.1027,0.488,0.4856,0.0158},
       {3.4158,6.2893,-0.0696,-2.1026,0.4706,0.4751,0.0157},
       {3.4239,6.2931,-0.0657,-2.1024,0.4533,0.4642,0.0155},
       {3.432,6.2968,-0.062,-2.1023,0.4363,0.453,0.0154},
       {3.4401,6.3004,-0.0583,-2.1022,0.4195,0.4414,0.0152},
       {3.4482,6.3037,-0.0547,-2.1021,0.4029,0.4296,0.0149},
       {3.4563,6.307,-0.0513,-2.102,0.3865,0.4175,0.0147},
       {3.4644,6.3101,-0.0479,-2.1018,0.3703,0.405,0.0144},
       {3.4725,6.3131,-0.0446,-2.1017,0.3543,0.3924,0.0141},
       {3.4806,6.316,-0.0414,-2.1016,0.3385,0.3794,0.0138},
       {3.4887,6.3187,-0.0383,-2.1015,0.323,0.3663,0.0134},
       {3.4968,6.3214,-0.0354,-2.1014,0.3076,0.3529,0.0131},
       {3.5048,6.3239,-0.0325,-2.1013,0.2924,0.3392,0.0127},
       {3.5129,6.3262,-0.0298,-2.1012,0.2774,0.3254,0.0122},
       {3.521,6.3285,-0.0271,-2.1011,0.2626,0.3114,0.0118},
       {3.5291,6.3306,-0.0246,-2.101,0.248,0.2972,0.0114},
       {3.5372,6.3326,-0.0222,-2.1009,0.2335,0.2828,0.0109},
       {3.5453,6.3345,-0.0199,-2.1008,0.2193,0.2682,0.0104},
       {3.5534,6.3363,-0.0178,-2.1007,0.2052,0.2534,0.0099},
       {3.5615,6.3379,-0.0157,-2.1006,0.1912,0.2385,0.0094},
       {3.5696,6.3395,-0.0138,-2.1006,0.1775,0.2235,0.0089},
       {3.5777,6.3409,-0.012,-2.1005,0.1639,0.2083,0.0083},
       {3.5858,6.3422,-0.0103,-2.1004,0.1504,0.193,0.0077},
       {3.5939,6.3435,-0.0087,-2.1004,0.1371,0.1775,0.0072},
       {3.602,6.3446,-0.0073,-2.1003,0.124,0.1619,0.0066},
       {3.6101,6.3456,-0.006,-2.1002,0.111,0.1462,0.006},
       {3.6182,6.3465,-0.0048,-2.1002,0.0981,0.1304,0.0053},
       {3.6263,6.3473,-0.0037,-2.1002,0.0854,0.1144,0.0047},
       {3.6344,6.348,-0.0028,-2.1001,0.0728,0.0984,0.0041},
       {3.6425,6.3485,-0.002,-2.1001,0.0604,0.0822,0.0034},
       {3.6506,6.349,-0.0013,-2.1001,0.048,0.0659,0.0028},
       {3.6587,6.3494,-0.0008,-2.1,0.0358,0.0496,0.0021},
       {3.6668,6.3497,-0.0004,-2.1,0.0238,0.0331,0.0014},
       {3.6749,6.3499,-0.0001,-2.1,0.0118,0.0166,0.0007},
       {3.683,6.35,0.0,-2.1,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
