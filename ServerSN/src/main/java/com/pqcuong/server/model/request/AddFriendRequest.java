package com.pqcuong.server.model.request;

public class AddFriendRequest {
    private int id_user;
    private int id_friend;

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
}
