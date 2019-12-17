package com.pqcuong.server.reponsitory.other;

import com.pqcuong.server.model.request.NewStatusRequest;
import com.pqcuong.server.model.response.NewStatusResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewStatusResponseRepository extends JpaRepository<NewStatusResponse, Integer> {
//    private int id;
//    private int id_user;
//    private String username;
//    private String avatar;
//    private String content;
//    private String link_image;
//    private String like_status;
//    private String comment_status;
//    private String timenew;
    @Query(nativeQuery = true, value = "SELECT news_status.*," +
            "user_profile.falname, user_profile.avatar FROM news_status " +
            "JOIN user_profile ON news_status.id_user =user_profile.id WHERE id_user=:id_user")
    List<NewStatusResponse> findAllStatusNews(@Param(value = "id_user")int id_user);

    @Query(nativeQuery = true, value = "SELECT news_status.*," +
            "user_profile.falname, user_profile.avatar FROM news_status " +
            "JOIN user_profile ON news_status.id_user =user_profile.id WHERE news_status.id=:id_new")
    NewStatusResponse findOneByIdNew(@Param(value = "id_new")int id_new);
}
