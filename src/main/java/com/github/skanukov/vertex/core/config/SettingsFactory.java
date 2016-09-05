package com.github.skanukov.vertex.core.config;

import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Holds the application settings.
 */
public final class SettingsFactory {
    private static final String SETTINGS_FILE_PATH = "./config/application.json";

    private SettingsFactory() {
    }

    // Lazy initialization
    private static class SettingsHolder {
        private static final JsonObject INSTANCE = loadFromFile();
    }

    /**
     * Returns the application settings.
     *
     * @return The application settings.
     */
    public static JsonObject getSettings() {
        return SettingsHolder.INSTANCE;
    }

    private static JsonObject loadFromFile() {
        Path settingFilePath = Paths.get(SETTINGS_FILE_PATH);
        String settingsFileContent = null;
        try {
            settingsFileContent = new String(Files.readAllBytes(settingFilePath), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return new JsonObject(settingsFileContent);
    }
}
