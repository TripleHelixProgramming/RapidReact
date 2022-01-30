from cmath import pi
from swerve_drive import swerve_drive
import math

drive = swerve_drive(2,1,1,1,1)

print(drive.solve_module_positions(math.pi  * 0.5))