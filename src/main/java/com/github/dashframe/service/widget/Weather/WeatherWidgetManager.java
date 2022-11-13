package com.github.dashframe.service.widget.Weather;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.WeatherServiceManager;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.service.widget.WidgetManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.Map;

@Service
public class WeatherWidgetManager implements WidgetManager<WeatherServiceManager.Context,WeatherWidgetManager.Context, Map> {

    @Override
    public WidgetType getType() {
        return WidgetType.WEATHER_TIME_DEFAULT;
    }

    @Override
    public Mono<Context> open(Widget widget,WeatherServiceManager.Context serviceContext) {
        return Mono.just(new Context(serviceContext, widget));
    }

    @Override
    public Mono<Map> fetchData(WeatherWidgetManager.Context widgetContext) {
        var client = widgetContext.serviceContext.client();
        var finalLocation = widgetContext.serviceContext.finalLocation();
        var locResponse = widgetContext.serviceContext.locResponse();
        return client.get().uri(uriBuilder ->
                        uriBuilder
                                .queryParam("latitude",locResponse.get("lat"))
                                .queryParam("longitude",locResponse.get("lon"))
                                .queryParam("hourly","temperature_2m")
                                .queryParam("current_weather",true)
                                .queryParam("timezone","Europe/Paris")
                                .build())
                .retrieve()
                .bodyToMono(Map.class);
    }
    public record Context(WeatherServiceManager.Context serviceContext, Widget widget) implements WidgetContext {}

    private record RawData(@JsonProperty("weather") RawWeather weather) {}

    private record RawWeather(Map<String,Object> weather) {}

}
