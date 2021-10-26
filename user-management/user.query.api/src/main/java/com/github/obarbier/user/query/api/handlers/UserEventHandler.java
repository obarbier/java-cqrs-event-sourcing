package com.github.obarbier.user.query.api.handlers;

import com.github.obarbier.user.core.events.UserRegisteredEvent;
import com.github.obarbier.user.core.events.UserRemovedEvent;
import com.github.obarbier.user.core.events.UserUpdatedEvent;

public interface UserEventHandler {
    void on(UserRegisteredEvent event);
    void on(UserUpdatedEvent event);
    void on(UserRemovedEvent event);
}
