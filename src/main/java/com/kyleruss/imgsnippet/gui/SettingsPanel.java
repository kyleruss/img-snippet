//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import com.kyleruss.imgsnippet.app.AppConfig;
import com.kyleruss.imgsnippet.app.ConfigManager;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jnativehook.keyboard.NativeKeyEvent;

public class SettingsPanel extends JPanel implements ActionListener
{
    private final int BINDING_NONE              =   -1;
    private final int BINDING_SNIPPET           =   0;
    private final int BINDING_SCREENSHOT        =   1;
    private final String DRAW_BIND_LABEL        =   "Snippet bind";
    private final String SS_BIND_LABEL          =   "Screenshot bind";
    private final String IMG_DIR_LABEL          =   "Capture Directory";
    private final String STORE_IMG_LABEL        =   "Store captures";
    private final String UPLOAD_CAPTURE_LABEL   =   "Upload captures";
    
    private JTextField imgDirInput;
    private JCheckBox storeImgCheck, uploadImgCheck;
    private int binding;
    private KeybindBean tempKeybindBean;
    private JButton snippetBindToggle, screenshotBindToggle;
    private JButton activeBindButton;
    private static SettingsPanel instance;
    
    private SettingsPanel()
    {
        setLayout(new GridLayout(6, 2));
        
        imgDirInput             =   new JTextField();
        storeImgCheck           =   new JCheckBox();
        uploadImgCheck          =   new JCheckBox();
        snippetBindToggle       =   new JButton("Bind");
        screenshotBindToggle    =   new JButton("Bind");   
        activeBindButton        =   null;
        binding                 =   BINDING_NONE;
        
        addSettingComponent(DRAW_BIND_LABEL, snippetBindToggle);
        addSettingComponent(SS_BIND_LABEL, screenshotBindToggle);
        addSettingComponent(IMG_DIR_LABEL, imgDirInput);
        addSettingComponent(STORE_IMG_LABEL, storeImgCheck);
        addSettingComponent(UPLOAD_CAPTURE_LABEL, uploadImgCheck);

        snippetBindToggle.addActionListener(this);
        screenshotBindToggle.addActionListener(this);
    }
    
    private void addSettingComponent(String label, Component component)
    {
        add(new JLabel(label));
        add(component);
    }
    
    public void initSettings()
    {
        AppConfig config    =   ConfigManager.getInstance().getAppConfig();
        
        imgDirInput.setText(config.getImageDirectory());
        storeImgCheck.setSelected(config.isStoreLocally());
        uploadImgCheck.setSelected(config.isUploadOnline());
    }
    
    public void setAppSettings()
    {
        ConfigManager confManager   =   ConfigManager.getInstance();      
        AppConfig config            =   confManager.getAppConfig();
        
        config.setImageDirectory(imgDirInput.getText());
        config.setStoreLocally(storeImgCheck.isSelected());
        config.setUploadOnline(uploadImgCheck.isSelected());
    }
    
    public void setKeybindBean()
    {
        KeybindBean keybindConfig   =   ConfigManager.getInstance().getKeybindConfig();
        
        if(tempKeybindBean != null)
        {
            if(tempKeybindBean.getSnippetKeyEvent() != null)
                keybindConfig.setSnippetKeyEvent(tempKeybindBean.getSnippetKeyEvent());
            
            if(tempKeybindBean.getScreenshotKeyEvent() != null)
                keybindConfig.setScreenshotKeyEvent(tempKeybindBean.getScreenshotKeyEvent());
        }
    }
    
    
    public void showSettingsPanel()
    {
        initSettings();
        int option      =   JOptionPane.showConfirmDialog(null, this, "Settings", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        
        if(option == JOptionPane.OK_OPTION)
        {
            setAppSettings();
            setKeybindBean();
            ConfigManager.getInstance().saveSettings();
        }
        
        tempKeybindBean =   null;
        instance        =   null;
    }
    
    public void registerShortcut(int binding)
    {
        this.binding    =   binding;
        SnippetKeyHook hook =   AppManager.getInstance().getKeyHook();
        hook.toggleShortcutBinding(true);
    }
    
    public void registerShortcutCallback(NativeKeyEvent keyEvent)
    {
        if(tempKeybindBean == null) tempKeybindBean = new KeybindBean();
        
        if(binding == BINDING_SNIPPET || binding == BINDING_SCREENSHOT)
        {
            SeriableKeyEvent seriableKeyEvent   =   new SeriableKeyEvent(keyEvent.getModifiers(), keyEvent.getKeyCode());
            
            if(binding == BINDING_SNIPPET)
                tempKeybindBean.setSnippetKeyEvent(seriableKeyEvent);

            else if(binding == BINDING_SCREENSHOT)
                tempKeybindBean.setScreenshotKeyEvent(seriableKeyEvent);
        }
    }
    
    private void toggleBindButton(JButton button, int binding)
    {
        if(this.binding != binding)
        {
            if(activeBindButton != null)
                activeBindButton.setText("Bind");
            
            activeBindButton    =   button;
            button.setText("Done");
            registerShortcut(binding);
        }
        
        else 
        {
            activeBindButton    =   null;
            button.setText("Bind");
            binding = BINDING_NONE;
            SnippetKeyHook hook     =   AppManager.getInstance().getKeyHook();
            hook.toggleShortcutBinding(false);
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src  =   e.getSource();
        
        if(src == snippetBindToggle)
            toggleBindButton(snippetBindToggle, BINDING_SNIPPET);
        
        else if(src == screenshotBindToggle)
            toggleBindButton(screenshotBindToggle, BINDING_SCREENSHOT);
    }
    
    public static SettingsPanel getInstance()
    {
        if(instance == null) instance   =   new SettingsPanel();
        return instance;
    }
}
