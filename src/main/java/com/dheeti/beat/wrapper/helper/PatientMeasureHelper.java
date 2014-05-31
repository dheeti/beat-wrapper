package com.dheeti.beat.wrapper.helper;

import java.util.List;
import java.util.Map;

/**
 * Created by jayramj on 31/5/14.
 */
public class PatientMeasureHelper {
    public static Map<String,Object> getPatientMeasure(List<Map<String,Object>> measureList,String measureId){
        Map<String,Object> resultMap = null;
        for(Map<String,Object> element:measureList){
            if(measureId.equals(element.get("measure_id").toString()))
                System.out.println("matched");
            resultMap = element;
        }
        return resultMap;

    }
}
