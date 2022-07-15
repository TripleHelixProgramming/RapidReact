package frc.robot.auto.groups;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.lib.HelixJoysticks;
import frc.paths.NewAutoPartFour;
import frc.paths.NewAutoPartOne;
import frc.paths.NewAutoPartThree;
import frc.paths.NewAutoPartTwo;
import frc.paths.Path;
import frc.paths.TrajectoriesManager;
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
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.vision.Limelight;

public class EarlyFiveBallAuto extends SequentialCommandGroup {

    public EarlyFiveBallAuto(TrajectoriesManager trajectoriesManager, Drivetrain drive, Shooter shooter, Intake intake, Limelight limelight) {
        Path earlyFiveBall1 = trajectoriesManager.loadTrajectory("early_five_ball_1");
        Path earlyFiveBall2 = trajectoriesManager.loadTrajectory("early_five_ball_2");
        Path earlyFiveBall3 = trajectoriesManager.loadTrajectory("early_five_ball_3");
        Path earlyFiveBall4 = trajectoriesManager.loadTrajectory("early_five_ball_4");
        double earlyFiveBall1Duration = earlyFiveBall1.getPath().getTotalTime();
        double earlyFiveBall2Duration = earlyFiveBall2.getPath().getTotalTime();
        double earlyFiveBall3Duration = earlyFiveBall3.getPath().getTotalTime();
        double earlyFiveBall4Duration = earlyFiveBall4.getPath().getTotalTime();
        Pose2d shot1Pose = earlyFiveBall1.getPath().getFinalPose();
        Pose2d shot2Pose = earlyFiveBall2.getPath().getFinalPose();
        Pose2d shot3Pose = earlyFiveBall4.getPath().getFinalPose();
        double shot1Dist = Math.hypot(shot1Pose.getX(), shot1Pose.getY());
        double shot2Dist = Math.hypot(shot2Pose.getX(), shot2Pose.getY());
        double shot3Dist = Math.hypot(shot3Pose.getX(), shot3Pose.getY());
        double shot1Velocity = Shooter.lookupVelocity(shot1Dist);
        double shot2Velocity = Shooter.lookupVelocity(shot2Dist);
        double shot3Velocity = Shooter.lookupVelocity(shot3Dist);
        double shot1Angle = Shooter.lookupHoodAngle(shot1Dist);
        double shot2Angle = Shooter.lookupHoodAngle(shot2Dist);
        double shot3Angle = Shooter.lookupHoodAngle(shot3Dist);
        addCommands(
            new ResetOdometry(drive, earlyFiveBall1.getPath().getInitialPose()),
            new ResetEncoder(shooter),
            new ActionCommand(new ImageAction(Robot.fiveBallAutoImage, 0.02, ImageAction.FOREVER).brightness(0.7).oscillate()),
            new ParallelDeadlineGroup(
                new WaitCommand(earlyFiveBall1Duration + 0.8),
                new FlywheelController(shooter, shot1Velocity, shot1Angle)),
                new TrajectoryFollower(drive, earlyFiveBall1),
                new FastIntake(intake),
                new SequentialCommandGroup(
                    new WaitCommand(earlyFiveBall1Duration + 0.4), // intake out duration
                    new RetractIntake(intake)
                )
            ),
            new PullTrigger(shooter, intake),
            new WaitCommand(1.8), // first shot trigger time
            new StopTrigger(shooter, intake),
            new ParallelDeadlineGroup(
                new WaitCommand(earlyFiveBall2Duration + 0.8), // second shot wait time
                new FlywheelController(shooter, shot2Velocity, shot2Angle)),
                new TrajectoryFollower(drive, earlyFiveBall2),
                new FastIntake(intake),
                new SequentialCommandGroup(
                    new WaitCommand(earlyFiveBall2Duration + 0.4), // intake out duration
                    new RetractIntake(intake)
                )
            ),
            new PullTrigger(shooter, intake),
            new WaitCommand(1.8), // second shot trigger time
            new StopTrigger(shooter, intake),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new WaitCommand(earlyFiveBall3Duration + 2.0),
                new TrajectoryFollower(drive, earlyFiveBall3),
                new FastIntake(intake),
                new SequentialCommandGroup(
                    new WaitCommand(earlyFiveBall3Duration + 1.8),
                    new RetractIntake(intake)
                )
            ),
            new FlywheelController(shooter, shot3Velocity, shot3Angle),
            new TrajectoryFollower(drive, earlyFiveBall4),
            new WaitCommand(0.4),
            new PullTrigger(shooter, intake),
            new WaitCommand(1.8), // third shot trigger time
            new StopTrigger(shooter, intake),
            new StopShooter(shooter)
        );
    }
}
