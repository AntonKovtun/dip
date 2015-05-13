package com.dip.frontend.web.model;

public class LoginModel extends AbstractDataModel {
    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private Boolean keepMeLoggedIn;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Boolean getKeepMeLoggedIn() {
        return keepMeLoggedIn;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setKeepMeLoggedIn(Boolean keepMeLoggedIn) {
        this.keepMeLoggedIn = keepMeLoggedIn;
    }

}
