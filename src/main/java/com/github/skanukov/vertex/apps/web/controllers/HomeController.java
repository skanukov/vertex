package com.github.skanukov.vertex.apps.web.controllers;

import com.github.skanukov.vertex.core.controller.VertexController;
import io.vertx.ext.web.RoutingContext;

import java.util.Arrays;
import java.util.List;

public class HomeController extends VertexController {
    public void actionIndex(RoutingContext context) {
        context.put("name", "User");
        renderTemplate(context, "./templates/web/home/index.ftl");
    }

    public void actionJson(RoutingContext context) {
        List<Integer> intArray = Arrays.asList(1, 2, 3);
        renderJson(context, intArray);
    }
}
