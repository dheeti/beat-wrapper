package com.dheeti.beat.wrapper.helper;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jayram on 9/3/15.
 */
public class JsonHashMapHelper {
    public static HashMap<String,Object> jsonToHashMap(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, Object> mapObject = null;
        try {
            mapObject = mapper.readValue(jsonString,new TypeReference<HashMap<String, Object>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapObject;
    }
    public static ArrayList<HashMap<String,Object>> jsonToListMap(String jsonString){
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<HashMap<String, Object>> mapObject = null;
        try {
            mapObject = mapper.readValue(jsonString,new TypeReference<ArrayList<HashMap<String, Object>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapObject;
    }

}
