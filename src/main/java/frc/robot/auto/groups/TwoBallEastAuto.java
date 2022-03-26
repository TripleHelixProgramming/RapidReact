package frc.robot.auto.groups;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.paths.WeirdAutoPartOne;
import frc.paths.WeirdAutoPartTwo;
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
import frc.robot.status.Status;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.commands.ActionCommand;
import frc.robot.status.commands.SetColor;

public class TwoBallEastAuto extends SequentialCommandGroup{
    public TwoBallEastAuto(Drivetrain drive, Intake intake, Shooter shooter) {
        addCommands(    
            new ActionCommand(new ImageAction("THfade.png", 0.01).oscillate().brightness(0.7)),
            new ResetOdometry(drive, new Pose2d(new Translation2d(0,0),Rotation2d.fromDegrees(-90.0))),
            new ParallelDeadlineGroup( // Pick up ball
                new TrajectoryFollower(drive, new WeirdAutoPartOne()),
                new ResetHood(shooter), // Reset the hood while moving to pick up ball 
                new DeployIntake(intake)),
            new ParallelDeadlineGroup( // Shoot two balls
                new SequentialCommandGroup(
                    new WaitCommand(2), // Give shooter time to spin up & hood to move
                    new PullTrigger(shooter, intake),
                    new WaitCommand(2)),
                new FlywheelController(shooter, 1800, 78.25),
                new RetractIntake(intake)),
            new StopTrigger(shooter, intake),
            new StopShooter(shooter),
            new ParallelDeadlineGroup( // Pick up red ball
                new TrajectoryFollower(drive, new WeirdAutoPartTwo()),
                new DeployIntake(intake)),
            new WaitCommand(1.5),
            new ParallelDeadlineGroup( // Toss red ball away
                new SequentialCommandGroup(
                    new WaitCommand(1),
                    new PullTrigger(shooter, intake),
                    new WaitCommand(1)), 
                new FlywheelController(shooter, 1000, 65),
                new RetractIntake(intake)),
            new StopShooter(shooter),
            new StopTrigger(shooter, intake),
            new ResetHood(shooter),
            new SetColor(Status.getInstance(), Color.kBlack)
        );
    }    
}
