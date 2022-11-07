package com.github.dashframe;

import com.github.dashframe.config.RsaKeyProperties;
import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@Controller
@ResponseBody
@EnableConfigurationProperties(RsaKeyProperties.class)
public class DashframeApplication {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private WidgetParameterDAO widgetParameterDAO;

    public static void main(String[] args) {
        SpringApplication.run(DashframeApplication.class, args);
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping("/about.json")
    public ResponseEntity<Map<String, Object>> aboutJson() {
        Map<String, Object> body = new HashMap<>();
        body.put("client", new HashMap<>());
        body.put("server", new HashMap<>());
        return ResponseEntity.ok(body);
    }
}
