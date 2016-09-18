package com.github.skanukov.vertex.apps.web.controllers.home;

import com.github.skanukov.vertex.core.action.JsonAction;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;
import java.util.List;

/**
 * Home controller IndexAction action.
 */
public class JsonSampleAction extends HomeAction implements JsonAction {
    @Override
    public void call(RoutingContext context) {
        List<Integer> intArray = Arrays.asList(1, 2, 3);
        renderJson(context, intArray);
    }
}
