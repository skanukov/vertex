package com.github.skanukov.vertex.lib.models;

import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * User model.
 */
public final class User {
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
