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
        90, 1.5,
        # 73, 1,
        # 50, 1,
        # Wheel radius
        0.051)

    generator = trajectory_generator(drive)

    # generator.generate(
    #                 [[0,0,-1.57],
    #                 [0,1.0,-1.57],
    #                 [0,0,-1.95]],
    #                 "WeirdAutoPartOne")

    # generator.generate(
    #                 [[0,0,-1.95],
    #                 [-1.25,1.225,-0.6]],
    #                 "WeirdAutoPartTwo")

    # generator.generate(
    #                 [[-1.0,0,-0.15],
    #                 [0,2,-math.pi/2],
    #                 [-0.42,6.25,-1]],
    #                 "CollectSecondBall")

    # generator.generate(
    #             [[-0.42,6.121,-1],
    #             [0.0,0.5,-0.59]],
    #             "GoHome")

    # generator.generate([
    #             [0,0,2.32],
    #             [1.2,0,2.62],
    #             [0,0,2.32]],
    #             "TwoBallPartOne")

    # generator.generate([
    #             [0,0,2.32],
    #             [1.8,-1.52,0],
    #             [0.9,-1.52,0]],
    #             "TwoBallPartTwo")
    
    # generator.generate([
    #                 [0,0,-1.95],
    #                 [2.5,0.85,-1.95],
    #                 [5.7,-0.3,-2.1],
    #                 [6.35,0.0,-2.1]],
    #                 "FourBallPartTwo")

    # generator.generate([
    #                 [0,0,-1.95],
    #                 [2.5,0.85,-1.95],
    #                 [5.7,-0.3,-2.1],
    #                 [6.15,0.15,-2.3]],
    #                 "FourBallPartTwo")

    # generator.generate([
    #                 [6.35,0.0,-2.1],
    #                 [2.5,0.85,-2.1],
    #                 [-0.3,-0.3,-1.95]],
    #                 "FourBallPartThree")

    # generator.generate([
    #                 [6.15,0.15,-2.3],
    #                 [2.5,0.85,-2.1],
    #                 [0.2,-0.3,-2.0]],
    #                 "FourBallPartThree")

    # generator.generate([
    #                 [0,0,-1.57],
    #                 [0,0.00000001,-1.85]],
    #                 "FiveBallPartOne")

    # generator.generate([
    #                 [0,0,-1.85],
    #                 [-0.2,0.9,-1.85],
    #                 [1.85,-0.5,-math.pi+0.6]
    #                 ],
    #                 "FiveBallPartTwo")

    generator.generate([
                    [1.85,-0.5,-math.pi+0.6],
                    [6.15,0.3,-math.pi+0.7]
                    ],
                    "FiveBallPartThree")

    generator.generate([
                    [6.15,0.3,-math.pi+0.7],
                    [0.10,-0.375,-math.pi+1.25]
                    ],
                    "FiveBallPartFour")

main()