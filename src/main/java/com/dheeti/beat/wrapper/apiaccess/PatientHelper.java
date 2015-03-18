package com.dheeti.beat.wrapper.apiaccess;

import com.dheeti.beat.wrapper.common.StringConstants;
import org.apache.http.HttpHost;

import javax.servlet.ServletContext;

/**
 * Created by jayram on 12/3/15.
 */
public class PatientHelper implements StringConstants{
    public static String getPatients(ServletContext sc, HttpHost target) {
        String patientJSON = null;
        String apiURL = StringConstants.POPHEALTH_API_GET_PATIENT;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        patientJSON = apiRequestHelper.executeRequest(target,apiURL);
        return patientJSON;
    }

    public static String getPatient(ServletContext sc, HttpHost target,String patientId) {
        String patientJSON = null;
        String apiURL = StringConstants.POPHEALTH_API_GET_PATIENT+patientId;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        patientJSON = apiRequestHelper.executeRequest(target,apiURL);
        return patientJSON;
    }
}
