package com.github.skanukov.vertex.core.db;

import io.vertx.core.Future;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.sql.ResultSet;
import io.vertx.ext.sql.SQLConnection;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Represents db query.
 */
public final class Query {
    private String sql;
    private final JsonArray params = new JsonArray();

    /**
     * Sets the query sql.
     *
     * @param sql The query sql.
     * @return The Query instance.
     */
    public Query setQuery(String sql) {
        this.sql = sql;
        return this;
    }

    /**
     * Adds parameter to the query.
     *
     * @param param The parameter to add to the query.
     * @return The Query instance.
     */
    public Query addParameter(Object param) {
        if (null != param) {
            params.add(param);
        } else {
            params.addNull();
        }
        return this;
    }

    public Future<Void> execute(SQLConnection connection) {
        Future<Void> result = Future.future();

        executeQuery(connection).setHandler(queryRes -> {
            if (queryRes.failed()) {
                result.fail(queryRes.cause());
            } else {
                result.complete();
            }
        });

        return result;
    }

    public <T> Future<Optional<T>> executeScalar(SQLConnection connection, Class<T> clazz) {
        Future<Optional<T>> result = Future.future();

        executeQuery(connection).setHandler(queryRes -> {
            if (queryRes.failed()) {
                result.fail(queryRes.cause());
            } else {
                ResultSet rs = queryRes.result();
                if (rs.getNumRows() < 1) {
                    result.complete(Optional.empty());
                } else {
                    T value = clazz.cast(rs.getResults().get(0).getValue(0));
                    result.complete(Optional.of(value));
                }
            }
        });

        return result;
    }

    public <T> Future<Optional<List<T>>> executeScalarList(SQLConnection connection, Class<T> clazz) {
        Future<Optional<List<T>>> result = Future.future();

        executeQuery(connection).setHandler(queryRes -> {
            if (queryRes.failed()) {
                result.fail(queryRes.cause());
            } else {
                ResultSet rs = queryRes.result();
                if (rs.getNumRows() < 1) {
                    result.complete(Optional.empty());
                } else {
                    List<T> resultList = new ArrayList<>();
                    for (JsonArray row : rs.getResults()) {
                        T value = clazz.cast(row.getValue(0));
                        resultList.add(value);
                    }
                    result.complete(Optional.of(resultList));
                }
            }
        });

        return result;
    }

    public <T extends Record> Future<Optional<T>> executeAndFetchFirst(SQLConnection connection, Class<T> clazz) {
        Future<Optional<T>> result = Future.future();

        executeQuery(connection).setHandler(queryRes -> {
            if (queryRes.failed()) {
                result.fail(queryRes.cause());
            } else {
                ResultSet rs = queryRes.result();
                if (rs.getNumRows() < 1) {
                    result.complete(Optional.empty());
                } else {
                    try {
                        JsonObject jsonRecord = rs.getRows().get(0);
                        T record = clazz.getConstructor(JsonObject.class).newInstance(jsonRecord);
                        result.complete(Optional.of(record));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        result.fail(e);
                    }
                }
            }
        });

        return result;
    }

    public <T extends Record> Future<Optional<List<T>>> executeAndFetch(SQLConnection connection, Class<T> clazz) {
        Future<Optional<List<T>>> result = Future.future();

        executeQuery(connection).setHandler(queryRes -> {
            if (queryRes.failed()) {
                result.fail(queryRes.cause());
            } else {
                ResultSet rs = queryRes.result();
                if (rs.getNumRows() < 1) {
                    result.complete(Optional.empty());
                } else {
                    try {
                        List<T> resultList = new ArrayList<>();
                        for (JsonObject jsonRecord : rs.getRows()) {
                            T record = clazz.getConstructor(JsonObject.class).newInstance(jsonRecord);
                            resultList.add(record);
                        }
                        result.complete(Optional.of(resultList));
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        result.fail(e);
                    }
                }
            }
        });

        return result;
    }

    private Future<ResultSet> executeQuery(SQLConnection connection) {
        Future<ResultSet> result = Future.future();
        connection.queryWithParams(sql, params, result.completer());
        return result;
    }
}
