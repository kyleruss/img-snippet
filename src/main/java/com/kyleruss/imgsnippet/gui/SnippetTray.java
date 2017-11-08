//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnippetTray implements ActionListener
{
    public static final String TRAY_IMG_PATH    =   "data/images/trayicon.png";
    
    private SystemTray tray;
    private TrayIcon trayIcon;
    private PopupMenu trayPopup;
    
    public SnippetTray()
    {
        initTray();
    }
    
    public void initTray()
    {
        if(SystemTray.isSupported())
        {
            tray        =   SystemTray.getSystemTray();
            trayPopup   =   new PopupMenu();   
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
        
        if(src == trayIcon)
            System.out.println("Open snippet window");
    }
}
