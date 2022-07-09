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

public class NewFiveBallAuto extends SequentialCommandGroup {

  public NewFiveBallAuto(TrajectoriesManager trajectoriesManager, Drivetrain drive, Shooter shooter, Intake intake, Limelight limelight, HelixJoysticks joysticks) {
    Path newFiveBall1 = trajectoriesManager.loadTrajectory("new_five_ball_1");
    Path newFiveBall2 = trajectoriesManager.loadTrajectory("new_five_ball_2");
    Path newFiveBall3 = trajectoriesManager.loadTrajectory("new_five_ball_3");
    Path newFiveBall4 = trajectoriesManager.loadTrajectory("new_five_ball_4");
    addCommands(
      new ResetOdometry(drive, newFiveBall1.getPath().getInitialPose()),
      new ResetEncoder(shooter),
      new ParallelDeadlineGroup(
        new WaitCommand(2.9), 
        new ActionCommand(new ImageAction(Robot.fiveBallAutoImage, 0.02, ImageAction.FOREVER).brightness(0.7).oscillate()),
        new TrajectoryFollower(drive, newFiveBall1),
        new FastIntake(intake),
        new SequentialCommandGroup(
          new WaitCommand(1.25),
          new PullTrigger(shooter, intake)
        ),
        new FlywheelController(shooter, 1840, 75)),
      new ParallelDeadlineGroup(
        new TrajectoryFollower(drive, newFiveBall2),
        new FastIntake(intake),
        new StopTrigger(shooter, intake),
        new StopShooter(shooter)
      ),
      new WaitCommand(0.35),
      new ParallelDeadlineGroup(
        new WaitCommand(4.3),
        new SequentialCommandGroup(
          new WaitCommand(1.75),
          new FlywheelController(shooter, 1730, 81.0)
        ),
        new SequentialCommandGroup(
          new TrajectoryFollower(drive, newFiveBall3),
          new TurnToAngle(drive, limelight, joysticks)
        ),
        new SequentialCommandGroup(
          new WaitCommand(3.1),
          new PullTrigger(shooter, intake)
        ),
        new SequentialCommandGroup(
          new WaitCommand(0.75),
          new RetractIntake(intake)
        )
      ),
      new ParallelDeadlineGroup(
        new WaitCommand(3.0),
        new TrajectoryFollower(drive, newFiveBall4),
        new FlywheelController(shooter, 1710, 80),
        new SequentialCommandGroup(
          new StopTrigger(shooter, intake),
          new WaitCommand(1.25),
          new PullTrigger(shooter, intake)
        ),
        new FastIntake(intake)
      ),
      new ParallelDeadlineGroup(
        new WaitCommand(1.0),
        new StopTrigger(shooter, intake),
        new StopShooter(shooter),
        new RetractIntake(intake))
    );
  }
}
