//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import java.util.Random;
import org.json.JSONObject;

public class ScreenshotManager 
{
    private static ScreenshotManager instance;
    
    private ScreenshotManager() {}
    
    public void saveScreenshotToStorage(BufferedImage screenshot, String fileName) throws IOException
    {
        final String EXTENSION  =   ".jpg";
        AppConfig appConfig     =   ConfigManager.getInstance().getAppConfig();
        fileName                =   fileName == null? generateFileName() : fileName;
        String path             =   appConfig.getImageDirectory() + fileName + EXTENSION;
        File file               =   new File(path);
        ImageIO.write(screenshot, "jpeg", file);
    }
    
    public void uploadScreenshot(BufferedImage screenshot)
    {
        APIManager apiManager   =   APIManager.getInstance();
        
        try
        {
            JSONObject response =   apiManager.uploadImage(screenshot).getJSONObject("data");
            System.out.println(response.get("link"));
        }
        
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void handleScreenshot(BufferedImage screenshot) throws IOException
    {
        AppConfig appConfig     =   ConfigManager.getInstance().getAppConfig();
        
        if(appConfig.isStoreLocally())
            saveScreenshotToStorage(screenshot, null);
        
        if(appConfig.isUploadOnline())
            uploadScreenshot(screenshot);
    }
    
    public BufferedImage createScreenshotArea(Rectangle area) throws AWTException
    {
        return new Robot().createScreenCapture(area);
    }
    
    public BufferedImage createMonitorScreenshot() throws AWTException
    {
        Rectangle area      =       new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        return createScreenshotArea(area);
    }
    
    public String generateFileName()
    {
        String fileName     =   "";
        String dir          =   ConfigManager.getInstance().getAppConfig().getImageDirectory();
        Random rGen         =   new Random();
        
        do
        {
            for(int i = 0; i < 5; i++)
                fileName += (char) (rGen.nextInt(26) + 'a') ;
        }
        
        while(new File(dir + fileName).exists());
        
        return fileName;
    }
    
    public String encodeImage(BufferedImage image) throws IOException
    {
        ByteArrayOutputStream byteStream    =   new ByteArrayOutputStream();
        ImageIO.write(image, "jpeg", byteStream);
        
        byte[] imageBytes   =   byteStream.toByteArray();
        return Base64.encode(imageBytes);
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
