package com.romant.blog;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.Random;

/**
 * Created by Roman Tereschenko on 10/26/2014.
 */
public class FindLinesTest {

    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("course");
        DBCollection lines = db.getCollection("linesCollection");
        lines.drop();
        for (int i = 0; i < 10; i++) {
            lines.insert(new BasicDBObject("_id",i)
                    .append("start",
                            new BasicDBObject("x", new Random().nextInt(90)+10)
                                      .append("y", new Random().nextInt(90) + 10)
                    )
                    .append("stop",
                            new BasicDBObject("x", new Random().nextInt(90) + 10)
                                    .append("y", new Random().nextInt(90) + 10)
                    ));

        }

        // Two approaches to qyery documents QueryBuilder or DBObject and append()
        QueryBuilder queryBuilder = QueryBuilder.start("start.x").greaterThan(50);

        System.out.println("\nFind all: ");
        // For find all documents the curser needs to be created to iterate over the (documents/objects)
        // QueryBuilder --> get()  returns DBObject
        try(DBCursor cursor = lines.find(queryBuilder.get()).sort(new BasicDBObject("start.x", 1).append("stop.x",1))) {   //  choose the fields to get via true/false   (sort(),limit(,)skip() on a cursor result)
            while(cursor.hasNext()){
                DBObject cur = cursor.next();
                System.out.println(cur);
            }
        }
        long curs = lines.count();
        System.out.println("\nCount: " + curs);

    }
}



