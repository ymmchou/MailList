package com.xupt.until;

import java.awt.Component;

import javax.swing.JOptionPane;

public class ViewUtils {
	private static final String WELCOME = "温馨提示！";
	
	public static void showWarnMessage(Component parentComponent, String message) {
		JOptionPane.showMessageDialog(parentComponent, message, WELCOME,
				JOptionPane.WARNING_MESSAGE);
	}
	
	public static int yesNoChoose(Component parentComponent, String message) {
		return JOptionPane.showConfirmDialog(parentComponent, message,
				WELCOME, JOptionPane.YES_NO_OPTION);
	}
}
