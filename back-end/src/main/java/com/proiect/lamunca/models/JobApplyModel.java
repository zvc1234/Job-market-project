package com.proiect.lamunca.models;

public class JobApplyModel {
    private String username;
    private int jobId;

    public JobApplyModel(String username, int jobId) {
        this.username = username;
        this.jobId = jobId;
    }

    public JobApplyModel() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }
}
