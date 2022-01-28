from re import A
from telnetlib import VT3270REGIME
from casadi import *
import pylab as plt

from trajectory_io import *
from drive.swerve_drive import swerve_drive
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
      # Ts = []
      # dts = []
      # for n in range(len(waypoints) - 1):
      #    T = self.opti.variable()
      #    dt = T / self.N
      #    Ts.append(T)
      #    dts.append(dt)

      #    self.opti.subject_to(T >= 0)
      #    self.opti.set_initial(T, 5)

      # total_time = sum(Ts)
      # self.opti.minimize(total_time)

      T = self.opti.variable()
      self.dt = T / self.N
      self.opti.minimize(T)
      self.opti.subject_to(T>=0)

      # Initialize variables
      self.X = self.opti.variable(3, self.N+1)
      self.x = self.X[0,:]
      self.y = self.X[1,:]
      self.theta = self.X[2,:]
      self.U = self.opti.variable(3, self.N)
      self.vx = self.U[0,:]
      self.vy = self.U[1,:]
      self.omega = self.U[2,:]
      

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

      for k in range(self.N):
               x_next = self.X[:, k] + dynamics(self.X[:, k], self.U[:, k]) * self.dt
               self.opti.subject_to(self.X[:, k + 1] == x_next)

      # Set initial guess
      x_init, y_init, theta_init = trajectory_util.generate_initial_trajectory(
         waypoints, self.N+1
      )
      self.set_initial_guess(x_init,y_init,theta_init)

      self.drive.add_kinematics_constraint(self.opti, self.vx, self.vy, self.omega, 25)
      self.add_boundry_constraint()
      self.add_waypoint_constraint(waypoints)

      self.opti.solver("ipopt")
      sol = self.opti.solve()

      print(sol.value(T))
      trajectory_util.draw_trajectory(sol.value(self.x),sol.value(self.y),sol.value(self.theta),self.drive,"trajectory")
      # trajectory_util.draw_trajectory(x_init, y_init, theta_init, self.drive, "initial")

      plt.show()

   def add_boundry_constraint(self):
      self.opti.subject_to(self.vx[0] == 0)
      self.opti.subject_to(self.vy[0] == 0)
      self.opti.subject_to(self.omega[0] == 0)
      self.opti.subject_to(self.vx[-1] == 0)
      self.opti.subject_to(self.vy[-1] == 0)
      self.opti.subject_to(self.omega[-1] == 0)

   def add_waypoint_constraint(self, waypoints):
      for k in range(len(waypoints)):
         index = k * self.N
         self.opti.subject_to(self.x[index] == waypoints[k][0])
         self.opti.subject_to(self.y[index] == waypoints[k][1])
         self.opti.subject_to(self.theta[index] == waypoints[k][2])
   
   def set_initial_guess(self, x, y, theta):
      self.opti.set_initial(self.x, x)
      self.opti.set_initial(self.y, y)
      self.opti.set_initial(self.theta, theta)