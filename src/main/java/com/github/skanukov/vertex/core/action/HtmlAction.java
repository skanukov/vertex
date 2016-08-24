package com.github.skanukov.vertex.core.action;

import com.github.skanukov.vertex.core.template.TemplateEngineFactory;
import io.vertx.ext.web.RoutingContext;

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
        TemplateEngineFactory.getTemplateEngine().render(context, templatePath, res -> {
            if (res.failed()) {
                context.fail(res.cause());
            } else {
                context.response().putHeader("content-type", "text/html; charset=utf-8")
                        .end(res.result());
            }
        });
    }
}
