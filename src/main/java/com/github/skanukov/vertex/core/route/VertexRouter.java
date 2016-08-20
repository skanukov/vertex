package com.github.skanukov.vertex.core.route;

import com.github.skanukov.vertex.core.action.HttpAction;
import io.vertx.ext.web.Router;

/**
 * Base class for all application routers.
 */
public abstract class VertexRouter {
    private final String basePath;
    private final Router router;

    /**
     * Creates the router with default settings.
     *
     * @param router The Vert.x router instance.
     */
    public VertexRouter(Router router) {
        this.basePath = "";
        this.router = router;
    }

    /**
     * Creates the router and mounts the base path.
     *
     * @param basePath The base path for router.
     * @param router   The Vert.x router instance.
     */
    public VertexRouter(String basePath, Router router) {
        this.basePath = basePath;
        this.router = router;
    }

    /**
     * Abstract method defines all routes.
     */
    public abstract void route();

    /**
     * Handles GET request.
     *
     * @param path   The path to handle.
     * @param action The action to use for handling.
     */
    protected void get(String path, HttpAction action) {
        router.get(basePath + path).handler(action::call);
    }
}
