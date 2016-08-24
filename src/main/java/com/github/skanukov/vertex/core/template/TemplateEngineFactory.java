package com.github.skanukov.vertex.core.template;

import io.vertx.ext.web.templ.FreeMarkerTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

/**
 * Holds the template engine.
 */
public final class TemplateEngineFactory {
    private TemplateEngineFactory() {
    }

    // Lazy initialization
    private static class TemplateEngineHolder {
        private static final TemplateEngine INSTANCE = FreeMarkerTemplateEngine.create();
    }

    /**
     * Returns the application template engine.
     *
     * @return The application template engine.
     */
    public static TemplateEngine getTemplateEngine() {
        return TemplateEngineHolder.INSTANCE;
    }
}
