//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

public class AppConfig
{
    private String imageDirectory;
    private boolean storeLocally;
    private String screenshotBind;
    private boolean uploadOnline;
    private String drawBind;
    
    public AppConfig()
    {
        
    }

    public boolean isUploadOnline() 
    {
        return uploadOnline;
    }

    public void setUploadOnline(boolean uploadOnline)
    {
        this.uploadOnline = uploadOnline;
    }
    
    public String getImageDirectory() 
    {
        return imageDirectory;
    }

    public void setImageDirectory(String imageDirectory)
    {
        this.imageDirectory = imageDirectory;
    }

    public boolean isStoreLocally()
    {
        return storeLocally;
    }

    public void setStoreLocally(boolean storeLocally) 
    {
        this.storeLocally = storeLocally;
    }

    public String getScreenshotBind()
    {
        return screenshotBind;
    }

    public void setScreenshotBind(String screenshotBind) 
    {
        this.screenshotBind = screenshotBind;
    }

    public String getDrawBind()
    {
        return drawBind;
    }

    public void setDrawBind(String drawBind) 
    {
        this.drawBind = drawBind;
    }
}
