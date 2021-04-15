package com.proiect.lamunca.models;

import java.io.Serializable;

public class JobModel implements Serializable {
    private Integer id;
    private String title;
    private String description;
    private String categoryName;
    private String username;

    public JobModel(Integer id, String title, String description, String categoryName, String username) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.username = username;
    }

    public JobModel(String title, String description, String categoryName, String username) {
        this.title = title;
        this.description = description;
        this.categoryName = categoryName;
        this.username = username;
    }

    public JobModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
