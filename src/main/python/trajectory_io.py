from math import *

def export_trajectory(x, y, theta, dts, N_per_segment, name):
        ts = [0]
        for dt in dts:
            for k in range(N_per_segment):
                ts.append(dt + ts[-1])
        xs, ys, thetas = [], [], []
        new_dt = 0.02
        time = 0
        index = 0
        # print(int(ts[-1] / 0.02))
        for k in range(int(ts[-1] / 0.02)):
            while ts[index + 1] < k * 0.02:
                index += 1
            percent = (k * 0.02 - ts[index]) / (ts[index + 1] - ts[index])
            xs.append(round((x[index + 1] - x[index]) * percent + x[index], 4))
            ys.append(round((y[index + 1] - y[index]) * percent + y[index], 4))
            thetas.append(round((theta[index + 1] - theta[index]) * percent + theta[index], 4))

        # f = open(name + ".java", "w")
        # f.write("package frc.paths;\n")
        # f.write("\n")
        # f.write("public class " + name + " extends Path {\n")
        # f.write("   private final static double[][] points = {\n")
        # for j in range(len(xs)):
        #     f.write("       {" + str(xs[j]) + "," + str(ys[j]) + "," + str(thetas[j]) + "},\n")
        # f.write("   };\n")
        # f.write("   public double[][] getPath() {\n")
        # f.write("       return points;\n")
        # f.write("   }\n")
        # f.write("}\n")
        # f.close()

        return xs, ys, thetas