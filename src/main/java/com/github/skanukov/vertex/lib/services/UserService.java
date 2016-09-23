package com.github.skanukov.vertex.lib.services;

import com.github.skanukov.vertex.core.db.SQLConnectionFactory;
import com.github.skanukov.vertex.lib.models.User;
import com.github.skanukov.vertex.lib.repositories.UserRepository;
import io.vertx.core.Future;
import io.vertx.ext.sql.SQLConnection;

import java.util.List;
import java.util.Optional;

public class UserService {
    public Future<Optional<List<User>>> allUsers() {
        Future<Optional<List<User>>> result = Future.future();

        SQLConnectionFactory.getConnection().setHandler(getConnection -> {
            if (getConnection.failed()) {
                result.fail(getConnection.cause());
                return;
            }

            SQLConnection connection = getConnection.result();
            UserRepository userRepo = new UserRepository(connection);
            userRepo.all().setHandler(allUsers -> {
                connection.close();
                if (allUsers.failed()) {
                    result.fail(allUsers.cause());
                } else {
                    result.complete(allUsers.result());
                }
            });
        });

        return result;
    }
}
