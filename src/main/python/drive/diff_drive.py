from math import hypot
from casadi import *

class swerve_drive:
    def __init__(self, wheelbase, mass, moi, length, width):
        self.wheelbase = wheelbase
        self.mass = mass
        self.moi = moi
        self.length = length
        self.width = width

    def add_kinematics_constraint(self, solver, theta, vx, vy, omega, ax, ay, alpha, N, f, v):
        for k in range(N):
            xc = cos(theta)
            yc = sin(theta)

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