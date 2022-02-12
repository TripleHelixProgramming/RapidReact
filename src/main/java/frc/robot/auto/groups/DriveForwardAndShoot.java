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
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class DriveForwardAndShoot extends SequentialCommandGroup{

    public DriveForwardAndShoot(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new SetShooterState(shooter, 1600, 70),
            new WaitCommand(1.0), // Give shooter time to spin up & hood to move
            new PullTrigger(shooter),
            new WaitCommand(1.25),
            new StopTrigger(shooter),
            new SetShooterState(shooter, 0, 50),
            new DeployIntake(intake),
            new TrajectoryFollower(drive, new ShootTwoBalls()),
            new SetShooterState(shooter, 1775, 63),
            new WaitCommand(1.0), // Give shooter time to spin up & hood to move
            new RetractIntake(intake),
            new PullTrigger(shooter),
            new WaitCommand(1.25),
            new StopTrigger(shooter),
            new SetShooterState(shooter, 0, 50), // Stop shooter & reset hood.
            new TrajectoryFollower(drive, new GoForwardHalfMeter())
        );
    }    
}
