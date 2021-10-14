package com.github.obarbier.user.core.events;

import com.github.obarbier.user.core.models.User;
import lombok.Builder;
import lombok.Data;

@Data
public class UserRemovedEvent {
    private String id;
}
