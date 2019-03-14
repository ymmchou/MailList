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

public class FirstView extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmfristFrame;
	private JButton btnallmess;
	private JButton btnidselectid;
	
	public FirstView() {
		loadView("/com/xupt/ymm/xml/frist.xml");
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
		jfrmfristFrame = view.getElement("jfrmfristFrame");
		btnallmess = view.getElement("btnallmess");
		btnidselectid = view.getElement("btnidselectid");
	}

	@Override
	public void dealAction() {
		jfrmfristFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		 BackGroud.backGroud(jfrmfristFrame, "./src/lib/早上好.gif");
		
		btnallmess.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				UserWindows uu = new UserWindows();
				uu.showWindow();
				exitWindow();
			}
		});
		btnidselectid.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				Windows ww = new Windows();
				ww.showWindow();
				exitWindow();
			}
			
		});
	}

	@Override
	public void exitWindow() {
		jfrmfristFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmfristFrame.setVisible(true);
	}
	
}
