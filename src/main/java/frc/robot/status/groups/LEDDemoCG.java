package frc.robot.status.groups;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.LedAction;
import frc.robot.status.commands.ActionCommand;
import java.io.File;

public class LEDDemoCG extends SequentialCommandGroup {

    public LEDDemoCG() {
        File deployDir = Filesystem.getDeployDirectory();
        String pathPrefix = deployDir.getAbsolutePath() + "/images/";

        ImageAction ia = new ImageAction(pathPrefix + "THfade.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "noise.png");
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "noisy-pulse.png");
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "pulse.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "pulse-down.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "stripes.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        ia = new ImageAction(pathPrefix + "fade.png").oscillate();
        addCommands(new ActionCommand(ia));
        addCommands(new WaitCommand(10));

        addCommands(new ActionCommand(new LedAction(0, 0, 0, 0)));
    }

}