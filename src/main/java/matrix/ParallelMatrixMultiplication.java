package matrix;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ParallelMatrixMultiplication {
    public static double[][] multiply(double[][] matrixA, double[][] matrixB, int numThreads) {
        int rowsA = matrixA.length;
        int colsA = matrixA[0].length;
        int colsB = matrixB[0].length;

        double[][] result = new double[rowsA][colsB];

        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < rowsA; i++) {
            int row = i;
            executor.submit(() -> {
                for (int j = 0; j < colsB; j++) {
                    for (int k = 0; k < colsA; k++) {
                        result[row][j] += matrixA[row][k] * matrixB[k][j];
                    }
                }
            });
        }

        executor.shutdown();
        while (!executor.isTerminated()) {
            // Wait for all threads to complete
        }

        return result;
    }
}
