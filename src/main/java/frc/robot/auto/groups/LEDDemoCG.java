package frc.robot.auto.groups;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.LedAction;
import frc.robot.status.commands.ActionCommand;
import java.io.File;

public class LEDDemoCG extends CommandGroup {

    public LEDDemoCG() {
        File deployDir = Filesystem.getDeployDirectory();
        String pathPrefix = deployDir.getAbsolutePath() + "/images/";

        ImageAction ia = new ImageAction(pathPrefix + "THfade.png").oscillate();
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "noise.png");
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "noisy-pulse.png");
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "pulse.png").oscillate();
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "pulse-down.png").oscillate();
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "stripes.png").oscillate();
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "fade.png").oscillate();
        addSequential(new ActionCommand(ia));
        addSequential(new WaitCommand(10));

        addSequential(new ActionCommand(new LedAction(0, 0, 0, 0)));
    }

}