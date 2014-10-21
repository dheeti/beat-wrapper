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
import java.io.*;
import java.nio.file.FileSystems;
import java.util.HashMap;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


/**
 * Root resource (exposed at "myresource" path)
 */
@Path("patients")
public class Patients implements StringConstants{
    @Context
    private HttpServletRequest request;
    private HashMap<String,Object> model = new HashMap<>();

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
        String popResponse =  new PopHealthConnector((String)request.getSession().getServletContext().getAttribute(POPHEALTH_IP_ADDRESS))
                .getPatientMeasure(patientId,measureId,userName,password);
        return popResponse;
    }

    @POST
    @Path("/search/")
    @Produces("text/html")
    public Viewable getPatientSearch(@FormParam("hqmf_id")String hqmf_id,
                                   @FormParam("firstname") String firstName,
                                   @FormParam("lastname") String lastName){
        String result = "";
        ServletContext sc = request.getSession().getServletContext();

        try {
        MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
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
@Path("upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.TEXT_PLAIN)
public String uploadFile(
        @FormDataParam("file") InputStream fileInputStream,
        @FormDataParam("file") FormDataContentDisposition fileDisposition)
        throws FileNotFoundException, IOException {
    ServletContext sc = request.getSession().getServletContext();
    String fileName = fileDisposition.getFileName();
    System.out.println("***** fileName " + fileDisposition.getFileName());

    File file = new File(sc.getRealPath(File.separator)+"upload.xml");

    System.out.println("***** filepath " + FileSystems.getDefault().getPath(
            file.getAbsolutePath()));

    try (OutputStream fileOutputStream = new FileOutputStream(file)) {
        int read = 0;
        final byte[] bytes = new byte[1024];
        while ((read = fileInputStream.read(bytes)) != -1) {
            fileOutputStream.write(bytes, 0, read);
        }
    }

    UploadQRDA1 upload = new UploadQRDA1((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue());
    String response = upload.executeMultiPartRequest(file);
    file.delete();
    return response;
}


}
