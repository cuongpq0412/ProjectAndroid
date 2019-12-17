package com.pqcuong.server.controller;

import com.pqcuong.server.model.database.Friend;
import com.pqcuong.server.model.request.AddFriendRequest;
import com.pqcuong.server.model.request.FriendRequest;
import com.pqcuong.server.model.response.BaseResponse;
import com.pqcuong.server.reponsitory.FriendReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
public class FriendController {
    @Autowired
    private FriendReponsitory friendReponsitory;

    @PostMapping("/api/getAllFriend")
    public Object getAllFriend(@RequestBody FriendRequest request){
        List<Friend> friend = friendReponsitory.findAllByIdUser(request.getId_user());
        if (friend.size()==0||friend==null){
            return new BaseResponse(false,"Your doesn't have friend!");
        }
        return new BaseResponse(friend);
    }
    @PostMapping("/api/addFriend")
    public Object addFriend(@RequestBody AddFriendRequest request){
        List<Friend> friends = friendReponsitory.findAllByIdUser(request.getId_user());
        for (Friend f : friends) {
            if (f.getId_user()==request.getId_user()&&f.getId_friend()==request.getId_friend()){
                return new BaseResponse(false,"your friend is exits!");
            }
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        Friend friend = new Friend();
        friend.setId_friend(request.getId_friend());
        friend.setId_user(request.getId_user());
        friend.setTimeadd(dateFormat.format(date));
        friend = friendReponsitory.save(friend);
        return new BaseResponse(friend);
    }

}
