package com.example.snclient.model.database;

public class Friend {
    private int id;
    private int id_user;
    private int id_friend;
    private String timeadd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_friend() {
        return id_friend;
    }

    public void setId_friend(int id_friend) {
        this.id_friend = id_friend;
    }

    public String getTimeadd() {
        return timeadd;
    }

    public void setTimeadd(String timeadd) {
        this.timeadd = timeadd;
    }
}
