package com.github.dashframe.service.service;

import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.widget.WidgetContext;
import com.github.dashframe.service.widget.WidgetManager;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

public class TestRssFeedWidgetManager
    implements
        WidgetManager<TestRssServiceManager.Context, TestRssFeedWidgetManager.Context, FakeExternalService.Data> {

    FakeExternalService externalService;

    public TestRssFeedWidgetManager(FakeExternalService externalService) {
        this.externalService = externalService;
    }

    @Override
    public WidgetType getType() {
        return WidgetType.RSS_FEED;
    }

    @Override
    public Mono<Context> open(Widget widget, TestRssServiceManager.Context serviceContext) {
        return Mono.fromSupplier(() -> {
            WebTestClient client = WebTestClient.bindToController(this.externalService).build();

            client.post().uri("/check").exchange().expectStatus().isOk();
            return new Context(client, widget);
        });
    }

    @Override
    public Mono<FakeExternalService.Data> fetchData(Context widgetContext) {
        return Mono.fromSupplier(
            widgetContext
                .client()
                .get()
                .uri("/data")
                .exchange()
                .expectBody(FakeExternalService.Data.class)
                .returnResult()::getResponseBody
        );
    }

    public record Context(WebTestClient client, Widget widget) implements WidgetContext {}
}
