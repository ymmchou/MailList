package com.xupt.view.test;

import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.view.Windows;

public class WindowTest {

	public static void main(String[] args) {
		ViewFactoryBuilder vfb = new ViewFactoryBuilder();
		try {
			vfb.addView("/com/xupt/ymm/xml/view_renderer.xml");
			new Windows().showWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
