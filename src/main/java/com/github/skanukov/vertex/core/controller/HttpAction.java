package com.github.skanukov.vertex.core.controller;

import io.vertx.ext.web.RoutingContext;

/**
 * Defines interface for applications actions.
 */
@FunctionalInterface
public interface HttpAction {
    /**
     * Handles the http request.
     *
     * @param context The Vert.x routing context.
     */
    void call(RoutingContext context);
}
