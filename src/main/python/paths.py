from trajectory_generator import trajectory_generator
from drive.swerve_drive import swerve_drive

def main():
    drive = swerve_drive(2,1,1,1,1)
    generator = trajectory_generator(drive)

    generator.generate([
                        [4.5,1.7,0],
                        [10,1.7,0]])

main()