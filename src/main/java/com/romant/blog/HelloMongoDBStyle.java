package com.romant.blog;

import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by rteresch on 17.10.2014.
 */
public class HelloMongoDBStyle {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient( new ServerAddress("localhost", 27017));

        //DB database = mongoClient.getDB("course");
        DBCollection collection = database.getCollection("hello");

        DBObject document = collection.findOne();
        System.out.println(document.toString());
    }
}
