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
        73, 1,
        # 50, 1,
        # Wheel radius
        0.051)

    generator = trajectory_generator(drive)

    # generator.generate(
    #                     [[0,0,0],
    #                     [-1.0,0,-0.15]],
    #                     "OnePointEightMetersForward")

    # generator.generate(
    #                 [[-1.0,0,-0.15],
    #                 [0,2,-math.pi/2],
    #                 [-0.42,6.121,-1]],
    #                 "CollectSecondBall")

    # generator.generate(
    #             [[-0.42,6.121,-1],
    #             [-0.2,0.5,-0.575]],
    #             "GoHome")

    # generator.generate([
    #             [0,0,-0.7],
    #             [-1.2,0,-0.4]],
    #             "ShootTwoBalls")
    
    generator.generate([
                [0,0,0],
                [-0.4,0,-math.pi * 0.9]],
                "GoForwardHalfMeter")

main()