package com.romant.blog;

import jdk.nashorn.internal.objects.annotations.Getter;
import spark.Route;

import javax.xml.ws.http.HTTPException;

import static spark.Spark.*;

/**
 * Created by rteresch on 17.10.2014.
 */
public class HelloWorldSparkStyle {

    public static void main(String[] args) throws HTTPException {
            get("/hello", (request,response) ->  "Hello World Spark Style");
    }
}
