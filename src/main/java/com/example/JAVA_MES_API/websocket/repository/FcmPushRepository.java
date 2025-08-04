package com.example.JAVA_MES_API.websocket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.JAVA_MES_API.api.entity.User;


@Repository
public interface FcmPushRepository  extends JpaRepository<User, String> {

}
