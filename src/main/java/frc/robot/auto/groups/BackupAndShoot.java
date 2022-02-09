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

public class BackupAndShoot extends SequentialCommandGroup{

    public BackupAndShoot(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new DeployIntake(intake),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new OnePointEightMetersForward())
            ),
            new RetractIntake(intake),
            new ParallelDeadlineGroup(new WaitCommand(1), 
            new SetShooterState(shooter, 1980, 61.5)), // Never Ends!)
            new ParallelDeadlineGroup(
                new WaitCommand(1.25),
                new PullTrigger(shooter)
            ),
            new SetShooterState(shooter, 0, 50),
            new StopTrigger(shooter),
            new DeployIntake(intake),
            new TrajectoryFollower(drive, new CollectSecondBall()),
            new WaitCommand(0.75),
            new RetractIntake(intake),
            new TrajectoryFollower(drive, new GoHome()),
            new ParallelDeadlineGroup(
                new WaitCommand(0.65),
                new SetShooterState(shooter, 1725, 65)
            ),
            new PullTrigger(shooter),
            new WaitCommand(1.5),
            new StopShooter(shooter),
            new StopTrigger(shooter)
        );
    }
    
}
