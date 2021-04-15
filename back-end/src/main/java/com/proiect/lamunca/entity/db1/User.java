package com.proiect.lamunca.entity.db1;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "phone", nullable = false)
    private String phone;


    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "first_name", nullable = false)
    private String first_name;

    @Column(name = "last_name", nullable = false)
    private String last_name;

    @Column(name = "bio")
    private String bio;

    @Column(name = "date_of_birth", nullable = false)
    private Date date_of_birth;

    @Column(name = "stars_average")
    private double stars_average;

    public User() {
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String username) {
        this.username = username;
    }

    public User(String username, String password, String phone, String email, String first_name, String last_name, String bio, Date date_of_birth, double stars_average) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.bio = bio;
        this.date_of_birth = date_of_birth;
        this.stars_average = stars_average;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public double getStars_average() {
        return stars_average;
    }

    public void setStars_average(double stars_average) {
        this.stars_average = stars_average;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", bio='" + bio + '\'' +
                ", date_of_birth=" + date_of_birth +
                ", stars_average=" + stars_average +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
