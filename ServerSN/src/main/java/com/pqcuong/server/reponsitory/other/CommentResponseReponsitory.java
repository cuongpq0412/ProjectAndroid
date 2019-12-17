package com.pqcuong.server.reponsitory.other;

import com.pqcuong.server.model.response.CommentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentResponseReponsitory extends JpaRepository<CommentResponse, Integer> {
//    private int id;
//    private int id_user;
//    private int id_news;
//    private String content;
//    private int numberlike;
//    private String timecm;
//    private String falname;
//    private String linkAvatar;
    @Query(nativeQuery = true,value = "SELECT comment.*, user_profile.falname, user_profile.avatar FROM comment "+
            " JOIN user_profile ON comment.id_user=user_profile.id WHERE id_news=:id_news")
        List<CommentResponse> findAllByUserId(@Param(value = "id_news")int id_news);
}
