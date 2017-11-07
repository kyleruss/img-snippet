//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class SnippetPanel extends JPanel
{
    private SnippetMouseListener mouseListener;
    private boolean isDrawingSnippet;
    private SnippetArea snippetArea;
    
    public SnippetPanel()
    {
        setPreferredSize(new Dimension(700, 700));
        setBackground(Color.WHITE);
    
        mouseListener           =   new SnippetMouseListener();
        isDrawingSnippet        =   false;
        snippetArea             =   null;
        
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    public void startDrawingSnippet(Point initialPoint)
    {
        snippetArea                 =   new SnippetArea(initialPoint);
        isDrawingSnippet            =   true;
    }
    
    public void stopDrawingSnippet()
    {
        isDrawingSnippet    =   false;
        snippetArea         =   null;
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
    
    private class SnippetMouseListener extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent e)
        {
            System.out.println("Mouse clicked");
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            System.out.println("Mouse pressed");
            startDrawingSnippet(e.getPoint());
        }
        
        @Override
        public void mouseReleased(MouseEvent e)
        {
            System.out.println("Mouse released");
            stopDrawingSnippet();
        }
        
        @Override
        public void mouseDragged(MouseEvent e)
        {
            System.out.println("Dragging");
            updateArea(e.getPoint());
        }
    }
}
