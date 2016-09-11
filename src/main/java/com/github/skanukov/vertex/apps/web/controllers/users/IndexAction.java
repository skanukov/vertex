package com.github.skanukov.vertex.apps.web.controllers.users;

import com.github.skanukov.vertex.core.action.JsonAction;
import com.github.skanukov.vertex.core.db.AsyncSQLClientFactory;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.asyncsql.AsyncSQLClient;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;
import io.vertx.ext.web.RoutingContext;

import java.util.ArrayList;
import java.util.List;

public class IndexAction implements JsonAction {
    @Override
    public void call(RoutingContext context) {
        AsyncSQLClient sqlClient = AsyncSQLClientFactory.getAsyncSQLClient();
        sqlClient.getConnection(con -> {
            if (con.succeeded()) {
                SQLConnection connection = con.result();
                String sql = "SELECT * FROM users;";
                connection.query(sql, query -> {
                    connection.close();
                    if (query.succeeded()) {
                        List<JsonObject> users = new ArrayList<>();
                        ResultSet rs = query.result();
                        if (rs.getNumRows() > 0) {
                            users.addAll(rs.getRows());
                        }
                        renderJson(context, users);
                    } else {
                        context.fail(query.cause());
                    }
                });
            } else {
                context.fail(con.cause());
            }
        });
    }
}
