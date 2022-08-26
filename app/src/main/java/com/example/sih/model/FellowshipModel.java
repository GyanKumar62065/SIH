package com.example.sih.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FellowshipModel {

    @SerializedName("applicationFormId")
    @Expose
    private Integer applicationFormId;
    @SerializedName("collegeName")
    @Expose
    private String collegeName;
    @SerializedName("collegeAddress")
    @Expose
    private String collegeAddress;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("dateOfRegistration")
    @Expose
    private String dateOfRegistration;
    @SerializedName("relatedDocsLinks")
    @Expose
    private String relatedDocsLinks;
    @SerializedName("isAffiliated")
    @Expose
    private Boolean isAffiliated;
    @SerializedName("isPending")
    @Expose
    private Boolean isPending;
    @SerializedName("isRejected")
    @Expose
    private Boolean isRejected;
    @SerializedName("collegeEmailId")
    @Expose
    private String collegeEmailId;
    @SerializedName("collegeContactNumber")
    @Expose
    private String collegeContactNumber;
    @SerializedName("collegeCity")
    @Expose
    private String collegeCity;
    @SerializedName("collegeState")
    @Expose
    private String collegeState;
    @SerializedName("collegeId")
    @Expose
    private Integer collegeId;

    public FellowshipModel() {
    }

    public Integer getApplicationFormId() {
        return applicationFormId;
    }

    public void setApplicationFormId(Integer applicationFormId) {
        this.applicationFormId = applicationFormId;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getCollegeAddress() {
        return collegeAddress;
    }

    public void setCollegeAddress(String collegeAddress) {
        this.collegeAddress = collegeAddress;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public String getRelatedDocsLinks() {
        return relatedDocsLinks;
    }

    public void setRelatedDocsLinks(String relatedDocsLinks) {
        this.relatedDocsLinks = relatedDocsLinks;
    }

    public Boolean getAffiliated() {
        return isAffiliated;
    }

    public void setAffiliated(Boolean affiliated) {
        isAffiliated = affiliated;
    }

    public Boolean getPending() {
        return isPending;
    }

    public void setPending(Boolean pending) {
        isPending = pending;
    }

    public Boolean getRejected() {
        return isRejected;
    }

    public void setRejected(Boolean rejected) {
        isRejected = rejected;
    }

    public String getCollegeEmailId() {
        return collegeEmailId;
    }

    public void setCollegeEmailId(String collegeEmailId) {
        this.collegeEmailId = collegeEmailId;
    }

    public String getCollegeContactNumber() {
        return collegeContactNumber;
    }

    public void setCollegeContactNumber(String collegeContactNumber) {
        this.collegeContactNumber = collegeContactNumber;
    }

    public String getCollegeCity() {
        return collegeCity;
    }

    public void setCollegeCity(String collegeCity) {
        this.collegeCity = collegeCity;
    }

    public String getCollegeState() {
        return collegeState;
    }

    public void setCollegeState(String collegeState) {
        this.collegeState = collegeState;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }
}
