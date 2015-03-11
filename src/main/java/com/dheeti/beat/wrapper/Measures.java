package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.helper.APIRequestHelper;
import com.dheeti.beat.wrapper.helper.MeasureHelper;
import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.apache.http.HttpHost;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("measures")
public class Measures implements StringConstants {
    @Context
    private HttpServletRequest request;

    @POST
    @Path("{measureId}/patients/")
    @Produces(MediaType.TEXT_PLAIN)
    public String addPatient(@PathParam("measureId") String measureId,@FormParam("selectPatient") String patientId){
        ServletContext sc = request.getSession().getServletContext();
        MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        client.addMeasureToPatient(measureId,patientId);
        return "Measure Added";
    }

    @GET
    @Path("/{measureId}")
    @Produces("application/json")
    public String getMeasure(@PathParam("measureId") String measureId){
        ServletContext sc = request.getSession().getServletContext();
        HttpHost target = new HttpHost(((String)sc.getAttribute(POPHEALTH_IP_ADDRESS)),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(), "http");
        String measureJSON = null;
        String apiURL = POPHEALTH_API_GET_MEASURE + measureId;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        measureJSON = apiRequestHelper.executeRequest(target,apiURL);
                return measureJSON;
    }

    @GET
    @Path("/")
    @Produces("application/json")
    public String getMeasures(){
        ServletContext sc = request.getSession().getServletContext();
        HttpHost target = new HttpHost(((String)sc.getAttribute(POPHEALTH_IP_ADDRESS)),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(), "http");
        String measureJSON = MeasureHelper.getMeasures(sc,target);
        return measureJSON;
    }
}
