package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.helper.APIRequestHelper;
import org.apache.http.HttpHost;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;

/**
 * Created by jayram on 9/3/15.
 */
@Path("dummypatients")
public class DummmyPatients implements StringConstants{
    @Context
    private HttpServletRequest request;


    @GET
    @Path("/")
    @Produces("application/json")
    public String getPatients() {
        ServletContext sc = request.getSession().getServletContext();
        HttpHost target = new HttpHost(((String)sc.getAttribute(POPHEALTH_IP_ADDRESS)),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(), "http");
        String patientJSON = null;
        String apiURL = POPHEALTH_API_GET_PATIENT;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        patientJSON = apiRequestHelper.executeRequest(target,apiURL);
        return patientJSON;
    }
}