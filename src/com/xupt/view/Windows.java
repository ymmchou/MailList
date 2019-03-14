package com.xupt.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
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
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.dao.Database;
import com.xupt.until.BackGroud;
import com.xupt.until.ViewUtils;

public class Windows extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmViewLoginFrame;
	private JButton btnLogin;
	private JTextField txtname;
	private JPasswordField pswPassword;
	private JButton btnother;	
	
	public Windows() {
		loadView("/com/xupt/ymm/xml/user_login.xml");
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
		jfrmViewLoginFrame = view.getElement("jfrmViewLoginFrame");
		btnLogin = view.getElement("btnLogin");
		txtname = view.getElement("txtname");
		pswPassword = view.getElement("pswPassword");
		btnother = view.getElement("btnother");
	}

	@Override
	public void dealAction() {
		jfrmViewLoginFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		BackGroud.backGroud(jfrmViewLoginFrame, "./src/lib/简单.gif");
		txtname.addCaretListener(new CaretListener() {
			
			@Override
			public void caretUpdate(CaretEvent e) {
				String id = txtname.getText().trim();
				btnLogin.setEnabled(id.length() > 0);
			}
		});
		txtname.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				pswPassword.requestFocus();
			}
		});
		
		pswPassword.addFocusListener(new FocusAdapter() {

			@Override
			public void focusGained(FocusEvent e) {
				pswPassword.setText("");
			}
		});
		
		pswPassword.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				btnLogin.requestFocus();
			}
		});
		
		btnLogin.addMouseListener(new MouseAdapter() {
			String sqlString = "select num,password from sys_type_person";

			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean falg = false;
				List<Map<String, String>> listMap = new ArrayList<>();
				
				listMap = Database.doSelect(sqlString);
				
				for(int index = 0;index < listMap.size(); index++) {
					if(listMap.get(index).get("num")
							.equals(txtname.getText())
						&& listMap.get(index).get("password")
						.equals(pswPassword.getText())) {
						falg = true;
						
						new Meau().showWindow();
						exitWindow();
					}
				}
				if(!falg) {
					ViewUtils.showWarnMessage(jfrmViewLoginFrame, "密码和账号错误！");
				}	
			}
		});
		btnother.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String number = txtname.getText();
				@SuppressWarnings("deprecation")
				String password = pswPassword.getText();
				
				try {
					String sql = "insert into sys_type_person values('"
							+ number +"','"
							+ password +"')";
					Database.add(sql);
					JOptionPane.showMessageDialog(null,"注册成功，可以登录");
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
	}

	@Override
	public void exitWindow() {
		jfrmViewLoginFrame.dispose();
		
		
	}

	@Override
	public void showWindow() {
		jfrmViewLoginFrame.setVisible(true);
		
	}
}
