package com.example.sih.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ScholarShipFormModel {
    @SerializedName("scholarShipId")
    @Expose
    private Integer scholarShipId;
    @SerializedName("scholarShipName")
    @Expose
    private String scholarShipName;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("information")
    @Expose
    private String information;

    public ScholarShipFormModel( String scholarShipName, String amount, String information) {
        this.scholarShipName = scholarShipName;
        this.amount = amount;
        this.information = information;
    }

    public Integer getScholarShipId() {
        return scholarShipId;
    }

    public void setScholarShipId(Integer scholarShipId) {
        this.scholarShipId = scholarShipId;
    }

    public String getScholarShipName() {
        return scholarShipName;
    }

    public void setScholarShipName(String scholarShipName) {
        this.scholarShipName = scholarShipName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
