package com.github.skanukov.vertex.lib.repositories;

import com.github.skanukov.vertex.core.db.Query;
import com.github.skanukov.vertex.core.db.SQLConnectionFactory;
import com.github.skanukov.vertex.lib.models.User;
import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.Optional;

public final class UserRepository {
    public Future<Optional<List<User>>> all() {
        Future<Optional<List<User>>> result = Future.future();

        SQLConnectionFactory.getConnection().setHandler(getConnection -> {
            if (getConnection.failed()) {
                result.fail(getConnection.cause());
                return;
            }

            SQLConnection connection = getConnection.result();
            Query query = new Query().setQuery("SELECT * FROM users;");
            query.executeAndFetch(connection, User.class).setHandler(getUsers -> {
                connection.close();
                if (getUsers.failed()) {
                    result.fail(getUsers.cause());
                } else {
                    result.complete(getUsers.result());
                }
            });
        });

        return result;
    }
}
