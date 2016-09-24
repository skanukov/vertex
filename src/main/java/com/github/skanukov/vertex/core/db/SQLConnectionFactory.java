package com.github.skanukov.vertex.core.db;

import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

public final class SQLConnectionFactory {
    private SQLConnectionFactory() {
    }

    public static Future<SQLConnection> getConnection() {
        Future<SQLConnection> result = Future.future();
        AsyncSQLClientFactory.getAsyncSQLClient().getConnection(result.completer());
        return result;
    }
}
