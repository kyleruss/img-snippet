//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.ConfigManager;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
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
    
    public void toggleShortcutBinding(boolean enable)
    {
        shortcutBinding     =   enable;
    }
    
    private boolean checkKeyInput(SeriableKeyEvent confKeyEvent, NativeKeyEvent inputKeyEvent)
    {
        return confKeyEvent.getModifiers() == inputKeyEvent.getModifiers()
               && confKeyEvent.getKeyCode() == inputKeyEvent.getKeyCode();
    }
    
    @Override
    public void nativeKeyPressed(NativeKeyEvent e) 
    {
        SnippetWindow display   =   SnippetWindow.getInstance();
        KeybindBean keyConf     =   ConfigManager.getInstance().getKeybindConfig();
        
        if(shortcutBinding)
            SettingsPanel.getInstance().registerShortcutCallback(e);
        
        else
        {
            if(checkKeyInput(keyConf.getSnippetKeyEvent(), e))
                display.showFrame();
            
            else if(checkKeyInput(keyConf.getScreenshotKeyEvent(), e))
                display.getSnippetPanel().saveMonitorScreenshot();
        }
    }
    

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
