package frc.robot.auto.groups;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.paths.Path;
import frc.paths.SuperRude;
import frc.paths.TrajectoriesManager;
import frc.paths.TwoBallPartOne;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.ResetOdometry;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.FastIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class SuperRudeAuto extends SequentialCommandGroup {
    public SuperRudeAuto(TrajectoriesManager trajectoriesManager, Drivetrain drive, Intake intake, Shooter shooter) {
        Path innerRude1 = trajectoriesManager.loadTrajectory("inner_rude_1");
        Path innerRude2 = trajectoriesManager.loadTrajectory("inner_rude_2");
        SmartDashboard.putNumber("initial auto heading", innerRude1.getPath().getInitialPose().getRotation().getRadians());
        addCommands(
          new ResetOdometry(drive, innerRude1.getPath().getInitialPose()),
          new ParallelDeadlineGroup(
            new TrajectoryFollower(drive, innerRude1),
            new FastIntake(intake)
          ),
          new ParallelDeadlineGroup( // Shoot the balls
              new SequentialCommandGroup(
                  new WaitCommand(1.5), // Give shooter time to spin up & hood to move
                  new PullTrigger(shooter, intake),
                  new WaitCommand(2)),
              new FlywheelController(shooter, 1735, 81)),
              // new RetractIntake(intake)),
          new StopTrigger(shooter, intake),
          new StopShooter(shooter),
          new ParallelDeadlineGroup(
            new TrajectoryFollower(drive, innerRude2),
            new FastIntake(intake)),
          new ParallelDeadlineGroup( // Toss red ball away
              new SequentialCommandGroup(
                  new WaitCommand(0.5), // Give shooter time to spin up & hood to move
                  new PullTrigger(shooter, intake),
                  new WaitCommand(2)),
              new FlywheelController(shooter, 550, 60),
              new RetractIntake(intake))
        );
    }    
}