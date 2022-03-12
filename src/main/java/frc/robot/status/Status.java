package frc.robot.status;

import java.io.File;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.util.Color;
import edu.wpi.first.wpilibj.util.Color8Bit;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.status.actions.Action;
import frc.robot.status.actions.ChaseAction;
import frc.robot.status.actions.ImageAction;
import frc.robot.status.actions.LedAction;
import frc.robot.status.actions.PowerUpAction;
import frc.robot.status.actions.ScannerAction;
import frc.robot.status.commands.ActionCommand;

//
// This subsystem should be used for any status output such as lights.
// While the flashlight can be considered OI, Drivetrain, or Shooter, the intent
// is to eventually put more in here like controlling LED status lights.
//

// TODO: This class is really dedicated to doing LED stuff with the addressable led class.
// Remove all the other stuff, and rename it as such.

public class Status extends SubsystemBase {

    private static Status INSTANCE = null;

    // Flashlight uses the DIO port and its +5v power.
    private static int FLASHLIGHT_DIO_CHANNEL = 9;

    // Addressable LEDs use the PWM port and its +6v power.
    private static int ADDRESSABLE_LED_PWM_CHANNEL = 0;
    public static int ADDRESSABLE_LED_COUNT = 28; // accessable to actions

    // The DO object that controls the flashlight.
    private DigitalOutput flashlightOutput = null;

    // Addressable LED support.
    private volatile AddressableLED addressableLed = null;
    AddressableLEDBuffer currentBuffer; // AddressableLED is "write only" so keep track of what has been written

    // The current LED action
    private Action currentAction = null;

    // Object used for locking operations around the currentAction
    private final Object actionLock = new Object();

    // Timer tracks the delay between intervals/frames.
    private Timer timer = null;
    private double currentDelay = 0;

    // If both are false then the RIO is running but neither init has triggered
    // (it's in boot up).
    private boolean inAuto = false;
    private boolean inTeleOp = false;

    private double teleopStartTime;

    private Status() {
//        super();

        // Init the addressable led stuff. Note that the docs indicate
        // setting the length is expensive, so doing so here in a singleton
        // is proably best. Don't attempt to do this during a run.
        // https://docs.wpilib.org/en/latest/docs/software/actuators/addressable-leds.html
        addressableLed = new AddressableLED(ADDRESSABLE_LED_PWM_CHANNEL);
        addressableLed.setLength(ADDRESSABLE_LED_COUNT);

        // Spawn the thread that runs actions to control the LEDs.
        new ActionRunner().start();

        // Initialize to black.
        setColor(Color.kBlack, 0);
        // This will output whatever was set in the last setData call continuously.
        addressableLed.start();

        // Create the timer and start it.
        // This will start counting when the RIO initializes.
        timer = new Timer();
        timer.start();
    }

    // This will set/send the buffer to the LEDs.
    public synchronized void setLedData(final AddressableLEDBuffer buffer) {
        this.currentBuffer = buffer;
        addressableLed.setData(buffer);
    }

    public synchronized AddressableLEDBuffer getLedData() {
        return this.currentBuffer;
    }

    public void setAction(final Action action) {
        // Before doing anything with an action synchornize around it.
        // This prevents swapping the action while the action runner thread is doing
        // something with it.
        synchronized (actionLock) {
            currentAction = action;
            action.reset();
            currentDelay = 0; // Run the first time
        }
    }

    public void fillLEDs() {
        Color color = currentBuffer.getLED(0);
        // Set the color of the whole buffer
        setColor(color);
    }
    // Things to do when boot starts.
    // Note: these can't be commands since commands require enablement.
    private void scheduleBootActions() {

        final ScannerAction scannerAction = new ScannerAction(245, 0, 255, 90);
        scannerAction.setIntervalTime(0.075);
        scannerAction.setIntervalCount(ADDRESSABLE_LED_COUNT * 5 * 2); // number of lights, how many times to update
                                                                       // them, back and fourth
        setAction(scannerAction);

        /*
         * ChaseAction chaseAction = new ChaseAction(245, 0, 255, 90);
         * chaseAction.setIntervalCount(-1); setAction(chaseAction);
         */
    }

