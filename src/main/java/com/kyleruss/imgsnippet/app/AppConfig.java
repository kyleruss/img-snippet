//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import java.awt.Color;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement
public class AppConfig
{
    private String imageDirectory;
    private boolean storeLocally;
    private boolean uploadOnline;
    private String clientID;
    private boolean enablePreview;
    private Color borderColorObj;
    private String borderColor;
    
    public AppConfig()
    {
        this("captures/", true, true, true, "#000000");
    }
    
    public AppConfig(String imageDirectory, boolean storeLocally, boolean uploadOnline , boolean enablePreview, String borderColor)
    {
        this.imageDirectory     =   imageDirectory;
        this.storeLocally       =   storeLocally;
        this.uploadOnline       =   uploadOnline;
        this.enablePreview      =   enablePreview;
        this.borderColor        =   borderColor;
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

    public Color getBorderColorObj() 
    {
        return borderColorObj;
    }

    @XmlTransient
    public void setBorderColorObj(Color borderColorObj) 
    {
        this.borderColorObj     =    borderColorObj;
        this.borderColor        =    String.format("#%06x", borderColorObj.getRGB() & 0x00FFFFFF);
    }

    public String getBorderColor()
    {
        return borderColor;
    }

    @XmlElement
    public void setBorderColor(String borderColor) 
    {
        this.borderColor     =   borderColor;
        borderColorObj       =   Color.decode(borderColor);
    }
}
