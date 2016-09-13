package com.github.skanukov.vertex.apps.web.controllers.home;

import com.github.skanukov.vertex.core.action.HtmlAction;
import io.vertx.ext.web.RoutingContext;

/**
 * Home controller IndexAction action.
 */
public class IndexAction extends HomeAction implements HtmlAction {
    @Override
    public void call(RoutingContext context) {
        context.put("name", "User");
        renderTemplate(context, "./templates/web/home/index.ftl");
    }
}
