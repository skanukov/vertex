package com.github.skanukov.vertex.core.db;

import io.vertx.core.json.JsonObject;

/**
 * Represents db record.
 */
public abstract class Record {
    public static final int EMPTY_ID = -1;

    private int id;

    public Record() {
        setId(EMPTY_ID);
    }

    public Record(JsonObject jsonRecord) {
        setId(jsonRecord.getInteger("id"));
    }

    public int getId() {
        return id;
    }

    public Record setId(int id) {
        this.id = id;
        return this;
    }
}
