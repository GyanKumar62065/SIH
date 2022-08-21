package com.example.sih.Network;

import com.example.sih.model.EmployeeRegister;
import com.example.sih.model.LoginRequest;
import com.example.sih.model.RegistationRequest;
import com.example.sih.model.ScholarShipFormModel;
import com.example.sih.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Service {

    @POST("register")
    Call<RegistationRequest> registation(@Body RegistationRequest body);

    @POST("register")
    Call<EmployeeRegister> registation(@Body EmployeeRegister body);

    @POST("login")
    Call<LoginRequest> login(@Body LoginRequest body);

    @GET("user/{emailId}")
    Call<UsersResponse> getData(@Path("emailId") String cnadidateEmailID, @Header("Authorization") String authorization);

    @GET("user/{id}/employee")
    Call<List<UsersResponse>> getEmployeeUserData( @Path("id") String cnadidateID,@Header("Authorization") String authorization);

    @GET("user/{id}/affiliator")
    Call<List<UsersResponse>> getAffiliatorUserData( @Path("id") String cnadidateID,@Header("Authorization") String authorization);

    @GET("scholarship")
    Call<List<ScholarShipFormModel>> getScholarship(@Header("Authorization") String authorization );

    @POST("scholarship")
    Call<ScholarShipFormModel> setScholarShip(@Body ScholarShipFormModel body , @Header("Authorization") String authentication);

}
