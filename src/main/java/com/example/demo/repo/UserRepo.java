package com.example.demo.repo;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    //<model type, primary key data type

    //JpaRepository already provides this, Native queries reduce portability
    //@Query(value = "SELECT * FROM users WHERE id = ?1", nativeQuery = true)
    //User getUserById(Integer userId);

    Optional<User> findById(Integer id);
}
