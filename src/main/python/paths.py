from trajectory_generator import trajectory_generator
from drive.swerve_drive import swerve_drive
import math

def main():
    drive = swerve_drive(2,1,1,1,1,1)
    generator = trajectory_generator(drive)

    generator.generate([
                        [5,2,0],
                        [7,2,math.pi]],
                        # [10,2,-1]],
                        "gogogogadget")
                        # [, 2, -1]])

main()