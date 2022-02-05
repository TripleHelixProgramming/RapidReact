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
        # Wheel radius
        0.051)
    generator = trajectory_generator(drive)

    generator.generate(
                        [[5,5,0],
                        # [2,5,-math.pi]],
                        [3,3,-math.pi*1.2],
                        [2.75,3.5,-math.pi*1.5]],
                        # trajectory_io.import_path("test"),
                        "gogogogadget")

main()