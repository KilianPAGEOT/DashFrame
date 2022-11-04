package com.github.dashframe.dao;

import com.github.dashframe.models.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceDAO extends JpaRepository<Service,Integer> {

    Service findById(int id);

}