#!/usr/bin/env bash

java -jar \
    -Dfile.encoding=UTF-8 \
    -Dvertx.logger-delegate-factory-class-name=io.vertx.core.logging.SLF4JLogDelegateFactory \
    target/vertex-1.0-SNAPSHOT-fat.jar
