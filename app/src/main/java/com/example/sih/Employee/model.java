package com.example.sih.Employee;

public class model {
    String scholarShipName , amount , information;

    public model(String scholarShipName, String amount, String information) {
        this.scholarShipName = scholarShipName;
        this.amount = amount;
        this.information = information;
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
