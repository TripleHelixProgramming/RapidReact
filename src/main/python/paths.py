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
        70, 1.9,
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
    #             [1.2,-0.15,2.62],
    #             [0.35,-0.35,2.32]],
    #             "TwoBallPartOne")

    # generator.generate([
    #             [0.35,-0.35,2.32],
    #             [1.8,-1.52,0],
    #             [0.9,-1.52,0]],
    #             "TwoBallPartTwo")

    # generator.generate([
    #             [0.35,-0.35,2.32],
    #             [0.5,-1.2,math.pi/2],
    #             [0.85,2,math.pi],
    #             [2,2.3,math.pi],
    #             [3.6,2.3,6.45/4*math.pi]],
    #             "SuperRude")
    
    # generator.generate([
    #             [0.35,-0.35,2.32],
    #             [0.7,-1.2,math.pi/2],
    #             # [2.5,0.5,3/2*math.pi],
    #             [2.8,1.45,3.4/2*math.pi],
    #             [2.4,2,3.4/2*math.pi],
    #             [3.4,2,3.4/2*math.pi]],
    #             "SuperRudeTwo")
    
    # generator.generate([
    #                 [0,0,-1.95],
    #                 [2.5,0.85,-1.95],
    #                 [5.7,-0.3,-2.1],
    #                 [6.35,0.0,-2.1]],
    #                 "FourBallPartTwo")

    generator.generate([
                    [0,0,-1.95],
                    [2.5,0.85,-1.95],
                    [6,-0.3,-2.1],
                    [6.3,0,-2.3],
                    [6,-0.3,-2.3]],
                    "FourBallPartTwo")

    generator.generate([
                    [6,-0.3,-2.3],
                    [2.5,0.85,-2.1],
                    [-0.3,0,-1.95]],
                    "FourBallPartThree")

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
    #                 [-0.7,0,-math.pi/2],
    #                 [-0.2,0.9,-math.pi/2+0.15],
    #                 [1.85,-0.5,-math.pi+0.6]
    #                 ],
    #                 "FiveBallPartTwo")

    # generator.generate([
    #                 [1.85,-0.5,-math.pi+0.6],
    #                 [6.15,0.3,-math.pi+0.7]
    #                 ],
    #                 "FiveBallPartThree")

    # generator.generate([
    #                 [6.15,0.3,-math.pi+0.7],
    #                 [0.10,-0.375,-math.pi+1.25]
    #                 ],
    #                 "FiveBallPartFour")

    # generator.generate([
    #                 [-0,0,-2.35],
    #                 [1.1,0.3,-2.55],
    #                 [0.95,0.15,-2.55],
    #                 ],
    #                 "NewAutoPartOne")
    
    # generator.generate([
    #                 [0.95,0.15,-2.55],
    #                 [5.0,0.5,-2.275],
    #                 [5.2,0.75,-2.275],
    #                 [4.85,0.4,-2.275]
    #                 ],
    #                 "NewAutoPartTwo")

    # generator.generate([
    #                 [4.85,0.4,-2.275],
    #                 [-1.3,0.775,-math.pi/2-0.2]
    #                 ],
    #                 "NewAutoPartThree")
    
    # generator.generate([
    #                 [-1.3,0.775,-math.pi/2-0.2],
    #                 [-1.1,1.7,-math.pi/2-0.14],
    #                 [-1.3,0.9,-math.pi/2-0.2]
    #                 ],
    #                 "NewAutoPartFour")

    # generator.generate([
    #                 [-0,0,-2.35],
    #                 [1.1,0.3,-2.55],
    #                 [0.95,0.15,-2.55],
    #                 ],
    #                 "NewFourPartOne")
    
    # generator.generate([
    #                 [0.95,0.15,-2.55],
    #                 [5.0,0.5,-2.275],
    #                 [5.2,0.75,-2.275],
    #                 [4.85,0.4,-2.275]
    #                 ],
    #                 "NewFourPartTwo")

    # generator.generate([
    #                 [4.85,0.4,-2.275],
    #                 [0.2,0.2,-2.35]
    #                 ],
    #                 "NewFourPartThree")    

main()