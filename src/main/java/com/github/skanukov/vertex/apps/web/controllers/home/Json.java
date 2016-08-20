package com.github.skanukov.vertex.apps.web.controllers.home;

import com.github.skanukov.vertex.core.controller.JsonAction;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;

/**
 * Home controller Index action.
 */
public class Json extends HomeAction implements JsonAction {
    @Override
    public void call(RoutingContext context) {
        JsonArray jsonArray = new JsonArray(Arrays.asList(1, 2, 3));
        renderJson(context, jsonArray);
    }
}
