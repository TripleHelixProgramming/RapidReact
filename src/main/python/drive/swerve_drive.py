from math import hypot
from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, length, width, mass, moi, omega_max, tau_max, wheel_radius):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.length = length
        self.width = width
        self.mass = mass
        self.moi = moi
        self.omega_max = omega_max
        self.tau_max = tau_max
        self.wheel_radius = wheel_radius
        self.force_max = tau_max / wheel_radius

    def solve_module_positions(self, k, theta):
        modules, thetas = [], []
        d = hypot(self.wheelbase_x, self.wheelbase_y)
        for a in [1, -1]:
            for b in [1, -1]:        
                thetas.append(atan2(self.wheelbase_y*a, self.wheelbase_x*b))
        for module_theta in thetas:
            modules.append([d*cos(module_theta+theta[k]), d*sin(module_theta+theta[k])])
        return modules

    def add_kinematics_constraint(self, solver, theta, vx, vy, omega, ax, ay, alpha, N):
        for k in range(N):
            modules = self.solve_module_positions(k, theta)

            max_v = self.omega_max * self.wheel_radius
            for module in modules:
                m_vx = vx[k] + module[0] * omega[k]
                m_vy = vy[k] + module[1] * omega[k]
                solver.subject_to(m_vx * m_vx + m_vy * m_vy < max_v * max_v)

            Fx = solver.variable(4)
            Fy = solver.variable(4)

            T = []
            for j in range(4):
                    T.append(modules[j][1] * Fx[j] - modules[j][0] * Fy[j])
                    solver.subject_to(Fx[j] * Fx[j] + Fy[j] * Fy[j] < self.force_max * self.force_max)

            solver.subject_to(ax[k] * self.mass == Fx[0] + Fx[1] + Fx[2] + Fx[3])
            solver.subject_to(ay[k] * self.mass == Fy[0] + Fy[1] + Fy[2] + Fy[3])
            solver.subject_to(alpha[k] * self.moi == sum(T))