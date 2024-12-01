package matrix;

import java.util.Arrays;

public class VectorizedMatrixMultiplication {


    public static double[][] multiply(double[][] matrixA, double[][] matrixB) {
        int m = matrixA.length;
        int n = matrixA[0].length;
        int p = matrixB[0].length;

        if (matrixB.length != n) {
            throw new IllegalArgumentException("Matrix dimensions are not compatible for multiplication.");
        }

        // Resultant matrix
        double[][] result = new double[m][p];

        // Perform multiplication in parallel
        Arrays.parallelSetAll(result, i -> multiplyRowByMatrix(matrixA[i], matrixB));

        return result;
    }

    /**
     * Multiplies a single row vector with a matrix.
     *
     * @param row     A single row vector from the first matrix
     * @param matrixB The second matrix
     * @return Resultant row vector
     */
    private static double[] multiplyRowByMatrix(double[] row, double[][] matrixB) {
        int p = matrixB[0].length;
        double[] resultRow = new double[p];

        for (int j = 0; j < p; j++) {
            double sum = 0;
            for (int k = 0; k < row.length; k++) {
                sum += row[k] * matrixB[k][j];
            }
            resultRow[j] = sum;
        }

        return resultRow;
    }

}
