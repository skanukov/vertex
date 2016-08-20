package com.github.skanukov.vertex.core.action;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

/**
 * Defines interface for applications actions rendering JSON.
 */
@FunctionalInterface
public interface JsonAction extends HttpAction {
    /**
     * Renders JSON response.
     *
     * @param context    The routing context.
     * @param jsonObject The JsonObject to render to response.
     */
    default void renderJson(RoutingContext context, JsonObject jsonObject) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(jsonObject.encode());
    }

    /**
     * Renders JSON response.
     *
     * @param context   The routing context.
     * @param jsonArray The JsonArray to render to response.
     */
    default void renderJson(RoutingContext context, JsonArray jsonArray) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(jsonArray.encode());
    }
}
