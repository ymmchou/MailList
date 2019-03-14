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

public class Meau extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmmeauFrame;
	private JButton btninter;
	private JButton btndetelet;
	private JButton btnupdate;
	private JButton btnselect;
	private JButton btnout;
	private JButton btnoot;
	private JButton btnuppass;
	
	public Meau() {
		loadView("/com/xupt/ymm/xml/menu.xml");
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
		jfrmmeauFrame = view.getElement("jfrmmeauFrame");
		
		btninter = view.getElement("btninter");
		btndetelet = view.getElement("btndetelet");
		btnupdate = view.getElement("btnupdate");
		btnselect = view.getElement("btnselect");
		btnuppass = view.getElement("btnuppass");
		btnout = view.getElement("btnout");
		btnoot = view.getElement("btnoot");
	}

	@Override
	public void dealAction() {
		jfrmmeauFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		BackGroud.backGroud(jfrmmeauFrame, "./src/lib/落叶2.gif");
		
		btninter.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Inster ii = new Inster();
				ii.showWindow();
			}
			 
		});
		 
		btndetelet.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Detelet dd = new Detelet();
				dd.showWindow();
			}
		});
		
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
				Select ss =new Select();
				ss.showWindow();
			}
			 
		});
		 
		btnuppass.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				UpPassword uu = new UpPassword();
				uu.showWindow();
			} 
		});
		 
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitWindow();
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
		jfrmmeauFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmmeauFrame.setVisible(true);
	}
}
