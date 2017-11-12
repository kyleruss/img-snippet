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
    public static final String CONFIG_PATH          =   "data/config.xml";
    public static final String DEFAULT_DIR          =   "data/captures/";
    public static final String DEFAULT_SS_BIND      =   "SHIFT+S";
    public static final String DEFAULT_DRAW_BIND    =   "SHIFT+D";   
    
    private String imageDirectory;
    private boolean storeLocally;
    private String screenshotBind;
    private boolean uploadOnline;
    private String drawBind;
    
    public AppConfig()
    {
        this(DEFAULT_DIR, true, true, DEFAULT_SS_BIND, DEFAULT_DRAW_BIND);
    }
    
    public AppConfig(String imageDirectory, boolean storeLocally, boolean uploadOnline,
    String screenshotBind, String drawBind)
    {
        this.imageDirectory     =   imageDirectory;
        this.storeLocally       =   storeLocally;
        this.screenshotBind     =   screenshotBind;
        this.uploadOnline       =   uploadOnline;
        this.drawBind           =   drawBind;
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

    public String getScreenshotBind()
    {
        return screenshotBind;
    }

    @XmlElement
    public void setScreenshotBind(String screenshotBind) 
    {
        this.screenshotBind = screenshotBind;
    }

    public String getDrawBind()
    {
        return drawBind;
    }

    @XmlElement
    public void setDrawBind(String drawBind) 
    {
        this.drawBind = drawBind;
    }
}
