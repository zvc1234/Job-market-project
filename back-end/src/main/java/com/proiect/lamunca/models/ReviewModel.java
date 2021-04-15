package com.proiect.lamunca.models;

public class ReviewModel {
    private String comment;
    private String writer_username;
    private String receiver_username;
    private int stars;

    public ReviewModel() {
    }

    public ReviewModel(String comment, String writer_username, String receiver_username, int stars) {
        this.comment = comment;
        this.writer_username = writer_username;
        this.receiver_username = receiver_username;
        this.stars = stars;
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

    public String getReceiver_username() {
        return receiver_username;
    }

    public void setReceiver_username(String receiver_username) {
        this.receiver_username = receiver_username;
    }

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }
}
