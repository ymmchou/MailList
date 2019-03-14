package com.xupt.view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.mec.server_client.common.ClientActionAdapter;
import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.until.BackGroud;

public class UserSelect extends ClientActionAdapter implements MecView {

	private ViewFactory view;
	private JFrame jfrmuserselectFrame;
	private JButton btncomfore;
	private JButton btnout;
	private JTextField txtid;
	private JTextField txttelphone;
	private JTextField txtname;
	
	public UserSelect() {
		loadView("/com/xupt/ymm/xml/userselect.xml");
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
		jfrmuserselectFrame = view.getElement("jfrmuserselectFrame");
		btncomfore = view.getElement("btncomfore");
		btnout = view.getElement("btnout");
		txtid = view.getElement("txtid");
		txtname = view.getElement("txtname");
		txttelphone = view.getElement("txttelphone");
	}

	@Override
	public void dealAction() {
		jfrmuserselectFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		
		btncomfore.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(txtid.getText() != null || txtname.getText() != null 
						|| txttelphone.getText() !=null) {
				new Selectid();
				}
			}
			
		});
		btnout.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				exitWindow();
			}
		});
		
		 BackGroud.backGroud(jfrmuserselectFrame, "./src/lib/timg.jfif");
		 
	}

	@Override
	public void exitWindow() {
		jfrmuserselectFrame.dispose();
		
	}

	@Override
	public void showWindow() {
		jfrmuserselectFrame.setVisible(true);
	}
	
}
