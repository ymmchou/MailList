package com.xupt.view.test;

import com.mec.xml_view.core.ViewFactoryBuilder;
import com.xupt.view.Inster;

public class InterTest {

	public static void main(String[] args) {
		ViewFactoryBuilder vfb = new ViewFactoryBuilder();
		try {
			vfb.addView("/com/xupt/ymm/xml/view_renderer.xml");
			new Inster().showWindow();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

}
