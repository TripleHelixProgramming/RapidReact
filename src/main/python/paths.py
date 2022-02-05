from trajectory_generator import trajectory_generator
from drive.swerve_drive import swerve_drive
import trajectory_io
import math

def main():
    drive = swerve_drive(
        # Wheelbase x/y
        0.622,0.572,
        # Bumper length/width
        0.954,0.903,
        # Mass/moi
        46.7,5.6,
        # Max velocity/force
        73, 1.1,
        # 50, 1,
        # Wheel radius
        0.051)

    generator = trajectory_generator(drive)

    generator.generate(
                        [[0.0,0,0],
                        [-2,-0.75,0.1],
                        [-1.7,1.7,-math.pi/2],
                        [-1,1.8,-math.pi * 0.25]
                        ],
                        "gogogogadget")

main()
