//====================================
//  Kyle Russell
//  img-snippet
//  github.com/kyleruss/img-snippet
//====================================

package com.kyleruss.imgsnippet.gui;

import javax.swing.JFrame;

public class SnippetWindow 
{
    public static void main(String[] args)
    {
       JFrame frame         =   new JFrame("imgSnippet");
       SnippetPanel panel   =   new SnippetPanel();
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.getContentPane().add(panel);
       frame.pack();
       frame.setLocationRelativeTo(null);
       frame.setVisible(true);
    }
}
