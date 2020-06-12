
package com.example.volley;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {


    private String id;
    private String name;
    private String email;
    private String gender;

    public User(){}
    public User(String id,String name,String email,String gender)
    {
        this.id=id;
        this.name=name;
        this.email=email;
        this.gender=gender;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

}
