package com.github.skanukov.vertex;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;

public final class Application {
    //<editor-fold desc="Singleton region">
    private Application() {
    }

    // Lazy singleton initialization
    private static class SingletonHolder {
        private static final Application INSTANCE = new Application();
    }

    /**
     * Returns the Application singleton instance.
     *
     * @return The Application singleton instance.
     */
    public static Application getInstance() {
        return SingletonHolder.INSTANCE;
    }
    //</editor-fold>

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
