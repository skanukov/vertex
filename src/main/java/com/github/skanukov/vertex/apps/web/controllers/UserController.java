package com.github.skanukov.vertex.apps.web.controllers;

import com.github.skanukov.vertex.core.controller.VertexController;
import com.github.skanukov.vertex.lib.models.User;
import com.github.skanukov.vertex.lib.repositories.UserRepository;
import io.vertx.ext.web.RoutingContext;

import java.util.Collections;
import java.util.List;

public final class UserController extends VertexController {
    public void actionIndex(RoutingContext context) {
        UserRepository userRepository = new UserRepository();
        userRepository.all().setHandler(allUsers -> {
            if (allUsers.failed()) {
                context.fail(allUsers.cause());
                return;
            }

            List<User> users = allUsers.result().orElse(Collections.emptyList());
            renderJson(context, users);
        });
    }
}
