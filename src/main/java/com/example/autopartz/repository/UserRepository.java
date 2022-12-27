package com.example.autopartz.repository;

import com.example.autopartz.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // List<User> findAllByUsername(String username);
}
