//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.kyleruss.imgsnippet.gui.SnippetTray;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.TrayIcon;
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
    
    public BufferedImage createScreenshotArea(Rectangle area) throws AWTException
    {
        return new Robot().createScreenCapture(area);
    }
    
    public BufferedImage createScreenshot() throws AWTException
    {
        Rectangle area      =       new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return createScreenshotArea(area);
    }
    
    public void screenshotNotify(String msg)
    {
        SnippetTray snippetTray     =   AppManager.getInstance().getDisplayWindow().getSnippetTray();
        TrayIcon trayIcon           =   snippetTray.getTrayIcon();
        
        trayIcon.displayMessage("ImgSnippet", msg, TrayIcon.MessageType.INFO);
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
