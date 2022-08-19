package com.example.sih.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    int id;
    @SerializedName("username")
    @Expose
    private final String username;
    @SerializedName("password")
    @Expose
    private final String password;
    @SerializedName("token")
    @Expose
    private String token;

    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
