package frc.lib;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class will create joysticks for all 6 slots and allow you to retrieve them based on their display name.
 */
public class ControllerPatroller {

    private static ControllerPatroller patroller = new ControllerPatroller();

    /**
     * Retrieve the singleton instance of the ControllerPatroller
     * 
     * @return the instance of the ControllerPatroller
     */
    public static ControllerPatroller getInstance() {
        if (patroller == null) {
            patroller = new ControllerPatroller();
        }

        return patroller;
    }

    private List<Joystick> controllers = new ArrayList<>();
    private List<String> controllerNames = new ArrayList<>();

    /**
     * @param controllerNames the list of controller names in the order the user would like to access them
     */
    private ControllerPatroller() {
        // Create a joystick at each port so we can check their names later
        // Most of these objects will go unused
        for (int i = 0; i < 6; i++) {
            controllers.add(new Joystick(i));
            controllerNames.add(controllers.get(i).getName());
        } 
    }

    /**
     * Detect if the list of controllers connected to USB has changed.
     * 
     * If so, update the list of controllers and return TRUE.
     * 
     * @return has the list of controllers changed?
     */
    public boolean controllersChanged() {

        boolean changed = false;
        for (int i = 0; i < 6; i++) {
            String controllerName = controllers.get(i).getName();
            if (!controllerNames.get(i).equals(controllerName)) {
                changed = true;
                controllerNames.set(i,controllerName);
            }
        }
        return changed;
    }

    /**
     * @param name string to look for in list of joystick names
     * @return Joystick or null
    */
    public Optional<Joystick> find(String name) {
        // Filter the list of controllers for ones that contain the provided name
        Optional<Joystick> joystick = controllers.stream()
            .filter(c -> c.getName().toLowerCase().contains(name.toLowerCase()))
            .findFirst();
            
        return joystick;
    }
    /**
     * @param name the name of the controller to access
     * @param defaultPort the port to access if a controller with the provided name is not found
     * 
     * @return the joystick with the provided name or at the provided defaultPort
     */
    public Joystick get(String name, int defaultPort) {
        // Filter the list of controllers for ones that contain the provided name
        Optional<Joystick> joystick = find(name);

        if (joystick.isPresent()) {
            return joystick.get();
        }

        // If we didn't find a controller with the provided name return the one at the default port
        return controllers.get(defaultPort);
    }

    public Joystick get(String[] names, int defaultPort) {
        // Filter the list of controllers for ones that contain the provided name
        for (String name : names) {
            Optional<Joystick> joystick = find(name);
            if (joystick.isPresent()) {
                return joystick.get();
            }
        }
        return controllers.get(defaultPort);
    }
}