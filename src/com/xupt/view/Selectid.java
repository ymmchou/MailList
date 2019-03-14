package com.xupt.view;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.xupt.dao.DatabaseConn;

@SuppressWarnings("serial")
public class Selectid extends JFrame {
	
	private static String id;
	
	public Selectid() {
		this(id);
	}
		
	public String getId() {
		return id;
	}

	public void setId(String id) {
		Selectid.id = id;
	}

	@SuppressWarnings("unchecked")
	public Selectid(String id){
		setId(id);
		JTable jt = new JTable();
		String header[] = {"编号","姓名","性别","身份证号","电话" , "QQ号", "邮箱" ,"住址"};//设置表头
		DefaultTableModel md = new DefaultTableModel(header,0);
		JScrollPane sc = new JScrollPane(jt);
		jt.setModel(md);
		this.setTitle("所有记录");
		
		try {
			int i=0;
			Object [][]da = new Object[20][8];
			String sql="select * from sys_type_student where id='" + 1 +"'";
		
			DatabaseConn dc = new DatabaseConn(sql);
			ResultSet rs = dc.doQuery();
			while(rs.next()){
				da[i][0] = rs.getString("id");//从数据库中读取数据
				da[i][1] = rs.getString("name");
				da[i][2] = rs.getString("stirngSex");
				da[i][3] = rs.getString("birthy");
				da[i][4] = rs.getString("telphone");
				da[i][5] = rs.getString("qq");
				da[i][6] = rs.getString("mail");
				da[i][7] = rs.getString("address");
				i++;
			}
			//dc.closeConnection();
			for(int j=0;j<i;j++){//将数据显示在表格中
				@SuppressWarnings("rawtypes")
				Vector v = new Vector();
				v.addElement(da[j][0]);
				v.addElement(da[j][1]);
				v.addElement(da[j][2]);
				v.addElement(da[j][3]);
				v.addElement(da[j][4]);
				v.addElement(da[j][5]);
				v.addElement(da[j][6]);
				v.addElement(da[j][7]);
				md.addRow(v);
			}
			this.getContentPane().add(sc,BorderLayout.CENTER);//设置表格
			this.setSize(900, 300);
			//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			int width = Toolkit.getDefaultToolkit().getScreenSize().width;
	        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	        this.setLocation(width /2-400 , height / 3-100);
	        this.setVisible(true);
			
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"查看失败");
		}
	}
	
	public void exit() {
		this.dispose();
	}
}
