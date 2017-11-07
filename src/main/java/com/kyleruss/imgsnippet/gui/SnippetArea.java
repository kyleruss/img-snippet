//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Point;
import java.awt.Rectangle;

public class SnippetArea 
{
    private Point initialPoint;
    private Rectangle shapeArea;
    
    public SnippetArea()
    {
        this(new Point(0, 0));
    }
    
    public SnippetArea(Point initialPoint)
    {
        this.initialPoint   =   initialPoint;
        shapeArea           =   new Rectangle(initialPoint.x, initialPoint.y, 0, 0);
    }
    
    public Rectangle getShapeArea()
    {
        return shapeArea;
    }
    
    public void setShapeArea(Rectangle shapeArea)
    {
        this.shapeArea  =   shapeArea;
    }
    
    public Point getAreaCoords()
    {
        return new Point(shapeArea.x, shapeArea.y);
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
        return shapeArea.width;
    }

    public void setWidth(int width)
    {
        shapeArea.width    =   width;
    }

    public int getHeight() 
    {
        return shapeArea.height;
    }

    public void setHeight(int height)
    {
        shapeArea.height    =   height;
    }
}
