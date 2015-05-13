package com.dip.common.service.task;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.EnumMap;

public abstract class AbstractBaseBackgroundTask implements IBaseBackgroundTask {
    private EnumMap<? extends Enum<?>, Object> backgroundTaskArgs;
    protected Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 
     */
    public AbstractBaseBackgroundTask() {
    }

    /**
     * @param backgroundTaskArgs
     */
    public AbstractBaseBackgroundTask(
            EnumMap<? extends Enum<?>, Object> backgroundTaskArgs)
            throws Exception {
        setBackgroundTaskArgs(backgroundTaskArgs);
    }

    /**
     * @return the backgroundTaskArgs
     */
    protected EnumMap<? extends Enum<?>, Object> getBackgroundTaskArgs() {
        return backgroundTaskArgs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.common.service.task.IErpBackgroundTask#setBackgroundTaskArgs
     * ( java.util.EnumMap)
     */
    @Override
    public void setBackgroundTaskArgs(
            EnumMap<? extends Enum<?>, Object> backgroundTaskArgs)
            throws Exception {
        this.backgroundTaskArgs = backgroundTaskArgs;
    }

}
