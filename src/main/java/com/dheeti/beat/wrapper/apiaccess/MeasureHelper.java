package com.dheeti.beat.wrapper.apiaccess;

import com.dheeti.beat.wrapper.apiaccess.APIRequestHelper;
import com.dheeti.beat.wrapper.common.StringConstants;
import org.apache.http.HttpHost;

import javax.servlet.ServletContext;

/**
 * Created by jayram on 10/3/15.
 */
public class MeasureHelper implements StringConstants {
    public static String getMeasures(ServletContext sc, HttpHost target) {
        String measureJSON = null;
        String apiURL = POPHEALTH_API_GET_MEASURE;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        measureJSON = apiRequestHelper.executeRequest(target,apiURL);
        return measureJSON;
    }


    public static String getMeasure(ServletContext sc, HttpHost target,String measureId) {
        String measureJSON = null;
        String apiURL = POPHEALTH_API_GET_MEASURE+measureId;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        measureJSON = apiRequestHelper.executeRequest(target,apiURL);
        return measureJSON;
    }

}
