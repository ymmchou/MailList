package com.xupt.view;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.mec.xml_view.core.MecView;
import com.mec.xml_view.core.ViewFactory;
import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.until.BackGroud;

public class EndWindows implements MecView {
	
	private ViewFactory view;
	private JFrame jfrmEndViewLoginFrame;
	
	public EndWindows() {
		loadView("/com/xupt/ymm/xml/end.xml");
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
		jfrmEndViewLoginFrame = view.getElement("jfrmEndViewLoginFrame");
		
	}

	@Override
	public void dealAction() {
		jfrmEndViewLoginFrame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exitWindow();
			}
		});
		BackGroud.backGroud(jfrmEndViewLoginFrame, "./src/lib/致谢.gif");
	}

	@Override
	public void exitWindow() {
		jfrmEndViewLoginFrame.dispose();
	}

	@Override
	public void showWindow() {
		jfrmEndViewLoginFrame.setVisible(true);
		
	}
	
}
