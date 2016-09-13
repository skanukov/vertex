package com.github.skanukov.vertex.apps.web.controllers.users;

import com.github.skanukov.vertex.core.action.JsonAction;
import com.github.skanukov.vertex.lib.model.User;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class IndexAction implements JsonAction {
    @Override
    public void call(RoutingContext context) {
        User.getAll().setHandler(getUsers -> {
            if (getUsers.failed()) {
                context.fail(getUsers.cause());
                return;
            }

            List<User> users = getUsers.result();
            renderJson(context, users);
        });
    }
}
