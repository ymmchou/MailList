package com.xupt.view;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.until.BackGroud;

public class UserMeau extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmusermeauFrame;
	
	private JButton btnupdate;
	private JButton btnselect;
	private JButton btnoot;
	private JButton btnout;
	
	public UserMeau() {
		loadView("/com/xupt/ymm/xml/userMeau.xml");
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
	
	public void init() {
	}

	@Override
	public void reinit() {
		jfrmusermeauFrame = view.getElement("jfrmusermeauFrame");
		btnupdate = view.getElement("btnupdate");
		btnselect = view.getElement("btnselect");
		btnoot = view.getElement("btnoot");
		btnout = view.getElement("btnout");
	}

	@Override
	public void dealAction() {
		jfrmusermeauFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		BackGroud.backGroud(jfrmusermeauFrame, "./src/lib/的.gif");
		
		btnupdate.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				Updata uu = new Updata();
				uu.showWindow();
			}
			 
		});
		
		btnselect.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserSelect uu = new UserSelect();
				uu.showWindow();
			}
			 
		});
		 
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				UpPassword uu = new UpPassword();
				uu.showWindow();
			} 
		});
		 
		btnoot.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				EndWindows ee = new EndWindows();
				ee.showWindow();
				exitWindow();
			}
			 
		});
		
	}

	@Override
	public void exitWindow() {
		jfrmusermeauFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmusermeauFrame.setVisible(true);
	}
}
