package com.example.nyxulric.simpleappui;

public class UserInfo {
    public String names;
    public double latitude;
    public double longitude;

    public UserInfo(){    }

    public UserInfo(String names, double latitude, double longitude) {
        this.names = names;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
