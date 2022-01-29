from math import hypot
import numpy as np
from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, mass, length, width):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.mass = mass
        self.length = length
        self.width = width

    def solve_module_positions(self, theta):
        modules, thetas = [], []
        d = hypot(self.wheelbase_x, self.wheelbase_y)
        for a in [1, -1]:
            for b in [1, -1]:        
                thetas.append(atan2(self.wheelbase_y*a, self.wheelbase_x*b))
        for module_theta in thetas:
            modules.append([d*cos(module_theta+theta), d*sin(module_theta+theta)])
        return modules

    def add_kinematics_constraint(self, solver, theta, vx, vy, omega, v2):
        modules = self.solve_module_positions(theta)

        for module in modules:
            m_vx = vx + module[0] * omega
            m_vy = vy + module[1] * omega
            solver.subject_to(solver.bounded(0, m_vx * m_vx + m_vy * m_vy, v2))