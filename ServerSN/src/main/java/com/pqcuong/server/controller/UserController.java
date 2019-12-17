package com.pqcuong.server.controller;

import com.pqcuong.server.model.database.UserProfile;
import com.pqcuong.server.model.request.LoginRequest;
import com.pqcuong.server.model.request.NewStatusRequest;
import com.pqcuong.server.model.request.RegisterRequest;
import com.pqcuong.server.model.response.BaseResponse;
import com.pqcuong.server.reponsitory.UserProfileReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserProfileReponsitory userProfileReponsitory;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @PostMapping("/api/login")
    public Object login(@RequestBody LoginRequest request){
        UserProfile userProfile = userProfileReponsitory.findOneByUsernamePassword(request.getUsername());

        if (userProfile==null){
            return new BaseResponse(false,"Your username is not correct");
        }
        if (!passwordEncoder.matches(request.getPassword(),userProfile.getPassword())){
            return new BaseResponse(false, "your password is not correct");
        }

        return new BaseResponse(userProfile);
    }
    @PostMapping("/api/register")
    public Object register(@RequestBody RegisterRequest request){
        UserProfile userProfile = userProfileReponsitory.findOneByUsernamePassword(request.getUsername());
        if (userProfile!=null){
            return new BaseResponse(false,"This user name is exit!");
        }
        userProfile= new UserProfile();
        userProfile.setAvatar(request.getAvatar());
        userProfile.setUsername(request.getUsername());
        userProfile.setPassword(passwordEncoder.encode(request.getPassword()));
        userProfile.setDob(request.getDob());
        userProfile=userProfileReponsitory.save(userProfile);
        return new BaseResponse(userProfile);

    }
    @PostMapping(value = "/api/getUserById")
    public Object getUserById(@RequestBody NewStatusRequest request){
        UserProfile userProfile = userProfileReponsitory.findOneByUserId(request.getId_user());
        if (userProfile==null){
            return new BaseResponse(false,"Your user doesn't exit!");
        }
        return  new BaseResponse(userProfile);
    }
    @PostMapping(value = "/api/getAllUser")
    public Object getAllUser(){
        List<UserProfile> userProfile = userProfileReponsitory.getAllUser();
        if (userProfile==null||userProfile.size()==0){
            return new BaseResponse(false,"Don't have a user in data");
        }
        return  new BaseResponse(userProfile);
    }
}
