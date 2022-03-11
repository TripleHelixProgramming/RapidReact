package frc.lib.util;

import java.util.Arrays;
import java.util.Comparator;

public class InterpolationTable {

    public static void main(String... args) {
        InterpolationTable table = new InterpolationTable(new double[][]{
            {1.7, 1800, 78.25},
            {2.4, 1875, 73.4},
            {2.95, 1980, 73.25}
        });
        System.out.println(Arrays.toString(table.sample(2)));
    }

    private static final Comparator<double[]> INPUT_SORTER = (point1, point2) -> {return point1[0] > point2[0] ? 1 : -1;};

    private final double[][] referencePoints;
    private final int propertyCount;

    public InterpolationTable(double[][] referencePoints) {
        Arrays.sort(referencePoints, INPUT_SORTER);
        this.referencePoints = referencePoints;
        propertyCount = referencePoints[0].length - 1;
    }

    public double[] sample(double input) {
        int leftBoundIndex = 0;
        int rightBoundIndex = referencePoints.length - 1;
        if (input < referencePoints[leftBoundIndex][0]) {
            double[] output = new double[propertyCount];
            for (int i = 0; i < propertyCount; i++) {
                output[i] = referencePoints[leftBoundIndex][1 + i];
            }
            return output;
        }
        if (input > referencePoints[rightBoundIndex][0]) {
            double[] output = new double[propertyCount];
            for (int i = 0; i < propertyCount; i++) {
                output[i] = referencePoints[rightBoundIndex][1 + i];
            }
            return output;
        }
        boolean found = false;
        while (!found) {
            int testIndex = (leftBoundIndex + rightBoundIndex) / 2;
            if (input < referencePoints[testIndex][0]) {
                rightBoundIndex = testIndex;
            } else {
                leftBoundIndex = testIndex;
            }
            if (rightBoundIndex - leftBoundIndex == 1) {
                found = true;
            }
        }
        double[] point1 = referencePoints[leftBoundIndex];
        double[] point2 = referencePoints[rightBoundIndex];
        double[] outputs = new double[propertyCount];
        for (int i = 0; i < propertyCount; i++) {
            outputs[i] = interpolate(input, i, point1, point2);
        }
        return outputs;
    }

    private double interpolate(double input, int outputProperty, double[] point1, double[] point2) {
        outputProperty++;
        double slope = (point2[outputProperty] - point1[outputProperty]) / (point2[0] - point1[0]);
        double sample = input - point1[0];
        double output = point1[outputProperty] + slope * sample;
        return output;
    }
}
