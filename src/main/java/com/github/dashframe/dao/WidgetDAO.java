package com.github.dashframe.dao;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.Widget;
import java.util.ArrayList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WidgetDAO extends JpaRepository<Widget, Integer> {
    Widget findById(int id);

    ArrayList<Widget> findByService(Service service);
}
