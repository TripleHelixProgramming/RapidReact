from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, mass, length, width):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.mass = mass
        self.length = length
        self.width = width

    def add_kinematics_constraint(self, solver, vx, vy, omega, v2):
      A = vx - omega
      B = vx + omega
      C = vy - omega
      D = vy + omega

      solver.subject_to(solver.bounded(0, B * B + C * C, v2))
      solver.subject_to(solver.bounded(0, B * B + D * D, v2))
      solver.subject_to(solver.bounded(0, A * A + D * D, v2))
      solver.subject_to(solver.bounded(0, A * A + C * C, v2))