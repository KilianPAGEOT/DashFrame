package com.github.dashframe.dao;

import com.github.dashframe.models.Widget;
import com.github.dashframe.models.WidgetParameter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetParameterDAO extends JpaRepository<WidgetParameter, Integer> {
    WidgetParameter findById(int id);

    ArrayList<WidgetParameter> findByWidget(Widget widget);
}
