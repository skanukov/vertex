package com.github.skanukov.vertex.core.vertx;

import io.vertx.core.Vertx;

/**
 * Holds the application Vert.x instance.
 */
public final class VertxFactory {
    private VertxFactory() {
    }

    // Lazy initialization
    private static class VertxHolder {
        private static final Vertx INSTANCE = Vertx.vertx();
    }

    /**
     * Returns the application Vert.x instance.
     *
     * @return The application Vert.x instance.
     */
    public static Vertx getVertx() {
        return VertxHolder.INSTANCE;
    }
}
