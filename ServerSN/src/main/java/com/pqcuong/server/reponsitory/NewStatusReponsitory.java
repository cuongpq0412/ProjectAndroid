package com.pqcuong.server.reponsitory;

import com.pqcuong.server.model.database.NewsStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewStatusReponsitory extends JpaRepository<NewsStatus, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT * FROM news_status WHERE " + " id_user=:id_user")
    List<NewsStatus> getAllNewsStatusByUser(@Param(value = "id_user")int id_user);

}
