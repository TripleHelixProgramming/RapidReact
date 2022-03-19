// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

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
import frc.paths.NewFourPartOne;
import frc.paths.NewFourPartThree;
import frc.paths.NewFourPartTwo;
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

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class NewFourBallAuto extends SequentialCommandGroup {
  /** Creates a new NewAuto. */
  public NewFourBallAuto(Drivetrain drive, Shooter shooter, Intake intake, Limelight limelight, HelixJoysticks joysticks) {
    addCommands(
      new ResetOdometry(drive, new Pose2d(new Translation2d(0,0), new Rotation2d(-2.35))),
      new ResetEncoder(shooter),
      new ParallelDeadlineGroup(
        new WaitCommand(2.9), 
        // new ActionCommand(new ImageAction(Robot.fiveBallAutoImage, 0.02, ImageAction.FOREVER).brightness(0.7).oscillate()),
        new TrajectoryFollower(drive, new NewFourPartOne()),
        new FastIntake(intake),
        new SequentialCommandGroup(
          new WaitCommand(1.25),
          new PullTrigger(shooter)
        ),
        new FlywheelController(shooter, 1980, 72)),
      new ParallelDeadlineGroup(
        new TrajectoryFollower(drive, new NewFourPartTwo()),
        new StopTrigger(shooter),
        new StopShooter(shooter)
      ),
      new WaitCommand(0.6),
      new ParallelDeadlineGroup(
        new WaitCommand(5),
        new SequentialCommandGroup(
          new WaitCommand(1.75),
          new FlywheelController(shooter, 1795, 76.0)
        ),
        new SequentialCommandGroup(
          new TrajectoryFollower(drive, new NewFourPartThree()),
          new TurnToAngle(drive, limelight, joysticks)
        ),
        new SequentialCommandGroup(
          new WaitCommand(3.6),
          new PullTrigger(shooter)
        ),
        new SequentialCommandGroup(
          new WaitCommand(0.75),
          new RetractIntake(intake)
        )
      )
    );
  }
}
