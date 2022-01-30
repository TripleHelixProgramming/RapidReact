from math import hypot
from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, mass, length, width):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.mass = mass
        self.length = length
        self.width = width

    def solve_module_positions(self, k, theta):
        modules, thetas = [], []
        d = hypot(self.wheelbase_x, self.wheelbase_y)
        for a in [1, -1]:
            for b in [1, -1]:        
                thetas.append(atan2(self.wheelbase_y*a, self.wheelbase_x*b))
        for module_theta in thetas:
            modules.append([d*cos(module_theta+theta[k]), d*sin(module_theta+theta[k])])
        return modules

    def add_kinematics_constraint(self, solver, theta, vx, vy, omega, ax, ay, alpha, f, v):
        for k in range(100):
            modules = self.solve_module_positions(k, theta)

            for module in modules:
                m_vx = vx[k] + module[0] * omega[k]
                m_vy = vy[k] + module[1] * omega[k]
                solver.subject_to(m_vx * m_vx + m_vy * m_vy < v*v)

            Fx = solver.variable(4)
            Fy = solver.variable(4)

            T = []
            for j in range(4):
                    T.append(modules[j][1] * Fx[j] - modules[j][0] * Fy[j])
                    solver.subject_to(Fx[j] * Fx[j] + Fy[j] * Fy[j] < f*f)

            solver.subject_to(ax[k] * self.mass == Fx[0] + Fx[1] + Fx[2] + Fx[3])
            solver.subject_to(ay[k] * self.mass == Fy[0] + Fy[1] + Fy[2] + Fy[3])
            solver.subject_to(alpha[k] == sum(T))