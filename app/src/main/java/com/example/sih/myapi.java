package com.example.sih;

import com.example.sih.Employee.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface myapi {

        @GET("/scholarship/{id}")
        Call<List<model>>getScholarshipData();

        @POST("/scholarship")
        Call<model>addData(@Body model m);

}

