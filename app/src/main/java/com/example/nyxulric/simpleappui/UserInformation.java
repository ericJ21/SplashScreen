package com.example.nyxulric.simpleappui;

import android.net.Uri;

public class UserInformation {

    public String username;
    public String email;

    public UserInformation(){

    }

    public UserInformation(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

