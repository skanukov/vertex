package com.github.skanukov.vertex.apps.web;

import com.github.skanukov.vertex.apps.web.controllers.HomeController;
import com.github.skanukov.vertex.apps.web.controllers.UserController;
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
        HomeController homeController = new HomeController();
        get("/", homeController::actionIndex);
        get("/json", homeController::actionJson);

        UserController userController = new UserController();
        get("/users", userController::actionIndex);
    }
}
