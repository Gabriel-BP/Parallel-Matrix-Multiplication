package matrix;

public class BenchmarkUtility {
    public static long measureExecutionTimeWithResources(Runnable task) {
        Runtime runtime = Runtime.getRuntime();

        // Force garbage collection to get a stable baseline
        runtime.gc();
        long beforeMemory = runtime.totalMemory() - runtime.freeMemory();

        long start = System.nanoTime();
        task.run();
        long duration = System.nanoTime() - start;

        runtime.gc();
        long afterMemory = runtime.totalMemory() - runtime.freeMemory();

        // Use absolute value to avoid negative results
        long memoryUsed = Math.abs(afterMemory - beforeMemory);
        System.out.println("Memory Used: " + memoryUsed / (1024 * 1024) + " MB");

        return duration;
    }
}
