package com.github.skanukov.vertex.lib.model;

import com.github.skanukov.vertex.core.db.AsyncSQLClientFactory;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.util.ArrayList;
import java.util.List;

/**
 * User model.
 */
public class User extends JsonObject {
    public User(JsonObject jsonUser) {
        super(jsonUser.getMap());
    }

    public static Future<List<User>> getAll() {
        Future<List<User>> result = Future.future();

        AsyncSQLClient sqlClient = AsyncSQLClientFactory.getAsyncSQLClient();
        sqlClient.getConnection(getConnection -> {
            if (getConnection.failed()) {
                result.fail(getConnection.cause());
                return;
            }

            SQLConnection connection = getConnection.result();
            String sql = "SELECT * FROM users;";
            connection.query(sql, query -> {
                connection.close();

                if (query.failed()) {
                    result.fail(query.cause());
                    return;
                }

                List<User> users = null;
                ResultSet rs = query.result();
                if (rs.getNumRows() > 0) {
                    users = new ArrayList<>();
                    for (JsonObject jsonUser : rs.getRows()) {
                        users.add(new User(jsonUser));
                    }
                }
                result.complete(users);
            });
        });
        return result;
    }
}
