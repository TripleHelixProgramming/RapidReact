from casadi import *
import pylab as plt

from trajectory_io import *
import trajectory_util

class trajectory_generator:
   def __init__(self, drive):
      self.drive = drive
   
   def generate(self, waypoints, name):
      self.N_per_segment = 100
      self.segments = len(waypoints) - 1
      self.N = self.N_per_segment * self.segments

      self.opti = Opti()

      # Minimize time
      Ts, dts = [], []
      for k in range(self.segments):
         T = self.opti.variable()
         dt = T / self.N_per_segment
         Ts.append(T)
         dts.append(dt)

         self.opti.subject_to(T >= 0)
         self.opti.set_initial(T, 5)
      self.opti.minimize(sum(Ts))

      # Initialize variables
      self.X = self.opti.variable(6, self.N+1)
      self.x = self.X[0,:]
      self.y = self.X[1,:]
      self.theta = self.X[2,:]
      self.vx = self.X[3,:]
      self.vy = self.X[4,:]
      self.omega = self.X[5,:]
      self.U = self.opti.variable(3, self.N)
      self.ax = self.U[0,:]
      self.ay = self.U[1,:]
      self.alpha = self.U[2,:]

      # Add dynamics constraint
      dynamics = lambda x, u: vertcat(
         x[3],
         x[4],
         x[5],
         u[0],
         u[1],
         u[2]
      )

      for k in range(self.N):
         x_next = self.X[:, k] + dynamics(self.X[:, k], self.U[:, k]) * dts[int(k / self.N_per_segment)]
         self.opti.subject_to(self.X[:, k + 1] == x_next)

      # Set initial guess
      x_init, y_init, theta_init = trajectory_util.generate_initial_trajectory(
         waypoints, self.N+1
      )
      self.opti.set_initial(self.x, x_init)
      self.opti.set_initial(self.y, y_init)
      self.opti.set_initial(self.theta, theta_init)

      # Add constraints
      self.drive.add_kinematics_constraint(self.opti, self.theta, self.vx, self.vy, self.omega, self.ax, self.ay, self.alpha, self.N)
      self.add_boundry_constraint()
      self.add_waypoint_constraint(waypoints)

      self.opti.solver("ipopt")
      sol = self.opti.solve()

      sol_dts = []
      for k in range(self.segments):
         sol_dts.append(sol.value(Ts[k] / self.N_per_segment))
      print(sum(sol_dts) * self.N_per_segment)

      xs, ys, thetas = export_trajectory(sol.value(self.x), 
                                       sol.value(self.y), 
                                       sol.value(self.theta), 
                                       sol.value(self.vx), 
                                       sol.value(self.vy), 
                                       sol.value(self.omega), 
                                       sol_dts, self.N_per_segment, name)

      trajectory_util.draw_trajectory(xs,ys,thetas,waypoints,self.drive,name)
      # trajectory_util.animate_trajectory(xs,ys,thetas,waypoints,self.drive,0.02,"trajectory")

      plt.show()

   def add_boundry_constraint(self):
      for k in [-1, 0]:
         self.opti.subject_to(self.vx[k] == 0)
         self.opti.subject_to(self.vy[k] == 0)
         self.opti.subject_to(self.omega[k] == 0)

   def add_waypoint_constraint(self, waypoints):
      for k in range(self.segments + 1):
         index = k * self.N_per_segment
         self.opti.subject_to(self.x[index] == waypoints[k][0])
         self.opti.subject_to(self.y[index] == waypoints[k][1])
         self.opti.subject_to(self.theta[index] == waypoints[k][2])