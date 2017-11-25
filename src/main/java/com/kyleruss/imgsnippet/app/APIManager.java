//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

public class APIManager 
{
    private static APIManager instance;
    
    private APIManager() {}
    
        
    public static APIManager getInstance()
    {
        if(instance == null) instance   =   new APIManager();
        return instance;
    }
}
