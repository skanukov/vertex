package com.github.skanukov.vertex.apps.web.controllers;

import com.github.skanukov.vertex.core.controller.VertexController;
import com.github.skanukov.vertex.lib.model.User;
import io.vertx.ext.web.RoutingContext;

import java.util.List;

public class UserController extends VertexController {
    public void actionIndex(RoutingContext context) {
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
