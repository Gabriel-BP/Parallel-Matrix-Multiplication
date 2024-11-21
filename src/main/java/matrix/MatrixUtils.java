package matrix;

import java.util.Random;

public class MatrixUtils {
    public static double[][] generateRandomMatrix(int rows, int cols) {
        Random random = new Random();
        double[][] matrix = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = random.nextDouble() * 10; // Random values between 0 and 10
            }
        }
        return matrix;
    }

    public static void printMatrix(double[][] matrix) {
        for (double[] row : matrix) {
            for (double val : row) {
                System.out.printf("%.2f ", val);
            }
            System.out.println();
        }
    }

    public static boolean areDimensionsCompatible(double[][] matrixA, double[][] matrixB) {
        return matrixA[0].length == matrixB.length;
    }
}
