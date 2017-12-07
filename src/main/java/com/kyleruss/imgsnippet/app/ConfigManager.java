//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.kyleruss.imgsnippet.gui.KeybindBean;
import java.io.File;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigManager 
{
    private static ConfigManager instance;
    private AppConfig appConfig;
    private KeybindBean keybindConfig;
    
    private ConfigManager()
    {
        initAppConfig();
    }
    
    public void initAppConfig()
    {
        try
        {
            File file                   =   new File(AppConfig.CONFIG_PATH);
            JAXBContext jaxbContext     =   JAXBContext.newInstance(AppConfig.class);
            Unmarshaller unmarshaller   =   jaxbContext.createUnmarshaller();
            appConfig                   =   (AppConfig) unmarshaller.unmarshal(file);
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to load settings");
            e.printStackTrace();
            appConfig   =   new AppConfig();
        }
    }
    
    public void saveAppConfig()
    {
        try
        {
            File file                   =   new File(AppConfig.CONFIG_PATH);
            JAXBContext jaxbContext     =   JAXBContext.newInstance(AppConfig.class);
            Marshaller marshaller       =   jaxbContext.createMarshaller();
            
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(appConfig, file);
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to save settings");
            e.printStackTrace();
        }
    }
    
    
    public KeybindBean getKeybindConfig()
    {
        return keybindConfig;
    }
    
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
