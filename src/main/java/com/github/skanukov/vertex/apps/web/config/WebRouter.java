package com.github.skanukov.vertex.apps.web.config;

import com.github.skanukov.vertex.apps.web.controllers.home.JsonSampleAction;
import com.github.skanukov.vertex.core.route.VertexRouter;
import io.vertx.ext.web.Router;

/**
 * Handles all routes for the current application.
 */
public final class WebRouter extends VertexRouter {
    /**
     * Creates router with default settings.
     *
     * @param router The Vert.x router instance.
     */
    public WebRouter(Router router) {
        super(router);
    }

    /**
     * Creates router and mounts the base path.
     *
     * @param basePath The base path for router.
     * @param router   The Vert.x router instance.
     */
    public WebRouter(String basePath, Router router) {
        super(basePath, router);
    }

    /**
     * Defines all routes for the current applications.
     */
    @Override
    public void route() {
        get("/", new com.github.skanukov.vertex.apps.web.controllers.home.IndexAction());
        get("/json", new JsonSampleAction());
        get("/users", new com.github.skanukov.vertex.apps.web.controllers.users.IndexAction());
    }
}
