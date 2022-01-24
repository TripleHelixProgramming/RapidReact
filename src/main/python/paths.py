from trajectory_generator import trajectory_generator
from swerve_drive import swerve_drive

def main():
    drive = swerve_drive(1,1,1,1,1)
    generator = trajectory_generator(drive)

    generator.generate([
                        [1,7.5,0],
                        [11,7.5,0],
                        [13,2,2]])

main()