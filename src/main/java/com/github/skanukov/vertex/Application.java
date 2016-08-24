package com.github.skanukov.vertex;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

/**
 * Singleton class for application managing.
 */
public enum Application {
    INSTANCE;

    /**
     * Entry point for the Vertex application.
     */
    public void init() {
        Vertx vertx = Vertx.vertx();

        // Set number of verticle instances to CPU count.
        int cpuCount = Runtime.getRuntime().availableProcessors();
        DeploymentOptions options = new DeploymentOptions().setInstances(cpuCount);
        vertx.deployVerticle(ApplicationVerticle.class.getName(), options);
    }
}
