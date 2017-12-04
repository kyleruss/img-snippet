//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import javax.swing.JOptionPane;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class SnippetKeyHook implements NativeKeyListener
{
    public SnippetKeyHook() {}
    
    public void registerHook()
    {
        try
        {
            GlobalScreen.registerNativeHook();
            GlobalScreen.addNativeKeyListener(this);
        }
        
        catch(NativeHookException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) 
    {
        int keyCode     =   e.getKeyCode();
        int modifiers   =   e.getModifiers();
        
        //if(NativeInputEvent.getModifiersText(modifiers).equals("Ctrl") && NativeInputEvent.getModifiersText(modifiers).equals("Shift"))
    }
    

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {}

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {}
}
