//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.app;

import com.kyleruss.imgsnippet.gui.KeybindBean;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class ConfigManager 
{
    private static ConfigManager instance;
    private AppConfig appConfig;
    private KeybindBean keybindConfig;
    
    private ConfigManager()
    {
        initSettings();
    }
    
    public void initSettings()
    {
        try
        {
            initAppConfig();
            initKeybindConfig();
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to load settings");
            e.printStackTrace();
            appConfig       =   new AppConfig();
            keybindConfig   =   new KeybindBean();   
        }
    }
    
    public void saveSettings()
    {
        try
        {
            saveAppConfig();
            saveKeybindConfig();
        }
        
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Failed to save settings");
            e.printStackTrace();
            initSettings();
        }
    }
    
    public void initAppConfig() throws JAXBException
    {
        File file                   =   new File("data/config.xml");
        JAXBContext jaxbContext     =   JAXBContext.newInstance(AppConfig.class);
        Unmarshaller unmarshaller   =   jaxbContext.createUnmarshaller();
        appConfig                   =   (AppConfig) unmarshaller.unmarshal(file);
    }
    
    public void saveAppConfig() throws IOException, JAXBException
    {
        File file                   =   new File("data/config.xml");
        JAXBContext jaxbContext     =   JAXBContext.newInstance(AppConfig.class);
        Marshaller marshaller       =   jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(appConfig, file);
    }
    
    public void saveKeybindConfig() throws FileNotFoundException, IOException
    {
        File file                           =   new File("data/keybinds.conf");
        
        ObjectOutputStream outputStream     =   new ObjectOutputStream(new FileOutputStream(file));
        outputStream.writeObject(keybindConfig);
        outputStream.close();
    }
    
    public void initKeybindConfig() throws FileNotFoundException, IOException, ClassNotFoundException
    {
        File file                           =   new File("data/keybinds.conf");
        
        ObjectInputStream inputStream       =   new ObjectInputStream(new FileInputStream(file));
        keybindConfig                       =   (KeybindBean) inputStream.readObject();
        inputStream.close();
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
