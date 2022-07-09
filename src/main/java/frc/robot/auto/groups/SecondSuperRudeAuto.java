package frc.robot.auto.groups;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.paths.Path;
import frc.paths.SuperRudeTwo;
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

public class SecondSuperRudeAuto extends SequentialCommandGroup{
    public SecondSuperRudeAuto(TrajectoriesManager trajectoriesManager, Drivetrain drive, Intake intake, Shooter shooter) {
        Path outerRude1 = trajectoriesManager.loadTrajectory("outer_rude_1");
        Path outerRude2 = trajectoriesManager.loadTrajectory("outer_rude_2");
        addCommands(
          new ResetOdometry(drive, outerRude1.getPath().getInitialPose()),
          new ParallelDeadlineGroup(
            new WaitCommand(4.5),
            new SequentialCommandGroup(
                new WaitCommand(1.0),
                new FlywheelController(shooter, 1735, 79)
            ),
            new SequentialCommandGroup(
                  new WaitCommand(2.5), // Give shooter time to spin up & hood to move
                  new PullTrigger(shooter, intake)),
            new TrajectoryFollower(drive, outerRude1),
            new FastIntake(intake)
          ),
          new StopTrigger(shooter, intake),
          new StopShooter(shooter),
          new ParallelDeadlineGroup(
            new TrajectoryFollower(drive, outerRude2),
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