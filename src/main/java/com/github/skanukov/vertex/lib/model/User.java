package com.github.skanukov.vertex.lib.model;

import com.github.skanukov.vertex.core.db.AsyncSQLClientFactory;
import io.vertx.core.Future;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

/**
 * User model.
 */
public class User {
    private Long id;
    private String email;
    private String passwordDigest;
    private String rememberToken;
    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public User(JsonObject jsonUser) {
        id = jsonUser.getLong("id");
        email = jsonUser.getString("email");
        passwordDigest = jsonUser.getString("password_digest");
        rememberToken = jsonUser.getString("remember_token");
        role = Role.valueOf(jsonUser.getString("role"));
        createdAt = LocalDateTime.ofInstant(jsonUser.getInstant("created_at"), ZoneId.systemDefault());
        updatedAt = LocalDateTime.ofInstant(jsonUser.getInstant("updated_at"), ZoneId.systemDefault());
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

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public String getRememberToken() {
        return rememberToken;
    }

    public Role getRole() {
        return role;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
