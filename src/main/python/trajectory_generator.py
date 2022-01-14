from tracemalloc import start
from casadi import *
import pylab as plt

from trajectory_io import *
from swerve_drive import swerve_drive
import trajectory_util

class trajectory_generator:
   def __init__(self, drive):
      self.drive = drive
   
   def generate(self, start_pose, end_pose, balls):
      N = 100 # number of control intervals

      opti = Opti()

      T = opti.variable()
      dt = T/N

      opti.minimize(T) # minimizing time is the trajectory planner's objective

      self.drive.initialize_drive(N)
      self.drive.add_dynamics_constraint(dt)
      self.drive.add_boundry_constraint(start_pose, end_pose)
      self.drive.add_speed_constraint(8,8)
      # self.drive.add_voltage_constraint(10)

      opti.subject_to(T>=0) # Time must be positive

      x_init, y_init, theta_init = trajectory_util.load_init_json(
         "barrel_racing.json", start_pose[2], end_pose[2], N+1
      )

      self.drive.set_initial_guess(x_init,y_init,theta_init)

      opti.set_initial(T, 10)

      opti.solver("ipopt")
      self.drive.add_accel_constraint(8)
      sol = opti.solve()

      # self.drive.export_trajectory(sol, "Traj")
      print(sol.value(T))
      # util.draw_trajectory(sol.value(self.drive.x),sol.value(self.drive.y),sol.value(self.drive.theta),obstacles,drive,"trajectory")
      # util.draw_trajectory(x_init,y_init,theta_init,obstacles,drive,"trajectory")

      # plt.show()