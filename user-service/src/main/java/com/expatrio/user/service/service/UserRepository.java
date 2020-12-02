package com.expatrio.user.service.service;

import com.expatrio.user.service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByRoles(String role);
    Optional<UserEntity> findByEmailAndPassword(String email, String password);

//    @Query("select ")
  //  List<UserEntity> findByRoleName(String name);
}

