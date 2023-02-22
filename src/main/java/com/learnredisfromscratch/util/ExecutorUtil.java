package com.learnredisfromscratch.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorUtil {

    private ExecutorUtil() {
    }

    private static ExecutorService executorService;

    public static ExecutorService getExecutorService() {

        if (executorService != null) {
            return executorService;
        }

        synchronized (ExecutorUtil.class) {

            if (executorService == null) {
                executorService = Executors.newFixedThreadPool(
                    2, 
                    runnable -> {
                    Thread t = new Thread(runnable);
                    t.setDaemon(true);
                    return t;
                });
            }

            return executorService;
        }
    }
}
