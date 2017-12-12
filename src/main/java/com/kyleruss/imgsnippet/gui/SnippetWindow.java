//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SnippetWindow extends JFrame
{
    private SnippetPanel snippetPanel;
    private SnippetKeyHook keyHook;
    
    private static SnippetWindow instance;
    
    public SnippetWindow()
    {
        super("imgSnippet");
        
        initFrame();
        initLookAndFeel();
        new SnippetTray().init();
    }
    
    private void initFrame()
    {
        snippetPanel   =   new SnippetPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 1));
        setIconImage(new ImageIcon("data/images/trayIcon.png").getImage());
        getContentPane().add(snippetPanel);
        
        pack();
        initKeyHook();
    }
    
    private void initLookAndFeel()
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    private void initKeyHook()
    {
        new SnippetKeyHook().registerHook();
    }
    
    public SnippetKeyHook getKeyHook()
    {
        return keyHook;
    }
    
    public SnippetPanel getSnippetPanel()
    {
        return snippetPanel;
    }
    
    public void showFrame()
    {
        setVisible(true);
    }
    
    public void hideFrame()
    {
        setVisible(false);
    }
    
    public static void createInstance()
    {
        instance    =   new SnippetWindow();
    }
    
    public static SnippetWindow getInstance()
    {
        return instance;
    }

    public static void main(String[] args)
    {
        SnippetWindow.createInstance();
    }
}
