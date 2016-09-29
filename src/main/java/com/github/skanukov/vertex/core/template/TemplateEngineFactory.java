package com.github.skanukov.vertex.core.template;

import com.github.skanukov.vertex.core.config.SettingsFactory;
import com.github.skanukov.vertex.core.vertx.VertxFactory;
import io.vertx.ext.web.templ.PebbleTemplateEngine;
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
        // If app is running in debug mode create a new template engine instance every time.
        // This will prevent templates from being cached.
        if (SettingsFactory.getSettings().getBoolean("isDebug", false)) {
            return createTemplateEngine();
        }
        return TemplateEngineHolder.INSTANCE;
    }

    private static TemplateEngine createTemplateEngine() {
        return PebbleTemplateEngine.create(VertxFactory.getVertx());
    }
}
