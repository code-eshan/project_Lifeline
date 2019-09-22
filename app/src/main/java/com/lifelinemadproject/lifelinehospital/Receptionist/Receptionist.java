package com.lifelinemadproject.lifelinehospital.Receptionist;

//Creating class for receptionist
public class Receptionist {

    private String sID;
    private String fName;
    private String lName;
    private String userName;
    private String password;

    public Receptionist() {
    }

    public String getsID() {
        return sID;
    }

    public void setNIC(String NIC) {
        this.sID = NIC;
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
