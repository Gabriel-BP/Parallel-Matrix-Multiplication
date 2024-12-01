package matrix;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

public class BenchmarkUtility {

    public static long measureExecutionTimeWithResources(Runnable task) {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long beforeUsedMem = memoryBean.getHeapMemoryUsage().getUsed();
        long startTime = System.nanoTime();

        task.run();

        long endTime = System.nanoTime();
        long afterUsedMem = memoryBean.getHeapMemoryUsage().getUsed();

        long memoryUsed = (afterUsedMem - beforeUsedMem) / (1024 * 1024); // In MB
        System.out.println("Memory used: " + memoryUsed + " MB");

        return endTime - startTime;
    }
}

