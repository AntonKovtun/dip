package com.dip.frontend.web.model;

import com.dip.common.constant.Status;

public class ReferenceBookModel extends AbstractDataModel {
    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String description;
    private Status status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

}
