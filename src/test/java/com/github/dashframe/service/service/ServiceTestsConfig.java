package com.github.dashframe.service.service;

import com.github.dashframe.models.json.ServiceType;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@TestConfiguration
public class ServiceTestsConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public ServiceManagerProvider serviceManagerProvider() {
        return type -> {
            if (type != ServiceType.RSS) throw new IllegalArgumentException(
                "unknown test service type " + type.getValue()
            );
            return new TestRssServiceManager();
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public WidgetManagerProvider widgetManagerProvider() {
        return type -> {
            if (type != WidgetType.RSS_FEED) throw new IllegalArgumentException(
                "unknown test widget type " + type.getValue()
            );
            return new TestRssFeedWidgetManager(this.fakeExternalService());
        };
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
    public FakeExternalService fakeExternalService() {
        return new FakeExternalService();
    }
}
