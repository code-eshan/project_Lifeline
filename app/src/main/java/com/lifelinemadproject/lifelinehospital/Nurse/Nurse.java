package com.lifelinemadproject.lifelinehospital.Nurse;

//Creating class to enter all nurse details...
public class Nurse {
//Creating all the necessary attributes

    private String sid;
    private String fName;
    private String lName;
    private String Nic;
    private String userName;
    private String password;

    public Nurse() {
    }

    public Nurse(String sid, String fName, String lName, String nic, String userName, String password) {
        this.sid = sid;
        this.fName = fName;
        this.lName = lName;
        Nic = nic;
        this.userName = userName;
        this.password = password;
    }



    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getNic() {
        return Nic;
    }

    public void setNic(String Nic) {
        this.Nic = Nic;
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
