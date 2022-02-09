package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class OnePointEightMetersForward extends Path {
   private final static double[][] points = {
       {0,0.0,0.0,0.0,-0.0,-0.0,0.0},
       {0.0154,-0.0,-0.0,0.0,-0.0259,-0.0,-0.0039},
       {0.0309,-0.0004,-0.0,-0.0001,-0.0518,-0.0,-0.0078},
       {0.0463,-0.0012,-0.0,-0.0002,-0.0777,-0.0,-0.0117},
       {0.0617,-0.0024,-0.0,-0.0004,-0.1037,-0.0,-0.0156},
       {0.0772,-0.004,-0.0,-0.0006,-0.1296,-0.0,-0.0195},
       {0.0926,-0.006,-0.0,-0.0009,-0.1555,-0.0,-0.0233},
       {0.1081,-0.0084,-0.0,-0.0013,-0.1814,-0.0,-0.0272},
       {0.1235,-0.0112,-0.0,-0.0017,-0.2073,-0.0,-0.0311},
       {0.1389,-0.0144,-0.0,-0.0022,-0.2332,-0.0,-0.035},
       {0.1544,-0.018,-0.0,-0.0027,-0.2591,-0.0,-0.0389},
       {0.1698,-0.022,-0.0,-0.0033,-0.285,-0.0,-0.0428},
       {0.1852,-0.0264,-0.0,-0.004,-0.311,-0.0,-0.0467},
       {0.2007,-0.0312,-0.0,-0.0047,-0.3369,-0.0,-0.0506},
       {0.2161,-0.0364,-0.0,-0.0055,-0.3628,-0.0,-0.0545},
       {0.2315,-0.042,-0.0,-0.0063,-0.3887,-0.0,-0.0584},
       {0.247,-0.048,-0.0,-0.0072,-0.4146,-0.0,-0.0623},
       {0.2624,-0.0544,-0.0,-0.0082,-0.4405,-0.0,-0.0662},
       {0.2778,-0.0612,-0.0,-0.0092,-0.4664,-0.0,-0.07},
       {0.2933,-0.0684,-0.0,-0.0103,-0.4924,-0.0,-0.0739},
       {0.3087,-0.076,-0.0,-0.0114,-0.5183,-0.0,-0.0778},
       {0.3242,-0.084,-0.0,-0.0126,-0.5442,-0.0,-0.0817},
       {0.3396,-0.0924,-0.0,-0.0139,-0.5701,-0.0,-0.0856},
       {0.355,-0.1012,-0.0,-0.0152,-0.596,-0.0,-0.0895},
       {0.3705,-0.1104,-0.0,-0.0166,-0.6219,-0.0,-0.0934},
       {0.3859,-0.12,-0.0,-0.018,-0.6478,-0.0,-0.0973},
       {0.4013,-0.13,-0.0,-0.0195,-0.6737,-0.0,-0.1012},
       {0.4168,-0.1404,-0.0,-0.0211,-0.6997,-0.0,-0.1051},
       {0.4322,-0.1512,-0.0,-0.0227,-0.7256,-0.0,-0.109},
       {0.4476,-0.1624,-0.0,-0.0244,-0.7515,-0.0,-0.1128},
       {0.4631,-0.174,-0.0,-0.0261,-0.7774,-0.0,-0.1167},
       {0.4785,-0.186,-0.0,-0.0279,-0.8033,-0.0,-0.1206},
       {0.494,-0.1984,-0.0,-0.0298,-0.8292,-0.0,-0.1245},
       {0.5094,-0.2112,-0.0,-0.0317,-0.8551,-0.0,-0.1284},
       {0.5248,-0.2244,-0.0,-0.0337,-0.8811,-0.0,-0.1323},
       {0.5403,-0.238,-0.0,-0.0357,-0.907,-0.0,-0.1362},
       {0.5557,-0.252,-0.0,-0.0378,-0.9329,-0.0,-0.1401},
       {0.5711,-0.2664,-0.0,-0.04,-0.9588,-0.0,-0.144},
       {0.5866,-0.2812,-0.0,-0.0422,-0.9847,-0.0,-0.1479},
       {0.602,-0.2964,-0.0,-0.0445,-1.0106,-0.0,-0.1517},
       {0.6174,-0.312,-0.0,-0.0469,-1.0365,-0.0,-0.1556},
       {0.6329,-0.328,-0.0,-0.0493,-1.0625,-0.0,-0.1595},
       {0.6483,-0.3444,-0.0,-0.0517,-1.0884,-0.0,-0.1634},
       {0.6637,-0.3612,-0.0,-0.0542,-1.1143,-0.0,-0.1673},
       {0.6792,-0.3784,-0.0,-0.0568,-1.1402,-0.0,-0.1712},
       {0.6946,-0.396,-0.0,-0.0595,-1.1661,-0.0,-0.1751},
       {0.7101,-0.414,-0.0,-0.0622,-1.192,-0.0,-0.1789},
       {0.7255,-0.4324,-0.0,-0.0649,-1.2179,-0.0,-0.1828},
       {0.7409,-0.4512,-0.0,-0.0677,-1.2438,-0.0,-0.1867},
       {0.7564,-0.4704,-0.0,-0.0706,-1.2698,-0.0,-0.1906},
       {0.7718,-0.49,-0.0,-0.0736,-1.2957,-0.0,-0.1944},
       {0.7872,-0.51,-0.0,-0.0766,-1.2698,0.0,-0.1904},
       {0.8027,-0.5296,-0.0,-0.0795,-1.2438,0.0,-0.1865},
       {0.8181,-0.5488,-0.0,-0.0824,-1.2179,0.0,-0.1826},
       {0.8335,-0.5676,-0.0,-0.0852,-1.192,0.0,-0.1787},
       {0.849,-0.586,-0.0,-0.088,-1.1661,0.0,-0.1748},
       {0.8644,-0.604,-0.0,-0.0907,-1.1402,0.0,-0.1709},
       {0.8799,-0.6216,-0.0,-0.0933,-1.1143,0.0,-0.167},
       {0.8953,-0.6388,-0.0,-0.0959,-1.0884,0.0,-0.1631},
       {0.9107,-0.6556,-0.0,-0.0984,-1.0625,0.0,-0.1592},
       {0.9262,-0.672,-0.0,-0.1009,-1.0365,0.0,-0.1553},
       {0.9416,-0.688,-0.0,-0.1033,-1.0106,0.0,-0.1515},
       {0.957,-0.7036,-0.0,-0.1056,-0.9847,0.0,-0.1476},
       {0.9725,-0.7188,-0.0,-0.1079,-0.9588,0.0,-0.1437},
       {0.9879,-0.7336,-0.0,-0.1101,-0.9329,0.0,-0.1398},
       {1.0033,-0.748,-0.0,-0.1122,-0.907,0.0,-0.1359},
       {1.0188,-0.762,-0.0,-0.1143,-0.8811,0.0,-0.132},
       {1.0342,-0.7756,-0.0,-0.1164,-0.8551,0.0,-0.1281},
       {1.0496,-0.7888,-0.0,-0.1184,-0.8292,0.0,-0.1242},
       {1.0651,-0.8016,-0.0,-0.1203,-0.8033,0.0,-0.1204},
       {1.0805,-0.814,-0.0,-0.1221,-0.7774,0.0,-0.1165},
       {1.096,-0.826,-0.0,-0.1239,-0.7515,0.0,-0.1126},
       {1.1114,-0.8376,-0.0,-0.1257,-0.7256,0.0,-0.1087},
       {1.1268,-0.8488,-0.0,-0.1274,-0.6997,0.0,-0.1048},
       {1.1423,-0.8596,-0.0,-0.129,-0.6738,0.0,-0.1009},
       {1.1577,-0.87,-0.0,-0.1305,-0.6478,0.0,-0.097},
       {1.1731,-0.88,-0.0,-0.132,-0.6219,0.0,-0.0932},
       {1.1886,-0.8896,-0.0,-0.1335,-0.596,0.0,-0.0893},
       {1.204,-0.8988,-0.0,-0.1348,-0.5701,0.0,-0.0854},
       {1.2194,-0.9076,-0.0,-0.1362,-0.5442,0.0,-0.0815},
       {1.2349,-0.916,-0.0,-0.1374,-0.5183,0.0,-0.0776},
       {1.2503,-0.924,-0.0,-0.1386,-0.4924,0.0,-0.0738},
       {1.2658,-0.9316,-0.0,-0.1398,-0.4664,0.0,-0.0699},
       {1.2812,-0.9388,-0.0,-0.1408,-0.4405,0.0,-0.066},
       {1.2966,-0.9456,-0.0,-0.1419,-0.4146,0.0,-0.0621},
       {1.3121,-0.952,-0.0,-0.1428,-0.3887,0.0,-0.0582},
       {1.3275,-0.958,-0.0,-0.1437,-0.3628,0.0,-0.0543},
       {1.3429,-0.9636,-0.0,-0.1445,-0.3369,0.0,-0.0505},
       {1.3584,-0.9688,-0.0,-0.1453,-0.311,0.0,-0.0466},
       {1.3738,-0.9736,-0.0,-0.146,-0.285,0.0,-0.0427},
       {1.3892,-0.978,-0.0,-0.1467,-0.2591,0.0,-0.0388},
       {1.4047,-0.982,-0.0,-0.1473,-0.2332,0.0,-0.0349},
       {1.4201,-0.9856,-0.0,-0.1478,-0.2073,0.0,-0.0311},
       {1.4355,-0.9888,-0.0,-0.1483,-0.1814,0.0,-0.0272},
       {1.451,-0.9916,-0.0,-0.1487,-0.1555,0.0,-0.0233},
       {1.4664,-0.994,-0.0,-0.1491,-0.1296,0.0,-0.0194},
       {1.4819,-0.996,-0.0,-0.1494,-0.1037,0.0,-0.0155},
       {1.4973,-0.9976,-0.0,-0.1496,-0.0777,0.0,-0.0116},
       {1.5127,-0.9988,-0.0,-0.1498,-0.0518,0.0,-0.0078},
       {1.5282,-0.9996,-0.0,-0.1499,-0.0259,0.0,-0.0039},
       {1.5436,-1.0,0.0,-0.15,0.0,0.0,0.0},
   };
   public SwerveTrajectory getPath() {
       return new SwerveTrajectory(points);
   }
}
