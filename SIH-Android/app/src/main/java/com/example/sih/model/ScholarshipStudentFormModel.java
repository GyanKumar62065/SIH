package com.example.sih.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarshipStudentFormModel {
    public ScholarshipStudentFormModel() {
    }

    @SerializedName("applicationId")
    @Expose
    private Integer applicationId;
    @SerializedName("studentName")
    @Expose
    private String studentName;
    @SerializedName("govtId")
    @Expose
    private String govtId;
    @SerializedName("seniorSecondaryPercentage")
    @Expose
    private Float seniorSecondaryPercentage;
    @SerializedName("secondaryPercentage")
    @Expose
    private Float secondaryPercentage;
    @SerializedName("collegeId")
    @Expose
    private Integer collegeId;
    @SerializedName("scholarshipId")
    @Expose
    private Integer scholarshipId;
    @SerializedName("collegeName")
    @Expose
    private Object collegeName;
    @SerializedName("studentAddress")
    @Expose
    private Object studentAddress;
    @SerializedName("dateOfRegistration")
    @Expose
    private String dateOfRegistration;
    @SerializedName("multipartFile")
    @Expose
    private Object multipartFile;
    @SerializedName("studentImage")
    @Expose
    private Object studentImage;
    @SerializedName("emailId")
    @Expose
    private Object emailId;
    @SerializedName("studentContactNumber")
    @Expose
    private Object studentContactNumber;
    @SerializedName("studentCity")
    @Expose
    private Object studentCity;
    @SerializedName("studentState")
    @Expose
    private Object studentState;
    @SerializedName("studentId")
    @Expose
    private Integer studentId;
    @SerializedName("scholared")
    @Expose
    private Boolean scholared;
    @SerializedName("pending")
    @Expose
    private Boolean pending;
    @SerializedName("rejected")
    @Expose
    private Boolean rejected;

    public ScholarshipStudentFormModel(String studentName, String govtId, float seniorSecondaryPercentage, float secondaryPercentage, int collegeId, int scholarshipId, String collegeName, String studentAddress, String emailId, String studentContactNumber, String studentCity, String studentState, int studentId) {
        this.studentName = studentName;
        this.govtId = govtId;
        this.seniorSecondaryPercentage = seniorSecondaryPercentage;
        this.secondaryPercentage = secondaryPercentage;
        this.collegeId = collegeId;
        this.scholarshipId = scholarshipId;
        this.collegeName = collegeName;
        this.studentAddress = studentAddress;
        this.emailId = emailId;
        this.studentContactNumber = studentContactNumber;
        this.studentCity = studentCity;
        this.studentState = studentState;
        this.studentId = studentId;
    }

    public Integer getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Integer applicationId) {
        this.applicationId = applicationId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getGovtId() {
        return govtId;
    }

    public void setGovtId(String govtId) {
        this.govtId = govtId;
    }

    public Float getSeniorSecondaryPercentage() {
        return seniorSecondaryPercentage;
    }

    public void setSeniorSecondaryPercentage(Float seniorSecondaryPercentage) {
        this.seniorSecondaryPercentage = seniorSecondaryPercentage;
    }

    public Float getSecondaryPercentage() {
        return secondaryPercentage;
    }

    public void setSecondaryPercentage(Float secondaryPercentage) {
        this.secondaryPercentage = secondaryPercentage;
    }

    public Integer getCollegeId() {
        return collegeId;
    }

    public void setCollegeId(Integer collegeId) {
        this.collegeId = collegeId;
    }

    public Integer getScholarshipId() {
        return scholarshipId;
    }

    public void setScholarshipId(Integer scholarshipId) {
        this.scholarshipId = scholarshipId;
    }

    public Object getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(Object collegeName) {
        this.collegeName = collegeName;
    }

    public Object getStudentAddress() {
        return studentAddress;
    }

    public void setStudentAddress(Object studentAddress) {
        this.studentAddress = studentAddress;
    }

    public String getDateOfRegistration() {
        return dateOfRegistration;
    }

    public void setDateOfRegistration(String dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }

    public Object getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(Object multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Object getStudentImage() {
        return studentImage;
    }

    public void setStudentImage(Object studentImage) {
        this.studentImage = studentImage;
    }

    public Object getEmailId() {
        return emailId;
    }

    public void setEmailId(Object emailId) {
        this.emailId = emailId;
    }

    public Object getStudentContactNumber() {
        return studentContactNumber;
    }

    public void setStudentContactNumber(Object studentContactNumber) {
        this.studentContactNumber = studentContactNumber;
    }

    public Object getStudentCity() {
        return studentCity;
    }

    public void setStudentCity(Object studentCity) {
        this.studentCity = studentCity;
    }

    public Object getStudentState() {
        return studentState;
    }

    public void setStudentState(Object studentState) {
        this.studentState = studentState;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Boolean getScholared() {
        return scholared;
    }

    public void setScholared(Boolean scholared) {
        this.scholared = scholared;
    }

    public Boolean getPending() {
        return pending;
    }

    public void setPending(Boolean pending) {
        this.pending = pending;
    }

    public Boolean getRejected() {
        return rejected;
    }

    public void setRejected(Boolean rejected) {
        this.rejected = rejected;
    }
}
