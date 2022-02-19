package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import javax.swing.GroupLayout.ParallelGroup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.GoForwardHalfMeter;
import frc.paths.OnePointEightMetersForward;
import frc.paths.ShootTwoBalls;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class DriveForwardAndShoot extends SequentialCommandGroup{
    public DriveForwardAndShoot(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1.5), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(1.25)
                ),
                new FlywheelController(shooter, 1700, 79)
            ),
            new StopTrigger(shooter),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new ShootTwoBalls()),  
                new DeployIntake(intake)),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1.5), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(1.25)),
                new FlywheelController(shooter, 1960, 70.5),
                new RetractIntake(intake)),
            new StopTrigger(shooter),
            new StopShooter(shooter),
            new TrajectoryFollower(drive, new GoForwardHalfMeter())
        );
    }    
}
