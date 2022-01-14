from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, intake_distance):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.intake_distance = intake_distance
    
    def set_solver(self, solver):
        self.solver = solver
    
    def initialize(self, N):
        self.N = N
        self.X = self.solver.variable(3, N)
        self.x = self.X[0,:]
        self.y = self.X[1,:]
        self.theta = self.X[2,:]
        self.U = self.solver.variable(3, N)
        self.x_dot = self.U[0,:]
        self.y_dot = self.U[1,:]
        self.theta_dot = self.U[2,:]

    def set_initial_guess(self, x, y, theta):
        self.solver.set_initial(self.x, x)
        self.solver.set_initial(self.y, y)
        self.solver.set_initial(self.theta, theta)

    def add_dynamics_constraint(self, dt):
        self.xdot = lambda u: vertcat(
            u[0],
            u[1],
            u[2]
        )
        for k in range(self.N-1):
            self.solver.subject_to(self.X[:,k+1]-self.X[:,k]==0.5*(self.xdot(self.U[:,k+1])+self.xdot(self.U[:,k]))*dt)
    
    def add_boundry_constraint(self, start_pose, end_pose):
        self.solver.subject_to(self.x[0] == start_pose[0])
        self.solver.subject_to(self.y[0] == start_pose[1])
        self.solver.subject_to(self.theta[0] == start_pose[2])
        self.solver.subject_to(self.x[-1] == end_pose[0])
        self.solver.subject_to(self.y[-1] == end_pose[1])
        self.solver.subject_to(self.theta[-1] == end_pose[2])
        self.solver.subject_to(self.x_dot[0] == 0)
        self.solver.subject_to(self.y_dot[0] == 0)
        self.solver.subject_to(self.theta_dot[0] == 0)
        self.solver.subject_to(self.x_dot[-1] == 0)
        self.solver.subject_to(self.y_dot[-1] == 0)
        self.solver.subject_to(self.theta_dot[-1] == 0)
    
    def add_speed_constraint(self, velocity, acceleration):
        self.solver.subject_to(self.solver.bounded(-velocity,self.x_dot,velocity))
        self.solver.subject_to(self.solver.bounded(-velocity,self.y_dot,velocity))
        self.solver.subject_to(self.solver.bounded(-velocity,self.theta_dot,velocity))