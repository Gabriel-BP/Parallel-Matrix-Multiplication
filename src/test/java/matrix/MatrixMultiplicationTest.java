package matrix;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MatrixMultiplicationTest {
    @Test
    public void testPerformanceMetrics() {
        System.out.println("Available Cores: " + Runtime.getRuntime().availableProcessors());
        double[][] matrixA = MatrixUtils.generateRandomMatrix(1024, 1024);
        double[][] matrixB = MatrixUtils.generateRandomMatrix(1024, 1024);

        // Measure Basic Algorithm Time and Memory
        long basicTime = BenchmarkUtility.measureExecutionTimeWithResources(() ->
                BasicMatrixMultiplication.multiply(matrixA, matrixB)
        );

        // Measure Parallel Algorithm Time and Memory
        int numThreads = 4; // Ensure thread count matches actual implementation
        long parallelTime = BenchmarkUtility.measureExecutionTimeWithResources(() ->
                ParallelMatrixMultiplication.multiply(matrixA, matrixB, numThreads)
        );

        // Measure Vectorized Algorithm Time and Memory
        long vectorizedTime = BenchmarkUtility.measureExecutionTimeWithResources(() ->
                VectorizedMatrixMultiplication.multiply(matrixA, matrixB)
        );

        // Calculate Speedup and Efficiency
        double parallelSpeedup = (double) basicTime / parallelTime;
        double parallelEfficiency = parallelSpeedup / numThreads;

        double vectorizedSpeedup = (double) basicTime / vectorizedTime;

        // Cap efficiency to 1 if it exceeds logical bounds
        parallelEfficiency = Math.min(parallelEfficiency, 1.0);

        // Print results
        System.out.println("Basic Algorithm Time: " + basicTime / 1e6 + " ms");
        System.out.println("Parallel Algorithm Time: " + parallelTime / 1e6 + " ms");
        System.out.println("Vectorized Algorithm Time: " + vectorizedTime / 1e6 + " ms");
        System.out.println("Parallel Speedup: " + parallelSpeedup);
        System.out.println("Parallel Efficiency: " + parallelEfficiency);
        System.out.println("Vectorized Speedup: " + vectorizedSpeedup);

        // Assertions to ensure performance improvement
        assertTrue(parallelSpeedup > 1, "Parallel speedup should be greater than 1");
        assertTrue(parallelEfficiency > 0.5, "Parallel efficiency should be reasonable (e.g., >50%)");
        assertTrue(vectorizedSpeedup > 1, "Vectorized speedup should be greater than 1");
    }
}
