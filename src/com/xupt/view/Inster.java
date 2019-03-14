package com.xupt.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.mec.xml_view.util.ViewTool;
import com.xupt.dao.Database;
import com.xupt.until.BackGroud;

public class Inster extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrminsterFrame;
	private JButton btncomfire;
	private JTextField txtnum;
	private JTextField txtname;
	private JTextField txtbirthy;
	private JTextField txtsex;
	private JTextField txtaddress;
	private JTextField txtphone;
	private JTextField txtqq;
	private JTextField txtmail;
	private JButton btnout;
	
	public Inster() {
		loadView("/com/xupt/ymm/xml/inter.xml");
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
		jfrminsterFrame = view.getElement("jfrminsterFrame");
		btncomfire = view.getElement("btncomfire");
		txtnum = view.getElement("txtnum");
		txtname = view.getElement("txtname");
		txtsex = view.getElement("txtsex");
		txtbirthy = view.getElement("txtbirthy");
		txtaddress = view.getElement("txtaddress");
		txtphone = view.getElement("txtphone");
		txtmail = view.getElement("txtmail");
		txtqq = view.getElement("txtqq");
		btnout = view.getElement("btnout");
	}

	@Override
	public void dealAction() {
		jfrminsterFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		BackGroud.backGroud(jfrminsterFrame, "./src/lib/的.gif");
		//BackGroud.backGroud(jfrminsterFrame, "./src/lib/的.gif");
		
		btncomfire.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				String number = txtnum.getText();
				String name = txtname.getText();
				String sex = txtsex.getText();
				String birthy = txtbirthy.getText();
				String telphone = txtphone.getText();
				String mail = txtmail.getText();
				String qq = txtqq.getText();
				String address = txtaddress.getText();
				
					try {
						String sql = "insert into sys_type_student values('"
								+ number +"','"
								+ name +"','"
								+ sex +"','"
								+ birthy +"','"
								+ address + "','"
								+ telphone + "','"
								+ qq + "','"
								+ mail + "')";
						if(!(txtnum.getText() == null && txtname.getText() ==null 
										&& txtsex.getText()==null
										&& txtbirthy.getText() == null
										&& txtphone.getText() == null)) {
							Database.add(sql);
							JOptionPane.showMessageDialog(null,"添加成功,将返回主菜单");
							exitWindow();
						}else {
							JOptionPane.showMessageDialog(null,"添加失败");
						}	
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					
			}
			
		});
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				exitWindow();
			}
		});
		
	}

	@Override
	public void exitWindow() {
		jfrminsterFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrminsterFrame.setVisible(true);
	}

	@Override
	public boolean confirmOffline() {
		int choice = ViewTool.yesNoChooser(jfrminsterFrame, "是否确认退出？");
		if (choice == JOptionPane.YES_OPTION) {
			exitWindow();
			return true;
		}
		return false;
	}
	
}
