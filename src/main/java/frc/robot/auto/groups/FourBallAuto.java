package frc.robot.auto.groups;

import edu.wpi.first.wpilibj2.command.WaitCommand;

import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.paths.CollectSecondBall;
import frc.paths.GoHome;
import frc.paths.OnePointEightMetersForward;
import frc.robot.drive.Drivetrain;
import frc.robot.drive.commands.TrajectoryFollower;
import frc.robot.intake.Intake;
import frc.robot.intake.commands.DeployIntake;
import frc.robot.intake.commands.RetractIntake;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.commands.FlywheelController;
import frc.robot.shooter.commands.PullTrigger;
import frc.robot.shooter.commands.StopShooter;
import frc.robot.shooter.commands.StopTrigger;

public class FourBallAuto extends SequentialCommandGroup{

    public FourBallAuto(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new OnePointEightMetersForward()),
                new DeployIntake(intake)),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(1.25)),
                new FlywheelController(shooter, 1800, 72),
                new RetractIntake(intake)),
            new StopTrigger(shooter),
            new StopShooter(shooter),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new CollectSecondBall()), 
                new DeployIntake(intake)),
            new WaitCommand(0.75),
            new ParallelDeadlineGroup(
                new TrajectoryFollower(drive, new GoHome()), 
                new RetractIntake(intake)),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    new WaitCommand(1), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter),
                    new WaitCommand(1.25)),
                new FlywheelController(shooter, 1775, 76),
                new RetractIntake(intake)),
            new StopTrigger(shooter),
            new StopShooter(shooter)
        );
    }
    
}
