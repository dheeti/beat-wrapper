package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.ServletContext;
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
public class Patients implements StringConstants{
    @Context
    private HttpServletRequest request;
    private HashMap<String,Object> model = new HashMap<>();
    private static String pophealthip = "pophealth.beatalerts.com";
    private static String pophealthport = "27017";
    private static String dbName = "pophealth-production";

    public HashMap<String,Object> getModel() {
        return model;
    }

    public void setModel(HashMap<String,Object> model) {
        this.model = model;
    }

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
        String popResponse =  new PopHealthConnector((String)request.getServletContext().getAttribute(pophealthip)).getPatientMeasure(patientId,measureId,userName,password);
        return popResponse;
    }

    @POST
    @Path("/search/")
    @Produces("text/html")
    public Viewable getPatientSearch(@FormParam("hqmf_id")String hqmf_id,
                                   @FormParam("firstname") String firstName,
                                   @FormParam("lastname") String lastName){
        String result = "";
        ServletContext sc = request.getServletContext();

        try {
        //MongoDAO client = new MongoDAO((String)sc.getAttribute(pophealthip),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
            MongoDAO client = new MongoDAO(pophealthip,pophealthport,dbName);
            model.put("hqmf_id",hqmf_id);
            model.put("patients",client.executePatientSearch(firstName, lastName));
        ObjectMapper mapper = new ObjectMapper();
            result=  mapper.writeValueAsString(model);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Viewable("/matchingpatients.jsp",model);
    }

    @POST
    @Path("{patientid}/measure/{measureid}")
    @Produces("application/json")
    public boolean addPatient(@PathParam("measureid") String measureId,
                              @PathParam("patientid") String patientId){
        ServletContext sc = request.getServletContext();

        MongoDAO client = new MongoDAO((String)sc.getAttribute(pophealthip),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        client.addMeasureToPatient(measureId,patientId);
        return false;
    }

}
