package com.github.skanukov.vertex.core.route;

import io.vertx.ext.web.Router;

/**
 * Base interface for mounting application routers.
 */
@FunctionalInterface
public interface VertexDispatcher {
    /**
     * Dispatches application routers.
     */
    void dispatch(Router router);
}
