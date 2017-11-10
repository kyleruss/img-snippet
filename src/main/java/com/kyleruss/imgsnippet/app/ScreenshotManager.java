//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ScreenshotManager 
{
    private static ScreenshotManager instance;
    
    private ScreenshotManager() {}
    
    public void saveScreenshotToStorage(BufferedImage screenshotImage) throws IOException
    {
        AppConfig appConfig     =   ConfigManager.getInstance().getAppConfig();
        String path             =   appConfig.getImageDirectory();
        File file               =   new File(path);
        ImageIO.write(screenshotImage, "jpeg", file);
    }
    
    public static ScreenshotManager getInstance()
    {
        if(instance == null) instance   =   new ScreenshotManager();
        return instance;
    }
}
