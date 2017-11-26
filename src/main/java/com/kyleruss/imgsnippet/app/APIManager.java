//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

public class APIManager 
{
    public static final String IMG_UPLOAD_ENDP  =   "https://api.imgur.com/3/image";
    public static final String IMG_GET_ENDP     =   "https://api.imgur.com/3/image/";
    public static final String CLIENT_ID        =   "5ee1ae88d9e1c42";
    
    private static APIManager instance;
    
    private APIManager() {}
    
    public void uploadImage(BufferedImage image) throws IOException
    {
        String encodedImage         =   ScreenshotManager.getInstance().encodeImage(image);
        HttpClient client           =   HttpClientBuilder.create().build();
        HttpPost postRequest        =   new HttpPost(IMG_UPLOAD_ENDP);
        
        List<NameValuePair> params  =   new ArrayList<>();
        params.add(new BasicNameValuePair("image", encodedImage));
        postRequest.setEntity(new UrlEncodedFormEntity(params));
        
        String apiKey               =   "Client-ID " + CLIENT_ID;
        postRequest.addHeader("Authorization", apiKey);
        
        HttpResponse response       =   client.execute(postRequest);
        String responseContent      =   getResponseString(response.getEntity().getContent());
        
        System.out.println(responseContent);
    }
    
    public String getResponseString(InputStream stream) throws IOException
    {
        String line             =   "";
        String response         =   "";
        BufferedReader reader   =   new BufferedReader(new InputStreamReader(stream));
        
        while((line = reader.readLine()) != null)
            response += line;
        
        return response;
    }
        
    public static APIManager getInstance()
    {
        if(instance == null) instance   =   new APIManager();
        return instance;
    }
}
