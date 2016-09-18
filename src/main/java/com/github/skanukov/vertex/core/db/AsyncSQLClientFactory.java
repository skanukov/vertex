package com.github.skanukov.vertex.core.db;

import com.github.skanukov.vertex.Application;
import com.github.skanukov.vertex.core.config.SettingsFactory;
import com.github.skanukov.vertex.core.vertx.VertxFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.asyncsql.PostgreSQLClient;

/**
 * Holds the async sql client.
 */
public final class AsyncSQLClientFactory {
    private AsyncSQLClientFactory() {
    }

    // Lazy initialization
    private static class AsyncSQLClientHolder {
        private static final AsyncSQLClient INSTANCE = createAsyncSQLClient();
    }

    /**
     * Returns the application async sql client.
     *
     * @return The application async sql client.
     */
    public static AsyncSQLClient getAsyncSQLClient() {
        return AsyncSQLClientHolder.INSTANCE;
    }

    private static AsyncSQLClient createAsyncSQLClient() {
        JsonObject dbSettings = SettingsFactory.getSettings().getJsonObject("db");
        return PostgreSQLClient.createShared(VertxFactory.getVertx(), dbSettings);
    }
}