    // Things to do when auto resets/inits.
    // This can be scheduled commands, command groups, etc.
    public void scheduleAutoActions() {

        // Power up to purple, and stay on.
        /*
         * PowerUpAction powerUpAction = new PowerUpAction(245, 0, 255, 90);
         * powerUpAction.setIntervalCount(ADDRESSABLE_LED_COUNT);
         * setAction(powerUpAction);
         */
        final File deployDir = Filesystem.getDeployDirectory();
        // ia.setOscillate(true);
        // setAction(ia);

        final SequentialCommandGroup commandGroup = new SequentialCommandGroup();

        String imagePath = deployDir.getAbsolutePath() + "/images/" + "blueredfade.png";
        ImageAction ia = new ImageAction(imagePath, 0.050).oscillate();
        commandGroup.addCommands(
            new ActionCommand(ia),
            new WaitCommand(10)
            );
/*
        imagePath = deployDir.getAbsolutePath() + "/images/" + "fade.png";
        ia = new ImageAction(imagePath, 0.050).oscillate();
        commandGroup.addSequential(new ActionCommand(ia));
        commandGroup.addSequential(new WaitCommand(10));

        final String ia2Path = deployDir.getAbsolutePath() + "/images/" + "stripes.png";
        final ImageAction ia2 = new ImageAction(ia2Path, 0.050).oscillate().brightness(0.5);
        commandGroup.addSequential(new ActionCommand(ia2));
        commandGroup.addSequential(new WaitCommand(10));

        final String ia3Path = deployDir.getAbsolutePath() + "/images/" + "pulse.png";
        final ImageAction ia3 = new ImageAction(ia3Path, 0.050);
        commandGroup.addSequential(new ActionCommand(ia3));
        commandGroup.addSequential(new WaitCommand(10));

        imagePath = deployDir.getAbsolutePath() + "/images/" + "noisy-pulse.png";
        ia = new ImageAction(imagePath, 0.050).oscillate().brightness(0.8);
        commandGroup.addSequential(new ActionCommand(ia));
        commandGroup.addSequential(new WaitCommand(10));

        imagePath = deployDir.getAbsolutePath() + "/images/" + "noise.png";
        ia = new ImageAction(imagePath, 0.050).oscillate().brightness(0.1);
        commandGroup.addSequential(new ActionCommand(ia));
        commandGroup.addSequential(new WaitCommand(10));

        imagePath = deployDir.getAbsolutePath() + "/images/" + "burst.bmp";
        ia = new ImageAction(imagePath, 0.050);
        commandGroup.addSequential(new ActionCommand(ia));
        commandGroup.addSequential(new WaitCommand(10));

        imagePath = deployDir.getAbsolutePath() + "/images/" + "pulse-down.png";
        ia = new ImageAction(imagePath, 0.050).oscillate();
        commandGroup.addSequential(new ActionCommand(ia));
        commandGroup.addSequential(new WaitCommand(10));

        final String burstPath = deployDir.getAbsolutePath() + "/images/" + "THfade.png";
        final ImageAction burstAction = new ImageAction(burstPath, 0.05).oscillate().brightness(200);
        commandGroup.addSequential(new ActionCommand(burstAction));
        // commandGroup.addSequential(new WaitCommand(10));

        // LedAction blackAction = new LedAction(0, 0, 0, 0);
        // commandGroup.addSequential(new ActionCommand(blackAction));
*/
        commandGroup.schedule();
    }

    // This is what we want to run when teleop starts.
    // This can be scheduled commands, command groups, etc.
/*    
    private void scheduleTeleOpActions() {

        // Power up to purple, and stay on - should be on already in match.
        final PowerUpAction powerUpAction = new PowerUpAction(245, 0, 255, 90);
        powerUpAction.setIntervalCount(ADDRESSABLE_LED_COUNT);
        setAction(powerUpAction);

        // Using a command group with sequentials to force timely control of the leds.
        final CommandGroup commandGroup = new CommandGroup();

        // With 40s to remain, warning.
        commandGroup.addSequential(new WaitCommand(94));
        final LedAction warnAction = new LedAction(255, 127, 0, 127);
        commandGroup.addSequential(new ActionCommand(warnAction));

        // With 15s remain, go nuts to climb.
        commandGroup.addSequential(new WaitCommand(24)); // 15 sec before match end (adding extra since it takes a bit
                                                         // to start an action)

        // Run the chase to indicate we need to climb.
        final ChaseAction chaseAction = new ChaseAction(255, 127, 0, 90);
        chaseAction.setIntervalCount(-1); // run forever
        commandGroup.addSequential(new ActionCommand(chaseAction));

        // Don't do anything for some time.
        commandGroup.addSequential(new WaitCommand(15)); // match end

        // Set red which should indicate match end.
        commandGroup.addSequential(new ActionCommand(new LedAction(255, 0, 0, 127)));

        Scheduler.getInstance().add(commandGroup);
    }
    */

