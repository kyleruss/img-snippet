//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.kyleruss.imgsnippet.gui.SnippetKeyHook;
import com.kyleruss.imgsnippet.gui.SnippetPanel;
import com.kyleruss.imgsnippet.gui.SnippetTray;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class AppManager extends JFrame
{
    private SnippetPanel snippetPanel;
    private SnippetKeyHook keyHook;
    
    private static AppManager instance;
    
    public AppManager()
    {
        super("imgSnippet");
        
        initFrame();
        initKeyHook();
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
        instance    =   new AppManager();
    }
    
    public static AppManager getInstance()
    {
        return instance;
    }

    public static void main(String[] args)
    {
        AppManager.createInstance();
    }
}
