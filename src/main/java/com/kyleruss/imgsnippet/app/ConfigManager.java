//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

public class ConfigManager 
{
    private static ConfigManager instance;
    private AppConfig appConfig;
    
    public AppConfig getAppConfig()
    {
        return appConfig;
    }
    
    public static ConfigManager getInstance()
    {
        if(instance == null) instance   =   new ConfigManager();
        return instance;
    }
}
