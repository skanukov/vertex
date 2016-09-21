package com.github.skanukov.vertex.lib.repositories;

import com.github.skanukov.vertex.core.db.AsyncSQLClientFactory;
import com.github.skanukov.vertex.lib.models.User;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class UserRepository {
    public Future<List<User>> all() {
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

                List<User> users = new ArrayList<>();
                ResultSet rs = query.result();
                if (rs.getNumRows() > 0) {
                    users.addAll(rs.getRows().stream().map(User::new).collect(Collectors.toList()));
                }
                result.complete(users);
            });
        });

        return result;
    }
}
