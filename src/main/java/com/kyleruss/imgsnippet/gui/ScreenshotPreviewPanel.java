//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import com.kyleruss.imgsnippet.app.AppManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.swingx.prompt.PromptSupport;

public class ScreenshotPreviewPanel extends JPanel implements ActionListener
{
    public static final int SAVE_OPTION         =   0;
    public static final int CANCEL_OPTION       =   1;
    
    private final BufferedImage screenshot;
    private JButton saveBtn, closeBtn;
    private JTextField fileNameField;
    private JPanel wrapper, controlWrapper;
    private PreviewDialog dialog;
    
    public ScreenshotPreviewPanel(BufferedImage screenshot)
    {
        setPreferredSize(new Dimension(screenshot.getWidth(), screenshot.getHeight()));
        
        this.screenshot     =   screenshot;
        saveBtn             =   new JButton("Save");
        closeBtn            =   new JButton("Cancel");
        fileNameField       =   new JTextField();
        wrapper             =   new JPanel(new BorderLayout());
        controlWrapper      =   new JPanel();

        PromptSupport.setPrompt("Filename (optional)", fileNameField);
        fileNameField.setPreferredSize(new Dimension(160, 20));
        controlWrapper.add(fileNameField);
        controlWrapper.add(saveBtn);
        controlWrapper.add(closeBtn);
        
        wrapper.add(this, BorderLayout.CENTER);
        wrapper.add(controlWrapper, BorderLayout.SOUTH);
        dialog      =   new PreviewDialog();
        
        saveBtn.requestFocusInWindow();
        closeBtn.addActionListener(this);
        saveBtn.addActionListener(this);
    }
    
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        g.drawImage(screenshot, 0, 0, null);
    }
    
    public int showPreviewPanel()
    {
        String[] options    =   { "Save", "Cancel" };
        //int option          =   JOptionPane.showOptionDialog(null, this, "Screenshot Preview", 0, JOptionPane.PLAIN_MESSAGE, null, options, null);
       // dialog.add(this);
      // if(dialog == null) dialog = new PreviewDialog();
        dialog.setVisible(true);
        return -1;
    }
    
    public BufferedImage getScreenshot()
    {
        return screenshot;
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        Object src  =   e.getSource();
        
        if(src == closeBtn)
            dialog.setVisible(false);
        
        else if(src == saveBtn)
        {
            dialog.setVisible(false);
            AppManager.getInstance().getSnippetPanel().saveScreenshot(screenshot);
        }
        
    }
    
    private class PreviewDialog extends JDialog
    {
        private PreviewDialog()
        {
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            getContentPane().add(wrapper);
            pack();
            setLocationRelativeTo(null);
        }
    }
}
