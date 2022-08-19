package com.example.sih.API;

import com.example.sih.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeApi {

    @GET("/user/1")
    Call<List<UsersResponse>> getUserDeatils();




}
