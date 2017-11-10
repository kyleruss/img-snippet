//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Color;
import javax.swing.JFrame;

public class SnippetWindow extends JFrame
{
    private SnippetPanel panel;
    private SnippetTray snippetTray;
    
    public SnippetWindow()
    {
        super("imgSnippet");
        
        initFrame();
        snippetTray     =   new SnippetTray();
    }
    
    private void initFrame()
    {
        panel   =   new SnippetPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 1));
        getContentPane().add(panel);
        pack();
    }
    
    public SnippetTray getSnippetTray()
    {
        return snippetTray;
    }
    
    public void showFrame()
    {
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        SnippetWindow snippetWindow     =   new SnippetWindow();
      //  snippetWindow.showFrame();
    }
}
