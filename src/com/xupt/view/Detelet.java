package com.xupt.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.dao.Database;
import com.xupt.until.BackGroud;

public class Detelet extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmdeteletFrame;
	private JTextField txtide;
	private JButton btncomfire;
	private JButton btnout;
	
	public Detelet() {
		loadView("/com/xupt/ymm/xml/delelet.xml");
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
		jfrmdeteletFrame = view.getElement("jfrmdeteletFrame");
		btncomfire = view.getElement("btncomfire");
		txtide = view.getElement("txtide");
		btnout = view.getElement("btnout");
	}

	@Override
	public void dealAction() {
		jfrmdeteletFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		btncomfire.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String comforedelete  = txtide.getText();
				String sqlString = "select id from sys_type_student";
				
				List<Map<String, String>> listMap = new ArrayList<>();
				
				listMap = Database.doSelect(sqlString);
				
				for(int index = 0;index < listMap.size(); index++) {
					if(listMap.get(index).get("id")
							.equals(txtide.getText())) {
						
						try {
							String sql="delete from sys_type_student where id = '" + comforedelete + "'";
							Database.add(sql);
							JOptionPane.showMessageDialog(null,"删除成功,程序将返回主菜单");
							exitWindow();
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}else {
						JOptionPane.showMessageDialog(null,"无此联系人或删除失败！");
					}
				}				
		}
	});
		
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitWindow();
			}
			
		});
		BackGroud.backGroud(jfrmdeteletFrame, "./src/lib/index1.jpg");

	}

	@Override
	public void exitWindow() {
		jfrmdeteletFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmdeteletFrame.setVisible(true);
	}
}
