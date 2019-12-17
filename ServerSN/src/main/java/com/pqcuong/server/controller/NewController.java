package com.pqcuong.server.controller;

import com.pqcuong.server.model.database.NewsStatus;
import com.pqcuong.server.model.request.GetOneNewRequest;
import com.pqcuong.server.model.request.NewStatusRequest;
import com.pqcuong.server.model.response.BaseResponse;
import com.pqcuong.server.model.response.NewStatusResponse;
import com.pqcuong.server.reponsitory.NewStatusReponsitory;
import com.pqcuong.server.reponsitory.other.NewStatusResponseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NewController {
    @Autowired
    private NewStatusResponseRepository newStatusResponseRepository;

    @PostMapping("/api/getOneNewById")
    public Object getOneNewById(@RequestBody GetOneNewRequest request){
        NewStatusResponse newsStatus = newStatusResponseRepository.findOneByIdNew(request.getId());
        if (newsStatus==null){
            return new BaseResponse(false,"doesn't have any your news!");
        }
        return new BaseResponse(newsStatus);
    }

    @PostMapping("/api/getAllNewByIdUser")
    public Object getAllNewByIdUser(@RequestBody NewStatusRequest request){
        List<NewStatusResponse> newsStatus = newStatusResponseRepository.findAllStatusNews(request.getId_user());
        if (newsStatus.size()==0||newsStatus==null){
            return new BaseResponse(false,"doesn't have any your news!");
    }
        return new BaseResponse(newsStatus);
    }
}
