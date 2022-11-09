package com.github.dashframe.dao;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ServiceDAO extends JpaRepository<Service, Integer> {
    Service findById(int id);
    ArrayList<Service> findByUser(User user);
}
