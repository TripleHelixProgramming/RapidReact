from casadi import *

class swerve_drive:
    def __init__(self, wheelbase_x, wheelbase_y, intake_distance):
        self.wheelbase_x = wheelbase_x
        self.wheelbase_y = wheelbase_y
        self.intake_distance = intake_distance