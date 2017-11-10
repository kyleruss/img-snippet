//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SnippetPanel extends JPanel implements ActionListener
{
    private SnippetMouseListener mouseListener;
    private SnippetKeyListener keyListener;
    private boolean isDrawingSnippet;
    private SnippetArea snippetArea;
    private JButton screenshotBtn;
    
    public SnippetPanel()
    {
        initDimensions();
        setOpaque(false);
    
        mouseListener           =   new SnippetMouseListener();
        keyListener             =   new SnippetKeyListener();
        isDrawingSnippet        =   false;
        snippetArea             =   null;
        screenshotBtn           =   new JButton("Screenshot");
        
        setFocusable(true);
        requestFocus();
        add(screenshotBtn);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        addKeyListener(keyListener);
        screenshotBtn.addActionListener(this);
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
    
    public void saveScreenshot()
    {
        if(snippetArea.isEmptySpace()) JOptionPane.showMessageDialog(null, "Please select an area to screenshot");
        else
        {
            JFileChooser fileChooser    =   new JFileChooser();
            int fileSaveOption          =   fileChooser.showSaveDialog(null);

            if(fileSaveOption == JFileChooser.APPROVE_OPTION)
            {
                try
                {
                    File file                   =   fileChooser.getSelectedFile();
                    BufferedImage imageCapture  =   new Robot().createScreenCapture(snippetArea.getShapeArea());
                    ImageIO.write(imageCapture, "jpeg", file);
                }
                
                catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, "Failed to save screenshot");
                    e.printStackTrace();
                }
            }
        }
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d  =   (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        
        if(isDrawingSnippet)
            g2d.draw(snippetArea.getShapeArea());
            
        g2d.dispose();
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src  =   e.getSource();
        
        if(src == screenshotBtn)
            saveScreenshot();
    }

    private class SnippetMouseListener extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            startDrawingSnippet(e.getPoint());
        }
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            stopDrawingSnippet();
        }
        
        @Override
        public void mouseDragged(MouseEvent e)
        {
            updateArea(e.getPoint());
        }
    }
    
    private class SnippetKeyListener implements KeyListener
    {
        @Override
        public void keyPressed(KeyEvent e) 
        {
            int keyCode =   e.getKeyCode();

            if(keyCode == KeyEvent.VK_ESCAPE)
                AppManager.getInstance().getDisplayWindow().hideFrame();
        }

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}
