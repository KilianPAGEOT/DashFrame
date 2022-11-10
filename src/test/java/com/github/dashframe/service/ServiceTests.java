package com.github.dashframe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.github.dashframe.models.Service;
import com.github.dashframe.models.User;
import com.github.dashframe.models.Widget;
import com.github.dashframe.models.json.ServiceType;
import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.service.*;
import com.github.dashframe.service.widget.WidgetManagerProvider;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ServiceTestsConfig.class })
public class ServiceTests {

    @Autowired
    private ServiceManagerProvider serviceManagerProvider;

    @Autowired
    private WidgetManagerProvider widgetManagerProvider;

    @Test
    public void apiUsage() {
        // Test parameters
        var testUser = new User();
        var serviceType = ServiceType.RSS;
        var widgetType = WidgetType.RSS_FEED;
        var service = new Service(testUser, serviceType, "secret", "test");
        var widget = new Widget();

        assertNotNull(this.serviceManagerProvider);
        assertNotNull(this.widgetManagerProvider);

        // Get both widget and server managers
        TestRssServiceManager serviceManager = (TestRssServiceManager) this.serviceManagerProvider.forType(serviceType);
        TestRssFeedWidgetManager widgetManager = (TestRssFeedWidgetManager) this.widgetManagerProvider.forType(
                widgetType
            );

        // Connect to service
        assertNotNull(serviceManager);
        var serviceContext = serviceManager.open(service).block();

        assertNotNull(serviceContext);
        assertNotNull(widgetManager);

        // Initialize widget connection
        var widgetContext = widgetManager.open(widget, serviceContext).block();
        assertNotNull(widgetContext);

        var result = widgetManager.fetchData(widgetContext).block();

        assertNotNull(result);
        assertEquals("Title", result.title());
        assertEquals("lorem ipsum", result.content());

        serviceManager.destroy(serviceContext);
    }
}
