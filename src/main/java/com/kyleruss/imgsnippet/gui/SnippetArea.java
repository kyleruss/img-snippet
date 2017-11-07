//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Point;

public class SnippetArea 
{
    private Point initialPoint;
    private int width;
    private int height;
    
    public SnippetArea()
    {
        this(new Point(0, 0));
    }
    
    public SnippetArea(Point initialPoint)
    {
        this.initialPoint   =   initialPoint;
        this.width          =   0;
        this.height         =   0;
    }

    public Point getInitialPoint()
    {
        return initialPoint;
    }

    public void setInitialPoint(Point initialPoint) 
    {
        this.initialPoint = initialPoint;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight() 
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }
}
