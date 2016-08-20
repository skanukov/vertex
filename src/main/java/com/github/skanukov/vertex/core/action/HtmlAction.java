package com.github.skanukov.vertex.core.action;

import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

/**
 * Defines interface for applications actions rendering HTML templates.
 */
@FunctionalInterface
public interface HtmlAction extends HttpAction {
    /**
     * Renders HTML.
     *
     * @param context      The routing holding the template args.
     * @param templatePath The path to template file.
     */
    default void renderTemplate(RoutingContext context, String templatePath) {
        final TemplateEngine templateEngine = FreeMarkerTemplateEngine.create();
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
