package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class FiveBallPartThree extends Path {
   private final static double[][] points = {
       {0,1.65,-0.7,-2.5916,-0.0,0.0,0.0},
       {0.0271,1.65,-0.7,-2.5916,0.0665,0.0148,0.0022},
       {0.0541,1.6518,-0.6996,-2.5915,0.1331,0.0296,0.0045},
       {0.0812,1.6554,-0.6988,-2.5914,0.1996,0.0444,0.0067},
       {0.1082,1.6608,-0.6976,-2.5912,0.2661,0.0591,0.0089},
       {0.1353,1.668,-0.696,-2.591,0.3327,0.0739,0.0112},
       {0.1623,1.677,-0.694,-2.5907,0.3992,0.0887,0.0134},
       {0.1894,1.6878,-0.6916,-2.5903,0.4657,0.1035,0.0156},
       {0.2164,1.7004,-0.6888,-2.5899,0.5323,0.1183,0.0179},
       {0.2435,1.7148,-0.6856,-2.5894,0.5988,0.1331,0.0201},
       {0.2705,1.731,-0.682,-2.5889,0.6653,0.1478,0.0223},
       {0.2976,1.749,-0.678,-2.5883,0.7319,0.1626,0.0245},
       {0.3247,1.7688,-0.6736,-2.5876,0.7984,0.1774,0.0268},
       {0.3517,1.7904,-0.6688,-2.5869,0.8649,0.1922,0.029},
       {0.3788,1.8138,-0.6636,-2.5861,0.9314,0.207,0.0312},
       {0.4058,1.839,-0.658,-2.5853,0.998,0.2218,0.0335},
       {0.4329,1.866,-0.652,-2.5843,1.0645,0.2366,0.0357},
       {0.4599,1.8948,-0.6456,-2.5834,1.131,0.2513,0.0379},
       {0.487,1.9254,-0.6388,-2.5824,1.1976,0.2661,0.0402},
       {0.514,1.9578,-0.6316,-2.5813,1.2641,0.2809,0.0424},
       {0.5411,1.992,-0.624,-2.5801,1.3306,0.2957,0.0446},
       {0.5681,2.028,-0.616,-2.5789,1.3972,0.3105,0.0468},
       {0.5952,2.0658,-0.6076,-2.5776,1.4637,0.3253,0.0491},
       {0.6223,2.1054,-0.5988,-2.5763,1.5302,0.3401,0.0513},
       {0.6493,2.1468,-0.5896,-2.5749,1.5968,0.3548,0.0535},
       {0.6764,2.19,-0.58,-2.5735,1.6633,0.3696,0.0558},
       {0.7034,2.235,-0.57,-2.572,1.7298,0.3844,0.058},
       {0.7305,2.2818,-0.5596,-2.5704,1.7964,0.3992,0.0602},
       {0.7575,2.3304,-0.5488,-2.5688,1.8629,0.414,0.0624},
       {0.7846,2.3808,-0.5376,-2.5671,1.9294,0.4288,0.0647},
       {0.8116,2.433,-0.526,-2.5653,1.996,0.4435,0.0669},
       {0.8387,2.487,-0.514,-2.5635,2.0625,0.4583,0.0691},
       {0.8657,2.5428,-0.5016,-2.5617,2.129,0.4731,0.0713},
       {0.8928,2.6004,-0.4888,-2.5597,2.1956,0.4879,0.0736},
       {0.9199,2.6598,-0.4756,-2.5577,2.2621,0.5027,0.0758},
       {0.9469,2.721,-0.462,-2.5557,2.3286,0.5175,0.078},
       {0.974,2.784,-0.448,-2.5536,2.3952,0.5323,0.0802},
       {1.001,2.8488,-0.4336,-2.5514,2.4617,0.547,0.0824},
       {1.0281,2.9154,-0.4188,-2.5492,2.5282,0.5618,0.0847},
       {1.0551,2.9838,-0.4036,-2.5469,2.5947,0.5766,0.0869},
       {1.0822,3.054,-0.388,-2.5445,2.6613,0.5914,0.0891},
       {1.1092,3.126,-0.372,-2.5421,2.7278,0.6062,0.0913},
       {1.1363,3.1998,-0.3556,-2.5397,2.7943,0.621,0.0935},
       {1.1634,3.2754,-0.3388,-2.5371,2.8609,0.6357,0.0957},
       {1.1904,3.3528,-0.3216,-2.5345,2.9274,0.6505,0.0979},
       {1.2175,3.432,-0.304,-2.5319,2.9939,0.6653,0.1002},
       {1.2445,3.513,-0.286,-2.5292,3.0605,0.6801,0.1024},
       {1.2716,3.5958,-0.2676,-2.5264,3.127,0.6949,0.1045},
       {1.2986,3.6804,-0.2488,-2.5236,3.1935,0.7097,0.1067},
       {1.3257,3.7668,-0.2296,-2.5207,3.2601,0.7245,0.1089},
       {1.3527,3.855,-0.21,-2.5177,3.3266,0.7392,0.1109},
       {1.3798,3.945,-0.19,-2.5147,3.2601,0.7245,0.1085},
       {1.4068,4.0332,-0.1704,-2.5118,3.1935,0.7097,0.1062},
       {1.4339,4.1196,-0.1512,-2.5089,3.127,0.6949,0.1039},
       {1.461,4.2042,-0.1324,-2.5061,3.0605,0.6801,0.1017},
       {1.488,4.287,-0.114,-2.5034,2.9939,0.6653,0.0995},
       {1.5151,4.368,-0.096,-2.5007,2.9274,0.6505,0.0972},
       {1.5421,4.4472,-0.0784,-2.4981,2.8609,0.6358,0.095},
       {1.5692,4.5246,-0.0612,-2.4955,2.7943,0.621,0.0928},
       {1.5962,4.6002,-0.0444,-2.493,2.7278,0.6062,0.0905},
       {1.6233,4.674,-0.028,-2.4905,2.6613,0.5914,0.0883},
       {1.6503,4.746,-0.012,-2.4881,2.5947,0.5766,0.0861},
       {1.6774,4.8162,0.0036,-2.4858,2.5282,0.5618,0.0839},
       {1.7044,4.8846,0.0188,-2.4835,2.4617,0.547,0.0817},
       {1.7315,4.9512,0.0336,-2.4813,2.3952,0.5323,0.0795},
       {1.7586,5.016,0.048,-2.4792,2.3286,0.5175,0.0772},
       {1.7856,5.079,0.062,-2.4771,2.2621,0.5027,0.075},
       {1.8127,5.1402,0.0756,-2.4751,2.1956,0.4879,0.0728},
       {1.8397,5.1996,0.0888,-2.4731,2.129,0.4731,0.0706},
       {1.8668,5.2572,0.1016,-2.4712,2.0625,0.4583,0.0684},
       {1.8938,5.313,0.114,-2.4693,1.996,0.4435,0.0662},
       {1.9209,5.367,0.126,-2.4675,1.9294,0.4288,0.064},
       {1.9479,5.4192,0.1376,-2.4658,1.8629,0.414,0.0617},
       {1.975,5.4696,0.1488,-2.4641,1.7964,0.3992,0.0595},
       {2.002,5.5182,0.1596,-2.4625,1.7298,0.3844,0.0573},
       {2.0291,5.565,0.17,-2.461,1.6633,0.3696,0.0551},
       {2.0562,5.61,0.18,-2.4595,1.5968,0.3548,0.0529},
       {2.0832,5.6532,0.1896,-2.458,1.5302,0.3401,0.0507},
       {2.1103,5.6946,0.1988,-2.4567,1.4637,0.3253,0.0485},
       {2.1373,5.7342,0.2076,-2.4554,1.3972,0.3105,0.0463},
       {2.1644,5.772,0.216,-2.4541,1.3306,0.2957,0.0441},
       {2.1914,5.808,0.224,-2.4529,1.2641,0.2809,0.0419},
       {2.2185,5.8422,0.2316,-2.4518,1.1976,0.2661,0.0397},
       {2.2455,5.8746,0.2388,-2.4507,1.131,0.2513,0.0375},
       {2.2726,5.9052,0.2456,-2.4497,1.0645,0.2366,0.0353},
       {2.2996,5.934,0.252,-2.4487,0.998,0.2218,0.0331},
       {2.3267,5.961,0.258,-2.4479,0.9314,0.207,0.0309},
       {2.3538,5.9862,0.2636,-2.447,0.8649,0.1922,0.0286},
       {2.3808,6.0096,0.2688,-2.4462,0.7984,0.1774,0.0264},
       {2.4079,6.0312,0.2736,-2.4455,0.7319,0.1626,0.0242},
       {2.4349,6.051,0.278,-2.4449,0.6653,0.1478,0.022},
       {2.462,6.069,0.282,-2.4443,0.5988,0.1331,0.0198},
       {2.489,6.0852,0.2856,-2.4437,0.5323,0.1183,0.0176},
       {2.5161,6.0996,0.2888,-2.4433,0.4657,0.1035,0.0154},
       {2.5431,6.1122,0.2916,-2.4428,0.3992,0.0887,0.0132},
       {2.5702,6.123,0.294,-2.4425,0.3327,0.0739,0.011},
       {2.5972,6.132,0.296,-2.4422,0.2661,0.0591,0.0088},
       {2.6243,6.1392,0.2976,-2.442,0.1996,0.0444,0.0066},
       {2.6514,6.1446,0.2988,-2.4418,0.1331,0.0296,0.0044},
       {2.6784,6.1482,0.2996,-2.4417,0.0665,0.0148,0.0022},
       {2.7055,6.15,0.3,-2.4416,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
