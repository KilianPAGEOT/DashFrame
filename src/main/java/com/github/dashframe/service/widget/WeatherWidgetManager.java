package com.github.dashframe.service.widget;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.WeatherServiceManager;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public class WeatherWidgetManager implements WidgetManager<WeatherServiceManager.Context,WeatherWidgetManager.Context, Map<String,Object>> {

    @Override
    public WidgetType getType() {
        return WidgetType.WEATHER_TIME_DEFAULT;
    }

    @Override
    public Mono<Context> open(Widget widget,WeatherServiceManager.Context serviceContext) {
        return Mono.just(new Context(serviceContext, widget));
    }

    @Override
    public Mono<Map<String,Object>> fetchData(WeatherWidgetManager.Context widgetContext) {
        var client = widgetContext.serviceContext.client();
        var finalLocation = widgetContext.serviceContext.finalLocation();
        var locResponse = widgetContext.serviceContext.locResponse();
        return client.get().uri(uriBuilder ->
                        uriBuilder
                                .queryParam("latitude",locResponse.get("lat"))
                                .queryParam("longitude",locResponse.get("lon"))
                                .queryParam("hourly","temperature_2m")
                                .build())
                .retrieve()
                .bodyToMono(RawData.class)
                .map(rawData -> rawData.weather.weather());

    }
    public record Context(WeatherServiceManager.Context serviceContext, Widget widget) implements WidgetContext {}

    private record RawData(@JsonProperty("Weather") RawWeather weather) {}

    private record RawWeather(Map<String,Object> weather) {}

}
