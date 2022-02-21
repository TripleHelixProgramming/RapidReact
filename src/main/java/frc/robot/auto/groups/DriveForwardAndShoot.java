package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import javax.swing.GroupLayout.ParallelGroup;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.GoForwardHalfMeter;
import frc.paths.OnePointEightMetersForward;
import frc.paths.ShootTwoBalls;
import frc.paths.TwoBallPartOne;
import frc.paths.TwoBallPartTwo;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.ResetHood;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class DriveForwardAndShoot extends SequentialCommandGroup{
    public DriveForwardAndShoot(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new ResetOdometry(drive, new Pose2d(new Translation2d(0,0),new Rotation2d(2.32))),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new TwoBallPartOne()),
                new ResetHood(shooter),
                new DeployIntake(intake)),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1.5), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(2)),
                new FlywheelController(shooter, 1805, 78.25),
                new RetractIntake(intake)),
            new StopTrigger(shooter),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new WaitCommand(4.0),
                new DeployIntake(intake),
                new TrajectoryFollower(drive, new TwoBallPartTwo())),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1.5), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(2)),
                new FlywheelController(shooter, 650, 60),
                new RetractIntake(intake)),
            new StopShooter(shooter),
            new StopTrigger(shooter)
        );
    }    
}
