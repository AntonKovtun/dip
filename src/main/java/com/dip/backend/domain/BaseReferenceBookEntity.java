package com.sulin.backend.domain;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.sulin.common.constant.Status;

/**
 * @author anton
 * 
 */
@MappedSuperclass
public abstract class BaseReferenceBookEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(nullable = false, length = 128, name = "NAME")
    private String name;
    @Column(nullable = true, length = 255, name = "DESCRIPTION")
    private String description;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "STATUS")
    private Status status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseReferenceBookEntity other = (BaseReferenceBookEntity) obj;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (status != other.status)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BaseReferenceBookEntity [name=" + name + ", description=" + description + ", status=" + status + "]";
    }

}
