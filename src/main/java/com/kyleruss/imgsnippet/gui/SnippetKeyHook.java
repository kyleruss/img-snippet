//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import com.kyleruss.imgsnippet.app.ScreenshotManager;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class SnippetKeyHook implements NativeKeyListener
{
    private boolean shortcutBinding;
    
    public SnippetKeyHook() 
    {
        shortcutBinding     =   false;
    }
    
    public void registerHook()
    {
        try
        {
            LogManager.getLogManager().reset();
            Logger logger   =   Logger.getLogger(GlobalScreen.class.getPackage().getName());
            logger.setLevel(Level.OFF);
            
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
            
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public void toggleShortcutBinding()
    {
        shortcutBinding     =   !shortcutBinding;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) 
    {
        int keyCode     =   e.getKeyCode();
        int modifiers   =   e.getModifiers();
        String modText  =   NativeInputEvent.getModifiersText(modifiers);
        
        if(modText.equals("Shift+Ctrl"))
        {
            if(keyCode == NativeKeyEvent.VC_1)
                AppManager.getInstance().getDisplay().showFrame();
            
            else if(keyCode == NativeKeyEvent.VC_2)
                AppManager.getInstance().getDisplay().getSnippetPanel().saveMonitorScreenshot();
            
            else if(keyCode == NativeKeyEvent.VC_3)
                ScreenshotManager.getInstance().browseScreenshotDirectory();
        }
    }
    

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
