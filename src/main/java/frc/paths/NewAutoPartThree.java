package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class NewAutoPartThree extends Path {
   private final static double[][] points = {
       {0,5.05,0.5,-2.275,-0.0,0.0,0.0},
       {0.0277,5.05,0.5,-2.275,-0.093,0.004,0.0128},
       {0.0555,5.0474,0.5001,-2.2746,-0.186,0.008,0.0256},
       {0.0832,5.0423,0.5003,-2.2739,-0.2791,0.0121,0.0383},
       {0.1109,5.0345,0.5007,-2.2729,-0.3721,0.0161,0.0509},
       {0.1386,5.0242,0.5011,-2.2715,-0.4651,0.0201,0.0634},
       {0.1664,5.0113,0.5017,-2.2697,-0.5581,0.0241,0.0759},
       {0.1941,4.9958,0.5023,-2.2676,-0.6511,0.0282,0.0882},
       {0.2218,4.9778,0.5031,-2.2651,-0.7442,0.0322,0.1005},
       {0.2496,4.9571,0.504,-2.2624,-0.8372,0.0362,0.1126},
       {0.2773,4.9339,0.505,-2.2592,-0.9302,0.0402,0.1246},
       {0.305,4.9081,0.5061,-2.2558,-1.0232,0.0443,0.1365},
       {0.3328,4.8798,0.5074,-2.252,-1.1163,0.0483,0.1483},
       {0.3605,4.8488,0.5087,-2.2479,-1.2093,0.0523,0.1599},
       {0.3882,4.8153,0.5102,-2.2435,-1.3023,0.0564,0.1714},
       {0.4159,4.7792,0.5117,-2.2387,-1.3954,0.0604,0.1827},
       {0.4437,4.7405,0.5134,-2.2336,-1.4884,0.0644,0.1938},
       {0.4714,4.6992,0.5152,-2.2283,-1.5814,0.0684,0.2048},
       {0.4991,4.6553,0.5171,-2.2226,-1.6744,0.0725,0.2155},
       {0.5269,4.6089,0.5191,-2.2166,-1.7675,0.0765,0.2261},
       {0.5546,4.5599,0.5212,-2.2103,-1.8605,0.0805,0.2364},
       {0.5823,4.5083,0.5234,-2.2038,-1.9535,0.0845,0.2465},
       {0.6101,4.4541,0.5258,-2.1969,-2.0466,0.0886,0.2562},
       {0.6378,4.3974,0.5282,-2.1898,-2.1396,0.0926,0.2657},
       {0.6655,4.338,0.5308,-2.1825,-2.2326,0.0966,0.2749},
       {0.6932,4.2761,0.5335,-2.1748,-2.3257,0.1007,0.2837},
       {0.721,4.2116,0.5363,-2.167,-2.4187,0.1047,0.2921},
       {0.7487,4.1446,0.5392,-2.1589,-2.5118,0.1087,0.3001},
       {0.7764,4.0749,0.5422,-2.1506,-2.6048,0.1128,0.3075},
       {0.8042,4.0027,0.5453,-2.142,-2.6979,0.1168,0.3144},
       {0.8319,3.9279,0.5486,-2.1333,-2.7909,0.1208,0.3207},
       {0.8596,3.8505,0.5519,-2.1244,-2.884,0.1249,0.3262},
       {0.8874,3.7705,0.5554,-2.1154,-2.977,0.1289,0.3309},
       {0.9151,3.688,0.5589,-2.1062,-3.0701,0.1329,0.3346},
       {0.9428,3.6028,0.5626,-2.0969,-3.1631,0.137,0.337},
       {0.9705,3.5151,0.5664,-2.0876,-3.2562,0.141,0.338},
       {0.9983,3.4248,0.5703,-2.0782,-3.3492,0.145,0.3371},
       {1.026,3.332,0.5744,-2.0689,-3.4423,0.1491,0.3339},
       {1.0537,3.2365,0.5785,-2.0596,-3.5353,0.1531,0.3275},
       {1.0815,3.1385,0.5827,-2.0505,-3.6283,0.1572,0.3167},
       {1.1092,3.0378,0.5871,-2.0417,-3.7213,0.1612,0.2992},
       {1.1369,2.9347,0.5916,-2.0334,-3.8142,0.1652,0.2704},
       {1.1647,2.8289,0.5962,-2.0259,-3.9066,0.1692,0.2185},
       {1.1924,2.7206,0.6008,-2.0199,-3.9967,0.1733,0.103},
       {1.2201,2.6097,0.6056,-2.017,-4.0762,0.1765,0.0},
       {1.2478,2.4967,0.6105,-2.017,-4.0762,0.1765,0.0},
       {1.2756,2.3837,0.6154,-2.017,-4.0762,0.1765,0.0},
       {1.3033,2.2706,0.6203,-2.017,-4.0762,0.1765,0.0},
       {1.331,2.1576,0.6252,-2.017,-4.0762,0.1765,0.0},
       {1.3588,2.0446,0.6301,-2.017,-4.0762,0.1765,0.0},
       {1.3865,1.9315,0.635,-2.017,-4.0762,0.1765,0.0},
       {1.4142,1.8185,0.6399,-2.017,-4.0762,0.1765,0.0},
       {1.442,1.7055,0.6448,-2.017,-4.0762,0.1765,0.0},
       {1.4697,1.5924,0.6497,-2.017,-4.0762,0.1765,0.0},
       {1.4974,1.4794,0.6546,-2.017,-4.0762,0.1765,0.0},
       {1.5251,1.3664,0.6595,-2.017,-4.0762,0.1765,0.0},
       {1.5529,1.2533,0.6644,-2.017,-4.0762,0.1765,0.0},
       {1.5806,1.1403,0.6693,-2.017,-3.9968,0.1734,0.1029},
       {1.6083,1.0295,0.6741,-2.0142,-3.9068,0.1693,0.2187},
       {1.6361,0.9211,0.6788,-2.0081,-3.8143,0.1653,0.2663},
       {1.6638,0.8154,0.6834,-2.0007,-3.7214,0.1613,0.2923},
       {1.6915,0.7122,0.6878,-1.9926,-3.6284,0.1572,0.3079},
       {1.7193,0.6116,0.6922,-1.9841,-3.5354,0.1532,0.3172},
       {1.747,0.5135,0.6965,-1.9753,-3.4423,0.1491,0.3225},
       {1.7747,0.4181,0.7006,-1.9663,-3.3493,0.1451,0.3248},
       {1.8024,0.3252,0.7046,-1.9573,-3.2562,0.1411,0.3249},
       {1.8302,0.2349,0.7085,-1.9483,-3.1631,0.137,0.3233},
       {1.8579,0.1472,0.7123,-1.9394,-3.0701,0.133,0.3204},
       {1.8856,0.0621,0.716,-1.9305,-2.977,0.129,0.3163},
       {1.9134,-0.0205,0.7196,-1.9217,-2.884,0.1249,0.3114},
       {1.9411,-0.1005,0.723,-1.9131,-2.7909,0.1209,0.3057},
       {1.9688,-0.1779,0.7264,-1.9046,-2.6979,0.1169,0.2993},
       {1.9966,-0.2527,0.7296,-1.8963,-2.6048,0.1128,0.2924},
       {2.0243,-0.3249,0.7328,-1.8882,-2.5118,0.1088,0.285},
       {2.052,-0.3946,0.7358,-1.8803,-2.4188,0.1048,0.2771},
       {2.0797,-0.4616,0.7387,-1.8726,-2.3257,0.1007,0.2689},
       {2.1075,-0.5261,0.7415,-1.8651,-2.2327,0.0967,0.2603},
       {2.1352,-0.588,0.7442,-1.8579,-2.1396,0.0927,0.2514},
       {2.1629,-0.6474,0.7467,-1.851,-2.0466,0.0886,0.2422},
       {2.1907,-0.7041,0.7492,-1.8442,-1.9536,0.0846,0.2327},
       {2.2184,-0.7583,0.7515,-1.8378,-1.8605,0.0806,0.2231},
       {2.2461,-0.8099,0.7538,-1.8316,-1.7675,0.0765,0.2132},
       {2.2739,-0.8589,0.7559,-1.8257,-1.6745,0.0725,0.2031},
       {2.3016,-0.9053,0.7579,-1.8201,-1.5814,0.0685,0.1929},
       {2.3293,-0.9492,0.7598,-1.8147,-1.4884,0.0645,0.1824},
       {2.357,-0.9905,0.7616,-1.8096,-1.3954,0.0604,0.1719},
       {2.3848,-1.0291,0.7633,-1.8049,-1.3024,0.0564,0.1612},
       {2.4125,-1.0653,0.7648,-1.8004,-1.2093,0.0524,0.1503},
       {2.4402,-1.0988,0.7663,-1.7962,-1.1163,0.0483,0.1393},
       {2.468,-1.1298,0.7676,-1.7924,-1.0233,0.0443,0.1282},
       {2.4957,-1.1581,0.7689,-1.7888,-0.9302,0.0403,0.117},
       {2.5234,-1.1839,0.77,-1.7856,-0.8372,0.0363,0.1057},
       {2.5512,-1.2071,0.771,-1.7827,-0.7442,0.0322,0.0943},
       {2.5789,-1.2278,0.7719,-1.78,-0.6512,0.0282,0.0828},
       {2.6066,-1.2458,0.7727,-1.7777,-0.5581,0.0242,0.0712},
       {2.6343,-1.2613,0.7733,-1.7758,-0.4651,0.0201,0.0595},
       {2.6621,-1.2742,0.7739,-1.7741,-0.3721,0.0161,0.0478},
       {2.6898,-1.2845,0.7743,-1.7728,-0.2791,0.0121,0.0359},
       {2.7175,-1.2923,0.7747,-1.7718,-0.186,0.0081,0.024},
       {2.7453,-1.2974,0.7749,-1.7711,-0.093,0.004,0.012},
       {2.773,-1.3,0.775,-1.7708,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}