package com.example.snclient.model.response;

public class NewStatusResponse {
    private int id;
    private int id_user;
    private String falname;
    private String avatar;
    private String content;
    private String link_image;
    private String like_status;
    private String comment_status;
    private String timenew;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLink_image() {
        return link_image;
    }

    public void setLink_image(String link_image) {
        this.link_image = link_image;
    }

    public String getLike_status() {
        return like_status;
    }

    public void setLike_status(String like_status) {
        this.like_status = like_status;
    }

    public String getComment_status() {
        return comment_status;
    }

    public void setComment_status(String comment_status) {
        this.comment_status = comment_status;
    }

    public String getTimenew() {
        return timenew;
    }

    public void setTimenew(String timenew) {
        this.timenew = timenew;
    }
}
