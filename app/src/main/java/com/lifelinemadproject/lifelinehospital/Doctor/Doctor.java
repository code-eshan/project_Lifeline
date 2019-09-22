package com.lifelinemadproject.lifelinehospital.Doctor;

//Creating class to record doctor details
public class Doctor {

    private String stfID;
    private String NIC;
    private String fName;
    private String lName;
    private String contNum;
    private String userName;
    private String password;

    public Doctor() {
    }

    public String getStfID() { return stfID; }

    public void setStfID(String stfID) { this.stfID = stfID; }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getContNum() {
        return contNum;
    }

    public void setContNum(String contNum) {
        this.contNum = contNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
