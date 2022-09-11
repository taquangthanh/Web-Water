package com.example.java6.repository;

import com.example.java6.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    @Query("select o from User o where o.username = ?1")
    User findByUsername(String email);
}
