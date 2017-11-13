//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.kyleruss.imgsnippet.gui.SnippetWindow;

public class AppManager
{
    private static AppManager instance;
    private SnippetWindow display;
    
    private AppManager() 
    {
        display   =   new SnippetWindow();
    }
    
    public SnippetWindow getDisplay()
    {
        return display;
    }
    
    public static AppManager createInstance()
    {
        instance    =   new AppManager();
        return instance;
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
