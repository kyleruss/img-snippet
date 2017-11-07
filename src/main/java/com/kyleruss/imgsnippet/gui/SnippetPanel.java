//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class SnippetPanel extends JPanel
{
    private SnippetMouseListener mouseListener;
    private boolean isDrawingSnippet;
    private Point initialSnippetArea;
    
    public SnippetPanel()
    {
        setPreferredSize(new Dimension(700, 700));
        setBackground(Color.WHITE);
    
        mouseListener           =   new SnippetMouseListener();
        isDrawingSnippet        =   false;
        initialSnippetArea      =   null;
        
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
    }
    
    public void startDrawingSnippet(Point initialSnippetArea)
    {
        this.initialSnippetArea     =   initialSnippetArea;
        isDrawingSnippet            =   true;
    }
    
    public void stopDrawingSnippet()
    {
        isDrawingSnippet        =   false;
        initialSnippetArea      =   null;
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
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
        }
    }
}
