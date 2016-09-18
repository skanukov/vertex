package com.github.skanukov.vertex.core.controller;

import com.github.skanukov.vertex.core.template.TemplateEngineFactory;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;

/**
 * Base class for the application controllers.
 */
public class VertexController {
    /**
     * Renders HTML.
     *
     * @param context      The routing holding the template args.
     * @param templatePath The path to template file.
     */
    protected void renderTemplate(RoutingContext context, String templatePath) {
        TemplateEngineFactory.getTemplateEngine().render(context, templatePath, res -> {
            if (res.failed()) {
                context.fail(res.cause());
            } else {
                context.response().putHeader("content-type", "text/html; charset=utf-8")
                        .end(res.result());
            }
        });
    }

    /**
     * Renders JSON response.
     *
     * @param context The routing context.
     * @param object  The object to render to response.
     */
    protected void renderJson(RoutingContext context, Object object) {
        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encode(object));
    }
}
