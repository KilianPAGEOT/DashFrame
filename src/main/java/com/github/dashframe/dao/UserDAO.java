package com.github.dashframe.dao;

import com.github.dashframe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    User findById(int id);

    User findByUsername(String username);
    User findByName(String name);
    User findByUsername(String username);
}
