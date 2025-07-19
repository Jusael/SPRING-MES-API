package com.example.JAVA_MES_API.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.entity.User;

//JPA 기본 제공함수
//save() → INSERT or UPDATE
//findById(), findAll()
//deleteById()
//existsById()
//count()

@Repository
public interface UserRepository extends JpaRepository<User, String> {
  /*  User findByUserId(String userId);
    
    //Modifying 예시
    @Modifying
    @Query("UPDATE User u SET u.jwtToken = :token WHERE u.userId = :userId")
    int updateJwtToken(@Param("userId") String userId, @Param("token") String token);*/
}
