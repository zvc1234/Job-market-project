package com.proiect.lamunca.models;

public class ShortUserModel {
    private String username;
    private String phone;
    private double stars_average;

    public ShortUserModel(String username, String phone, double stars_average) {
        this.username = username;
        this.phone = phone;
        this.stars_average = stars_average;
    }

    public ShortUserModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getStars_average() {
        return stars_average;
    }

    public void setStars_average(double stars_average) {
        this.stars_average = stars_average;
    }
}
