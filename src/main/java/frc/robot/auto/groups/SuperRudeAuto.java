package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import javax.swing.GroupLayout.ParallelGroup;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.GoForwardHalfMeter;
import frc.paths.OnePointEightMetersForward;
import frc.paths.ShootTwoBalls;
import frc.paths.SuperRude;
import frc.paths.TwoBallPartOne;
import frc.paths.TwoBallPartTwo;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.FastIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.ResetHood;
import frc.robot.shooter.commands.SetShooterState;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;
import frc.robot.status.Status;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.status.commands.SetColor;

public class SuperRudeAuto extends SequentialCommandGroup{
    public SuperRudeAuto(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
          new ResetOdometry(drive, new Pose2d(0,0,new Rotation2d(2.32))),
          new ParallelDeadlineGroup(
            new TrajectoryFollower(drive, new TwoBallPartOne()),
            new FastIntake(intake)
          ),
          new TrajectoryFollower(drive, new SuperRude())
        );
    }    
}