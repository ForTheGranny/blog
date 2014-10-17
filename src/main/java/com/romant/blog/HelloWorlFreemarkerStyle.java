package com.romant.blog;


import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rteresch on 17.10.2014.
 */
public class HelloWorlFreemarkerStyle {

    public static void main(String[] args) throws TemplateException {
        Configuration configuration = new Configuration();
        configuration.setClassForTemplateLoading(HelloWorlFreemarkerStyle.class, "/");

        try {
            Template helloTemplate = configuration.getTemplate("helloworld.html");
            StringWriter strWriter = new StringWriter();
            Map<String, Object> helloMap = new HashMap<>();
            helloMap.put("name", "Roman");
            helloMap.put("title", "Hello World");

            helloTemplate.process(helloMap, strWriter);
            System.out.println(strWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
