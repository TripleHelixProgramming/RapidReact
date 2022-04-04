package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class SuperRudeTwo extends Path {
   private final static double[][] points = {
       {0,0.35,-0.35,2.32,0.0,0.0,0.0},
       {0.0093,0.35,-0.35,2.32,0.0056,-0.0243,-0.0562},
       {0.0186,0.3501,-0.3502,2.3195,0.0112,-0.0487,-0.112},
       {0.0279,0.3502,-0.3507,2.3184,0.0168,-0.0732,-0.1674},
       {0.0371,0.3503,-0.3514,2.3169,0.0224,-0.0976,-0.2224},
       {0.0464,0.3505,-0.3523,2.3148,0.028,-0.1221,-0.277},
       {0.0557,0.3508,-0.3534,2.3122,0.0336,-0.1466,-0.3312},
       {0.065,0.3511,-0.3548,2.3092,0.0392,-0.1712,-0.385},
       {0.0743,0.3515,-0.3563,2.3056,0.0448,-0.1958,-0.4384},
       {0.0836,0.3519,-0.3582,2.3015,0.0504,-0.2204,-0.4915},
       {0.0929,0.3523,-0.3602,2.297,0.056,-0.2451,-0.5442},
       {0.1021,0.3529,-0.3625,2.2919,0.0616,-0.2698,-0.5965},
       {0.1114,0.3534,-0.365,2.2864,0.0672,-0.2945,-0.6484},
       {0.1207,0.3541,-0.3677,2.2803,0.0728,-0.3193,-0.6999},
       {0.13,0.3547,-0.3707,2.2738,0.0784,-0.3441,-0.751},
       {0.1393,0.3555,-0.3739,2.2669,0.084,-0.3689,-0.8017},
       {0.1486,0.3562,-0.3773,2.2594,0.0897,-0.3938,-0.8519},
       {0.1579,0.3571,-0.381,2.2515,0.0953,-0.4187,-0.9017},
       {0.1671,0.358,-0.3849,2.2431,0.101,-0.4436,-0.9509},
       {0.1764,0.3589,-0.389,2.2343,0.1066,-0.4686,-0.9996},
       {0.1857,0.3599,-0.3933,2.225,0.1123,-0.4936,-1.0477},
       {0.195,0.3609,-0.3979,2.2153,0.118,-0.5187,-1.0952},
       {0.2043,0.362,-0.4027,2.2051,0.1237,-0.5438,-1.142},
       {0.2136,0.3632,-0.4078,2.1945,0.1294,-0.569,-1.1881},
       {0.2229,0.3644,-0.4131,2.1835,0.1352,-0.5942,-1.2335},
       {0.2321,0.3656,-0.4186,2.172,0.1409,-0.6195,-1.2779},
       {0.2414,0.3669,-0.4243,2.1602,0.1467,-0.6449,-1.3214},
       {0.2507,0.3683,-0.4303,2.1479,0.1525,-0.6702,-1.3639},
       {0.26,0.3697,-0.4365,2.1352,0.1584,-0.6957,-1.4053},
       {0.2693,0.3712,-0.443,2.1222,0.1642,-0.7212,-1.4454},
       {0.2786,0.3727,-0.4497,2.1088,0.1702,-0.7468,-1.4843},
       {0.2879,0.3743,-0.4566,2.095,0.1761,-0.7725,-1.5216},
       {0.2971,0.3759,-0.4638,2.0809,0.1822,-0.7982,-1.5574},
       {0.3064,0.3776,-0.4712,2.0664,0.1883,-0.824,-1.5915},
       {0.3157,0.3794,-0.4789,2.0516,0.1944,-0.8499,-1.6237},
       {0.325,0.3812,-0.4868,2.0365,0.2007,-0.8758,-1.6538},
       {0.3343,0.383,-0.4949,2.0212,0.207,-0.9018,-1.6817},
       {0.3436,0.385,-0.5033,2.0056,0.2135,-0.9279,-1.7071},
       {0.3529,0.3869,-0.5119,1.9897,0.22,-0.954,-1.7299},
       {0.3621,0.389,-0.5207,1.9737,0.2267,-0.9802,-1.7499},
       {0.3714,0.3911,-0.5298,1.9574,0.2336,-1.0064,-1.7668},
       {0.3807,0.3933,-0.5392,1.941,0.2405,-1.0327,-1.7802},
       {0.39,0.3955,-0.5488,1.9245,0.2477,-1.0589,-1.79},
       {0.3993,0.3978,-0.5586,1.9078,0.255,-1.0852,-1.7957},
       {0.4086,0.4002,-0.5687,1.8912,0.2626,-1.1114,-1.797},
       {0.4179,0.4026,-0.579,1.8745,0.2703,-1.1376,-1.7932},
       {0.4271,0.4051,-0.5896,1.8578,0.2783,-1.1636,-1.7839},
       {0.4364,0.4077,-0.6004,1.8413,0.2865,-1.1895,-1.7679},
       {0.4457,0.4104,-0.6114,1.8249,0.2949,-1.2151,-1.7476},
       {0.455,0.4131,-0.6227,1.8086,0.3035,-1.2405,-1.7208},
       {0.4643,0.4159,-0.6342,1.7926,0.3123,-1.2654,-1.6836},
       {0.4736,0.4188,-0.646,1.777,0.3216,-1.2899,-1.6424},
       {0.4829,0.4218,-0.658,1.7618,0.3314,-1.3141,-1.6013},
       {0.4921,0.4249,-0.6702,1.7469,0.342,-1.3381,-1.5605},
       {0.5014,0.428,-0.6826,1.7324,0.3533,-1.3617,-1.5199},
       {0.5107,0.4313,-0.6952,1.7183,0.3656,-1.3848,-1.4796},
       {0.52,0.4347,-0.7081,1.7045,0.3789,-1.4074,-1.4398},
       {0.5293,0.4382,-0.7212,1.6912,0.3936,-1.4291,-1.4004},
       {0.5386,0.4419,-0.7344,1.6782,0.4098,-1.4497,-1.3618},
       {0.5479,0.4457,-0.7479,1.6655,0.4278,-1.4687,-1.3242},
       {0.5571,0.4497,-0.7615,1.6532,0.4481,-1.4855,-1.2883},
       {0.5664,0.4538,-0.7753,1.6413,0.4707,-1.4992,-1.2549},
       {0.5757,0.4582,-0.7892,1.6296,0.4955,-1.5087,-1.2252},
       {0.585,0.4628,-0.8033,1.6182,0.522,-1.5128,-1.2007},
       {0.5943,0.4677,-0.8173,1.6071,0.549,-1.5108,-1.183},
       {0.6036,0.4728,-0.8313,1.5961,0.5749,-1.5025,-1.1726},
       {0.6129,0.4781,-0.8453,1.5852,0.5987,-1.4892,-1.1692},
       {0.6221,0.4836,-0.8591,1.5744,0.6199,-1.4719,-1.1714},
       {0.6314,0.4894,-0.8728,1.5635,0.6378,-1.4515,-1.162},
       {0.6407,0.4953,-0.8863,1.5527,0.6526,-1.4291,-1.138},
       {0.65,0.5014,-0.8995,1.5421,0.6646,-1.4055,-1.1022},
       {0.6593,0.5076,-0.9126,1.5319,0.6743,-1.3816,-1.0561},
       {0.6686,0.5138,-0.9254,1.5221,0.682,-1.3576,-1.0007},
       {0.6779,0.5202,-0.938,1.5128,0.6884,-1.3337,-0.9374},
       {0.6872,0.5265,-0.9504,1.5041,0.6936,-1.31,-0.8675},
       {0.6964,0.533,-0.9626,1.496,0.6979,-1.2867,-0.7923},
       {0.7057,0.5395,-0.9745,1.4887,0.7017,-1.2635,-0.7128},
       {0.715,0.546,-0.9862,1.4821,0.7049,-1.2407,-0.6299},
       {0.7243,0.5525,-0.9978,1.4762,0.7077,-1.218,-0.5441},
       {0.7336,0.5591,-1.0091,1.4712,0.7101,-1.1956,-0.456},
       {0.7429,0.5657,-1.0202,1.4669,0.7123,-1.1733,-0.366},
       {0.7522,0.5723,-1.0311,1.4635,0.7143,-1.1511,-0.2742},
       {0.7614,0.5789,-1.0418,1.461,0.7161,-1.1291,-0.1811},
       {0.7707,0.5856,-1.0522,1.4593,0.7177,-1.1072,-0.0868},
       {0.78,0.5923,-1.0625,1.4585,0.7192,-1.0854,0.0087},
       {0.7893,0.5989,-1.0726,1.4586,0.7205,-1.0637,0.1051},
       {0.7986,0.6056,-1.0825,1.4595,0.7217,-1.0421,0.2023},
       {0.8079,0.6123,-1.0922,1.4614,0.7228,-1.0206,0.3004},
       {0.8172,0.619,-1.1016,1.4642,0.7238,-0.9992,0.3991},
       {0.8264,0.6258,-1.1109,1.4679,0.7246,-0.9778,0.4986},
       {0.8357,0.6325,-1.12,1.4725,0.7254,-0.9565,0.5986},
       {0.845,0.6392,-1.1289,1.4781,0.7261,-0.9352,0.6993},
       {0.8543,0.646,-1.1376,1.4846,0.7266,-0.9141,0.8006},
       {0.8636,0.6527,-1.146,1.492,0.7271,-0.8929,0.9023},
       {0.8729,0.6595,-1.1543,1.5004,0.7274,-0.8719,1.0047},
       {0.8822,0.6662,-1.1624,1.5097,0.7276,-0.8509,1.1075},
       {0.8914,0.673,-1.1703,1.52,0.7278,-0.8299,1.2108},
       {0.9007,0.6797,-1.178,1.5313,0.7278,-0.809,1.3146},
       {0.91,0.6865,-1.1856,1.5435,0.7276,-0.7882,1.4188},
       {0.9193,0.6932,-1.1929,1.5566,0.7274,-0.7674,1.5235},
       {0.9286,0.7,-1.2,1.5708,0.727,-0.7466,1.6286},
       {0.955,0.7192,-1.2197,1.6138,0.7223,-0.6775,1.8231},
       {0.9813,0.7382,-1.2376,1.6619,0.7133,-0.6025,1.9132},
       {1.0077,0.7571,-1.2535,1.7123,0.7024,-0.5267,1.9793},
       {1.0341,0.7756,-1.2674,1.7645,0.6901,-0.4511,2.043},
       {1.0605,0.7938,-1.2793,1.8184,0.6765,-0.3757,2.1036},
       {1.0869,0.8116,-1.2892,1.8739,0.6615,-0.3004,2.1608},
       {1.1132,0.8291,-1.2971,1.9309,0.6451,-0.2253,2.2141},
       {1.1396,0.8461,-1.303,1.9894,0.6272,-0.1505,2.263},
       {1.166,0.8626,-1.307,2.0491,0.6077,-0.0759,2.3068},
       {1.1924,0.8787,-1.309,2.1099,0.5868,-0.0016,2.345},
       {1.2188,0.8942,-1.3091,2.1718,0.5642,0.0723,2.3772},
       {1.2452,0.909,-1.3071,2.2345,0.54,0.1458,2.4028},
       {1.2715,0.9233,-1.3033,2.2979,0.5142,0.2188,2.4212},
       {1.2979,0.9369,-1.2975,2.3617,0.4868,0.2914,2.4322},
       {1.3243,0.9497,-1.2898,2.4259,0.4579,0.3633,2.4354},
       {1.3507,0.9618,-1.2803,2.4902,0.4276,0.4347,2.4303},
       {1.3771,0.9731,-1.2688,2.5543,0.3961,0.5055,2.4168},
       {1.4034,0.9835,-1.2555,2.618,0.3638,0.5758,2.3942},
       {1.4298,0.9931,-1.2403,2.6812,0.3316,0.6461,2.3619},
       {1.4562,1.0019,-1.2232,2.7435,0.3015,0.7169,2.3176},
       {1.4826,1.0098,-1.2043,2.8046,0.2765,0.7894,2.2584},
       {1.509,1.0171,-1.1835,2.8642,0.2446,0.859,2.2002},
       {1.5353,1.0236,-1.1608,2.9223,0.2063,0.9254,2.1429},
       {1.5617,1.029,-1.1364,2.9788,0.2673,0.9662,2.0227},
       {1.5881,1.0361,-1.1109,3.0322,0.3364,0.9326,1.9715},
       {1.6145,1.0449,-1.0863,3.0842,0.4058,0.8992,1.927},
       {1.6409,1.0556,-1.0626,3.135,0.4766,0.8687,1.8848},
       {1.6673,1.0682,-1.0397,3.1847,0.549,0.8422,1.8433},
       {1.6936,1.0827,-1.0175,3.2334,0.6229,0.8204,1.8014},
       {1.72,1.0991,-0.9958,3.2809,0.6982,0.8039,1.7579},
       {1.7464,1.1175,-0.9746,3.3273,0.7745,0.7934,1.712},
       {1.7728,1.138,-0.9537,3.3724,0.8513,0.7899,1.6626},
       {1.7992,1.1604,-0.9328,3.4163,0.928,0.794,1.609},
       {1.8255,1.1849,-0.9119,3.4587,1.0037,0.8063,1.5506},
       {1.8519,1.2114,-0.8906,3.4996,1.0774,0.8271,1.4871},
       {1.8783,1.2398,-0.8688,3.5389,1.1479,0.8564,1.4188},
       {1.9047,1.2701,-0.8462,3.5763,1.2143,0.8937,1.3461},
       {1.9311,1.3021,-0.8226,3.6118,1.2759,0.9382,1.2697},
       {1.9574,1.3358,-0.7979,3.6453,1.3322,0.9889,1.1903},
       {1.9838,1.3709,-0.7718,3.6767,1.3828,1.0449,1.1086},
       {2.0102,1.4074,-0.7442,3.706,1.4276,1.1046,1.026},
       {2.0366,1.4451,-0.7151,3.733,1.465,1.16,0.9515},
       {2.063,1.4837,-0.6845,3.7581,1.4898,1.1795,0.9226},
       {2.0894,1.523,-0.6534,3.7825,1.5116,1.1926,0.9022},
       {2.1157,1.5629,-0.6219,3.8063,1.5332,1.2134,0.8736},
       {2.1421,1.6034,-0.5899,3.8293,1.5565,1.2789,0.7952},
       {2.1685,1.6444,-0.5562,3.8503,1.574,1.3495,0.7135},
       {2.1949,1.6859,-0.5206,3.8691,1.5879,1.414,0.639},
       {2.2213,1.7278,-0.4833,3.886,1.5972,1.4713,0.5743},
       {2.2476,1.77,-0.4444,3.9011,1.6031,1.5222,0.5178},
       {2.274,1.8123,-0.4043,3.9148,1.6063,1.5675,0.4686},
       {2.3004,1.8546,-0.3629,3.9271,1.6072,1.6077,0.4261},
       {2.3268,1.897,-0.3205,3.9384,1.6062,1.6433,0.3896},
       {2.3532,1.9394,-0.2772,3.9487,1.6037,1.6747,0.3585},
       {2.3795,1.9817,-0.233,3.9581,1.5996,1.7025,0.3325},
       {2.4059,2.0239,-0.1881,3.9669,1.5942,1.7268,0.3112},
       {2.4323,2.066,-0.1425,3.9751,1.5876,1.7482,0.2942},
       {2.4587,2.1079,-0.0964,3.9829,1.5796,1.7668,0.2813},
       {2.4851,2.1495,-0.0498,3.9903,1.5704,1.7829,0.2723},
       {2.5115,2.191,-0.0027,3.9975,1.5598,1.7967,0.2671},
       {2.5378,2.2321,0.0447,4.0045,1.5477,1.8084,0.2655},
       {2.5642,2.2729,0.0924,4.0115,1.5341,1.8182,0.2677},
       {2.5906,2.3134,0.1403,4.0186,1.5188,1.826,0.2735},
       {2.617,2.3535,0.1885,4.0258,1.5016,1.8321,0.283},
       {2.6434,2.3931,0.2368,4.0333,1.4823,1.8363,0.2964},
       {2.6697,2.4322,0.2853,4.0411,1.4607,1.8388,0.314},
       {2.6961,2.4707,0.3338,4.0494,1.4201,1.8184,0.3756},
       {2.7225,2.5082,0.3818,4.0593,1.3587,1.7758,0.4809},
       {2.7489,2.544,0.4286,4.072,1.2945,1.7371,0.5818},
       {2.7753,2.5782,0.4744,4.0873,1.228,1.7018,0.678},
       {2.8016,2.6106,0.5193,4.1052,1.1599,1.6695,0.7698},
       {2.828,2.6412,0.5634,4.1255,1.0904,1.6396,0.8574},
       {2.8544,2.67,0.6066,4.1481,1.02,1.6117,0.9409},
       {2.8808,2.6969,0.6491,4.1729,0.9489,1.5851,1.0206},
       {2.9072,2.7219,0.691,4.1999,0.8774,1.5594,1.097},
       {2.9336,2.745,0.7321,4.2288,0.809,1.5263,1.1747},
       {2.9599,2.7664,0.7724,4.2598,0.7448,1.4856,1.2531},
       {2.9863,2.786,0.8116,4.2929,0.6845,1.4393,1.331},
       {3.0127,2.8041,0.8495,4.328,0.6273,1.3892,1.4076},
       {3.0391,2.8206,0.8862,4.3651,0.5723,1.3364,1.4828},
       {3.0655,2.8357,0.9214,4.4042,0.5188,1.2822,1.5567},
       {3.0918,2.8494,0.9553,4.4453,0.4658,1.2275,1.6295},
       {3.1182,2.8617,0.9876,4.4883,0.4121,1.1733,1.7012},
       {3.1446,2.8726,1.0186,4.5332,0.3568,1.1208,1.7717},
       {3.171,2.882,1.0482,4.5799,0.2985,1.0715,1.8403},
       {3.1974,2.8899,1.0764,4.6285,0.2361,1.0275,1.9054},
       {3.2237,2.8961,1.1035,4.6787,0.1688,0.9909,1.9641},
       {3.2501,2.9006,1.1297,4.7305,0.0967,0.9641,2.0125},
       {3.2765,2.9031,1.1551,4.7836,0.021,0.9488,2.0461},
       {3.3029,2.9037,1.1801,4.8376,-0.0563,0.9451,2.0621},
       {3.3293,2.9022,1.2051,4.892,-0.1336,0.9514,2.0596},
       {3.3556,2.8987,1.2302,4.9463,-0.2097,0.9657,2.0401},
       {3.382,2.8931,1.2556,5.0002,-0.2843,0.9859,2.0058},
       {3.4084,2.8856,1.2817,5.0531,-0.3573,1.01,1.9594},
       {3.4348,2.8762,1.3083,5.1048,-0.4291,1.037,1.9031},
       {3.4612,2.8649,1.3357,5.155,-0.5023,1.0598,1.8461},
       {3.4876,2.8516,1.3636,5.2037,-0.5768,1.078,1.7893},
       {3.5139,2.8364,1.3921,5.2509,-0.6522,1.0918,1.7327},
       {3.5403,2.8192,1.4209,5.2966,-0.7277,1.1045,1.6723},
       {3.5667,2.8,1.45,5.3407,-0.8019,1.117,1.5797},
       {3.5732,2.7948,1.4572,5.3509,-0.8201,1.1199,1.5563},
       {3.5796,2.7895,1.4645,5.361,-0.8382,1.1227,1.5322},
       {3.5861,2.7841,1.4717,5.3709,-0.8564,1.1254,1.5073},
       {3.5926,2.7786,1.479,5.3806,-0.8745,1.1279,1.4817},
       {3.599,2.7729,1.4863,5.3902,-0.8926,1.1302,1.4552},
       {3.6055,2.7671,1.4936,5.3996,-0.9106,1.1323,1.4278},
       {3.612,2.7612,1.5009,5.4089,-0.9286,1.1342,1.3993},
       {3.6184,2.7552,1.5083,5.4179,-0.9466,1.1358,1.3697},
       {3.6249,2.7491,1.5156,5.4268,-0.9645,1.1371,1.3389},
       {3.6314,2.7429,1.523,5.4354,-0.9823,1.138,1.3067},
       {3.6378,2.7365,1.5303,5.4439,-1.0,1.1385,1.273},
       {3.6443,2.7301,1.5377,5.4521,-1.0175,1.1384,1.2375},
       {3.6508,2.7235,1.545,5.4601,-1.0348,1.1376,1.1997},
       {3.6572,2.7168,1.5524,5.4679,-1.0518,1.1357,1.1586},
       {3.6637,2.71,1.5597,5.4754,-1.0677,1.132,1.1104},
       {3.6702,2.7031,1.5671,5.4825,-1.081,1.1264,1.0448},
       {3.6766,2.6961,1.5744,5.4893,-1.0911,1.1203,0.9568},
       {3.6831,2.689,1.5816,5.4955,-1.0991,1.1145,0.8552},
       {3.6896,2.6819,1.5888,5.501,-1.1057,1.109,0.746},
       {3.696,2.6748,1.596,5.5058,-1.1109,1.1037,0.6314},
       {3.7025,2.6676,1.6031,5.5099,-1.1146,1.0985,0.5122},
       {3.709,2.6604,1.6102,5.5132,-1.1159,1.0934,0.3893},
       {3.7154,2.6532,1.6173,5.5158,-1.1135,1.0876,0.2697},
       {3.7219,2.646,1.6243,5.5175,-1.1078,1.0796,0.1659},
       {3.7284,2.6388,1.6313,5.5186,-1.1,1.0697,0.0791},
       {3.7348,2.6317,1.6382,5.5191,-1.0906,1.0587,0.0063},
       {3.7413,2.6246,1.6451,5.5191,-1.0797,1.0472,-0.0543},
       {3.7478,2.6177,1.6518,5.5188,-1.0675,1.0357,-0.1046},
       {3.7542,2.6107,1.6585,5.5181,-1.0543,1.0244,-0.1465},
       {3.7607,2.6039,1.6652,5.5172,-1.0403,1.0134,-0.1819},
       {3.7672,2.5972,1.6717,5.516,-1.0256,1.0027,-0.2121},
       {3.7736,2.5906,1.6782,5.5146,-1.0105,0.9923,-0.2383},
       {3.7801,2.584,1.6846,5.5131,-0.995,0.9822,-0.2611},
       {3.7866,2.5776,1.691,5.5114,-0.9791,0.9724,-0.2813},
       {3.793,2.5713,1.6973,5.5096,-0.9631,0.9629,-0.2991},
       {3.7995,2.565,1.7035,5.5076,-0.9468,0.9536,-0.3151},
       {3.806,2.5589,1.7096,5.5056,-0.9303,0.9445,-0.3294},
       {3.8124,2.5529,1.7158,5.5035,-0.9137,0.9355,-0.3422},
       {3.8189,2.547,1.7218,5.5012,-0.897,0.9267,-0.3537},
       {3.8254,2.5412,1.7278,5.499,-0.8802,0.9181,-0.3642},
       {3.8318,2.5355,1.7337,5.4966,-0.8633,0.9096,-0.3736},
       {3.8383,2.5299,1.7396,5.4942,-0.8463,0.9012,-0.3821},
       {3.8448,2.5244,1.7454,5.4917,-0.8293,0.893,-0.3897},
       {3.8512,2.5191,1.7512,5.4892,-0.8122,0.8848,-0.3967},
       {3.8577,2.5138,1.7569,5.4866,-0.795,0.8768,-0.4029},
       {3.8642,2.5087,1.7626,5.484,-0.7778,0.8688,-0.4085},
       {3.8706,2.5037,1.7682,5.4814,-0.7605,0.861,-0.4135},
       {3.8771,2.4987,1.7738,5.4787,-0.7432,0.8531,-0.418},
       {3.8836,2.4939,1.7793,5.476,-0.7258,0.8454,-0.422},
       {3.89,2.4892,1.7848,5.4733,-0.7085,0.8378,-0.4256},
       {3.8965,2.4847,1.7902,5.4705,-0.6911,0.8302,-0.4287},
       {3.903,2.4802,1.7956,5.4678,-0.6736,0.8226,-0.4314},
       {3.9094,2.4758,1.8009,5.465,-0.6561,0.8151,-0.4337},
       {3.9159,2.4716,1.8062,5.4622,-0.6387,0.8077,-0.4357},
       {3.9224,2.4675,1.8114,5.4593,-0.6211,0.8003,-0.4374},
       {3.9288,2.4634,1.8166,5.4565,-0.6036,0.793,-0.4387},
       {3.9353,2.4595,1.8217,5.4537,-0.5861,0.7857,-0.4398},
       {3.9418,2.4558,1.8268,5.4508,-0.5685,0.7785,-0.4406},
       {3.9482,2.4521,1.8318,5.448,-0.5509,0.7712,-0.4411},
       {3.9547,2.4485,1.8368,5.4451,-0.5333,0.7641,-0.4414},
       {3.9612,2.4451,1.8417,5.4423,-0.5157,0.7569,-0.4414},
       {3.9676,2.4417,1.8466,5.4394,-0.4981,0.7498,-0.4412},
       {3.9741,2.4385,1.8515,5.4366,-0.4804,0.7428,-0.4408},
       {3.9806,2.4354,1.8563,5.4337,-0.4628,0.7357,-0.4402},
       {3.987,2.4324,1.861,5.4309,-0.4451,0.7287,-0.4394},
       {3.9935,2.4295,1.8657,5.428,-0.4274,0.7218,-0.4384},
       {4.0,2.4268,1.8704,5.4252,-0.4097,0.7148,-0.4373},
       {4.0064,2.4241,1.875,5.4224,-0.392,0.7079,-0.436},
       {4.0129,2.4216,1.8796,5.4195,-0.3743,0.701,-0.4345},
       {4.0194,2.4192,1.8841,5.4167,-0.3566,0.6941,-0.4328},
       {4.0258,2.4169,1.8886,5.4139,-0.3389,0.6873,-0.4311},
       {4.0323,2.4147,1.8931,5.4111,-0.3211,0.6804,-0.4291},
       {4.0388,2.4126,1.8975,5.4084,-0.3034,0.6736,-0.4271},
       {4.0452,2.4106,1.9018,5.4056,-0.2857,0.6668,-0.4249},
       {4.0517,2.4088,1.9062,5.4029,-0.2679,0.6601,-0.4226},
       {4.0582,2.407,1.9104,5.4001,-0.2501,0.6533,-0.4201},
       {4.0646,2.4054,1.9146,5.3974,-0.2324,0.6466,-0.4176},
       {4.0711,2.4039,1.9188,5.3947,-0.2146,0.6399,-0.4149},
       {4.0776,2.4025,1.923,5.392,-0.1968,0.6332,-0.4122},
       {4.084,2.4013,1.9271,5.3894,-0.179,0.6265,-0.4093},
       {4.0905,2.4001,1.9311,5.3867,-0.1612,0.6198,-0.4063},
       {4.097,2.3991,1.9351,5.3841,-0.1435,0.6132,-0.4032},
       {4.1034,2.3981,1.9391,5.3815,-0.1256,0.6066,-0.4001},
       {4.1099,2.3973,1.943,5.3789,-0.1078,0.5999,-0.3968},
       {4.1164,2.3966,1.9469,5.3763,-0.09,0.5933,-0.3935},
       {4.1228,2.396,1.9507,5.3738,-0.0722,0.5867,-0.3901},
       {4.1293,2.3956,1.9545,5.3713,-0.0544,0.5802,-0.3866},
       {4.1358,2.3952,1.9583,5.3688,-0.0366,0.5736,-0.383},
       {4.1422,2.395,1.962,5.3663,-0.0188,0.567,-0.3793},
       {4.1487,2.3949,1.9656,5.3638,-0.0009,0.5605,-0.3756},
       {4.1552,2.3949,1.9693,5.3614,0.0169,0.554,-0.3718},
       {4.1616,2.395,1.9729,5.359,0.0348,0.5475,-0.3679},
       {4.1681,2.3952,1.9764,5.3566,0.0526,0.541,-0.3639},
       {4.1746,2.3955,1.9799,5.3543,0.0704,0.5345,-0.3599},
       {4.181,2.396,1.9833,5.3519,0.0883,0.528,-0.3558},
       {4.1875,2.3966,1.9868,5.3496,0.1061,0.5215,-0.3517},
       {4.194,2.3972,1.9901,5.3474,0.124,0.5151,-0.3475},
       {4.2004,2.398,1.9935,5.3451,0.1419,0.5086,-0.3432},
       {4.2069,2.399,1.9968,5.3429,0.1597,0.5022,-0.3389},
       {4.2134,2.4,2.0,5.3407,0.1776,0.4957,-0.3345},
       {4.2247,2.402,2.0056,5.3369,0.2089,0.4844,-0.3268},
       {4.2361,2.4044,2.0111,5.3332,0.2403,0.473,-0.3191},
       {4.2475,2.4071,2.0165,5.3296,0.2717,0.4617,-0.3114},
       {4.2588,2.4102,2.0218,5.326,0.303,0.4502,-0.3037},
       {4.2702,2.4136,2.0269,5.3226,0.3343,0.4388,-0.2959},
       {4.2815,2.4174,2.0319,5.3192,0.3656,0.4273,-0.2881},
       {4.2929,2.4216,2.0367,5.3159,0.397,0.4158,-0.2804},
       {4.3043,2.4261,2.0414,5.3128,0.4282,0.4043,-0.2726},
       {4.3156,2.431,2.046,5.3097,0.4595,0.3927,-0.2647},
       {4.327,2.4362,2.0505,5.3067,0.4908,0.3811,-0.2569},
       {4.3383,2.4418,2.0548,5.3037,0.522,0.3694,-0.249},
       {4.3497,2.4477,2.059,5.3009,0.5533,0.3577,-0.2411},
       {4.3611,2.454,2.0631,5.2982,0.5845,0.346,-0.2332},
       {4.3724,2.4606,2.067,5.2955,0.6157,0.3342,-0.2252},
       {4.3838,2.4676,2.0708,5.293,0.6469,0.3224,-0.2172},
       {4.3951,2.475,2.0745,5.2905,0.678,0.3105,-0.2092},
       {4.4065,2.4827,2.078,5.2881,0.7092,0.2985,-0.2012},
       {4.4179,2.4907,2.0814,5.2858,0.7403,0.2865,-0.1931},
       {4.4292,2.4991,2.0846,5.2836,0.7714,0.2744,-0.1849},
       {4.4406,2.5079,2.0878,5.2815,0.8024,0.2623,-0.1768},
       {4.4519,2.517,2.0907,5.2795,0.8335,0.2501,-0.1686},
       {4.4633,2.5265,2.0936,5.2776,0.8645,0.2378,-0.1603},
       {4.4747,2.5363,2.0963,5.2758,0.8954,0.2255,-0.152},
       {4.486,2.5465,2.0988,5.2741,0.9264,0.213,-0.1436},
       {4.4974,2.557,2.1013,5.2724,0.9573,0.2005,-0.1351},
       {4.5087,2.5679,2.1035,5.2709,0.9881,0.1878,-0.1266},
       {4.5201,2.5791,2.1057,5.2695,1.0189,0.1751,-0.118},
       {4.5315,2.5907,2.1077,5.2681,1.0497,0.1622,-0.1094},
       {4.5428,2.6026,2.1095,5.2669,1.0804,0.1492,-0.1006},
       {4.5542,2.6149,2.1112,5.2657,1.111,0.1361,-0.0918},
       {4.5655,2.6275,2.1127,5.2647,1.1416,0.1228,-0.0828},
       {4.5769,2.6405,2.1141,5.2638,1.1721,0.1093,-0.0737},
       {4.5883,2.6538,2.1154,5.2629,1.2025,0.0956,-0.0645},
       {4.5996,2.6674,2.1165,5.2622,1.2328,0.0817,-0.0551},
       {4.611,2.6814,2.1174,5.2616,1.263,0.0676,-0.0456},
       {4.6223,2.6958,2.1182,5.261,1.2931,0.0532,-0.0358},
       {4.6337,2.7105,2.1188,5.2606,1.323,0.0385,-0.0259},
       {4.6451,2.7255,2.1192,5.2603,1.3527,0.0234,-0.0156},
       {4.6564,2.7409,2.1195,5.2602,1.3822,0.0079,-0.0051},
       {4.6678,2.7566,2.1196,5.2601,1.4114,-0.0081,0.0058},
       {4.6791,2.7726,2.1195,5.2602,1.4403,-0.0247,0.0171},
       {4.6905,2.789,2.1192,5.2604,1.4688,-0.0421,0.029},
       {4.7019,2.8057,2.1187,5.2607,1.4966,-0.0603,0.0415},
       {4.7132,2.8227,2.118,5.2612,1.5237,-0.0796,0.0548},
       {4.7246,2.84,2.1171,5.2618,1.5497,-0.1004,0.0693},
       {4.7359,2.8576,2.116,5.2626,1.5739,-0.1231,0.0853},
       {4.7473,2.8755,2.1146,5.2635,1.5955,-0.1484,0.1033},
       {4.7587,2.8936,2.1129,5.2647,1.6121,-0.1771,0.1239},
       {4.77,2.9119,2.1109,5.2661,1.62,-0.2093,0.1464},
       {4.7814,2.9303,2.1085,5.2678,1.6136,-0.2418,0.1673},
       {4.7927,2.9486,2.1058,5.2697,1.593,-0.2678,0.1831},
       {4.8041,2.9667,2.1027,5.2718,1.5645,-0.2851,0.1933},
       {4.8155,2.9845,2.0995,5.274,1.5329,-0.2957,0.1995},
       {4.8268,3.0019,2.0961,5.2762,1.5001,-0.3019,0.2031},
       {4.8382,3.019,2.0927,5.2785,1.4669,-0.3051,0.2049},
       {4.8496,3.0356,2.0892,5.2809,1.4335,-0.3062,0.2055},
       {4.8609,3.0519,2.0857,5.2832,1.4001,-0.3057,0.205},
       {4.8723,3.0678,2.0823,5.2855,1.3668,-0.304,0.2038},
       {4.8836,3.0833,2.0788,5.2878,1.3335,-0.3014,0.202},
       {4.895,3.0985,2.0754,5.2901,1.3003,-0.298,0.1997},
       {4.9064,3.1133,2.072,5.2924,1.2671,-0.294,0.197},
       {4.9177,3.1277,2.0687,5.2946,1.234,-0.2896,0.194},
       {4.9291,3.1417,2.0654,5.2969,1.201,-0.2846,0.1907},
       {4.9404,3.1553,2.0621,5.299,1.1681,-0.2793,0.1872},
       {4.9518,3.1686,2.059,5.3011,1.1352,-0.2737,0.1834},
       {4.9632,3.1815,2.0559,5.3032,1.1023,-0.2678,0.1795},
       {4.9745,3.194,2.0528,5.3053,1.0695,-0.2617,0.1754},
       {4.9859,3.2062,2.0498,5.3073,1.0367,-0.2553,0.1712},
       {4.9972,3.2179,2.0469,5.3092,1.004,-0.2487,0.1668},
       {5.0086,3.2293,2.0441,5.3111,0.9713,-0.242,0.1623},
       {5.02,3.2404,2.0414,5.3129,0.9387,-0.2351,0.1577},
       {5.0313,3.251,2.0387,5.3147,0.906,-0.2281,0.153},
       {5.0427,3.2613,2.0361,5.3165,0.8734,-0.2209,0.1482},
       {5.054,3.2713,2.0336,5.3182,0.8409,-0.2136,0.1433},
       {5.0654,3.2808,2.0312,5.3198,0.8083,-0.2062,0.1383},
       {5.0768,3.29,2.0288,5.3214,0.7758,-0.1987,0.1333},
       {5.0881,3.2988,2.0266,5.3229,0.7433,-0.1911,0.1282},
       {5.0995,3.3072,2.0244,5.3243,0.7108,-0.1834,0.1231},
       {5.1108,3.3153,2.0223,5.3257,0.6784,-0.1756,0.1178},
       {5.1222,3.323,2.0203,5.3271,0.646,-0.1677,0.1126},
       {5.1336,3.3304,2.0184,5.3283,0.6135,-0.1598,0.1073},
       {5.1449,3.3373,2.0166,5.3296,0.5811,-0.1518,0.1019},
       {5.1563,3.3439,2.0149,5.3307,0.5487,-0.1438,0.0965},
       {5.1676,3.3502,2.0132,5.3318,0.5164,-0.1357,0.0911},
       {5.179,3.356,2.0117,5.3328,0.484,-0.1275,0.0856},
       {5.1904,3.3615,2.0103,5.3338,0.4517,-0.1193,0.0801},
       {5.2017,3.3667,2.0089,5.3347,0.4193,-0.111,0.0746},
       {5.2131,3.3714,2.0076,5.3356,0.387,-0.1027,0.069},
       {5.2244,3.3758,2.0065,5.3364,0.3547,-0.0943,0.0634},
       {5.2358,3.3799,2.0054,5.3371,0.3224,-0.0859,0.0577},
       {5.2472,3.3835,2.0044,5.3377,0.2901,-0.0775,0.0521},
       {5.2585,3.3868,2.0035,5.3383,0.2579,-0.069,0.0464},
       {5.2699,3.3898,2.0028,5.3389,0.2256,-0.0605,0.0406},
       {5.2812,3.3923,2.0021,5.3393,0.1934,-0.052,0.0349},
       {5.2926,3.3945,2.0015,5.3397,0.1611,-0.0434,0.0291},
       {5.304,3.3963,2.001,5.34,0.1289,-0.0348,0.0233},
       {5.3153,3.3978,2.0006,5.3403,0.0966,-0.0261,0.0175},
       {5.3267,3.3989,2.0003,5.3405,0.0644,-0.0174,0.0117},
       {5.338,3.3996,2.0001,5.3406,0.0322,-0.0087,0.0059},
       {5.3494,3.4,2.0,5.3407,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
