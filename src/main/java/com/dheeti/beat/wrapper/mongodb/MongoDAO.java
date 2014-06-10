package com.dheeti.beat.wrapper.mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jayramj on 31/5/14.
 */
public class MongoDAO {
    DB mongoDB = null;
    public  MongoDAO(){
        MongoClient mongo = null;
        try {
            mongo = new MongoClient("localhost", 27017);
            this.mongoDB = mongo.getDB("beat-development");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
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

    public ArrayList<HashMap<String,Object>> executePatientSearch(String firstName, String lastName) {
        DB db = this.getMongoDB();
        DBCollection table = db.getCollection("records");
        BasicDBObject searchQuery = new BasicDBObject();
        if(!firstName.isEmpty())
            searchQuery.put("first",firstName);
        if(!lastName.isEmpty())
            searchQuery.put("last",lastName);
        DBCursor cursor = table.find(searchQuery);
        DBObject dbObject=null;
        ArrayList<HashMap<String,Object>> recordsList = new ArrayList<HashMap<String, Object>>();
        if(cursor.hasNext()) {
            dbObject = cursor.next();
            HashMap<String,Object> record = new HashMap<String,Object>();
            record.put("first", dbObject.get("first"));
            record.put("last",dbObject.get("last"));
            record.put("id",dbObject.get("_id"));
        }
        return recordsList;
    }

    private DB getMongoDB() {
        return mongoDB;
    }
}
