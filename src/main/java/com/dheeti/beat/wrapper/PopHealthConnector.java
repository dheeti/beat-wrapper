package com.dheeti.beat.wrapper;

import com.dheeti.beat.wrapper.helper.PatientMeasureHelper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * Created by jayramj on 31/5/14.
 */
public class PopHealthConnector {
    private static HttpHost target = new HttpHost("localhost", 3000, "http");

    public String getPatientMeasure(String patientId,String measureId,String userName,String password){
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String,Object>> userData = null;
        Map<String,Object> patientMeasure = null;
        HttpGet getRequest = new HttpGet (target.toURI()+"/api/patients/"+patientId+"/results") ;
        System.out.print("**********"+getRequest);

        String responseString = "" ;

        InputStream responseStream = null ;

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials(userName, password));

        CloseableHttpClient client = HttpClients.custom()
                .setDefaultCredentialsProvider(credsProvider)
                .build();

        // Create AuthCache instance
        AuthCache authCache = new BasicAuthCache();
        // Generate BASIC scheme object and add it to the local
        // auth cache
        BasicScheme basicAuth = new BasicScheme();
        authCache.put(target, basicAuth);

        // Add AuthCache to the execution context
        HttpClientContext localContext = HttpClientContext.create();
        localContext.setAuthCache(authCache);


        //HttpClient client = new DefaultHttpClient() ;
        try{
            HttpResponse response = client.execute(target,getRequest,localContext) ;
            if (response != null){
                HttpEntity responseEntity = response.getEntity() ;

                if (responseEntity != null){
                    responseStream = responseEntity.getContent() ;
                    if (responseStream != null){
                        BufferedReader br = new BufferedReader (new InputStreamReader(responseStream)) ;

                        userData = mapper.readValue(br, List.class);
                        patientMeasure = PatientMeasureHelper.getPatientMeasure(userData,measureId);
                        responseString = mapper.writeValueAsString(patientMeasure);
                    }
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if (responseStream != null){
                try {
                    responseStream.close() ;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        client.getConnectionManager().shutdown() ;

        return responseString;


    }
}
