from math import *
import trajectory_util

def export_trajectory(x, y, theta, old_dt, T, name):
        # data = []
        # x = sol.value(self.x)
        # y = sol.value(self.y)
        # vl = sol.value(self.vl)
        # vr = sol.value(self.vr)
        # al = sol.value(self.al)
        # ar = sol.value(self.ar)
        # theta = sol.value(self.theta)
        # for k in range(self.N+1):
        #     data.append({
        #         't':round(k*sol.value(self.dt),4),
        #         'x':round(x[k],4),
        #         'y':round(y[k],4),
        #         'theta':round(theta[k],4),
        #         'Vl': 0.0 if k>=self.N else round(vl[k] * self.kv + al[k] * self.ka,4),
        #         'Vr': 0.0 if k>=self.N else round(vr[k] * self.kv + ar[k] * self.ka,4),
        #         'vl':round(vl[k],4),
        #         'vr':round(vr[k],4),
        #         'al':0.0 if k>=self.N else round(al[k],4),
        #         'ar':0.0 if k>=self.N else round(ar[k],4),
        #     })
        # with open('Trajectory.json', 'w') as outfile:
        #     json.dump(data, outfile, indent=4)
        xs, ys, thetas = [], [], []
        new_dt = 0.02
        time = 0
        while time < T:
            time = min(T, time + new_dt)
            index = min(int(floor(time / old_dt)),99)
            percent = (time % new_dt) / new_dt
            xs.append(round((x[index + 1] - x[index]) * percent + x[index], 4))
            ys.append(round((y[index + 1] - y[index]) * percent + y[index], 4))
            thetas.append(round((theta[index + 1] - theta[index]) * percent + theta[index], 4))

        f = open(name + ".java", "w")
        f.write("package frc.paths;\n")
        f.write("\n")
        f.write("public class " + name + " extends Path {\n")
        f.write("   private final static double[][] points = {\n")
        for j in range(len(xs)):
            f.write("       {" + str(xs[j]) + "," + str(ys[j]) + "," + str(thetas[j]) + "},\n")
        f.write("   };\n")
        f.write("   public double[][] getPath() {\n")
        f.write("       return points;\n")
        f.write("   }\n")
        f.write("}\n")
        f.close()

        return xs, ys, thetas