package com.resky1607479.utsmobpro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crew {
    @SerializedName("credit_id")
    @Expose
    private int credit_id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("job")
    @Expose
    private String job;

    public int getCredit_id() {
        return credit_id;
    }

    public void setCredit_id(int credit_id) {
        this.credit_id = credit_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
