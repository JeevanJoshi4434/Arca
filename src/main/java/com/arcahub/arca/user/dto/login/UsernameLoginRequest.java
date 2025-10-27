package com.arcahub.arca.user.dto.login;

import com.arcahub.arca.user.dto.LoginRequest;

public class UsernameLoginRequest  extends LoginRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
