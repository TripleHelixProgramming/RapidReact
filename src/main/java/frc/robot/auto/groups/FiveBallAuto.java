// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.FiveBallPartFour;
import frc.paths.FiveBallPartOne;
import frc.paths.FiveBallPartThree;
import frc.paths.FiveBallPartTwo;
import frc.paths.Spinnnn;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
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

public class FiveBallAuto extends SequentialCommandGroup {
  public FiveBallAuto(Drivetrain drive, Intake intake, Shooter shooter) {
    addCommands(
      new ResetOdometry(drive, new Pose2d(new Translation2d(-0.7, 0), Rotation2d.fromDegrees(-90.0))),
      new ResetEncoder(shooter),
      new ParallelDeadlineGroup(
        new SequentialCommandGroup(
            new WaitCommand(0.8), // Give shooter time to spin up & hood to move
            new PullTrigger(shooter),
            new WaitCommand(0.5)),
        // new ActionCommand(new ImageAction("THfade.png", 0.01).brightness(0.7)),
        new FlywheelController(shooter, 1810, 77.90)),
    new ParallelDeadlineGroup(
      new StopShooter(shooter),
      new StopTrigger(shooter),
      new FastIntake(intake)),
    new ParallelDeadlineGroup(
      new WaitCommand(5.0),
      new SequentialCommandGroup(
        new WaitCommand(1.1), 
        new FlywheelController(shooter, 1980, 73.25)),
      new TrajectoryFollower(drive, new FiveBallPartTwo()),
      new SequentialCommandGroup(
        new WaitCommand(3.25),
        new PullTrigger(shooter)),
      new SequentialCommandGroup(
        new WaitCommand(4.0),
        new RetractIntake(intake))),
    new StopShooter(shooter),
    new StopTrigger(shooter)
    // new ParallelDeadlineGroup(
    //   new TrajectoryFollower(drive, new FiveBallPartThree()),
    //   new FastIntake(intake)),
    // new WaitCommand(0.9), // Pick up balls 4 & 5
    // new ParallelDeadlineGroup(
    //   new WaitCommand(4.5),
    //   new SequentialCommandGroup(
    //     new WaitCommand(1.75),
    //     new FlywheelController(shooter, 1795, 77.60)),
    //   new SequentialCommandGroup(
    //     new WaitCommand(2.9),
    //     new PullTrigger(shooter)),
    //   new SequentialCommandGroup(
    //     new WaitCommand(1.5),
    //     new RetractIntake(intake)),
    //   new TrajectoryFollower(drive, new FiveBallPartFour())),
    // new StopShooter(shooter),
    // new StopTrigger(shooter),
    // new ResetHood(shooter),
    // new SetColor(Status.getInstance(), Color.kBlack)
    );
  }
}
