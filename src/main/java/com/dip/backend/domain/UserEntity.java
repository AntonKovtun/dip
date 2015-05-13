package com.dip.backend.domain;

import com.dip.common.constant.Status;
import com.dip.common.constant.UserType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")
@AttributeOverride(name = "id", column = @Column(name = "USER_ID", length = 32))
public class UserEntity extends BaseComplianceEntity {

    @Column(name = "NAME", length = 512, nullable = false)
    private String name;

    @Column(name = "EMAIL", length = 128, nullable = false)
    private String email;

    @Column(name = "PASSWORD", length = 32, nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, name = "USER_TYPE")
    private UserType userType;

    @Column(name = "LOGIN", length = 128, nullable = false)
    private String login;

    @Column(name = "PHONE", length = 32, nullable = true)
    private String phone;

    @Column(name = "DESCRIPTION", length = 512, nullable = true)
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
