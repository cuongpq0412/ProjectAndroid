package com.pqcuong.server.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "messager")
public class Messager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String listuser;
    private String timeChat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getListuser() {
        return listuser;
    }

    public void setListuser(String listuser) {
        this.listuser = listuser;
    }

    public String getTimeChat() {
        return timeChat;
    }

    public void setTimeChat(String timeChat) {
        this.timeChat = timeChat;
    }
}
