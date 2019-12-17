package com.pqcuong.server.reponsitory;

import com.pqcuong.server.model.database.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendReponsitory extends JpaRepository<Friend, Integer> {
    @Query(nativeQuery = true,
            value = "SELECT  * FROM  friend WHERE id_user="+":id_user ORDER  BY timeadd DESC")
    List<Friend> findAllByIdUser(@Param(value = "id_user")int id_user);
}
