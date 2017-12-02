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
    public static final int SAVE_OPTION     =   0;
    public static final int CANCEL_OPTION   =   1;
    
    private final BufferedImage screenshot;
    
    public ScreenshotPreviewPanel(BufferedImage screenshot)
    {
        this.screenshot     =   screenshot;
        
        setPreferredSize(new Dimension(screenshot.getWidth(), screenshot.getHeight()));
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(screenshot, 0, 0, null);
    }
    
    public int showPreviewPanel()
    {
        String[] options    =   { "Save", "Cancel" };
        int option  =   JOptionPane.showOptionDialog(null, this, "Screenshot Preview", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);
        return option;
    }

    public BufferedImage getScreenshot()
    {
        return screenshot;
    }
}
