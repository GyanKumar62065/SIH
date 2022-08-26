package com.example.sih.Network;

import com.example.sih.model.ApplyAffiliationFormModel;
import com.example.sih.model.EmployeeRegister;
import com.example.sih.model.FellowshipModel;
import com.example.sih.model.LoginRequest;
import com.example.sih.model.RegistationRequest;
import com.example.sih.model.ScholarShipFormModel;
import com.example.sih.model.ScholarshipStudentFormModel;
import com.example.sih.model.UsersResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
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
    Call<List<UsersResponse>> getEmployeeUserData(@Path("id") String cnadidateID, @Header("Authorization") String authorization);

    @GET("user/{id}/affiliator")
    Call<List<UsersResponse>> getAffiliatorUserData(@Path("id") String cnadidateID, @Header("Authorization") String authorization);

    @GET("scholarship")
    Call<List<ScholarShipFormModel>> getScholarship(@Header("Authorization") String authorization);

    @GET("scholarship/{id}")
    Call<ScholarShipFormModel> getScholarshipById(@Path("id") String id, @Header("Authorization") String authorization);

    @POST("scholarship")
    Call<ScholarShipFormModel> setScholarShip(@Body ScholarShipFormModel body, @Header("Authorization") String authentication);

    @DELETE("scholarship/{id}")
    Call<String> deleteScholarShipById(@Path("id") String id, @Header("Authorization") String authentication);

    @POST("scholarshipform")
    Call<ScholarshipStudentFormModel> setScholarshipFrom(@Body ScholarshipStudentFormModel body, @Header("Authorization") String authentication);

    @GET("showNonApprovedScholarshipForms")
    Call<List<ScholarshipStudentFormModel>> getAllPendingForms(@Header("Authorization") String authentication);


    @GET("showApprovedScholarshipForms")
    Call<List<ScholarshipStudentFormModel>> getAllApprovedForms(@Header("Authorization") String authentication);

    @POST("affiliationform")
    Call<ApplyAffiliationFormModel> setAffiliationFormData(@Body ApplyAffiliationFormModel applyAffiliationFormModel, @Header("Authorization") String authentication);

    @GET("scholarshipformofcollege/{id}")
    Call<List<ScholarshipStudentFormModel>> getAllStudentDataByCollegeId(@Path("id") String id, @Header("Authorization") String auth);

    @GET("scholarshipformsbyapplicationid/{id}")
    Call<ScholarshipStudentFormModel> getScholarshipDataByApplicationId(@Path("id") String id , @Header("Authorization") String auth);

    @PATCH("scholarshipform/{userId}/{applicationId}")
    Call<ScholarshipStudentFormModel>setStudentApproved(@Path("userId") String userId , @Path("applicationId") String applicationId, @Header("Authorization") String auth);

    @PATCH("scholarshipform/{userId}/{applicationId}")
    Call<ScholarshipStudentFormModel>setStudentRejected(@Path("userId") String userId , @Path("applicationId") String applicationId, @Header("Authorization") String auth);

    @DELETE("scholarshipform/{applicationId}")
    Call<ScholarshipStudentFormModel>deleteScholarshipForm(@Path("applicationId") String applicationId ,@Header("Authorization") String authentication );

    @GET("showNonAffiliatedColleges")
    Call<List<FellowshipModel>>getAllPendingFellowshipFroms(@Header("Authorization") String authentication);

    @GET("showAffiliatedColleges")
    Call<List<FellowshipModel>>getAllApprovedFellowshipFroms(@Header("Authorization") String authentication);


}
