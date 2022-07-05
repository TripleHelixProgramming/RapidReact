package frc.paths;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.lib.control.SwerveTrajectory;
import frc.lib.control.SwerveTrajectory.State;

public class TrajectoriesManager {
    
    private static final TypeReference<List<State>> STATE_LIST_TYPE = new TypeReference<>() {};

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final File trajectoriesDir;
    private final Map<String, Path> trajectoriesMap = new HashMap<>();

    public TrajectoriesManager(File trajectoriesDir) {
        this.trajectoriesDir = trajectoriesDir;
    }

    public Path loadTrajectory(String name) {
        File trajectoryFile = new File(trajectoriesDir, name + ".json");
        if (trajectoryFile.exists()) {
            try {
                List<State> states = objectMapper.readValue(trajectoryFile, STATE_LIST_TYPE);
                Path trajectory = new ImportedTrajectory(new SwerveTrajectory(states));
                trajectoriesMap.put(name, trajectory);
                return trajectory;
            } catch (JsonParseException e) {
                SmartDashboard.putString("JSONERROR", e.getMessage());
                // use these for debugging later
            } catch (JsonMappingException e) {
                // SmartDashboard.putString("JSONMAPERROR", "Unfortunate");
                SmartDashboard.putString("JSONMapError", e.getMessage());
            } catch (IOException e) {
                SmartDashboard.putString("Fileerror", e.getMessage());
            }
            return null;
        }
        return null;
    }

    public void loadAllTrajectories() {
        File[] files = trajectoriesDir.listFiles();
        for (File file : files) {
            String name = file.getName();
            name = name.substring(0, name.indexOf("."));
            loadTrajectory(name);
        }
    }

    public boolean contains(String name) {
        return trajectoriesMap.containsKey(name);
    }

    public Path get(String name) {
        if (contains(name)) {
            return trajectoriesMap.get(name);
        } else {
            return null;
        }
    }
    
}