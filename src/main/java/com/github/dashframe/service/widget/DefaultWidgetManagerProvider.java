package com.github.dashframe.service.widget;

import com.github.dashframe.models.json.WidgetType;
import com.github.dashframe.service.widget.steam.SteamFriendsListWidgetManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * The default implementation of {@link WidgetManagerProvider} used when dependency injecting.
 */
@Service
@Primary
public final class DefaultWidgetManagerProvider implements WidgetManagerProvider {

    @Autowired
    private ApplicationContext context;

    @Override
    public WidgetManager<?, ?, ?> forType(WidgetType type) {
        //noinspection SwitchStatementWithTooFewBranches
        return switch (type) {
            case STEAM_FRIENDS_LIST -> context.getBean(SteamFriendsListWidgetManager.class);
            default -> null;
        };
    }
}
