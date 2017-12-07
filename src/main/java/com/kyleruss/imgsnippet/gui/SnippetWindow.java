//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class SnippetWindow extends JFrame
{
    private SnippetPanel snippetPanel;
    private SnippetTray snippetTray;
    private SnippetKeyHook keyHook;
    
    public SnippetWindow()
    {
        super("imgSnippet");
        
        initFrame();
        initLookAndFeel();
        snippetTray     =   new SnippetTray();
    }
    
    private void initFrame()
    {
        snippetPanel   =   new SnippetPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true);
        setBackground(new Color(0, 255, 0, 1));
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
        keyHook     =    new SnippetKeyHook();
        keyHook.registerHook();
    }
    
    public SnippetKeyHook getKeyHook()
    {
        return keyHook;
    }
    
    public SnippetPanel getSnippetPanel()
    {
        return snippetPanel;
    }
    
    public SnippetTray getSnippetTray()
    {
        return snippetTray;
    }
    
    public void showFrame()
    {
        setVisible(true);
    }
    
    public void hideFrame()
    {
        setVisible(false);
    }

}
