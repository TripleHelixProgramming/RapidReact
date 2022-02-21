package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.CollectSecondBall;
import frc.paths.FourBallPartThree;
import frc.paths.FourBallPartTwo;
import frc.paths.GoHome;
import frc.paths.OnePointEightMetersForward;
import frc.paths.WeirdAutoPartOne;
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
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class FourBallAuto extends SequentialCommandGroup{

    public FourBallAuto(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new ResetOdometry(drive, new Pose2d(new Translation2d(0,0),Rotation2d.fromDegrees(-90))),
            new ParallelDeadlineGroup(
                new WaitCommand(4.0),
                new ResetHood(shooter),
                new SequentialCommandGroup(
                    new WaitCommand(1.5),
                    new FlywheelController(shooter, 1830, 78.25)),
                new SequentialCommandGroup(
                    new WaitCommand(3.0),
                    new PullTrigger(shooter)),
                new TrajectoryFollower(drive, new WeirdAutoPartOne()),
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(
                        new WaitCommand(1.7),
                        new DeployIntake(intake)),
                    new RetractIntake(intake))),
            new StopTrigger(shooter),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new FourBallPartTwo()),
                new DeployIntake(intake)),
            new WaitCommand(1.0),
            new ParallelDeadlineGroup( 
                new WaitCommand(4.25),
                new SequentialCommandGroup(
                    new WaitCommand(1.75),
                    new FlywheelController(shooter, 1830, 78.5)),
                new SequentialCommandGroup(
                    new WaitCommand(1.25),
                    new RetractIntake(intake)),
                new SequentialCommandGroup(
                    new WaitCommand(3.2),
                    new PullTrigger(shooter)),
                new TrajectoryFollower(drive, new FourBallPartThree())),
            new StopShooter(shooter),
            new StopTrigger(shooter)
        );
    }
    
}
