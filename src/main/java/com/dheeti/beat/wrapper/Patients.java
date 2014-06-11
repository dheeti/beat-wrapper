package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import org.codehaus.jackson.map.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static javax.ws.rs.core.MediaType.TEXT_HTML_TYPE;

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
    public String getPatientSearch(@FormParam("hqmf_id")String hqmf_id,
                                   @FormParam("firstname") String firstName,
                                   @FormParam("lastname") String lastName){
        String result = "";
        try {
        MongoDAO client = new MongoDAO();
        ArrayList<HashMap<String,Object>> patientList = client.executePatientSearch(firstName, lastName);
        ObjectMapper mapper = new ObjectMapper();
            result=  mapper.writeValueAsString(patientList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @POST
    @Path("{patientid}/measure/{measureid}")
    @Produces("application/json")
    public boolean addPatient(@PathParam("measureid") String measureId,
                              @PathParam("patientid") String patientId){
        MongoDAO client = new MongoDAO();
        client.addMeasureToPatient(measureId,patientId);
        return false;
    }

}
