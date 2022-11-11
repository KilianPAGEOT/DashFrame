package com.github.dashframe;

import com.github.dashframe.config.RsaKeyProperties;
import com.github.dashframe.dao.ServiceDAO;
import com.github.dashframe.dao.UserDAO;
import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.WidgetParameter;
import com.github.dashframe.models.json.*;
import com.github.dashframe.models.json.ServiceType;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.ServiceManagerProvider;
import com.github.dashframe.service.service.WeatherServiceManager;
import com.github.dashframe.service.widget.Weather.WeatherWidgetManager;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import java.time.Instant;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private ServiceManagerProvider serviceManagerProvider;

    @Autowired
    private WidgetManagerProvider widgetManagerProvider;

    public static void main(String[] args) {
        SpringApplication.run(DashframeApplication.class, args);
    }

    @ResponseBody
    @CrossOrigin
    @GetMapping(value = "/about.json", produces = "application/json")
    public AboutInformation aboutJson(HttpServletRequest request) {
        Map<ServiceType, List<WidgetType>> supportedWidgets = Arrays
            .stream(WidgetType.values())
            .filter(widgetType -> this.widgetManagerProvider.forType(widgetType) != null)
            .collect(Collectors.groupingBy(WidgetType::getServiceType));

        return new AboutInformation(
            new ClientInformation(request.getRemoteAddr()),
            new ServerInformation()
                .currentTime(Instant.now().getEpochSecond())
                .services(
                    Arrays
                        .stream(ServiceType.values())
                        .filter(serviceType -> this.serviceManagerProvider.forType(serviceType) != null)
                        .map(serviceType ->
                            new ServiceDescription()
                                .name(serviceType.getValue())
                                .description(serviceType.getDescription())
                                .widgets(
                                    supportedWidgets
                                        .getOrDefault(serviceType, Collections.emptyList())
                                        .stream()
                                        .map(widgetType ->
                                            new WidgetDescription()
                                                .name(widgetType.getValue())
                                                .description(widgetType.getDescription())
                                                .params(widgetType.getParameterDescriptions())
                                        )
                                        .toList()
                                )
                        )
                        .toList()
                )
        );
    }
    

    /*@ResponseBody
    @GetMapping("/weather")
    public Mono<?> weather() {
        // FIXME: temporary route
        System.out.println("call weather");
        WeatherServiceManager serviceManager = (WeatherServiceManager) this.serviceManagerProvider.forType(
                ServiceType.WEATHER_TIME
            );
        WeatherWidgetManager widgetManager = (WeatherWidgetManager) this.widgetManagerProvider.forType(
                WidgetType.WEATHER_TIME_DEFAULT
            );
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userDAO.findByUsername(username);
        Service service = new Service(user, ServiceType.WEATHER_TIME, "76561198181932666", "test");
        serviceDAO.save(service);
        Widget widget = new Widget(service, "Weather", 0, 0, "weather_time/default", 30);
        widgetDAO.save(widget);
        WidgetParameter widgetParameter = new WidgetParameter(widget, "location", "Nantes");
        widgetParameterDAO.save(widgetParameter);
        assert serviceManager != null;
        assert widgetManager != null;

        return serviceManager
            .open(service)
            .map(serviceContext -> widgetManager.open(widget, serviceContext).map(widgetManager::fetchData));
    }*/
}
