//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.ConfigManager;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;

public class SnippetWindow extends JFrame
{
    private SnippetPanel panel;
    
    public SnippetWindow()
    {
        super("imgSnippet");
        panel   =   new SnippetPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 1));
        getContentPane().add(panel);
        pack();
    }
    
    public void showFrame()
    {
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        SnippetWindow snippetWindow     =   new SnippetWindow();
        snippetWindow.showFrame();
    }
}
