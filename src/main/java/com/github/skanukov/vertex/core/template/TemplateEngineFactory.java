package com.github.skanukov.vertex.core.template;

import com.github.skanukov.vertex.core.config.SettingsFactory;
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
        private static final TemplateEngine INSTANCE = createTemplateEngine();
    }

    /**
     * Returns the application template engine.
     *
     * @return The application template engine.
     */
    public static TemplateEngine getTemplateEngine() {
        return TemplateEngineHolder.INSTANCE;
    }

    private static TemplateEngine createTemplateEngine() {
        FreeMarkerTemplateEngine templateEngine = FreeMarkerTemplateEngine.create();
        if (SettingsFactory.getSettings().getBoolean("is_debug", false)) {
            templateEngine.setMaxCacheSize(0);
        }
        return templateEngine;
    }
}
