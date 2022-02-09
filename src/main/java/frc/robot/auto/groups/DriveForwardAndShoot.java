package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import javax.swing.GroupLayout.ParallelGroup;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.CollectSecondBall;
import frc.paths.GoHome;
import frc.paths.OnePointEightMetersForward;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.SpinUpShooter;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class DriveForwardAndShoot extends SequentialCommandGroup{

    public DriveForwardAndShoot(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new TrajectoryFollower(drive, new OnePointEightMetersForward()),
            new ParallelDeadlineGroup(
                new WaitCommand(1), // Give shooter time to spin up & hood to move
                new SetShooterState(shooter, 1980, 61.5)  // Never Ends!)
            ),
            new ParallelDeadlineGroup(
                new WaitCommand(1.25),
                new PullTrigger(shooter)
            ),
            new StopTrigger(shooter),
            new SetShooterState(shooter, 0, 50) // Stop shooter & reset hood.
        );
    }
    
}
