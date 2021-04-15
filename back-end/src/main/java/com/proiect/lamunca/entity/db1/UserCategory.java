package com.proiect.lamunca.entity.db1;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_category")
@Data
public class UserCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usercategory", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name="name_category", referencedColumnName = "id_category", nullable = false)
    private Category category;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    public UserCategory() {
    }

    public UserCategory( Category category, String title, String description) {
        this.category = category;
        this.title = title;
        this.description = description;
    }


    public UserCategory(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

}
