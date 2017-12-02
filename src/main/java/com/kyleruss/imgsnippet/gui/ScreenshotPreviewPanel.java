//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ScreenshotPreviewPanel extends JPanel
{
    private final BufferedImage screenshot;
    
    public ScreenshotPreviewPanel(BufferedImage screenshot)
    {
        this.screenshot     =   screenshot;
        
        setPreferredSize(new Dimension(screenshot.getWidth(), screenshot.getHeight()));
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(screenshot, 0, 0, null);
    }
    
    public int showPreviewPanel()
    {
        int option  =   JOptionPane.showConfirmDialog(null, this, "Screenshot Preview", JOptionPane.OK_CANCEL_OPTION);
        return option;
    }

    public BufferedImage getScreenshot()
    {
        return screenshot;
    }
}
