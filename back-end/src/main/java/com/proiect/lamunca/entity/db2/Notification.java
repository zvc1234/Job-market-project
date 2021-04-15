package com.proiect.lamunca.entity.db2;


import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "notification")
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_notification", nullable = false)
    private int id_notification;

    @Column(name = "from_user")
    private String from_user;

    @Column(name = "to_user")
    private String to_user;

    @Column(name = "message")
    private String message;

    public Notification() {
    }

    public Notification(String from_user, String to_user, String message) {
        this.from_user = from_user;
        this.to_user = to_user;
        this.message = message;
    }

    public int getId_notification() {
        return id_notification;
    }

    public void setId_notification(int id_notification) {
        this.id_notification = id_notification;
    }

    public String getFrom_user() {
        return from_user;
    }

    public void setFrom_user(String from_user) {
        this.from_user = from_user;
    }

    public String getTo_user() {
        return to_user;
    }

    public void setTo_user(String to_user) {
        this.to_user = to_user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
