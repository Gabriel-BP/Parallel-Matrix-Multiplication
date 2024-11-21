package matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixMultiplicationTest {
    @Test
    public void testPerformanceMetrics() {
        System.out.println("Available Cores: " + Runtime.getRuntime().availableProcessors());
        double[][] matrixA = MatrixUtils.generateRandomMatrix(1000, 1000);
        double[][] matrixB = MatrixUtils.generateRandomMatrix(1000, 1000);

        // Measure Basic Algorithm Time and Memory
        long basicTime = BenchmarkUtility.measureExecutionTimeWithResources(() ->
                BasicMatrixMultiplication.multiply(matrixA, matrixB)
        );

        // Measure Parallel Algorithm Time and Memory
        int numThreads = 4; // Ensure thread count matches actual implementation
        long parallelTime = BenchmarkUtility.measureExecutionTimeWithResources(() ->
                ParallelMatrixMultiplication.multiply(matrixA, matrixB, numThreads)
        );

        // Calculate Speedup and Efficiency
        double speedup = (double) basicTime / parallelTime;
        double efficiency = speedup / numThreads;

        // Cap efficiency to 1 if it exceeds logical bounds
        efficiency = Math.min(efficiency, 1.0);

        // Print results
        System.out.println("Basic Algorithm Time: " + basicTime / 1e6 + " ms");
        System.out.println("Parallel Algorithm Time: " + parallelTime / 1e6 + " ms");
        System.out.println("Speedup: " + speedup);
        System.out.println("Efficiency: " + efficiency);

        // Assertions to ensure performance improvement
        assertTrue(speedup > 1, "Speedup should be greater than 1");
        assertTrue(efficiency > 0.5, "Efficiency should be reasonable (e.g., >50%)");
    }
}
