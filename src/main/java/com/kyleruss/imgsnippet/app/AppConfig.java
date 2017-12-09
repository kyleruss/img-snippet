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
    public static final String APP_CONFIG_PATH      =   "data/config.xml";
    public static final String KEY_CONFIG_PATH      =   "data/keybinds.conf";
    public static final String SOUNDS_DIR           =   "data/sounds/";
    public static final String IMG_RES_DIR          =   "data/images/";
    public static final String DEFAULT_DIR          =   "data/captures/";
    
    private String imageDirectory;
    private boolean storeLocally;
    private boolean uploadOnline;
    private String clientID;
    private boolean enablePreview;
    private boolean runOnStartup;
    
    public AppConfig()
    {
        this(DEFAULT_DIR, true, true, true, true);
    }
    
    public AppConfig(String imageDirectory, boolean storeLocally, boolean uploadOnline , boolean enablePreview, boolean runOnStartup)
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

    public boolean isRunOnStartup() 
    {
        return runOnStartup;
    }

    @XmlElement
    public void setRunOnStartup(boolean runOnStartup)
    {
        this.runOnStartup = runOnStartup;
    }
}
