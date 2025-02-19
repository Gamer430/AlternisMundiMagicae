package org.magicmaker.alternismundimagicae.classes;

import net.minecraft.server.MinecraftServer;

import java.util.PriorityQueue;

public class TaskScheduler {
    private static final PriorityQueue<ScheduledTask> TASKS = new PriorityQueue<>();

    public static void schedule(Runnable task, long delayTicks) {
        long executionTick = getCurrentTick() + delayTicks;
        TASKS.add(new ScheduledTask(task, executionTick));
    }

    public static void tick(MinecraftServer server) {
        long currentTick = getCurrentTick();

        while (!TASKS.isEmpty() && TASKS.peek().executionTick <= currentTick) {
            ScheduledTask task = TASKS.poll();
            if (task != null) {
                task.task.run();
            }
        }
    }

    private static long getCurrentTick() {
        return System.currentTimeMillis() / 50; // Convert milliseconds to ticks (20 TPS)
    }

    private static class ScheduledTask implements Comparable<ScheduledTask> {
        private final Runnable task;
        private final long executionTick;

        public ScheduledTask(Runnable task, long executionTick) {
            this.task = task;
            this.executionTick = executionTick;
        }

        @Override
        public int compareTo(ScheduledTask other) {
            return Long.compare(this.executionTick, other.executionTick);
        }
    }
}