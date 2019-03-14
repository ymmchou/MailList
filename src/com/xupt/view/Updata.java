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
import javax.swing.JTextField;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.dao.Database;
import com.xupt.until.BackGroud;

public class Updata extends ClientActionAdapter implements MecView{
	private ViewFactory view;
	private JFrame jfrmupdataFrame;
	private JButton btncomfire;
	private JTextField txtid;
	private JTextField txtname;
	private JTextField txtptelhone;
	private JButton btnout;

	public Updata() {
		loadView("/com/xupt/ymm/xml/updata.xml");
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
		jfrmupdataFrame = view.getElement("jfrmupdataFrame");
		btncomfire = view.getElement("btncomfire");
		txtname = view.getElement("txtname");
		txtptelhone = view.getElement("txtptelhone");
		txtid = view.getElement("txtid");
		btnout = view.getElement("btnout");
	}

	@Override
	public void dealAction() {
		jfrmupdataFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		btncomfire.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				String sqlString = "select id,name,telphone from sys_type_student";
				List<Map<String, String>> listMap = new ArrayList<>();
				
				listMap = Database.doSelect(sqlString);
				System.out.println(listMap);
				for(int index = 0;index < listMap.size();index++) {
					String id = listMap.get(index).get("id");
					if( txtid.getText().compareTo(id) != 0) {
						JOptionPane.showMessageDialog(null, "编号不正确！");
					}else if(id.equals(txtid.getText())&&
								txtname.getText() != null ||
								txtptelhone.getText() != null) {
							String sqll = "UPDATE sys_type_student SET name='" +
									txtname.getText() + "'," + "telphone='" + 
									txtptelhone.getText()  + "' WHERE id='" +
									id + "'";
							try {
								Database.add(sqll);
								JOptionPane.showMessageDialog(null,"更改成功！");
								
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null, "修改错误!");
							break;
						}
					//break;
				}
			}
			
		});
		
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitWindow();
			}
			
		});
		
		BackGroud.backGroud(jfrmupdataFrame, "./src/lib/timg.gif");
	}

	@Override
	public void exitWindow() {
		jfrmupdataFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmupdataFrame.setVisible(true);
	}
}
