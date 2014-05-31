package com.dheeti.beat.wrapper;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.AuthCache;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.entity.FileEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicAuthCache;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.*;

/**
 * Created by jayramj on 4/5/14.
 */
public class UploadQRDA1 {
    public static void main(String args[]) {
        UploadQRDA1 inst = new UploadQRDA1();
        HttpHost target = new HttpHost("localhost", 3000, "http");
        String response = inst.executeMultiPartRequest(target,"sample-qrda1-patient1.xml");
        System.out.println("Response : "+response);
    }


    public String executeMultiPartRequest(HttpHost target, String fileName) {

        HttpPost postRequest = new HttpPost (target.toURI()+"/records") ;
        File f = new File(fileName);
        FileEntity fileEntity = new FileEntity(f) ;
        fileEntity.setContentType("text/xml");

        //FileBody fileBody = new FileBody(f, "text/xml") ;
        //multiPartEntity.addPart("attachment", fileBody) ;

        postRequest.setEntity(fileEntity) ;

        return executeRequest (target,postRequest) ;
    }

    private static String executeRequest(HttpHost target,HttpPost requestBase){

        String responseString = "" ;

        InputStream responseStream = null ;

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(target.getHostName(), target.getPort()),
                new UsernamePasswordCredentials("jjdeepali", "pophealth"));

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
            HttpResponse response = client.execute(target,requestBase,localContext) ;
            if (response != null){
                HttpEntity responseEntity = response.getEntity() ;

                if (responseEntity != null){
                    responseStream = responseEntity.getContent() ;
                    if (responseStream != null){
                        BufferedReader br = new BufferedReader (new InputStreamReader (responseStream)) ;
                        String responseLine = br.readLine() ;
                        String tempResponseString = "" ;
                        while (responseLine != null){
                            tempResponseString = tempResponseString + responseLine + System.getProperty("line.separator") ;
                            responseLine = br.readLine() ;
                        }
                        br.close() ;
                        if (tempResponseString.length() > 0){
                            responseString = tempResponseString ;
                        }
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

        return responseString ;
    }
}