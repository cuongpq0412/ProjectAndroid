package com.example.snclient.model.response;

import com.google.gson.annotations.SerializedName;

public class BaseResponse<T> {
    @SerializedName("success")
    private boolean isSusccess;
    private String message;
    private T data;

    public BaseResponse(T data) {
        this.data = data;
        isSusccess=true;
    }

    public BaseResponse(boolean isSusccess, String message) {
        this.isSusccess = isSusccess;
        this.message = message;
    }

    public boolean isSusccess() {
        return isSusccess;
    }

    public void setSusccess(boolean susccess) {
        isSusccess = susccess;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
