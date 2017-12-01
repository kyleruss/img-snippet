//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppConfig;
import com.kyleruss.imgsnippet.app.ConfigManager;
import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingsPanel extends JPanel
{
    private final String DRAW_BIND_LABEL        =   "Snippet bind";
    private final String SS_BIND_LABEL          =   "Screenshot bind";
    private final String IMG_DIR_LABEL          =   "Capture Directory";
    private final String STORE_IMG_LABEL        =   "Store captures";
    private final String UPLOAD_CAPTURE_LABEL   =   "Upload captures";
    
    private JLabel snippetLabel, screenshotLabel;
    private JTextField imgDirInput;
    private JCheckBox storeImgCheck, uploadImgCheck;
    private static SettingsPanel instance;
    
    private SettingsPanel()
    {
        setLayout(new GridLayout(5, 2));
        
        snippetLabel    =   new JLabel();
        screenshotLabel =   new JLabel();
        imgDirInput     =   new JTextField();
        storeImgCheck   =   new JCheckBox();
        uploadImgCheck  =   new JCheckBox();
        
        addSettingComponent(DRAW_BIND_LABEL, snippetLabel);
        addSettingComponent(SS_BIND_LABEL, screenshotLabel);
        addSettingComponent(IMG_DIR_LABEL, imgDirInput);
        addSettingComponent(STORE_IMG_LABEL, storeImgCheck);
        addSettingComponent(UPLOAD_CAPTURE_LABEL, uploadImgCheck);
    }
    
    private void addSettingComponent(String label, Component component)
    {
        add(new JLabel(label));
        add(component);
    }
    
    public void initSettings()
    {
        AppConfig config    =   ConfigManager.getInstance().getAppConfig();
        
        snippetLabel.setText(config.getDrawBind());
        screenshotLabel.setText(config.getScreenshotBind());
        imgDirInput.setText(config.getImageDirectory());
        storeImgCheck.setSelected(config.isStoreLocally());
        uploadImgCheck.setSelected(config.isUploadOnline());
    }
    
    public void saveSettings()
    {
        ConfigManager confManager   =   ConfigManager.getInstance();      
        AppConfig config            =   confManager.getAppConfig();
        
        config.setDrawBind(snippetLabel.getText());
        config.setScreenshotBind(screenshotLabel.getText());
        config.setImageDirectory(imgDirInput.getText());
        config.setStoreLocally(storeImgCheck.isSelected());
        config.setUploadOnline(uploadImgCheck.isSelected());
        
        confManager.saveAppConfig();
    }
    
    public void showSettingsPanel()
    {
        initSettings();
        int option  =   JOptionPane.showConfirmDialog(null, this, "Settings", JOptionPane.OK_CANCEL_OPTION);
        
        if(option == JOptionPane.OK_OPTION)
            saveSettings();
        
        instance = null;
    }
    
    public static SettingsPanel getInstance()
    {
        if(instance == null) instance   =   new SettingsPanel();
        return instance;
    }
}
