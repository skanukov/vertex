package com.github.skanukov.vertex;

import com.github.skanukov.vertex.config.ApplicationDispatcher;
import com.github.skanukov.vertex.core.config.SettingsFactory;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.http.HttpServer;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;

/**
 * Creates, configs and starts the HTTP server.
 */
public final class ApplicationVerticle extends AbstractVerticle {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationVerticle.class);

    /**
     * Creates and starts the server, sets the router dispatcher.
     */
    @Override
    public void start() {
        startServer(getRouter());
    }

    private Router getRouter() {
        Router router = Router.router(vertx);
        new ApplicationDispatcher().dispatch(router);
        return router;
    }

    private void startServer(Router router) {
        int serverPort = SettingsFactory.getSettings().getInteger("port", SettingsFactory.DEFAULT_SERVER_PORT);
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(serverPort);
        logger.info("Verticle server {} running at {}", this, serverPort);
    }
}
