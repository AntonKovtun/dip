package com.dip.common.service.task.impl;



import com.dip.common.service.task.AbstractBaseBackgroundTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

@Service
public class BaseBackgroundTaskService extends AbstractBaseBackgroundTaskService {

    @Autowired
    public BaseBackgroundTaskService(TaskExecutor taskExecutor, TaskExecutor daemonTaskExecutor) {
        this.daemonTaskExecutor = daemonTaskExecutor;
        this.taskExecutor = taskExecutor;
    }

    public boolean isFinished() throws Exception {
        return ((ThreadPoolTaskExecutor) taskExecutor).getActiveCount() == 0;
    }
}