    /**
     * @return the singleton instance of the Status subsystem
     */
    public static Status getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Status();
        }
        return INSTANCE;
    }

    public void setTeleopStartTime() {
        this.teleopStartTime = Timer.getFPGATimestamp();
    }

    /** In seconds */
    public double getTeleopStartTime() {
        return this.teleopStartTime;
    }

    public void resetBoot() {
        inAuto = false;
        inTeleOp = false;

        // Resets the timer so that it represents "time since code init".
        timer.reset();

        scheduleBootActions();
    }

    // Resets the class state for Auto mode.
    public void resetAuto() {
        // Set state flags.
        inAuto = true;
        inTeleOp = false;

        // Resets the timer so that it represents "time since auto init".
        timer.reset();

        scheduleAutoActions();
    }

    // Resets the class state for TeleOp mode.
    public void resetTeleOp() {
        // Set state flags.
        inAuto = false;
        inTeleOp = true;

        // Resets the timer so that it represents "time since teleOp init".
        timer.reset();

//        scheduleTeleOpActions();
    }


    // @Override
    // public void initDefaultCommand() {
    //     String imagePath = Filesystem.getDeployDirectory().getAbsolutePath() + "/images/" + "fade.png";
    //     ImageAction ia = new ImageAction(imagePath).oscillate();
    //     setDefaultCommand(new ActionCommand(ia, 1000.0)); 
    // }

    @Override
    public void periodic() {

        // TODO: Summer project - update the subsystem to look at it's own scheduled
        // list of things to do based on state and what was set in the schedule* methods
        // (internal vs. Command/CommandGroup on that scheduler).
        // See an old commit for this class that had such things.
    }

    public void setColor(final Color color) {
        setColor(color, 255);
    }

    // Set a color from the predefined wpilib Color
    // Brightness is on a scale of 0-255
    public void setColor(final Color color, final int brightness) {
        Color8Bit intColor = new Color8Bit(color);
        setColor(intColor.red, intColor.green, intColor.blue, brightness, 100);
    }

    public void setColor(final Color color, final int brightness, final int percent) {
        Color8Bit intColor = new Color8Bit(color);
        setColor(intColor.red, intColor.green, intColor.blue, brightness, percent);
    }

    // Set RGB color values.
    // RGB values are 0 (full off) - 255 (full on)
    // Brightness is on a scale of 0-255
    public void setColor(int red, int green, int blue, final int brightness, int percent) {
        synchronized (actionLock) {
            this.currentAction = null; // Clear any action that is running.
        }
        
        final double b = brightness / 255.0;

        if (0 > percent) {percent = 0;}
        if (100 < percent) {percent = 100;}

        final int lastIndex = (int) (ADDRESSABLE_LED_COUNT * (percent / 100.0));

        red = (int) (red * b);
        green = (int) (green * b);
        blue = (int) (blue * b);

        System.out.println (red);
        System.out.println (green);
        System.out.println (blue);

        // Create a buffer for all the LEDs, set all of them to the same value, and
        // output the buffer.
        final AddressableLEDBuffer buffer = new AddressableLEDBuffer(ADDRESSABLE_LED_COUNT);
        for (var i = 0; i < buffer.getLength(); i++) {
            if (i > lastIndex) { // This will always set at least one LED to the specified color.
                buffer.setRGB(i, 0, 0, 0);
            } else {
                buffer.setRGB(i, red, green, blue);
            }
        }
        setLedData(buffer);
//        addressableLed.start();
    }

    // This is the thread that runs the current action.
    private class ActionRunner extends Thread {

        // The minium amount of time to delay the tread.
        // While the RIO should handle it either way, the delay
        // allows the OS schedular a good slice to do things.
        private static final double MINIMUM_DELAY_SECONDS = 0.010;

        public void run() {

            // The thread should persist while the code does (forever).
            //
            // TODO: A watchdog should probably check to see if the thread died off
            // due to an exception and restart it.
            while (true) {
                synchronized (actionLock) {
                    if (currentAction != null) {

                        // Delay the amount of time requested by the action.
                        currentDelay = currentAction.getIntervalTime();
                        if (currentDelay < MINIMUM_DELAY_SECONDS) {
                            currentDelay = MINIMUM_DELAY_SECONDS;
                        }

                        if (timer.hasElapsed(currentDelay)) {
                            timer.reset();
                            // System.out.println("ActionRunner: run");
                            currentAction.run();

                            // If the current action is now done, remove it and loop back around.
                            if (currentAction.isFinished() == true) {
                                // System.out.println("ActionRunner: finished");
                                currentAction = null;
                            }
                        } // currentDelay expired
                    } // currentAction != null
                } // sync
            } // while(true)
        } // run()
    }
}