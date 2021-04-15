package com.proiect.lamunca.entity.db1;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "review")
@Data
public class Review implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review", nullable = false)
    private int id_review;

    @Column(name = "comment", nullable = false)
    private String comment;

    @Column(name = "writer_username", nullable = false)
    private String writer_username;

    @ManyToOne
    @JoinColumn(name = "receiver_username", referencedColumnName = "id_user", nullable = false)
    private User username;

    @Column(name = "stars", nullable = false)
    private int stars;

    public Review() {
    }

    public Review(String comment, String writer_username, User username, int stars) {
        this.comment = comment;
        this.writer_username = writer_username;
        this.username = username;
        this.stars = stars;
    }

    public int getId_review() {
        return id_review;
    }

    public void setId_review(int id_review) {
        this.id_review = id_review;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getWriter_username() {
        return writer_username;
    }

    public void setWriter_username(String writer_username) {
        this.writer_username = writer_username;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
