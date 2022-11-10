package com.github.dashframe.service.service;

import com.github.dashframe.dao.WidgetDAO;
import com.github.dashframe.dao.WidgetParameterDAO;
import com.github.dashframe.models.Service;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.WidgetParameter;
import com.github.dashframe.models.json.ServiceType;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.naming.Context;

@org.springframework.stereotype.Service
public class WeatherServiceManager implements ServiceManager<WeatherServiceManager.Context> {

    private static final String MAP_URL_START = "https://nominatim.openstreetmap.org/search?q=";

    private static final String MAP_URL_END = "&format=json&polygon=1&addressdetails=1";

    private static final String WEATHER_URL = "https://api.open-meteo.com/v1/forecast"; //?latitude=52.52&longitude=13.41&hourly=temperature_2m

    private final Map<Integer, Context> OPEN_SERVICES = new ConcurrentHashMap<>();

    @Autowired
    private WidgetDAO widgetDAO;

    @Autowired
    private WidgetParameterDAO widgetParameterDAO;

    @Autowired
    private Environment environment;

    @Override
    public ServiceType getType() {
        return ServiceType.WEATHER_TIME;
    }

    @Override
    public Mono<Void> destroy(Context serviceContext) {
        OPEN_SERVICES.remove(serviceContext.service.getId());
        return Mono.empty();
    }

    @Override
    public Mono<Context> open(com.github.dashframe.models.Service service) {
        this.checkType(service);

        var client = WebClient.create(WEATHER_URL);
        var widgets= widgetDAO.findByService(service);
        if(widgets.size()!=0)
        {
            ArrayList<WidgetParameter> params = new ArrayList<>();
            for (Widget widget:widgets) {
                params = widgetParameterDAO.findByWidget(widget);
            }
            if(params.size()!=0){
                String location="";
                for (WidgetParameter param:params) {
                    location = param.getValue();
                }
                String finalLocation = location;
                RestTemplate restTemplate = new RestTemplate();
                String url =
                        "https://nominatim.openstreetmap.org/search?q=" + finalLocation + "&format=json&polygon=1&addressdetails=1";
                ArrayList response = restTemplate.getForObject(url, ArrayList.class);
                Map<String, Object> locResponse = (Map<String, Object>) response.get(0);
                return client.get().uri(uriBuilder ->
                                uriBuilder
                                        .queryParam("latitude",locResponse.get("lat"))
                                        .queryParam("longitude",locResponse.get("lon"))
                                        .queryParam("hourly","temperature_2m")
                                        .build())
                        .retrieve()
                        .toBodilessEntity()
                        .then(Mono.fromSupplier(() ->
                                        OPEN_SERVICES.computeIfAbsent(service.getId(), id -> new Context(service,client,finalLocation,locResponse))
                                )
                        );
            }
        }else return Mono.error(
            new MissingApiKeyException(
                    "Missing widget"
            )
        );
        return null;
    }

    public record Context(Service service,WebClient client,String finalLocation,Map<String, Object> locResponse) implements ServiceContext {}
}
