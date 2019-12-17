package com.pqcuong.server.model.response;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CommentResponse {
    @Id
    private int id;
    private int id_user;
    private int id_news;
    private String content;
    private int numberlike;
    private String timecm;
    private String falname;
    private String avatar;

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

    public int getId_news() {
        return id_news;
    }

    public void setId_news(int id_news) {
        this.id_news = id_news;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getNumberlike() {
        return numberlike;
    }

    public void setNumberlike(int numberlike) {
        this.numberlike = numberlike;
    }

    public String getTimecm() {
        return timecm;
    }

    public void setTimecm(String timecm) {
        this.timecm = timecm;
    }

    public String getFalname() {
        return falname;
    }

    public void setFalname(String falname) {
        this.falname = falname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
