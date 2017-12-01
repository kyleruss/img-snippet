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
        
        initSettings();
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
        
    }
    
    public void openSettingsPanel()
    {
    }
}
