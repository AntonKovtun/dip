package com.dip.common.dto;

import com.dip.common.constant.Status;
import com.dip.common.constant.UserType;

import java.util.Date;
import java.util.List;

public class UserDto {
    private String id;
    private String name;
    private String description;
    private String email;
    private String password;
    private String phone;
    private UserType userType;
    private Status status;
    private String login;

    private Date lastUpdateTime;
    private String updateByUserId;
    private Date createTime;
    private String createByUserId;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserDto() {
    }

    public UserDto(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDto userDto = (UserDto) o;

        if (id != null ? !id.equals(userDto.id) : userDto.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
