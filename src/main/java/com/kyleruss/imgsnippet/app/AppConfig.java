//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class AppConfig
{
    private String imageDirectory;
    private boolean storeLocally;
    private boolean uploadOnline;
    private String clientID;
    private boolean enablePreview;
    
    public AppConfig()
    {
        this("captures/", true, true, true);
    }
    
    public AppConfig(String imageDirectory, boolean storeLocally, boolean uploadOnline , boolean enablePreview)
    {
        this.imageDirectory     =   imageDirectory;
        this.storeLocally       =   storeLocally;
        this.uploadOnline       =   uploadOnline;
        this.enablePreview      =   enablePreview;
    }

    public boolean isUploadOnline() 
    {
        return uploadOnline;
    }

    @XmlElement
    public void setUploadOnline(boolean uploadOnline)
    {
        this.uploadOnline = uploadOnline;
    }
    
    public String getImageDirectory() 
    {
        return imageDirectory;
    }

    @XmlElement
    public void setImageDirectory(String imageDirectory)
    {
        this.imageDirectory = imageDirectory;
    }

    public boolean isStoreLocally()
    {
        return storeLocally;
    }

    @XmlElement
    public void setStoreLocally(boolean storeLocally) 
    {
        this.storeLocally = storeLocally;
    }

    public String getClientID() 
    {
        return clientID;
    }

    @XmlElement
    public void setClientID(String clientID) 
    {
        this.clientID = clientID;
    }

    public boolean isEnablePreview() 
    {
        return enablePreview;
    }

    @XmlElement
    public void setEnablePreview(boolean enablePreview) 
    {
        this.enablePreview = enablePreview;
    }
}
