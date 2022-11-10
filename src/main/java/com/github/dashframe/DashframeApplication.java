package com.github.dashframe;

import com.github.dashframe.config.RsaKeyProperties;
import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.ServiceType;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.ServiceManagerProvider;
import com.github.dashframe.service.service.SteamServiceManager;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import com.github.dashframe.service.widget.steam.SteamFriendsListWidgetManager;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

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

    @Autowired
    private ServiceManagerProvider serviceManagerProvider;

    @Autowired
    private WidgetManagerProvider widgetManagerProvider;

    @ResponseBody
    @GetMapping("/steam")
    public Mono<?> steam() {
        // FIXME: temporary route
        SteamServiceManager serviceManager = (SteamServiceManager) this.serviceManagerProvider.forType(
                ServiceType.STEAM
            );
        SteamFriendsListWidgetManager widgetManager = (SteamFriendsListWidgetManager) this.widgetManagerProvider.forType(
                WidgetType.STEAM_FRIENDS_LIST
            );
        Service service = new Service(42, new User(), ServiceType.STEAM.getValue(), "76561198181932666", new Date());

        assert serviceManager != null;
        assert widgetManager != null;

        return serviceManager
            .open(service)
            .map(serviceContext -> widgetManager.open(new Widget(), serviceContext).map(widgetManager::fetchData));
    }
}
