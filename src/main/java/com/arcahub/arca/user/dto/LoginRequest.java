package com.arcahub.arca.user.dto;

import com.arcahub.arca.user.dto.login.EmailLoginRequest;
import com.arcahub.arca.user.dto.login.UsernameLoginRequest;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "loginType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = UsernameLoginRequest.class, name = "USERNAME"),
        @JsonSubTypes.Type(value = EmailLoginRequest.class, name = "EMAIL")
})
public abstract class LoginRequest {
    private String loginType;

    public String getLoginType() {
        return loginType;
    }
    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
