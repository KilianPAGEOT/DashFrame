package com.github.dashframe.dao;

import com.github.dashframe.models.WidgetParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetParameterDAO extends JpaRepository<WidgetParameter,Integer> {

    WidgetParameter findById(int id);

}
