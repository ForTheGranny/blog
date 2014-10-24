package com.romant.blog;


import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;

/**
 * Created by rteresch on 24.10.2014.
 */
public class InsertDocumentTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client= new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection hello = courseDb.getCollection("insertTests");
        hello.drop();
        DBObject doc = new BasicDBObject("_id", new ObjectId()).append("y",2);

        System.out.println(doc.toString());
        hello.insert(doc);
        System.out.println(hello.find());
    }


}