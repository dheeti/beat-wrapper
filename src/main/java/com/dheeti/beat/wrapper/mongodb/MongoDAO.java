package com.dheeti.beat.wrapper.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

import java.net.UnknownHostException;

/**
 * Created by jayramj on 31/5/14.
 */
public class MongoDAO {
    public boolean executeLoginQuery(String userName,String password){

        boolean success = false;
        /*MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("pophealth-development");
        DBCollection table = db.getCollection("measures");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", "40280381-3D61-56A7-013E-6649110743CE");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }*/
        return success;

    }
}
