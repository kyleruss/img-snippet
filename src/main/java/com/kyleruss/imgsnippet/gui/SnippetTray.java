//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

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
    public static final String TRAY_IMG_PATH    =   "data/images/trayIcon.png";
    
    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu trayMenu;
    private MenuItem exitItem, settingsItem, browseItem;
    
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
            
            trayMenu.add(browseItem);
            trayMenu.add(settingsItem);
            trayMenu.add(exitItem);
            
            exitItem.addActionListener(this);
            browseItem.addActionListener(this);
            settingsItem.addActionListener(this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
        
        if(src == trayIcon)
            System.out.println("Open snippet window");
        
        else if(src == exitItem)
            System.exit(0);
    }
}
