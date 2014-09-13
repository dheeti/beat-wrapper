package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.mongodb.MongoDAO;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * Created by jayramj on 7/9/14.
 */

@Path("measures")
public class Measures implements StringConstants {
    @Context
    private HttpServletRequest request;

    @POST
    @Path("{measureId}/patients/")
    @Produces(MediaType.TEXT_PLAIN)
    public String addPatient(@PathParam("measureId") String measureId,@FormParam("selectPatient") String patientId){
        ServletContext sc = request.getServletContext();
        MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        client.addMeasureToPatient(measureId,patientId);
        return "Measure Added";
    }

}
