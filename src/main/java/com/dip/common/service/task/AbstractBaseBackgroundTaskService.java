package com.dip.common.service.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.EnumMap;

public abstract class AbstractBaseBackgroundTaskService {

    protected Logger log = LoggerFactory.getLogger(getClass());

    protected TaskExecutor daemonTaskExecutor;

    protected TaskExecutor taskExecutor;

    public void addTask(IBaseRunnableBackgroundTask task,
            EnumMap<? extends Enum<?>, Object> backgroundTaskArgs)
            throws Exception {

        log.info("Add task {}", task.getClass().getName());

        task = task.cloneTask();
        task.setBackgroundTaskArgs(backgroundTaskArgs);
        if (task.isDaemon())
            daemonTaskExecutor.execute(task);
        else
            taskExecutor.execute(task);
    }

    public boolean isFull() {
        ThreadPoolTaskExecutor te = ((ThreadPoolTaskExecutor) taskExecutor);
        return te.getActiveCount() > te.getCorePoolSize();
    }

}
