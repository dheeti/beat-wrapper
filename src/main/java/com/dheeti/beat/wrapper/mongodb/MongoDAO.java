package com.dheeti.beat.wrapper.mongodb;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by jayramj on 31/5/14.
 */
public class MongoDAO implements StringConstants{
    DB mongoDB = null;
    public  MongoDAO(String ip,String port,String db){
        MongoClient mongo = null;
        MongoClientOptions options = MongoClientOptions.builder().connectTimeout(10000)
                .maxWaitTime(10000).build();
        try {
            mongo = new MongoClient(ip, options);
            this.mongoDB = mongo.getDB(db);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }
    public String executeLoginQuery(String userName,String password){

        String encryptedPassword= null;
        MongoClient mongo = null;
        try {
            mongo = new MongoClient( "localhost" , 27017 );
        DB db = mongo.getDB("pophealth-development");
        DBCollection table = db.getCollection("users");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("username", userName);
        DBCursor cursor = table.find(searchQuery);
        DBObject dbObject=null;
            while(cursor.hasNext()) {
                dbObject = cursor.next();
                encryptedPassword = (String)dbObject.get("encrypted_password");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return encryptedPassword;

    }

    public HashMap<String,HashMap<String,Object>> executePatientSearch(String firstName, String lastName) {
        DB db = this.getMongoDB();
        DBCollection table = db.getCollection("records");
        BasicDBObject searchQuery = new BasicDBObject();
        if(!firstName.isEmpty())
            searchQuery.put("first",new BasicDBObject("$regex",firstName));
        if(!lastName.isEmpty())
            searchQuery.put("last",new BasicDBObject("$regex",lastName));
        DBCursor cursor = table.find(searchQuery);
        DBObject dbObject=null;
        HashMap<String,HashMap<String,Object>> patientMap = new HashMap<String,HashMap<String,Object>>();
        //ArrayList<HashMap<String,Object>> recordsList = new ArrayList<HashMap<String, Object>>();
        cursor.addOption(Bytes.QUERYOPTION_NOTIMEOUT);
        while(cursor.hasNext()) {
            dbObject = cursor.next();
            HashMap<String,Object> record = new HashMap<String,Object>();
            record.put("first", dbObject.get("first"));
            record.put("last",dbObject.get("last"));
            record.put("id",dbObject.get("_id").toString());
            patientMap.put(dbObject.get("_id").toString(),record);
        }
        return patientMap;
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

    public DBObject addMRNToPatient(String patientId,String mrn){
        DBObject dbObject = null;
        DB db = this.getMongoDB();
        DBCollection table = db.getCollection("records");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(patientId));
        DBCursor cursor = table.find(searchQuery);

        while(cursor.hasNext()) {
            dbObject = cursor.next();
        }

        if(dbObject!=null){
            BasicDBObject update = new BasicDBObject();
            update.put("$push", new BasicDBObject("mrn", mrn));
            table.update(dbObject,update);
        }
        return dbObject;
    }
    public DBObject getPatientByPatientId(String patientId) {
        DBObject dbObject = null;
        DB db = this.getMongoDB();
        DBCollection table = db.getCollection("records");
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("_id", new ObjectId(patientId));
        DBCursor cursor = table.find(searchQuery);

        while(cursor.hasNext()) {
            dbObject = cursor.next();
        }
        return dbObject;

    }
    private DB getMongoDB() {
        return mongoDB;
    }


}
