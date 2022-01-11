package frc.lib;
/**
 * This class maps the value of a stick input to an linear curve.
 * It is a subclass of <code>Curve</code>.
 *
 * @author Justin Babilino
 * @version 0.0.3
 */
public class LinCurve extends Curve {
    /**
     * Constructs an Linear Curve object which can
     * be used to map a stick input proportionally.
     * Initialized with values provided.
     * 
     * @param offset value used to offset the final curve
     * @param scalar value used to scale the value before offset
     * @param deadzone value for the width of the deadband in the center of the curve
     */
    public LinCurve(double offset, double scalar, double deadzone) {
        setOffset(offset);
        setScalar(scalar);
        setDeadzone(deadzone);
    }

    /**
     * Constructs an Linear Curve object which can
     * be used to map a stick input proportionally.
     * Initialized with default values:
     * <code>
     *     offset = 0.0;
     *     scalar = 1.0;
     *     deadzone = 0.0;
     * </code>
     */
    public LinCurve() {
        setOffset(0.0);
        setScalar(1.0);
        setDeadzone(0.0);
    }

    /**
     * @param input value to be mapped
     */
    @Override public double calculateMappedVal(double input) {
        double val = calculateOffset(calculateScalar(calculateDeadzone(input)));
        if (val > 1.0) {
            val = 1.0;
        } else if (val < -1.0) {
            val = -1.0;
        }
        return val;
    }
}