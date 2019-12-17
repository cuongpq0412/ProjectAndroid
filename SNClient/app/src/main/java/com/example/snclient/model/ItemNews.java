package com.example.snclient.model;

public class ItemNews {
    private int id;
    private int id_user;
    private String linkAvatar;
    private String nName, nImage,nContent,nNumLike,nNumComment;
    private String timeNews;

    public ItemNews(int id, int id_user, String linkAvatar, String nName, String nImage, String nContent, String nNumLike, String nNumComment, String timeNews) {
        this.id = id;
        this.id_user = id_user;
        this.linkAvatar = linkAvatar;
        this.nName = nName;
        this.nImage = nImage;
        this.nContent = nContent;
        this.nNumLike = nNumLike;
        this.nNumComment = nNumComment;
        this.timeNews = timeNews;
    }

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

    public String getTimeNews() {
        return timeNews;
    }

    public void setTimeNews(String timeNews) {
        this.timeNews = timeNews;
    }

    public String getLinkAvatar() {
        return linkAvatar;
    }

    public void setLinkAvatar(String linkAvatar) {
        this.linkAvatar = linkAvatar;
    }

    public String getnNumComment() {
        return nNumComment;
    }

    public void setnNumComment(String nNumComment) {
        this.nNumComment = nNumComment;
    }

    public String getnName() {
        return nName;
    }

    public void setnName(String nName) {
        this.nName = nName;
    }


    public String getnImage() {
        return nImage;
    }

    public void setnImage(String nImage) {
        this.nImage = nImage;
    }

    public String getnContent() {
        return nContent;
    }

    public void setnContent(String nContent) {
        this.nContent = nContent;
    }

    public String getnNumLike() {
        return nNumLike;
    }

    public void setnNumLike(String nNumLike) {
        this.nNumLike = nNumLike;
    }


}
