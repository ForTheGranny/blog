package com.romant.blog;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Arrays;
import java.util.Date;

/**
 * Created by rteresch on 24.10.2014.
 */
public class DocumentRepresentationTest {

    public static void main(String[] args) {
        DBObject document = new BasicDBObject();
        document.put("username","Roman");
        document.put("birthdate", new Date(234832423));
        document.put("programme", true);
        document.put("age", 5);
        document.put("languages", Arrays.asList("Java","Scala"));
        document.put("address", new BasicDBObject("street", "Krasnozavodskay 2/13")
                                                .append("town", "Kiev")
                                                .append("zip","03062"));

    }
}
