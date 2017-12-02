//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

public class ScreenshotPreviewPanel
{
    private static ScreenshotPreviewPanel instance;
    
    private ScreenshotPreviewPanel()
    {
        
    }
    
    public static ScreenshotPreviewPanel getInstance()
    {
        if(instance == null) instance   =   new ScreenshotPreviewPanel();
        return instance;
    }
}
