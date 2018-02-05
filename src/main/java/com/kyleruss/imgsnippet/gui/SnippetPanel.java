//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import com.kyleruss.imgsnippet.app.AppConfig;
import com.kyleruss.imgsnippet.app.ConfigManager;
import com.kyleruss.imgsnippet.app.ScreenshotManager;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javazoom.jl.player.Player;

public class SnippetPanel extends JPanel implements MouseListener, MouseMotionListener
{
    private boolean isDrawingSnippet;
    private SnippetArea snippetArea;
    
    public SnippetPanel()
    {
        initDimensions();
        setOpaque(false);
    
        isDrawingSnippet        =   false;
        snippetArea             =   null;
        
        setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        setFocusable(true);
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    private void initDimensions()
    {
        GraphicsDevice gd   =   GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        int width           =   gd.getDisplayMode().getWidth();
        int height          =   gd.getDisplayMode().getHeight();
        
        setPreferredSize(new Dimension(width, height));
    }
    
    public void startDrawingSnippet(Point initialPoint)
    {
        snippetArea                 =   new SnippetArea(initialPoint);
        isDrawingSnippet            =   true;
    }
    
    public void stopDrawingSnippet()
    {
        isDrawingSnippet    =   false;
    }
    
    public void playScreenshotSound(boolean successSound)
    {
        try
        {
            String soundFile                =   successSound? "success-sound.mp3" : "snippet-sound.mp3";
            String soundFilePath            =   "data/sounds/" + soundFile;
            FileInputStream audioFileStream =   new FileInputStream(new File(soundFilePath));
            Player audioPlayer              =   new Player(audioFileStream);
            
            audioPlayer.play();
            audioFileStream.close();
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to load resource");
            e.printStackTrace();
        }
    }
    
    public void updateArea(Point currentPoint)
    {
        if(snippetArea != null)
        {
            int x       =   Math.min(currentPoint.x, snippetArea.getInitialPoint().x);
            int y       =   Math.min(currentPoint.y, snippetArea.getInitialPoint().y);
            int maxX    =   Math.max(currentPoint.x, snippetArea.getInitialPoint().x);
            int maxY    =   Math.max(currentPoint.y, snippetArea.getInitialPoint().y);
            int width   =   maxX    -   x;
            int height  =   maxY    -   y;
                    
            snippetArea.setShapeArea(new Rectangle(x, y, width, height));
            repaint();
        }
    }
    
    //Trims snippet area to remove the included snippet drawn border
    public void trimArea(Rectangle rect)
    {
        rect.x++;
        rect.y++;
        
        rect.width--;
        rect.height--;
    }
    
    public void saveScreenshot(BufferedImage screenshot, String filename)
    {
        try
        {
            ScreenshotManager screenshotManager     =   ScreenshotManager.getInstance();
            screenshotManager.handleScreenshot(screenshot, filename);
            playScreenshotSound(true);
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to save screenshot", "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void saveDrawnScreenshot()
    {
        if(snippetArea.isEmptySpace()) JOptionPane.showMessageDialog(null, "Please select an area to screenshot");
        else
        {
            try
            {
                ScreenshotManager screenshotManager     =   ScreenshotManager.getInstance();
                
                trimArea(snippetArea.getShapeArea());
                BufferedImage screenshot                =   screenshotManager.createScreenshotArea(snippetArea.getShapeArea());
                
                AppManager.getInstance().hideFrame();
                playScreenshotSound(false);
                new ScreenshotPreviewPanel(screenshot).showPreviewPanel();
                
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Failed to draw snippet", "Error", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            }
               /* boolean isSaveScreenshot                =   true;
                
                if(config.isEnablePreview())
                {
                    int option          =   new ScreenshotPreviewPanel(screenshot).showPreviewPanel();
                    isSaveScreenshot    =   option == ScreenshotPreviewPanel.SAVE_OPTION;
                }
                
                
                if(isSaveScreenshot)
                {
                    screenshotManager.handleScreenshot(screenshot);
                    playScreenshotSound(true);
                }
            }
            
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null, "Failed to save screenshot", "Error", JOptionPane.WARNING_MESSAGE);
                e.printStackTrace();
            } */
        }
    }
    
    public void saveMonitorScreenshot()
    {
        try
        {
             ScreenshotManager screenshotManager        =   ScreenshotManager.getInstance();
             BufferedImage screenshot                   =   screenshotManager.createMonitorScreenshot();
             playScreenshotSound(false);
             screenshotManager.handleScreenshot(screenshot, null);
             playScreenshotSound(true);
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to save screenshot", "Error", JOptionPane.WARNING_MESSAGE);
            e.printStackTrace();
        }
    }
    
    public void stopDrawing()
    {
        isDrawingSnippet    =    false;
        snippetArea         =   null;
        AppManager.getInstance().hideFrame();
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Graphics2D g2d      =   (Graphics2D) g.create();
        Color borderColor   =   ConfigManager.getInstance().getAppConfig().getBorderColorObj();
        g2d.setColor(borderColor);
        
        if(isDrawingSnippet)
            g2d.draw(snippetArea.getShapeArea());
        
        else if(snippetArea == null)
            g2d.draw(new Rectangle());
            
        g2d.dispose();
    }
    
    @Override
    public void mousePressed(MouseEvent e)
    {
        startDrawingSnippet(e.getPoint());
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        stopDrawingSnippet();
        saveDrawnScreenshot();
    }
    
    @Override
    public void mouseDragged(MouseEvent e)
    {
        updateArea(e.getPoint());
    }
        
    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}
    
    @Override
    public void mouseMoved(MouseEvent e) {}
}
