package com.github.skanukov.vertex;

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
        logger.info("Verticle {} created", this);

        HttpServer server = vertx.createHttpServer();
        Router router = Router.router(vertx);
        router.route("/").handler(rc ->
                rc.response().putHeader("content-type", "text/html").end("Hello, world!"));
        server.requestHandler(router::accept).listen(8080);
    }
}
