package com.github.skanukov.vertex.config;

import com.github.skanukov.vertex.apps.web.config.WebRouter;
import com.github.skanukov.vertex.core.route.VertexDispatcher;
import io.vertx.ext.web.Router;

/**
 * Handles all routes for applications.
 */
public final class ApplicationDispatcher implements VertexDispatcher {
    /**
     * Defines mount points for applications.
     *
     * @param router The Vert.x router instance.
     */
    @Override
    public void dispatch(Router router) {
        new WebRouter(router).route();
    }
}
