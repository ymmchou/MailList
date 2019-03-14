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

public class Select extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmselectFrame;
	private JButton btnallmess;
	private JButton btnidselectid;
	private JButton btnout;
	
	public Select() {
		loadView("/com/xupt/ymm/xml/select.xml");
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
		jfrmselectFrame = view.getElement("jfrmselectFrame");
		btnallmess = view.getElement("btnallmess");
		btnidselectid = view.getElement("btnidselectid");
		btnout = view.getElement("btnout");
	}

	@Override
	public void dealAction() {
		jfrmselectFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		 BackGroud.backGroud(jfrmselectFrame, "./src/lib/娃娃.jpg");
		
		btnallmess.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				new SreachTable();
			}
		});
		btnidselectid.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				new Selectid();				
			}
			
		});
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitWindow();
			}
			
		});
	}

	@Override
	public void exitWindow() {
		jfrmselectFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmselectFrame.setVisible(true);
	}
}
