//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

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
    
    public void browseScreenshotDirectory()
    {
        String dirPath      =   ConfigManager.getInstance().getAppConfig().getImageDirectory();
        dirPath             =   dirPath.equals(AppConfig.DEFAULT_DIR)? System.getProperty("user.dir") + "\\" + dirPath: dirPath;
        File dir            =   new File(dirPath);
        Desktop desktop     =   Desktop.getDesktop();
        
        try
        {
            desktop.open(dir);
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not locate screenshot directory");
        }
    }
    
    public static ScreenshotManager getInstance()
    {
        if(instance == null) instance   =   new ScreenshotManager();
        return instance;
    }
}
