package models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import utils.IModel;

/**
 *  Password should not be included here
 *  This is for prototyping purposes only - author
 */
public class User implements IModel {
    public static User currentUser = null;
    private String id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String mobileNo;
    private String telephoneNo;

    public User(JSONObject userObj) throws JSONException {
        if (userObj.getString("_id") == "")
            this.id = "";
        else this.id = userObj.getString("_id");

        this.name = userObj.getString("name");
        this.address = userObj.getString("address");

        JSONObject contactInfo = new JSONObject(userObj.getString("contactInfo"));
        this.email = contactInfo.getString("email");
        this.mobileNo = contactInfo.getString("mobileNo");
        this.telephoneNo = contactInfo.getString("name");
    }

    public User(String name, String email, String password, String address, String mobileNo, String telephoneNo) {
        this.id = "";
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.mobileNo = mobileNo;
        this.telephoneNo = telephoneNo;
    }

    public User(String id, String name, String email, String password, String address, String mobileNo, String telephoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
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

    public String GetPassword() {
        return this.password;
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

    @Override
    public JSONObject ToJsonObject() throws JSONException {
        JSONObject userObj = new JSONObject();

        if (!Objects.equals(this.id, ""))
            userObj.put("_id", this.id);

        userObj.put("name", this.name);
        userObj.put("address", this.address);

        userObj.put("mobile", this.mobileNo);
        userObj.put("email", this.email);
        userObj.put("password", this.password);
        userObj.put("telephone", this.telephoneNo);

        return userObj;
    }
}
