package com.romant.blog;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import spark.HaltException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.halt;

/**
 * Created by rteresch on 17.10.2014.
 */
public class HelloWorldSparkFreemarketStyle {

    public static void main(String[] args) throws TemplateException {

        //freemarket configuration here
        final Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorlFreemarkerStyle.class, "/");

        // Define (spark framework) Route's interface handle(req, res) method implementation through lambda expression  (request, response) -> {}
        get("/hello", (request, response) -> {
            StringWriter strWriter = new StringWriter();
            try {
                //Template helloTemplate = configuration.getTemplate("helloworld.html");
                Map<String, Object> helloMap = new HashMap<>();
                helloMap.put("name", "Roman");
                helloMap.put("title", "HelloWorld");

                helloTemplate.process(helloMap, strWriter);

            } catch (IOException e) {
                halt(500);
            } catch (TemplateException e) {
                e.printStackTrace();
            }
            return strWriter;
        });
    }

}
