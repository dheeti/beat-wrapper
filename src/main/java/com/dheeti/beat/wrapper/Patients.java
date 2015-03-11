package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.common.StringConstants;
import com.dheeti.beat.wrapper.helper.APIRequestHelper;
import com.dheeti.beat.wrapper.helper.JsonHashMapHelper;
import com.dheeti.beat.wrapper.helper.MeasureHelper;
import com.dheeti.beat.wrapper.mongodb.MongoDAO;
import com.mongodb.DBObject;
import org.apache.http.HttpHost;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.server.mvc.Viewable;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.io.*;
import java.nio.file.FileSystems;
import java.util.ArrayList;
import java.util.HashMap;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;


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

    @GET
    @Path("{patientId}/measure/{measureId}")
    @Produces("application/json")
    public String getPatientMeasure(@PathParam("patientId")String patientId,@PathParam("measureId")String measureId) {
        String userName = (String)request.getSession().getAttribute("userName");
        String password = (String)request.getSession().getAttribute("password");
        String popResponse =  new PopHealthConnector((String)request.getSession().getServletContext().getAttribute(POPHEALTH_IP_ADDRESS)).getPatientMeasure(patientId,measureId,userName,password);
        return popResponse;
    }

    @POST
    @Path("/search/")
    @Produces("text/html")
    public Viewable getPatientSearch(@FormParam("hqmf_id")String hqmf_id,
                                   @FormParam("firstname") String firstName,
                                   @FormParam("lastname") String lastName){
        ServletContext sc = request.getSession().getServletContext();
        MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        HashMap<String,HashMap<String,Object>> wrapper  = client.executePatientSearch(firstName, lastName);

        return new Viewable("/patientlist.ftl",wrapper);
    }

@POST
@Path("uploadHTTP")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.TEXT_PLAIN)
public String uploadHTTP(
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
        fileOutputStream.close();
    }

    return uploadPatientData(file,sc);
}

    @POST
    @Path("uploadFTP")
    @Produces(MediaType.TEXT_PLAIN)
    public String uploadFTP(
            @QueryParam("subdir") String subdir,
            @QueryParam("filename") String filename)
            throws FileNotFoundException, IOException {
        ServletContext sc = request.getSession().getServletContext();
        String baseDir = (String)sc.getAttribute(QRDA_BASE_DIR);
        if (!(subdir==null || subdir.equals("null") || subdir.equals("")))
            baseDir = baseDir+subdir+"/";
        File file = new File(baseDir+filename);
        return uploadPatientData(file,sc);
    }

    private String uploadPatientData(File file, ServletContext sc) throws IOException {


        UploadQRDA1 upload = new UploadQRDA1((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        String response = upload.executeMultiPartRequest(file);
        file.delete();
        return response;
    }

    @POST
    @Path("{patientId}/mrn/{mrn}")
    @Produces("application/json")
    public String addMRNToPatient(@PathParam("patientId")String patientId,
                                    @PathParam("mrn")String mrn){
        String result = "";
        ServletContext sc = request.getSession().getServletContext();

            MongoDAO client = new MongoDAO((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),(String)sc.getAttribute(POPHEALTH_MONGO_PORT),(String)sc.getAttribute(POPHEALTH_MONGO_DB));
        ObjectMapper mapper = new ObjectMapper();
        try {
            result=  mapper.writeValueAsString(client.addMRNToPatient(patientId,mrn));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GET
    @Path("/{patientId}")
    @Produces("text/html")
    public Viewable getPatientByPatientId(@PathParam("patientId")String patientId){
        String patientJSON = null;

        ServletContext sc = request.getSession().getServletContext();
        HttpHost target = new HttpHost(((String)sc.getAttribute(POPHEALTH_IP_ADDRESS)),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(), "http");

        String apiURL = POPHEALTH_API_GET_PATIENT + patientId;
        APIRequestHelper apiRequestHelper = new APIRequestHelper((String)sc.getAttribute(POPHEALTH_IP_ADDRESS),
                new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_UID),
                (String)sc.getAttribute(POPHEALTH_PATIENTUPLOAD_PWD));
        patientJSON = apiRequestHelper.executeRequest(target,apiURL);
        HashMap<String,Object> patientMap = JsonHashMapHelper.jsonToHashMap(patientJSON);
        return new Viewable("/patient.ftl", patientMap);
    }

    @GET
    @Path("{patientId}/measures/")
    @Produces("text/html")
    public Viewable getPatientMeasures(@PathParam("patientId") String patientId){
        ServletContext sc = request.getSession().getServletContext();
        HttpHost target = new HttpHost(((String)sc.getAttribute(POPHEALTH_IP_ADDRESS)),new Integer((String)sc.getAttribute(POPHEALTH_PORT)).intValue(), "http");
        String measureJSON = MeasureHelper.getMeasures(sc, target);
        ArrayList<HashMap<String,Object>> measureListMap = JsonHashMapHelper.jsonToListMap(measureJSON);
        HashMap<String,Object> model= new HashMap<String,Object>();
        model.put("patientId",patientId);
        model.put("measures",measureListMap);
        return new Viewable("/patientmeasures.ftl",model);
    }
}
