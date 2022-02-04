package frc.lib.control;

public class PIDController {
    private double kP, kD, kI;
    private double derivative, integral, lastError;
    private double reference;
    private double inputRange;
    private boolean continous;

    public PIDController(double kP, double kI, double kD) {
        this.kP = kP;
        this.kI = kI;
        this.kD = kD;
        integral = 0;
        lastError = 0;
        inputRange = Double.POSITIVE_INFINITY;
        continous = false;
    }

    public double calculate(double state, double dt) {
        double error = (reference - state) % inputRange;
        
        if (Math.abs(error) > inputRange / 2) {
            if (error > 0) {
                error -= inputRange;
            } else {
                error += inputRange;
            }
        }
        
        if (dt > 0) {
            derivative = (error - lastError) / dt;
            integral += error * dt;
        }
        
        lastError = error;
        
        return kP * error + kI * integral + kD * derivative;
    }

    public void setReference(double reference) {
        this.reference = reference;
    }

    public void setInputRange(double inputRange) {
        this.inputRange = inputRange;
    }

    public void setContinous(boolean continous) {
        this.continous = continous;
    }
}