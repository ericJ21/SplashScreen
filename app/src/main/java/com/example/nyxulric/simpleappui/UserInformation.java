package com.example.nyxulric.simpleappui;

import android.net.Uri;

public class UserInformation {

    public String username;
    public String email;
    public String imageAddress;

    public UserInformation(){

    }

    public UserInformation(String username, String email, String imageAddress) {
        this.username = username;
        this.email = email;
        this.imageAddress = imageAddress;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
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

