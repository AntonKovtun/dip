package com.sulin.common.service.task;


import java.util.EnumMap;

/**
 * @author Alexander Dukkardt
 * 
 */
public interface IBaseBackgroundTask extends Cloneable {

    /**
     * @param backgroundTaskArgs
     *            the backgroundTaskArgs to set
     */
    public void setBackgroundTaskArgs(
            EnumMap<? extends Enum<?>, Object> backgroundTaskArgs)
            throws Exception;

}
