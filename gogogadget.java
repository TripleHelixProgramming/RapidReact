package frc.paths;

public class gogogadget extends Path {
   private final static double[][] points = {
       {5.0,2.0,-0.0},
       {5.0,2.0,0.0},
       {5.0029,1.9999,0.0155},
       {5.0029,1.9999,0.0155},
       {5.0029,1.9999,0.0155},
       {5.006,1.9998,0.0308},
       {5.0101,1.9997,0.0512},
       {5.0154,1.9996,0.0765},
       {5.03,1.9993,0.1412},
       {5.03,1.9993,0.1412},
       {5.0395,1.9992,0.1801},
       {5.0508,1.999,0.2229},
       {5.0642,1.9986,0.2686},
       {5.0799,1.9979,0.3162},
       {5.0799,1.9979,0.3162},
       {5.0799,1.9979,0.3162},
       {5.098,1.9967,0.3643},
       {5.1186,1.9948,0.4114},
       {5.1416,1.9924,0.4575},
       {5.1416,1.9924,0.4575},
       {5.1669,1.9893,0.5025},
       {5.1947,1.9856,0.5465},
       {5.2248,1.9812,0.5896},
       {5.2573,1.9762,0.6317},
       {5.2573,1.9762,0.6317},
       {5.2921,1.9704,0.6729},
       {5.3294,1.964,0.7132},
       {5.369,1.9568,0.7527},
       {5.4109,1.949,0.7914},
       {5.4109,1.949,0.7914},
       {5.4553,1.9404,0.8294},
       {5.502,1.9311,0.8666},
       {5.5511,1.9211,0.9031},
       {5.5511,1.9211,0.9031},
       {5.6026,1.9105,0.9389},
       {5.6565,1.8991,0.974},
       {5.7127,1.8871,1.0084},
       {5.7714,1.8745,1.0422},
       {5.7714,1.8745,1.0422},
       {5.8325,1.8612,1.0753},
       {5.8959,1.8474,1.1078},
       {5.9618,1.8331,1.1397},
       {6.0302,1.8182,1.1708},
       {6.0302,1.8182,1.1708},
       {6.101,1.803,1.2014},
       {6.1742,1.7875,1.2312},
       {6.2499,1.7716,1.2604},
       {6.3281,1.7557,1.2888},
       {6.3281,1.7557,1.2888},
       {6.4087,1.7397,1.3164},
       {6.4918,1.7238,1.3433},
       {6.5773,1.7082,1.3694},
       {6.6652,1.6931,1.3946},
       {6.6652,1.6931,1.3946},
       {6.7554,1.6788,1.4188},
       {6.8479,1.6655,1.4422},
       {6.9424,1.6536,1.4646},
       {7.0388,1.6433,1.4862},
       {7.0388,1.6433,1.4862},
       {7.1367,1.635,1.5069},
       {7.2362,1.6286,1.5268},
       {7.3373,1.6242,1.5459},
       {7.3373,1.6242,1.5459},
       {7.44,1.6216,1.5639},
       {7.5444,1.6209,1.581},
       {7.6471,1.622,1.599},
       {7.7482,1.625,1.618},
       {7.7482,1.625,1.618},
       {7.8476,1.63,1.638},
       {7.9455,1.6367,1.6588},
       {8.0417,1.6453,1.6806},
       {8.1361,1.6557,1.7032},
       {8.1361,1.6557,1.7032},
       {8.2286,1.6677,1.7268},
       {8.3191,1.681,1.7512},
       {8.4072,1.6952,1.7765},
       {8.4931,1.7102,1.8027},
       {8.4931,1.7102,1.8027},
       {8.5766,1.7258,1.8298},
       {8.6576,1.7417,1.8577},
       {8.7361,1.7577,1.8863},
       {8.8122,1.7737,1.9157},
       {8.8122,1.7737,1.9157},
       {8.8859,1.7896,1.9458},
       {8.9571,1.8053,1.9766},
       {9.0258,1.8207,2.008},
       {9.0921,1.8356,2.0402},
       {9.0921,1.8356,2.0402},
       {9.1559,1.8501,2.0729},
       {9.2173,1.8641,2.1063},
       {9.2763,1.8775,2.1403},
       {9.2763,1.8775,2.1403},
       {9.3329,1.8903,2.175},
       {9.3871,1.9024,2.2103},
       {9.4389,1.9139,2.2463},
       {9.4884,1.9247,2.2829},
       {9.4884,1.9247,2.2829},
       {9.5354,1.9347,2.3203},
       {9.5801,1.944,2.3583},
       {9.6224,1.9526,2.3971},
       {9.6624,1.9605,2.4367},
       {9.6624,1.9605,2.4367},
       {9.7,1.9676,2.4771},
       {9.7353,1.9739,2.5183},
       {9.7682,1.9796,2.5603},
       {9.7987,1.9844,2.6033},
       {9.7987,1.9844,2.6033},
       {9.8269,1.9886,2.6471},
       {9.8528,1.992,2.692},
       {9.8762,1.9948,2.7378},
       {9.8973,1.9969,2.7848},
       {9.8973,1.9969,2.7848},
       {9.916,1.9982,2.8319},
       {9.9323,1.9989,2.8784},
       {9.9462,1.9992,2.923},
       {9.9581,1.9993,2.9648},
       {9.9581,1.9993,2.9648},
       {9.9681,1.9994,3.0029},
       {9.9766,1.9995,3.0369},
       {9.9836,1.9996,3.0664},
       {9.9836,1.9996,3.0664},
       {9.9892,1.9997,3.0912},
       {9.9936,1.9998,3.1112},
       {9.9969,1.9999,3.1264},
       {9.999,2.0,3.1365},
       {9.999,2.0,3.1365},
       {9.9991,2.0,3.1369},
   };
   public double[][] getPath() {
       return points;
   }
}
