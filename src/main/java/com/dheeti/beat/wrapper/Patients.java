package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.mongodb.MongoDAO;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("patients")
public class Patients {
    @Context
    private HttpServletRequest request;

    /**
     * Method handling HTTP GET requests. The returned object will be sent
     * to the client as "text/plain" media type.
     *
     * @return String that will be returned as a text/plain response.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }

    @GET
    @Path("{patientId}/measure/{measureId}")
    @Produces("application/json")
    public String getPatientMeasure(@PathParam("patientId")String patientId,@PathParam("measureId")String measureId) {
        String userName = (String)request.getSession().getAttribute("userName");
        String password = (String)request.getSession().getAttribute("password");
        String popResponse =  new PopHealthConnector().getPatientMeasure(patientId,measureId,userName,password);
        return popResponse;
    }

    @POST
    @Path("/search/")
    @Produces("application/json")
    public ArrayList<HashMap<String,Object>> getPatientSearch(@FormParam("hqmf_id")String hqmf_id,
                                   @FormParam("firstname") String firstName,
                                   @FormParam("lastname") String lastName){
        MongoDAO client = new MongoDAO();
        ArrayList<HashMap<String,Object>> result = client.executePatientSearch(firstName, lastName);
        return result;
    }

}
