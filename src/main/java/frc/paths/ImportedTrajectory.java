package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class ImportedTrajectory extends Path {

    private SwerveTrajectory trajectory;

    public ImportedTrajectory(SwerveTrajectory trajectory) {
        this.trajectory = trajectory;
    }

    @Override
    public SwerveTrajectory getPath() {
        return trajectory;
    }
    
}
