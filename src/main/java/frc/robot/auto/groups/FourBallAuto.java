package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.lib.HelixJoysticks;
import frc.paths.CollectSecondBall;
import frc.paths.FourBallPartThree;
import frc.paths.FourBallPartTwo;
import frc.paths.GoHome;
import frc.paths.OnePointEightMetersForward;
import frc.paths.WeirdAutoPartOne;
import frc.robot.Robot;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.drive.commands.TurnToAngle;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.FastIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.ResetEncoder;
import frc.robot.shooter.commands.ResetHood;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;
import frc.robot.status.Status;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.status.commands.SetColor;
import frc.robot.vision.Limelight;

public class FourBallAuto extends SequentialCommandGroup{

    public FourBallAuto(Drivetrain drive, Intake intake, Shooter shooter, Limelight limelight, HelixJoysticks joysticks) {
        addCommands(
            new ActionCommand(new ImageAction(Robot.fourBallAutoImage,0.02, ImageAction.FOREVER).oscillate().brightness(0.7)),
            new ResetOdometry(drive, new Pose2d(new Translation2d(0,0),Rotation2d.fromDegrees(-90))),
            new ResetEncoder(shooter),
            new ParallelDeadlineGroup(
                new WaitCommand(4.25),
                // new ResetHood(shooter),
                new SequentialCommandGroup(
                    new WaitCommand(1.75),
                    new FlywheelController(shooter, 1740, 78.25)),
                new SequentialCommandGroup(
                    new WaitCommand(3.25),
                    new PullTrigger(shooter, intake)),
                new TrajectoryFollower(drive, new WeirdAutoPartOne()),
                new SequentialCommandGroup(
                    new ParallelDeadlineGroup(
                        new WaitCommand(1.95),
                        new FastIntake(intake)),
                    new RetractIntake(intake))),
            new StopTrigger(shooter, intake),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new FourBallPartTwo()),
                new FastIntake(intake)),
            new WaitCommand(0.5),
            new ParallelDeadlineGroup( 
                new WaitCommand(4.75),
                new SequentialCommandGroup(
                    new WaitCommand(1.75),
                    new FlywheelController(shooter, 1750, 79.5)),
                new SequentialCommandGroup(
                    new WaitCommand(0.75),
                    new RetractIntake(intake)),
                new SequentialCommandGroup(
                    new WaitCommand(3.35),
                    new PullTrigger(shooter, intake)),
                new SequentialCommandGroup(
                    new TrajectoryFollower(drive, new FourBallPartThree()),
                    new TurnToAngle(drive, limelight, joysticks)
                )),
            new StopShooter(shooter),
            new StopTrigger(shooter, intake),
            new ResetHood(shooter),
            new SetColor(Status.getInstance(), Color.kBlack)
            );
    }
}