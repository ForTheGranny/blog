package com.romant.blog;

import com.mongodb.*;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.net.UnknownHostException;

import static spark.Spark.get;
import static spark.Spark.halt;

/**
 * Created by rteresch on 17.10.2014.
 */
public class MongoDBSparkFreemarkerHelloWorld {
    public static void main(String[] args) throws TemplateException, UnknownHostException {

        //freemarket configuration here
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorlFreemarkerStyle.class, "/");

        //MongoDB connection and
        MongoClient mongoClient = new MongoClient( new ServerAddress("localhost", 27017));
        DB database = mongoClient.getDB("course");
        DBCollection collection = database.getCollection("hello");


        // Define (spark framework) Route's interface handle(req, res) method implementation through lambda expression  (request, response) -> {}
        get("/hello", (request, response) -> {
            StringWriter strWriter = new StringWriter();
            try {
                Template helloTemplate = configuration.getTemplate("helloworld.html");
                DBObject document = collection.findOne();
                helloTemplate.process(document, strWriter);
            } catch (IOException e) {
                halt(500);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            return strWriter;
        });
    }
}
