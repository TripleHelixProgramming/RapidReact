package frc.paths;

import frc.lib.control.SwerveTrajectory;

public class main {
    public static void main(String[] args) {
        double a = 1.42;
        double b = 543;
        double c = 24.5;
        double scale = a / Math.min(b, a / c);
        double scale2 = Math.max(a / b, c);
        System.out.println(scale);
        System.out.println(scale2);
    }
}
