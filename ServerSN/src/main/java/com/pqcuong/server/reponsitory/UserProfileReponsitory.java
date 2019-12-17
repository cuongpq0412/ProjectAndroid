package com.pqcuong.server.reponsitory;

import com.pqcuong.server.model.database.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserProfileReponsitory extends JpaRepository<UserProfile, Integer> {
    @Query(nativeQuery =true,value = "SELECT * FROM user_profile WHERE "+
            " username=:username LIMIT 1")
    UserProfile findOneByUsernamePassword(
                    @Param(value = "username") String username
            );

    @Query(nativeQuery = true,value = "SELECT * FROM user_profile WHERE "+
            " id=:user_id LIMIT 1")
    UserProfile findOneByUserId(
            @Param(value = "user_id")int user_id
    );

    @Query(nativeQuery = true,value = "SELECT  * FROM user_profile ORDER  BY id DESC")
    List<UserProfile> getAllUser();
}
