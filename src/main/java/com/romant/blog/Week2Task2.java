package com.romant.blog;

import com.mongodb.*;

import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by Roman Tereschenko on 10/26/2014.
 */
public class Week2Task2 {

    // Program that removes the grade of type "homework" with the lowest score for each student
    public static void main(String[] args) throws UnknownHostException {
        MongoClient client = new MongoClient();
        DB db = client.getDB("students");
        DBCollection collection = db.getCollection("grades");

        // Two approaches to qyery documents QueryBuilder or DBObject and append()
        QueryBuilder queryBuilder = QueryBuilder.start("type").is("homework");


        System.out.println("\nFind all: ");
        // For find all documents the curser needs to be created to iterate over the (documents/objects)
        // QueryBuilder --> get()  returns DBObject
        Integer temp_student_id = -1;
        Double temp_score = -1.0;
        try(DBCursor cursor = collection.find(queryBuilder.get(), new BasicDBObject("score", true).append("student_id",true).append("type",true).append("_id",false)).sort(new BasicDBObject("student_id", 1).append("score",-1))) {   //  choose the fields to get via true/false   (sort(),limit(,)skip() on a cursor result)
            while(cursor.hasNext()){
                DBObject document = cursor.next();
                Integer student_id = (Integer) document.get("student_id");
                if(student_id == temp_student_id) {
                    collection.remove(document);
                }
//                if(student_id == temp_student_id) {
//                    System.out.println(score + "   " + temp_score);
//                    collection.remove(document);
//                }
                temp_student_id = student_id;
                //temp_score = score;
                //System.out.println(student_id + "    " + temp_score + "     " + score);
                System.out.println(document);

            }
        }

    }
}
