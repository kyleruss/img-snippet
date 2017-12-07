//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.io.Serializable;

public class KeybindBean implements Serializable
{
    private SeriableKeyEvent snippetKeyEvent;
    private SeriableKeyEvent screenshotKeyEvent;
    
    public KeybindBean()
    {
        snippetKeyEvent     =   new SeriableKeyEvent();
        screenshotKeyEvent  =   new SeriableKeyEvent();
    }

    public SeriableKeyEvent getScreenshotKeyEvent() 
    {
        return screenshotKeyEvent;
    }

    public void setScreenshotKeyEvent(SeriableKeyEvent screenshotKeyEvent) 
    {
        this.screenshotKeyEvent = screenshotKeyEvent;
    }

    public SeriableKeyEvent getSnippetKeyEvent() 
    {
        return snippetKeyEvent;
    }

    public void setSnippetKeyEvent(SeriableKeyEvent snippetKeyEvent)
    {
        this.snippetKeyEvent = snippetKeyEvent;
    }
}
