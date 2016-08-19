package com.github.skanukov.vertex.apps.web.controllers;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

/**
 * Base class for the current application actions.
 */
public abstract class WebAction {
    private final TemplateEngine templateEngine = FreeMarkerTemplateEngine.create();

    /**
     * Renders JSON response.
     *
     * @param context    The routing context.
     * @param jsonObject The JsonObject to render to response.
     */
    protected void renderJson(RoutingContext context, JsonObject jsonObject) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(jsonObject.encode());
    }

    /**
     * Renders JSON response.
     *
     * @param context   The routing context.
     * @param jsonArray The JsonArray to render to response.
     */
    protected void renderJson(RoutingContext context, JsonArray jsonArray) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(jsonArray.encode());
    }

    /**
     * Renders HTML.
     *
     * @param context      The routing holding the template args.
     * @param templatePath The path to template file.
     */
    protected void renderTemplate(RoutingContext context, String templatePath) {
        templateEngine.render(context, templatePath, res -> {
            if (res.failed()) {
                context.fail(res.cause());
            } else {
                context.response().putHeader("content-type", "text/html; charset=utf-8")
                        .end(res.result());
            }
        });
    }
}
