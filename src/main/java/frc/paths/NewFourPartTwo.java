package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class NewFourPartTwo extends Path {
   private final static double[][] points = {
       {0,0.95,0.15,-2.55,-0.0,0.0,0.0},
       {0.0191,0.95,0.15,-2.55,0.0642,-0.0037,0.005},
       {0.0383,0.9512,0.1499,-2.5499,0.1284,-0.0073,0.01},
       {0.0574,0.9537,0.1498,-2.5497,0.1926,-0.0109,0.0149},
       {0.0766,0.9574,0.1496,-2.5494,0.2568,-0.0145,0.0199},
       {0.0957,0.9623,0.1493,-2.549,0.321,-0.018,0.0249},
       {0.1149,0.9684,0.149,-2.5486,0.3852,-0.0215,0.0299},
       {0.134,0.9758,0.1485,-2.548,0.4494,-0.025,0.0349},
       {0.1532,0.9844,0.1481,-2.5473,0.5136,-0.0284,0.0398},
       {0.1723,0.9942,0.1475,-2.5466,0.5778,-0.0318,0.0448},
       {0.1915,1.0053,0.1469,-2.5457,0.642,-0.0351,0.0498},
       {0.2106,1.0176,0.1462,-2.5448,0.7063,-0.0385,0.0547},
       {0.2297,1.0311,0.1455,-2.5437,0.7705,-0.0417,0.0597},
       {0.2489,1.0459,0.1447,-2.5426,0.8347,-0.0449,0.0646},
       {0.268,1.0619,0.1438,-2.5413,0.8989,-0.0481,0.0696},
       {0.2872,1.0791,0.1429,-2.54,0.9632,-0.0512,0.0745},
       {0.3063,1.0975,0.1419,-2.5386,1.0274,-0.0543,0.0795},
       {0.3255,1.1172,0.1409,-2.537,1.0916,-0.0573,0.0844},
       {0.3446,1.1381,0.1398,-2.5354,1.1558,-0.0602,0.0894},
       {0.3638,1.1602,0.1387,-2.5337,1.2201,-0.0631,0.0943},
       {0.3829,1.1836,0.1375,-2.5319,1.2843,-0.0659,0.0992},
       {0.4021,1.2082,0.1362,-2.53,1.3486,-0.0687,0.1041},
       {0.4212,1.234,0.1349,-2.528,1.4128,-0.0713,0.1091},
       {0.4403,1.261,0.1335,-2.5259,1.4771,-0.074,0.114},
       {0.4595,1.2893,0.1321,-2.5238,1.5413,-0.0765,0.1189},
       {0.4786,1.3188,0.1306,-2.5215,1.6056,-0.0789,0.1238},
       {0.4978,1.3496,0.1291,-2.5191,1.6698,-0.0813,0.1287},
       {0.5169,1.3815,0.1276,-2.5166,1.7341,-0.0835,0.1335},
       {0.5361,1.4147,0.126,-2.5141,1.7984,-0.0857,0.1384},
       {0.5552,1.4492,0.1243,-2.5114,1.8626,-0.0877,0.1433},
       {0.5744,1.4848,0.1226,-2.5087,1.9269,-0.0896,0.1481},
       {0.5935,1.5217,0.1209,-2.5059,1.9912,-0.0914,0.153},
       {0.6127,1.5598,0.1192,-2.5029,2.0554,-0.0931,0.1578},
       {0.6318,1.5992,0.1174,-2.4999,2.1197,-0.0946,0.1626},
       {0.6509,1.6398,0.1156,-2.4968,2.184,-0.096,0.1675},
       {0.6701,1.6816,0.1137,-2.4936,2.2483,-0.0972,0.1723},
       {0.6892,1.7246,0.1119,-2.4903,2.3126,-0.0982,0.177},
       {0.7084,1.7689,0.11,-2.4869,2.3769,-0.099,0.1818},
       {0.7275,1.8144,0.1081,-2.4834,2.4412,-0.0997,0.1866},
       {0.7467,1.8611,0.1062,-2.4798,2.5055,-0.1,0.1913},
       {0.7658,1.9091,0.1043,-2.4762,2.5698,-0.1001,0.196},
       {0.785,1.9583,0.1024,-2.4724,2.6341,-0.0999,0.2007},
       {0.8041,2.0087,0.1005,-2.4686,2.6984,-0.0994,0.2054},
       {0.8233,2.0604,0.0986,-2.4647,2.7627,-0.0985,0.21},
       {0.8424,2.1133,0.0967,-2.4606,2.827,-0.0972,0.2146},
       {0.8615,2.1674,0.0948,-2.4565,2.8913,-0.0954,0.2192},
       {0.8807,2.2228,0.093,-2.4523,2.9555,-0.093,0.2237},
       {0.8998,2.2794,0.0912,-2.448,3.0198,-0.09,0.2282},
       {0.919,2.3372,0.0895,-2.4437,3.084,-0.0863,0.2326},
       {0.9381,2.3962,0.0878,-2.4392,3.1481,-0.0816,0.2369},
       {0.9573,2.4565,0.0863,-2.4347,3.2121,-0.0759,0.2412},
       {0.9764,2.518,0.0848,-2.4301,3.276,-0.0687,0.2454},
       {0.9956,2.5807,0.0835,-2.4254,3.3397,-0.0599,0.2494},
       {1.0147,2.6447,0.0823,-2.4206,3.4031,-0.0487,0.2532},
       {1.0339,2.7098,0.0814,-2.4158,3.4658,-0.0345,0.2567},
       {1.053,2.7762,0.0808,-2.4108,3.5273,-0.0158,0.2598},
       {1.0721,2.8437,0.0804,-2.4059,3.5863,0.0097,0.2622},
       {1.0913,2.9124,0.0806,-2.4008,3.6395,0.0459,0.2633},
       {1.1104,2.982,0.0815,-2.3958,3.6769,0.0982,0.2618},
       {1.1296,3.0524,0.0834,-2.3908,3.6795,0.1625,0.257},
       {1.1487,3.1229,0.0865,-2.3859,3.6478,0.2185,0.2508},
       {1.1679,3.1927,0.0907,-2.3811,3.6,0.2614,0.2446},
       {1.187,3.2616,0.0957,-2.3764,3.5454,0.2954,0.2385},
       {1.2062,3.3295,0.1013,-2.3718,3.4876,0.3235,0.2326},
       {1.2253,3.3963,0.1075,-2.3674,3.428,0.3476,0.2268},
       {1.2445,3.4619,0.1142,-2.363,3.3673,0.3688,0.2211},
       {1.2636,3.5264,0.1213,-2.3588,3.3059,0.388,0.2155},
       {1.2828,3.5897,0.1287,-2.3547,3.244,0.4055,0.2099},
       {1.3019,3.6518,0.1364,-2.3506,3.1818,0.4217,0.2044},
       {1.321,3.7127,0.1445,-2.3467,3.1193,0.4369,0.199},
       {1.3402,3.7724,0.1529,-2.3429,3.0567,0.4512,0.1936},
       {1.3593,3.8309,0.1615,-2.3392,2.9938,0.4649,0.1882},
       {1.3785,3.8883,0.1704,-2.3356,2.9308,0.4778,0.1829},
       {1.3976,3.9444,0.1796,-2.3321,2.8678,0.4903,0.1776},
       {1.4168,3.9993,0.189,-2.3287,2.8046,0.5023,0.1723},
       {1.4359,4.053,0.1986,-2.3254,2.7413,0.5139,0.167},
       {1.4551,4.1055,0.2084,-2.3222,2.678,0.5251,0.1618},
       {1.4742,4.1567,0.2185,-2.3191,2.6147,0.5361,0.1566},
       {1.4934,4.2068,0.2287,-2.3161,2.5513,0.5467,0.1514},
       {1.5125,4.2556,0.2392,-2.3132,2.4878,0.5571,0.1462},
       {1.5316,4.3033,0.2499,-2.3104,2.4243,0.5672,0.141},
       {1.5508,4.3497,0.2607,-2.3077,2.3608,0.5771,0.1359},
       {1.5699,4.3949,0.2718,-2.3051,2.2972,0.5869,0.1307},
       {1.5891,4.4389,0.283,-2.3026,2.2336,0.5964,0.1256},
       {1.6082,4.4816,0.2944,-2.3002,2.17,0.6058,0.1204},
       {1.6274,4.5232,0.306,-2.2979,2.1064,0.6151,0.1153},
       {1.6465,4.5635,0.3178,-2.2957,2.0427,0.6241,0.1102},
       {1.6657,4.6026,0.3298,-2.2936,1.9791,0.6331,0.1051},
       {1.6848,4.6405,0.3419,-2.2916,1.9154,0.642,0.1},
       {1.704,4.6772,0.3542,-2.2897,1.8517,0.6507,0.0949},
       {1.7231,4.7126,0.3666,-2.2878,1.7879,0.6593,0.0899},
       {1.7422,4.7469,0.3792,-2.2861,1.7242,0.6678,0.0848},
       {1.7614,4.7799,0.392,-2.2845,1.6605,0.6763,0.0797},
       {1.7805,4.8117,0.405,-2.283,1.5967,0.6846,0.0747},
       {1.7997,4.8422,0.4181,-2.2815,1.5329,0.6929,0.0696},
       {1.8188,4.8716,0.4314,-2.2802,1.4692,0.701,0.0646},
       {1.838,4.8997,0.4448,-2.279,1.4054,0.7091,0.0595},
       {1.8571,4.9266,0.4584,-2.2778,1.3416,0.7172,0.0545},
       {1.8763,4.9523,0.4721,-2.2768,1.2778,0.7251,0.0494},
       {1.8954,4.9768,0.486,-2.2758,1.214,0.733,0.0444},
       {1.9146,5.0,0.5,-2.275,1.1501,0.7409,0.0394},
       {1.9193,5.0055,0.5035,-2.2748,1.1343,0.7426,0.0381},
       {1.924,5.0108,0.507,-2.2746,1.1185,0.7442,0.0369},
       {1.9288,5.0161,0.5106,-2.2745,1.1026,0.7456,0.0357},
       {1.9335,5.0214,0.5141,-2.2743,1.0867,0.7468,0.0345},
       {1.9383,5.0265,0.5176,-2.2741,1.0708,0.7478,0.0333},
       {1.943,5.0316,0.5212,-2.274,1.0549,0.7486,0.0321},
       {1.9477,5.0366,0.5247,-2.2738,1.039,0.7492,0.0309},
       {1.9525,5.0415,0.5283,-2.2737,1.0231,0.7496,0.0297},
       {1.9572,5.0464,0.5318,-2.2735,1.0072,0.7498,0.0286},
       {1.962,5.0511,0.5354,-2.2734,0.9913,0.7498,0.0274},
       {1.9667,5.0558,0.5389,-2.2733,0.9753,0.7496,0.0263},
       {1.9714,5.0605,0.5425,-2.2731,0.9594,0.7492,0.0251},
       {1.9762,5.065,0.5461,-2.273,0.9435,0.7486,0.024},
       {1.9809,5.0695,0.5496,-2.2729,0.9276,0.7478,0.0229},
       {1.9857,5.0739,0.5531,-2.2728,0.9117,0.7468,0.0218},
       {1.9904,5.0782,0.5567,-2.2727,0.8958,0.7456,0.0207},
       {1.9951,5.0825,0.5602,-2.2726,0.88,0.7442,0.0197},
       {1.9999,5.0866,0.5637,-2.2725,0.8641,0.7426,0.0186},
       {2.0046,5.0907,0.5673,-2.2724,0.8483,0.7408,0.0176},
       {2.0094,5.0947,0.5708,-2.2723,0.8325,0.7388,0.0165},
       {2.0141,5.0987,0.5743,-2.2723,0.8167,0.7366,0.0155},
       {2.0189,5.1026,0.5778,-2.2722,0.801,0.7342,0.0145},
       {2.0236,5.1064,0.5813,-2.2721,0.7853,0.7316,0.0135},
       {2.0283,5.1101,0.5847,-2.272,0.7696,0.7288,0.0126},
       {2.0331,5.1137,0.5882,-2.272,0.754,0.7258,0.0116},
       {2.0378,5.1173,0.5916,-2.2719,0.7384,0.7226,0.0107},
       {2.0426,5.1208,0.5951,-2.2719,0.7228,0.7192,0.0097},
       {2.0473,5.1242,0.5985,-2.2718,0.7073,0.7156,0.0088},
       {2.052,5.1276,0.6019,-2.2718,0.6919,0.7118,0.0079},
       {2.0568,5.1309,0.6052,-2.2718,0.6764,0.7078,0.007},
       {2.0615,5.1341,0.6086,-2.2717,0.6611,0.7036,0.0062},
       {2.0663,5.1372,0.6119,-2.2717,0.6458,0.6992,0.0053},
       {2.071,5.1403,0.6152,-2.2717,0.6305,0.6946,0.0045},
       {2.0757,5.1433,0.6185,-2.2716,0.6153,0.6898,0.0037},
       {2.0805,5.1462,0.6218,-2.2716,0.6002,0.6849,0.0029},
       {2.0852,5.149,0.625,-2.2716,0.5851,0.6797,0.0021},
       {2.09,5.1518,0.6283,-2.2716,0.5701,0.6744,0.0013},
       {2.0947,5.1545,0.6315,-2.2716,0.5552,0.6689,0.0006},
       {2.0995,5.1571,0.6346,-2.2716,0.5403,0.6631,-0.0002},
       {2.1042,5.1597,0.6378,-2.2716,0.5255,0.6573,-0.0009},
       {2.1089,5.1622,0.6409,-2.2716,0.5108,0.6512,-0.0016},
       {2.1137,5.1646,0.644,-2.2716,0.4962,0.6449,-0.0023},
       {2.1184,5.167,0.647,-2.2716,0.4816,0.6385,-0.003},
       {2.1232,5.1692,0.6501,-2.2716,0.4671,0.6319,-0.0036},
       {2.1279,5.1715,0.6531,-2.2716,0.4527,0.6251,-0.0042},
       {2.1326,5.1736,0.656,-2.2717,0.4384,0.6182,-0.0048},
       {2.1374,5.1757,0.659,-2.2717,0.4241,0.6111,-0.0054},
       {2.1421,5.1777,0.6619,-2.2717,0.4099,0.6038,-0.006},
       {2.1469,5.1796,0.6647,-2.2717,0.3959,0.5964,-0.0066},
       {2.1516,5.1815,0.6675,-2.2718,0.3819,0.5888,-0.0071},
       {2.1563,5.1833,0.6703,-2.2718,0.368,0.581,-0.0077},
       {2.1611,5.1851,0.6731,-2.2718,0.3541,0.5731,-0.0082},
       {2.1658,5.1867,0.6758,-2.2719,0.3404,0.565,-0.0087},
       {2.1706,5.1884,0.6785,-2.2719,0.3268,0.5568,-0.0091},
       {2.1753,5.1899,0.6811,-2.272,0.3132,0.5485,-0.0096},
       {2.18,5.1914,0.6837,-2.272,0.2998,0.5399,-0.01},
       {2.1848,5.1928,0.6863,-2.2721,0.2864,0.5313,-0.0105},
       {2.1895,5.1942,0.6888,-2.2721,0.2731,0.5225,-0.0109},
       {2.1943,5.1955,0.6913,-2.2722,0.26,0.5135,-0.0112},
       {2.199,5.1967,0.6937,-2.2722,0.2469,0.5044,-0.0116},
       {2.2038,5.1979,0.6961,-2.2723,0.2339,0.4952,-0.012},
       {2.2085,5.199,0.6985,-2.2723,0.221,0.4859,-0.0123},
       {2.2132,5.2,0.7008,-2.2724,0.2082,0.4764,-0.0126},
       {2.218,5.201,0.703,-2.2724,0.1955,0.4668,-0.0129},
       {2.2227,5.2019,0.7052,-2.2725,0.1829,0.457,-0.0132},
       {2.2275,5.2028,0.7074,-2.2726,0.1704,0.4472,-0.0135},
       {2.2322,5.2036,0.7095,-2.2726,0.158,0.4372,-0.0138},
       {2.2369,5.2044,0.7116,-2.2727,0.1457,0.4271,-0.014},
       {2.2417,5.2051,0.7136,-2.2728,0.1335,0.4169,-0.0142},
       {2.2464,5.2057,0.7156,-2.2728,0.1214,0.4065,-0.0144},
       {2.2512,5.2063,0.7175,-2.2729,0.1094,0.3961,-0.0146},
       {2.2559,5.2068,0.7194,-2.273,0.0974,0.3855,-0.0148},
       {2.2606,5.2072,0.7212,-2.273,0.0856,0.3749,-0.0149},
       {2.2654,5.2077,0.723,-2.2731,0.0739,0.3641,-0.0151},
       {2.2701,5.208,0.7247,-2.2732,0.0623,0.3532,-0.0152},
       {2.2749,5.2083,0.7264,-2.2733,0.0507,0.3422,-0.0153},
       {2.2796,5.2085,0.728,-2.2733,0.0393,0.3312,-0.0154},
       {2.2843,5.2087,0.7296,-2.2734,0.0279,0.32,-0.0155},
       {2.2891,5.2089,0.7311,-2.2735,0.0167,0.3087,-0.0156},
       {2.2938,5.2089,0.7326,-2.2736,0.0055,0.2973,-0.0156},
       {2.2986,5.209,0.734,-2.2736,-0.0055,0.2859,-0.0157},
       {2.3033,5.2089,0.7353,-2.2737,-0.0165,0.2743,-0.0157},
       {2.3081,5.2089,0.7366,-2.2738,-0.0274,0.2627,-0.0157},
       {2.3128,5.2087,0.7379,-2.2738,-0.0381,0.251,-0.0157},
       {2.3175,5.2085,0.7391,-2.2739,-0.0488,0.2392,-0.0157},
       {2.3223,5.2083,0.7402,-2.274,-0.0594,0.2273,-0.0157},
       {2.327,5.208,0.7413,-2.2741,-0.0699,0.2153,-0.0156},
       {2.3318,5.2077,0.7423,-2.2741,-0.0803,0.2032,-0.0156},
       {2.3365,5.2073,0.7433,-2.2742,-0.0906,0.1911,-0.0155},
       {2.3412,5.2069,0.7442,-2.2743,-0.1009,0.1789,-0.0154},
       {2.346,5.2064,0.745,-2.2744,-0.111,0.1666,-0.0154},
       {2.3507,5.2059,0.7458,-2.2744,-0.121,0.1543,-0.0152},
       {2.3555,5.2053,0.7465,-2.2745,-0.131,0.1418,-0.0151},
       {2.3602,5.2047,0.7472,-2.2746,-0.1409,0.1293,-0.015},
       {2.3649,5.204,0.7478,-2.2747,-0.1507,0.1168,-0.0149},
       {2.3697,5.2033,0.7484,-2.2747,-0.1604,0.1042,-0.0147},
       {2.3744,5.2026,0.7489,-2.2748,-0.17,0.0915,-0.0145},
       {2.3792,5.2017,0.7493,-2.2749,-0.1795,0.0787,-0.0144},
       {2.3839,5.2009,0.7497,-2.2749,-0.1889,0.0659,-0.0142},
       {2.3886,5.2,0.75,-2.275,-0.1983,0.053,-0.014},
       {2.3961,5.1985,0.7504,-2.2751,-0.2129,0.0328,-0.0137},
       {2.4035,5.1969,0.7506,-2.2752,-0.2276,0.0127,-0.0134},
       {2.4109,5.1953,0.7507,-2.2753,-0.2422,-0.0075,-0.0131},
       {2.4183,5.1935,0.7507,-2.2754,-0.2568,-0.0277,-0.0128},
       {2.4257,5.1916,0.7505,-2.2755,-0.2714,-0.0479,-0.0124},
       {2.4332,5.1895,0.7501,-2.2756,-0.286,-0.0681,-0.0121},
       {2.4406,5.1874,0.7496,-2.2757,-0.3006,-0.0883,-0.0118},
       {2.448,5.1852,0.749,-2.2758,-0.3152,-0.1085,-0.0115},
       {2.4554,5.1829,0.7481,-2.2759,-0.3298,-0.1287,-0.0112},
       {2.4628,5.1804,0.7472,-2.2759,-0.3443,-0.1489,-0.0109},
       {2.4703,5.1779,0.7461,-2.276,-0.3589,-0.1692,-0.0105},
       {2.4777,5.1752,0.7448,-2.2761,-0.3734,-0.1894,-0.0102},
       {2.4851,5.1724,0.7434,-2.2762,-0.388,-0.2097,-0.0099},
       {2.4925,5.1695,0.7419,-2.2762,-0.4025,-0.2299,-0.0096},
       {2.4999,5.1666,0.7402,-2.2763,-0.417,-0.2502,-0.0093},
       {2.5074,5.1635,0.7383,-2.2764,-0.4315,-0.2705,-0.0089},
       {2.5148,5.1603,0.7363,-2.2764,-0.4459,-0.2908,-0.0086},
       {2.5222,5.1569,0.7341,-2.2765,-0.4604,-0.3111,-0.0083},
       {2.5296,5.1535,0.7318,-2.2766,-0.4748,-0.3314,-0.008},
       {2.537,5.15,0.7294,-2.2766,-0.4893,-0.3517,-0.0076},
       {2.5445,5.1464,0.7268,-2.2767,-0.5037,-0.372,-0.0073},
       {2.5519,5.1426,0.724,-2.2767,-0.518,-0.3924,-0.007},
       {2.5593,5.1388,0.7211,-2.2768,-0.5324,-0.4128,-0.0066},
       {2.5667,5.1348,0.718,-2.2768,-0.5467,-0.4331,-0.0063},
       {2.5741,5.1308,0.7148,-2.2769,-0.561,-0.4535,-0.0059},
       {2.5816,5.1266,0.7115,-2.2769,-0.5753,-0.474,-0.0056},
       {2.589,5.1224,0.7079,-2.277,-0.5896,-0.4944,-0.0053},
       {2.5964,5.118,0.7043,-2.277,-0.6038,-0.5149,-0.0049},
       {2.6038,5.1135,0.7005,-2.2771,-0.618,-0.5354,-0.0045},
       {2.6112,5.1089,0.6965,-2.2771,-0.6321,-0.5559,-0.0042},
       {2.6187,5.1042,0.6924,-2.2771,-0.6462,-0.5765,-0.0038},
       {2.6261,5.0994,0.6881,-2.2771,-0.6602,-0.5971,-0.0035},
       {2.6335,5.0945,0.6836,-2.2772,-0.6742,-0.6177,-0.0031},
       {2.6409,5.0895,0.6791,-2.2772,-0.6881,-0.6384,-0.0027},
       {2.6483,5.0844,0.6743,-2.2772,-0.7019,-0.6591,-0.0023},
       {2.6557,5.0792,0.6694,-2.2772,-0.7156,-0.6799,-0.0019},
       {2.6632,5.0739,0.6644,-2.2772,-0.7292,-0.7008,-0.0015},
       {2.6706,5.0685,0.6592,-2.2773,-0.7427,-0.7217,-0.0011},
       {2.678,5.063,0.6538,-2.2773,-0.7561,-0.7428,-0.0007},
       {2.6854,5.0574,0.6483,-2.2773,-0.7693,-0.7639,-0.0002},
       {2.6928,5.0517,0.6427,-2.2773,-0.7822,-0.7852,0.0003},
       {2.7003,5.0459,0.6368,-2.2773,-0.7948,-0.8067,0.0008},
       {2.7077,5.04,0.6308,-2.2773,-0.8071,-0.8284,0.0013},
       {2.7151,5.034,0.6247,-2.2773,-0.8187,-0.8505,0.0019},
       {2.7225,5.0279,0.6184,-2.2772,-0.8295,-0.8729,0.0025},
       {2.7299,5.0218,0.6119,-2.2772,-0.8389,-0.896,0.0033},
       {2.7374,5.0155,0.6053,-2.2772,-0.8453,-0.9201,0.0043},
       {2.7448,5.0093,0.5984,-2.2772,-0.8443,-0.945,0.0057},
       {2.7522,5.003,0.5914,-2.2771,-0.8237,-0.959,0.0074},
       {2.7596,4.9969,0.5843,-2.2771,-0.7997,-0.9521,0.0083},
       {2.767,4.991,0.5772,-2.277,-0.7786,-0.939,0.0088},
       {2.7745,4.9852,0.5703,-2.2769,-0.7591,-0.9234,0.009},
       {2.7819,4.9795,0.5634,-2.2769,-0.7406,-0.9067,0.0091},
       {2.7893,4.9741,0.5567,-2.2768,-0.7227,-0.8894,0.0092},
       {2.7967,4.9687,0.5501,-2.2767,-0.7053,-0.8715,0.0092},
       {2.8041,4.9635,0.5436,-2.2767,-0.6882,-0.8534,0.0091},
       {2.8116,4.9584,0.5373,-2.2766,-0.6714,-0.835,0.009},
       {2.819,4.9534,0.5311,-2.2765,-0.6547,-0.8165,0.0089},
       {2.8264,4.9485,0.5251,-2.2765,-0.6382,-0.7978,0.0088},
       {2.8338,4.9438,0.5191,-2.2764,-0.6219,-0.779,0.0087},
       {2.8412,4.9392,0.5134,-2.2763,-0.6057,-0.7601,0.0085},
       {2.8487,4.9347,0.5077,-2.2763,-0.5895,-0.7411,0.0084},
       {2.8561,4.9303,0.5022,-2.2762,-0.5735,-0.722,0.0082},
       {2.8635,4.926,0.4969,-2.2762,-0.5575,-0.7029,0.008},
       {2.8709,4.9219,0.4916,-2.2761,-0.5415,-0.6837,0.0079},
       {2.8783,4.9179,0.4866,-2.276,-0.5257,-0.6645,0.0077},
       {2.8858,4.914,0.4816,-2.276,-0.5098,-0.6453,0.0075},
       {2.8932,4.9102,0.4769,-2.2759,-0.4941,-0.626,0.0073},
       {2.9006,4.9065,0.4722,-2.2759,-0.4783,-0.6067,0.0071},
       {2.908,4.903,0.4677,-2.2758,-0.4626,-0.5873,0.0069},
       {2.9154,4.8996,0.4634,-2.2758,-0.447,-0.5679,0.0067},
       {2.9228,4.8962,0.4591,-2.2757,-0.4313,-0.5485,0.0065},
       {2.9303,4.893,0.4551,-2.2757,-0.4157,-0.5291,0.0063},
       {2.9377,4.89,0.4511,-2.2756,-0.4001,-0.5097,0.0061},
       {2.9451,4.887,0.4474,-2.2756,-0.3845,-0.4902,0.0059},
       {2.9525,4.8841,0.4437,-2.2755,-0.369,-0.4707,0.0056},
       {2.9599,4.8814,0.4402,-2.2755,-0.3535,-0.4512,0.0054},
       {2.9674,4.8788,0.4369,-2.2754,-0.338,-0.4317,0.0052},
       {2.9748,4.8763,0.4337,-2.2754,-0.3225,-0.4122,0.005},
       {2.9822,4.8739,0.4306,-2.2754,-0.307,-0.3926,0.0047},
       {2.9896,4.8716,0.4277,-2.2753,-0.2916,-0.3731,0.0045},
       {2.997,4.8694,0.4249,-2.2753,-0.2761,-0.3535,0.0043},
       {3.0045,4.8674,0.4223,-2.2753,-0.2607,-0.334,0.0041},
       {3.0119,4.8654,0.4198,-2.2752,-0.2453,-0.3144,0.0038},
       {3.0193,4.8636,0.4175,-2.2752,-0.2299,-0.2948,0.0036},
       {3.0267,4.8619,0.4153,-2.2752,-0.2145,-0.2752,0.0034},
       {3.0341,4.8603,0.4133,-2.2752,-0.1991,-0.2556,0.0031},
       {3.0416,4.8589,0.4114,-2.2751,-0.1838,-0.2359,0.0029},
       {3.049,4.8575,0.4096,-2.2751,-0.1684,-0.2163,0.0027},
       {3.0564,4.8562,0.408,-2.2751,-0.1531,-0.1967,0.0024},
       {3.0638,4.8551,0.4066,-2.2751,-0.1377,-0.177,0.0022},
       {3.0712,4.8541,0.4053,-2.2751,-0.1224,-0.1574,0.0019},
       {3.0787,4.8532,0.4041,-2.2751,-0.1071,-0.1377,0.0017},
       {3.0861,4.8524,0.4031,-2.275,-0.0917,-0.1181,0.0015},
       {3.0935,4.8517,0.4022,-2.275,-0.0764,-0.0984,0.0012},
       {3.1009,4.8511,0.4015,-2.275,-0.0611,-0.0787,0.001},
       {3.1083,4.8507,0.4009,-2.275,-0.0458,-0.0591,0.0007},
       {3.1158,4.8503,0.4004,-2.275,-0.0306,-0.0394,0.0005},
       {3.1232,4.8501,0.4001,-2.275,-0.0153,-0.0197,0.0002},
       {3.1306,4.85,0.4,-2.275,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
