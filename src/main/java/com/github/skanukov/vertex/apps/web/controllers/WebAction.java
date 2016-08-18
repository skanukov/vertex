package com.github.skanukov.vertex.apps.web.controllers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Base class for the current application actions.
 */
public abstract class WebAction {
    protected void renderJson(RoutingContext context, JsonObject jsonObject) {
        context.response().putHeader("content-type", "application/json").end(jsonObject.encode());
    }

    protected void renderJson(RoutingContext context, JsonArray jsonArray) {
        context.response().putHeader("content-type", "application/json").end(jsonArray.encode());
    }
}
