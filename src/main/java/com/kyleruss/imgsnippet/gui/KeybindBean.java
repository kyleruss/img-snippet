//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.awt.event.KeyEvent;
import org.jnativehook.keyboard.NativeKeyEvent;

public class KeybindBean 
{
    private NativeKeyEvent snippetKeyEvent;
    private NativeKeyEvent screenshotKeyEvent;
    
    public KeybindBean()
    {
        
    }

    public NativeKeyEvent getScreenshotKeyEvent() 
    {
        return screenshotKeyEvent;
    }

    public void setScreenshotKeyEvent(NativeKeyEvent screenshotKeyEvent) 
    {
        this.screenshotKeyEvent = screenshotKeyEvent;
    }

    public NativeKeyEvent getSnippetKeyEvent() 
    {
        return snippetKeyEvent;
    }

    public void setSnippetKeyEvent(NativeKeyEvent snippetKeyEvent)
    {
        this.snippetKeyEvent = snippetKeyEvent;
    }
    
    
}
