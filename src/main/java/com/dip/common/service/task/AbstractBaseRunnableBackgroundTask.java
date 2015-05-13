package com.dip.common.service.task;

public abstract class AbstractBaseRunnableBackgroundTask extends
        AbstractBaseBackgroundTask implements IBaseRunnableBackgroundTask {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.eharvest.erp.common.service.task.IErpRunnableBackgroundTask#isDaemon
     * ()
     */
    @Override
    public boolean isDaemon() {
        return false;
    }
    
    @Override
    public IBaseRunnableBackgroundTask cloneTask() throws Exception {
        return (IBaseRunnableBackgroundTask) super.clone();
    }

}
