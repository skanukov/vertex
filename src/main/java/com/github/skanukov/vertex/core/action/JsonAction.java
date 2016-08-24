package com.github.skanukov.vertex.core.action;

import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * Defines interface for applications actions rendering JSON.
 */
@FunctionalInterface
public interface JsonAction extends HttpAction {
    /**
     * Renders JSON response.
     *
     * @param context The routing context.
     * @param object  The object to render to response.
     */
    default void renderJson(RoutingContext context, Object object) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(object));
    }
}
