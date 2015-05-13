package com.sulin.backend.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * @author Sergei Barinov
 * 
 */
@MappedSuperclass
public abstract class BaseComplianceEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = true, name = "LAST_UPDATE_TIME")
    private Date lastUpdateTime;
    @Column(nullable = true, length = 32, name = "UPDATE_BY_USER_ID")
    private String updateByUserId;
    @Column(nullable = true, name = "CREATE_TIME")
    private Date createTime;
    @Column(nullable = true, length = 32, name = "CREATE_BY_USER_ID")
    private String createByUserId;

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getUpdateByUserId() {
        return updateByUserId;
    }

    public void setUpdateByUserId(String updateByUserId) {
        this.updateByUserId = updateByUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateByUserId() {
        return createByUserId;
    }

    public void setCreateByUserId(String createByUserId) {
        this.createByUserId = createByUserId;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseComplianceEntity [lastUpdateTime=");
        builder.append(lastUpdateTime);
        builder.append(", updateByUserId=");
        builder.append(updateByUserId);
        builder.append(", createTime=");
        builder.append(createTime);
        builder.append(", createByUserId=");
        builder.append(createByUserId);
        builder.append("]");
        return builder.toString();
    }

    @PreUpdate
    public void updateTime() {
        // setLastUpdateTimeMillis(TimestampUtil.getCurrentTimeMillis());
        setLastUpdateTime(Calendar.getInstance().getTime());
    }

    @PrePersist
    public void prePersist() {
        // setCreateTimeMillis(TimestampUtil.getCurrentTimeMillis());
        setCreateTime(Calendar.getInstance().getTime());
        setLastUpdateTime(getCreateTime());
    }
}
