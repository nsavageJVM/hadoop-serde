package com.eduonix.hadoop.partone;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoURI;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Set;

/**
 * Created by ubu on 21.07.15.
 */
public class MongoRunner {

    public static void main(String[] args) {

       // MongoURI uri = new MongoURI("mongodb://127.0.0.1:27017/tweets");
         MongoClient mongo = new MongoClient( "0.0.0.0" , 27017 );
        // Now connect to your databases
        DB db = mongo.getDB("test");
        System.out.println("Connect to database successfully"+db.getMongo().toString());

        DBCollection databaseList = db.getCollection("dbParameters_Filtered");
        DBCollection dataLinksDb = db.getCollection("datalinks_Filtered");

        Set<String> colls = db.getCollectionNames();
        for (String s : colls) {
            System.out.println(s);
        }

    }
}
