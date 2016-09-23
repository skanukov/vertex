package com.github.skanukov.vertex.lib.repositories;

import com.github.skanukov.vertex.core.db.Query;
import com.github.skanukov.vertex.lib.models.User;
import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.Optional;

public final class UserRepository {
    private SQLConnection connection;

    public UserRepository(SQLConnection connection) {
        this.connection = connection;
    }

    public Future<Optional<List<User>>> all() {
        Query query = new Query().setQuery("SELECT * FROM users;");
        return query.executeAndFetch(connection, User.class);
    }
}
