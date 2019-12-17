package com.pqcuong.server.model.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "messager_detail")
public class MessagerDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int id_user;
    private int id_messager;
    private String content;
    private String timesend;

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

    public int getId_messager() {
        return id_messager;
    }

    public void setId_messager(int id_messager) {
        this.id_messager = id_messager;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimesend() {
        return timesend;
    }

    public void setTimesend(String timesend) {
        this.timesend = timesend;
    }
}
