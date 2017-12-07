//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import java.io.Serializable;

public class SeriableKeyEvent implements Serializable
{
    private int modifiers;
    private int keyCode;
    
    public SeriableKeyEvent()
    {
        this(0, 0);
    }
    
    public SeriableKeyEvent(int modifiers, int keyCode)
    {
        this.modifiers  =   modifiers;
        this.keyCode    =   keyCode;
    }
    
    public int getModifiers()
    {
        return modifiers;
    }

    public void setModifiers(int modifiers)
    {
        this.modifiers = modifiers;
    }

    public int getKeyCode()
    {
        return keyCode;
    }

    public void setKeyCode(int keyCode)
    {
        this.keyCode = keyCode;
    }
    
    
}
