package com.romant.blog;


import com.mongodb.*;

import java.net.UnknownHostException;

/**
 * Created by rteresch on 24.10.2014.
 */
public class InsertDocumentTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client= new MongoClient();
        DB courseDb = client.getDB("course");
        DBCollection hello = courseDb.getCollection("insertTests");

        DBObject doc = new BasicDBObject("x",1);

        hello.insert(doc);
    }


}