package com.dheeti.beat.wrapper;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("patients")
public class Patients {

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
        String popResponse =  new PopHealthConnector().getPatientMeasure(patientId,measureId);
        return popResponse;
    }
}
