package com.proiect.lamunca.models;

import java.io.Serializable;

public class ShortJobModel implements Serializable {
    private Integer id;
    private String category;
    private String title;

    public ShortJobModel() {
    }

    public ShortJobModel(String categoryName, String username, String title) {
        this.category = categoryName;
        this.title = title;
    }

    public ShortJobModel(Integer id, String categoryName, String username, String title) {
        this.id = id;
        this.category = categoryName;
        this.title = title;
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

    public String getCategory() {
        return category;
    }

    public void setCategoryName(String categoryName) {
        this.category    = categoryName;
    }
}
