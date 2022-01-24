from re import A
from telnetlib import VT3270REGIME
from casadi import *
import pylab as plt

from trajectory_io import *
from swerve_drive import swerve_drive
import trajectory_util

class trajectory_generator:
   def __init__(self, drive):
      self.drive = drive
   
   def generate(self, waypoints):
      self.N = 100 # number of control intervals
      # Ns = []
      # for k in range(len(waypoints) - 1):


      self.opti = Opti()

      # Minimize time
      Ts = []
      dts = []
      for n in range(len(waypoints) - 1):
         T = self.opti.variable()
         dt = T / self.N
         Ts.append(T)
         dts.append(dt)

         self.opti.subject_to(T >= 0)
         self.opti.set_initial(T, 5)

      total_time = sum(Ts)
      self.opti.minimize(total_time)

      # Initialize variables
      self.X = self.opti.variable(3, self.N)
      self.x = self.X[0,:]
      self.y = self.X[1,:]
      self.theta = self.X[2,:]
      self.U = self.opti.variable(3, self.N)
      self.x_dot = self.U[0,:]
      self.y_dot = self.U[1,:]
      self.theta_dot = self.U[2,:]

      # Add dynamics constraint
      dynamics = lambda x, u: vertcat(
         u[0],
         u[1],
         u[2]
      )

      # start_n = 0
      # for n, dt in zip(len(waypoints) - 1, dts):
      #    end_n = start_n + n
      #    for k in range(start_n, end_n):
      #          x_next = self.X[:, k] + dynamics(self.X[:, k], self.U[:, k]) * dt
      #          self.opti.subject_to(self.X[:, k + 1] == x_next)
      #    start_n = end_n

      # Set initial guess
      x_init, y_init, theta_init = trajectory_util.generate_initial_trajectory(
         waypoints, self.N
      )
      self.set_initial_guess(x_init,y_init,theta_init)

      self.add_kinematics_constraint(25)
      self.add_boundry_constraint()

      self.opti.solver("ipopt")
      # sol = self.opti.solve()

      # print(sol.value(T))
      # util.draw_trajectory(sol.value(self.drive.x),sol.value(self.drive.y),sol.value(self.drive.theta),obstacles,drive,"trajectory")
      trajectory_util.draw_trajectory(x_init, y_init, theta_init, self.drive, "initial")

      plt.show()

   def add_boundry_constraint(self):
      self.opti.subject_to(self.x_dot[0] == 0)
      self.opti.subject_to(self.y_dot[0] == 0)
      self.opti.subject_to(self.theta_dot[0] == 0)
      self.opti.subject_to(self.x_dot[-1] == 0)
      self.opti.subject_to(self.y_dot[-1] == 0)
      self.opti.subject_to(self.theta_dot[-1] == 0)

   def add_waypoint_constraint(self, waypoints):
      for k in range(len(waypoints)):
         index = k * self.N
         self.x[index] = waypoints[k][0]
         self.y[index] = waypoints[k][1]
         self.theta[index] = waypoints[k][2]
   
   def set_initial_guess(self, x, y, theta):
      self.opti.set_initial(self.x, x)
      self.opti.set_initial(self.y, y)
      self.opti.set_initial(self.theta, theta)
   
   def add_kinematics_constraint(self, v2):
      A = self.x_dot - self.theta_dot
      B = self.x_dot + self.theta_dot
      C = self.y_dot - self.theta_dot
      D = self.y_dot + self.theta_dot

      self.opti.subject_to(self.opti.bounded(0, B * B + C * C, v2))
      self.opti.subject_to(self.opti.bounded(0, B * B + D * D, v2))
      self.opti.subject_to(self.opti.bounded(0, A * A + D * D, v2))
      self.opti.subject_to(self.opti.bounded(0, A * A + C * C, v2))