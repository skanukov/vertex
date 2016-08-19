package com.github.skanukov.vertex.apps.web.controllers.home;

import com.github.skanukov.vertex.core.controller.HttpAction;
import io.vertx.ext.web.RoutingContext;

/**
 * Home controller Index action.
 */
public class Index extends HomeAction implements HttpAction {
    @Override
    public void call(RoutingContext context) {
        context.put("name", "User");
        renderTemplate(context, "./templates/web/home/index.ftl");
    }
}
