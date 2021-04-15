package com.proiect.lamunca.entity.db1;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_jobs")
@Data
public class UserJob implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_userjob", nullable = false)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_job", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name="job_category", referencedColumnName = "id_usercategory", nullable = false)
    private UserCategory user_category;

    @Column(name = "owner")
    private Integer owner;

    public UserJob() {
    }

    public UserJob(User user, UserCategory user_category, Integer owner) {
        this.user = user;
        this.user_category = user_category;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserCategory getUser_category() {
        return user_category;
    }

    public void setUser_category(UserCategory user_category) {
        this.user_category = user_category;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }
}
