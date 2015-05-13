package com.dip.common.searchbean;

import com.dip.common.constant.Status;
import com.dip.common.constant.UserType;

import java.util.List;

public class UserSearchBean extends AbstractSearchBean<String> {
    private String name;
    private String description;
    private String email;
    private String phone;
    private Status status;
    private UserType userType;
    private String login;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public UserSearchBean(String key) {
        super(key);
    }

    public UserSearchBean() {
        super();
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
}
