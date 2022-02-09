package frc.lib.control;

public class DCMotor {
    private double kv, ka, ks;

    public DCMotor(double kv, double ka, double ks) {
        this.kv = kv;
        this.ka = ka;
        this.ks = ks;
    }

    public double solveFeedforward(double velocity, double acceleration) {
        return kv * velocity + ka * acceleration + ks;
    }

    public double solveVoltage(double initialVelocity, double finalVelocity, double dt) {
        double m0 = Math.pow(Math.E, -kv / ka * dt);
        return (initialVelocity - finalVelocity * m0) / (1 - m0) * kv;
    }
}