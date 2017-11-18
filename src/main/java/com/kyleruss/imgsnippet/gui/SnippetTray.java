//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppConfig;
import com.kyleruss.imgsnippet.app.AppManager;
import com.kyleruss.imgsnippet.app.ScreenshotManager;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SnippetTray implements ActionListener
{
    public static final String TRAY_IMG_PATH    =   AppConfig.IMG_RES_DIR + "trayIcon.png";
    
    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu trayMenu;
    private MenuItem exitItem, settingsItem, browseItem, screenshotItem, drawCaptureItem;
    
    public SnippetTray()
    {
        initTray();
        initMenuItems();
    }
    
    public void initTray()
    {
        if(SystemTray.isSupported())
        {
            try
            {
                tray                =   SystemTray.getSystemTray();
                trayMenu            =   new PopupMenu();   
                BufferedImage image =   ImageIO.read(new File(TRAY_IMG_PATH));
                trayIcon            =   new TrayIcon(image, "Img-Snippet", trayMenu);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(this);
                tray.add(trayIcon);
            }
            
            catch(IOException | AWTException e)
            {
                JOptionPane.showMessageDialog(null, "Failed to initialize application tray");
                e.printStackTrace();
            }
        }
    }
    
    private void initMenuItems()
    {
        if(trayMenu != null)
        {
            exitItem        =   new MenuItem("Exit");
            browseItem      =   new MenuItem("Browse");
            settingsItem    =   new MenuItem("Settings");
            screenshotItem  =   new MenuItem("Screenshot");
            drawCaptureItem =   new MenuItem("Snippet");

            trayMenu.add(drawCaptureItem);            
            trayMenu.add(screenshotItem);
            trayMenu.add(browseItem);
            trayMenu.add(settingsItem);
            trayMenu.addSeparator();
            trayMenu.add(exitItem);
            
            exitItem.addActionListener(this);
            browseItem.addActionListener(this);
            settingsItem.addActionListener(this);
            drawCaptureItem.addActionListener(this);
            screenshotItem.addActionListener(this);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
        
        if(src == exitItem)
            System.exit(0);
        
        else if(src == drawCaptureItem)
            AppManager.getInstance().getDisplay().showFrame();
        
        else if(src == screenshotItem)
            AppManager.getInstance().getDisplay().getSnippetPanel().saveMonitorScreenshot();
        
        else if(src == browseItem)
            ScreenshotManager.getInstance().browseScreenshotDirectory();
    }

    public SystemTray getTray() 
    {
        return tray;
    }

    public TrayIcon getTrayIcon() 
    {
        return trayIcon;
    }

    public PopupMenu getTrayMenu() 
    {
        return trayMenu;
    }
    
    
}
