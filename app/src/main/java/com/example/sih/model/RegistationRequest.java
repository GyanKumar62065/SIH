package com.example.sih.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistationRequest {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("emailId")
    @Expose
    private String emailId;
    @SerializedName("role")
    @Expose
    private String role;

    public RegistationRequest(String name, String password, String emailId, String role) {
        this.name = name;
        this.password = password;
        this.emailId = emailId;
        this.role = role;
    }
}
