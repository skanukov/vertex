package com.github.skanukov.vertex;

import com.github.skanukov.vertex.core.config.SettingsFactory;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

/**
 * Singleton class for application managing.
 */
public enum Application {
    INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(Application.class);
    private Vertx vertx;

    /**
     * Entry point for the Vertex application.
     */
    public void init() {
        if (SettingsFactory.getSettings().getBoolean("is_debug", false)) {
            logger.info("Debug mode enabled");
        }

        vertx = Vertx.vertx();

        // Set number of verticle instances to CPU count.
        int cpuCount = Runtime.getRuntime().availableProcessors();
        DeploymentOptions options = new DeploymentOptions().setInstances(cpuCount);
        vertx.deployVerticle(ApplicationVerticle.class.getName(), options);
    }

    public Vertx getVertx() {
        return vertx;
    }
}
