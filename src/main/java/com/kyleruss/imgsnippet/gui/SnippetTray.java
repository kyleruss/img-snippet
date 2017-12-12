//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import com.kyleruss.imgsnippet.app.AppConfig;
import com.kyleruss.imgsnippet.app.ScreenshotManager;
import java.awt.AWTException;
import java.awt.Desktop;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class SnippetTray implements ActionListener
{
    private TrayIcon trayIcon;
    private PopupMenu trayMenu, aboutMenu;
    private MenuItem exitItem, settingsItem, browseItem, screenshotItem;
    private MenuItem drawCaptureItem, authorItem, licenseItem, repositoryItem;
    
    public SnippetTray() {}
    
    public void init()
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
                trayMenu            =   new PopupMenu();   
                BufferedImage image =   ImageIO.read(new File("data/images/trayIcon.png"));
                trayIcon            =   new TrayIcon(image, "Img-Snippet", trayMenu);
                trayIcon.setImageAutoSize(true);
                trayIcon.addActionListener(this);
                SystemTray.getSystemTray().add(trayIcon);
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
            aboutMenu       =   new PopupMenu("About");
            exitItem        =   new MenuItem("Exit");
            browseItem      =   new MenuItem("Browse");
            settingsItem    =   new MenuItem("Settings");
            screenshotItem  =   new MenuItem("Screenshot");
            drawCaptureItem =   new MenuItem("Snippet");
            authorItem      =   new MenuItem("Author");
            licenseItem     =   new MenuItem("License");
            repositoryItem  =   new MenuItem("Repository");
            
            aboutMenu.add(authorItem);
            aboutMenu.add(licenseItem);
            aboutMenu.add(repositoryItem);
            
            trayMenu.add(aboutMenu);
            trayMenu.addSeparator();
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
            authorItem.addActionListener(this);
            licenseItem.addActionListener(this);
            repositoryItem.addActionListener(this);
        }
    }
    
    public void showAuthorDialog()
    {
        String authorText   =   "Img-Snippet 2017\nCreated by Kyle Russell\ngithub.com/kyleruss\n";
        JOptionPane.showMessageDialog(null, authorText);
    }
    
    public void showRepository()
    {
        try
        {
            String repositoryURL   =   "https://github.com/kyleruss/img-snippet";
            Desktop.getDesktop().browse(new URL(repositoryURL).toURI());
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Could not open the repository");
        }
    }
    
    public void showLicense()
    {
        try
        {
            Desktop.getDesktop().edit(new File("LICENSE.txt"));
        }
        
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "Failed to open license");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
        
        if(src == exitItem)
            System.exit(0);
        
        else if(src == drawCaptureItem)
            AppManager.getInstance().showFrame();
        
        else if(src == screenshotItem)
            AppManager.getInstance().getSnippetPanel().saveMonitorScreenshot();
        
        else if(src == browseItem)
            ScreenshotManager.getInstance().browseScreenshotDirectory();
     
        else if(src == settingsItem)
            SettingsPanel.getInstance().showSettingsPanel();
        
        else if(src == licenseItem)
            showLicense();
        
        else if(src == authorItem)
            showAuthorDialog();
        
        else if(src == repositoryItem)
            showRepository();
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
