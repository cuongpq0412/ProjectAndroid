package com.example.snclient.retrofitservices;

import com.example.snclient.model.database.Friend;
import com.example.snclient.model.request.AddFriendRequest;
import com.example.snclient.model.request.CommentRequest;
import com.example.snclient.model.request.FriendRequest;
import com.example.snclient.model.request.GetOneNewRequest;
import com.example.snclient.model.response.BaseResponse;
import com.example.snclient.model.request.NewStatusRequest;
import com.example.snclient.model.database.NewsStatus;
import com.example.snclient.model.database.UserProfile;
import com.example.snclient.model.request.LoginRequest;
import com.example.snclient.model.request.RegisterRequest;
import com.example.snclient.model.response.CommentResponse;
import com.example.snclient.model.response.NewStatusResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Services {

    @POST("/api/login")
    Call<BaseResponse<UserProfile>> login(@Body LoginRequest request);

    @POST("/api/register")
    Call<BaseResponse<UserProfile>> register(@Body RegisterRequest request);

    @POST("/api/getUserById")
    Call<BaseResponse<UserProfile>> getUserById(@Body NewStatusRequest request);

    @POST("/api/getOneNewById")
    Call<BaseResponse<NewStatusResponse>> getOneNewById(@Body GetOneNewRequest request);

    @POST("/api/getAllFriend")
    Call<BaseResponse<List<Friend>>> getAllFriend(@Body FriendRequest request);

    @POST("/api/addFriend")
    Call<BaseResponse<Friend>> addFriend(@Body AddFriendRequest request);

    @POST("/api/getAllUser")
    Call<BaseResponse<List<UserProfile>>> getAllUser();

    @POST("/api/getAllNewByIdUser")
    Call<BaseResponse<List<NewStatusResponse>>> getAllNewByIdUser(@Body NewStatusRequest request);

    @POST("/api/getAllCommentByNewId")
    Call<BaseResponse<List<CommentResponse>>> getAllCommentByNewId(@Body CommentRequest request);

}
