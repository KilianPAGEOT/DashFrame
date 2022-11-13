package com.github.dashframe;

import com.github.dashframe.config.RsaKeyProperties;
import com.github.dashframe.models.json.*;
import com.github.dashframe.service.service.ServiceManagerProvider;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
