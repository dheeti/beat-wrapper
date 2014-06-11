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
        while(cursor.hasNext()) {
            dbObject = cursor.next();
            HashMap<String,Object> record = new HashMap<String,Object>();
            record.put("first", dbObject.get("first"));
            record.put("last",dbObject.get("last"));
            record.put("id",dbObject.get("_id").toString());
            recordsList.add(record);
        }
        return recordsList;
    }
    public DBObject addMeasureToPatient(String measureId,String patientId){
        DB db = this.getMongoDB();
        DBCollection table = db.getCollection("records");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(patientId));
        DBCursor cursor = table.find(searchQuery);
        DBObject dbObject=null;
        while(cursor.hasNext()) {
            dbObject = cursor.next();
        }

        if(dbObject!=null){
            BasicDBObject match = new BasicDBObject();
            match.put("_id",new ObjectId(patientId));
            BasicDBObject update = new BasicDBObject();
            update.put("$push", new BasicDBObject("measure_ids", measureId));
            table.update(match,update);
        }
        return dbObject;
    }

    private DB getMongoDB() {
        return mongoDB;
    }
}
