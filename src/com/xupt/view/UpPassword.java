package com.xupt.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.mec.xml_view.util.ViewTool;
import com.xupt.dao.Database;
import com.xupt.until.BackGroud;

public class UpPassword extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmupPassFrame;
	private JButton btncomfire;
	private JTextField pswoldPassword;
	private JPasswordField pswnewPassword;
	private JPasswordField pswotherPassword;
	
	public UpPassword() {
		loadView("/com/xupt/ymm/xml/upPass.xml");
	}
	
	@Override
	public void loadView(String xmlPath) {
		try {
			view = new ViewFactoryBuilder().addView(xmlPath);
			reinit();
			dealAction();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void reinit() {
		jfrmupPassFrame = view.getElement("jfrmupPassFrame");
		btncomfire = view.getElement("btncomfire");
		pswoldPassword = view.getElement("pswoldPassword");
		pswnewPassword = view.getElement("pswnewPassword");
		pswotherPassword = view.getElement("pswotherPassword");
	}

	@Override
	public void dealAction() {
		jfrmupPassFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		 BackGroud.backGroud(jfrmupPassFrame, "./src/lib/timg.gif");
		 
		btncomfire.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String sqlString = "select num, password from sys_type_person";
				
				List<Map<String, String>> listMap = new ArrayList<>();
				
				listMap = Database.doSelect(sqlString);
				for(int index = 0;index < listMap.size(); index++) {
					String oldmessage = listMap.get(index).get("num");
					String newmeaaage = String.valueOf(pswnewPassword.getPassword());
					String othermessage = String.valueOf(pswotherPassword.getPassword());
					if(!listMap.get(index).get("num")
							.equals(pswoldPassword.getText()) ) {
						JOptionPane.showMessageDialog(null,"账号不正确，请重新输入!");
					}else if(newmeaaage.compareToIgnoreCase(othermessage) != 0) {
						JOptionPane.showMessageDialog(null, "再次输入新密码错误，请重新确认！");
					}else {
						String sqll = "UPDATE sys_type_person SET `password` = '" +
								newmeaaage + "' WHERE num = '" + oldmessage+"'";

						try {
							Database.add(sqll);
							JOptionPane.showMessageDialog(null,"更改成功,注意保密！");
						} catch (SQLException e) {
							e.printStackTrace();
						}
						break;
					}
						
				}
			}
				
			
		});
	}

	@Override
	public void exitWindow() {
		jfrmupPassFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmupPassFrame.setVisible(true);
	}

	@Override
	public boolean confirmOffline() {
		int choice = ViewTool.yesNoChooser(jfrmupPassFrame, "是否确认退出？");
		if (choice == JOptionPane.YES_OPTION) {
			exitWindow();
			return true;
		}
		return false;
	}
	
}
