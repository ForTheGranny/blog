package com.romant.blog;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Roman Tereschenko on 10/26/2014.
 */
public class FindCriteriaTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection myCollection = db.getCollection("findCriteriaTest");
        myCollection.drop();
        for (int i = 0; i < 10; i++) {
            myCollection.insert(new BasicDBObject("x", new Random().nextInt(3))
                    .append("y", new Random().nextInt(100))
                    .append("z", new Random().nextInt(100)));
        }

        // Two approaches to qyery documents QueryBuilder or DBObject and append()
        QueryBuilder queryBuilder = QueryBuilder.start("x").is(0).and("y").greaterThan(10).lessThan(50);
        DBObject query = new BasicDBObject("x",0).append("y", new BasicDBObject("$gt",10).append("$lt", 90));

        DBObject doc = myCollection.findOne();
        System.out.println("Find one: " +doc);


        System.out.println("\nFind all: ");
        // For find all documents the curser needs to be created to iterate over the (documents/objects)
        // QueryBuilder --> get()  returns DBObject
        try(DBCursor cursor = myCollection.find(queryBuilder.get(), new BasicDBObject("y", true).append("_id",false))){   //  choose the fields to get via true/false
            while(cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
        long curs = myCollection.count();
        System.out.println("\nCount: " + curs);

    }
}



