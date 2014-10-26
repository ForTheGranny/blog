package com.romant.blog;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by Roman Tereschenko on 10/26/2014.
 */
public class FindTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection myCollection = db.getCollection("namesCollection");
        myCollection.drop();
        List<String> docs = Arrays.asList("Roman", "Jhon", "Emma", "Vova");

        for( String name : docs){
            myCollection.insert(new BasicDBObject("_id",name));
        }

        myCollection.update(new BasicDBObject("_id","Emma"), new BasicDBObject("age", 28));


        DBObject doc = myCollection.findOne();
        System.out.println("Find one: " +doc);


        System.out.println("\nFind all: ");
        // For find all documents the curser needs to be created to iterate over the (documents/objects)
        try(DBCursor cursor = myCollection.find()){
            while(cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
        long curs = myCollection.count();
        System.out.println("\nCount: " + curs);

    }
}



