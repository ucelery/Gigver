package models;

public class User {
    private String id;
    private String name;
    private String email;
    private String address;
    private String mobileNo;
    private String telephoneNo;

    public User(String id, String name, String email, String address, String mobileNo, String telephoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobileNo = mobileNo;
        this.telephoneNo = telephoneNo;
    }

    public String GetID() {
        return this.id;
    }

    public String GetName() {
        return this.name;
    }

    public String GetEmail() {
        return this.email;
    }

    public String GetAddress() {
        return this.address;
    }

    public String GetMobileNo() {
        return this.mobileNo;
    }

    public String GetTelephoneNo() {
        return this.telephoneNo;
    }
}
