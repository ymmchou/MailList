package com.xupt.until;

import java.awt.BorderLayout;
import java.awt.Image;


import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackGroud {
	
	private static ImageIcon icon;
	private static JLabel label;
	
	public static void backGroud(JFrame jframe,String path){
		icon = new ImageIcon(path);
		icon.setImage(icon.getImage().getScaledInstance(jframe.getWidth(), jframe.getHeight(), Image.SCALE_DEFAULT));
		label = new JLabel(icon);
		label.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight());
		
		JPanel panel = (JPanel) jframe.getContentPane();
		panel.setOpaque(false);
		jframe.getLayeredPane().setLayout(null);
		jframe.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		label.setLayout(new BorderLayout());
	}
	
}
