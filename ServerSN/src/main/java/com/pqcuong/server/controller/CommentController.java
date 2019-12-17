package com.pqcuong.server.controller;

import com.pqcuong.server.model.request.CommentRequest;
import com.pqcuong.server.model.response.BaseResponse;
import com.pqcuong.server.model.response.CommentResponse;
import com.pqcuong.server.reponsitory.other.CommentResponseReponsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentResponseReponsitory commentResponseReponsitory;

    @PostMapping("/api/getAllCommentByNewId")
    public Object getAllCommentByUserId(@RequestBody CommentRequest request){
        List<CommentResponse> commentResponse = commentResponseReponsitory.findAllByUserId(request.getId_news());
        if (commentResponse==null||commentResponse.size()==0){
            return new BaseResponse(false,"this new doesn't have comment");
        }
        return new BaseResponse(commentResponse);
    }
}
