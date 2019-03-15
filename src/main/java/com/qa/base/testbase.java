package com.qa.base;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;


public class testbase {

	public static Properties prop;
	public int RESPONSE_STATUS_CODE_200 = 200;
	public int RESPONSE_STATUS_CODE_201 = 201;
	public int RESPONSE_STATUS_CODE_204 = 204;
	public int RESPONSE_STATUS_CODE_400 = 400;
	public int RESPONSE_STATUS_CODE_401 = 401;
	public int RESPONSE_STATUS_CODE_404 = 404;
	public int RESPONSE_STATUS_CODE_500 = 500;
	public int RESPONSE_STATUS_CODE_504 = 504;
	public String value1;
	public String value2;
	public static String env_email;
	public static String env_password;
	public static String env_url;
	public static String testbase_x_api_key = "J8A47vayFK4zGSqtdd9an2m9OMhm0xlx9WoYqHFu";
	public static String entree;
	public static String entree2;
	
	public testbase() {
		try {
			prop = new Properties();
			/*JTextField field1 = new JTextField();
			JTextField field2 = new JTextField();
			Object[] message = {
			    "Enter the environment:\n 1. DEV\n 2. TEST\n 3. UAT ", field1,
			    "Enter the Role:\n 1. ADMIN\n 2. ACCOUNT HOLDER/AH \n 3. OPERATOR", field2,	    
			};
			Component parent = null;
			int option = JOptionPane.showConfirmDialog(parent, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
			if (option == JOptionPane.OK_OPTION)
			{
			    value1 = field1.getText().trim().toUpperCase();
			    value2 = field2.getText().trim().toUpperCase();
			    //System.out.println(value1);
			    //System.out.println(value2);	    
			}*/
			   JFrame f = new JFrame("Enter all your values");
			   
			    //Environment radio button
			    JPanel entreePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
			    JLabel lab1 = new JLabel("Select the environment:", JLabel.LEFT);
			    final ButtonGroup entreeGroup = new ButtonGroup( );
			    JRadioButton radioButton1;
			    JRadioButton radioButton2;
			    JRadioButton radioButton3;
			    entreePanel.add(radioButton1 = new JRadioButton("Dev"));
			    radioButton1.setActionCommand("Dev");
			    entreeGroup.add(radioButton1);
			    entreePanel.add(radioButton2 = new JRadioButton("Test"));
			    radioButton2.setActionCommand("Test");
			    entreeGroup.add(radioButton2);
			    entreePanel.add(radioButton3 = new JRadioButton("UAT"));
			    radioButton3.setActionCommand("UAT");
			    entreeGroup.add(radioButton3);
			    
			    //Roles radio button
			    JPanel entreePanel2 = new JPanel(new FlowLayout(FlowLayout.LEFT) );
			    JLabel lab2 = new JLabel("Select the Role:", JLabel.LEFT);
			    final ButtonGroup entreeGroup1 = new ButtonGroup( );
			    JRadioButton radioButton4;
			    JRadioButton radioButton5;
			    JRadioButton radioButton6;
			    entreePanel2.add(radioButton4 = new JRadioButton("Admin"));
			    radioButton4.setActionCommand("Admin");
			    entreeGroup1.add(radioButton4);
			    entreePanel2.add(radioButton5 = new JRadioButton("Account Holder"));
			    radioButton5.setActionCommand("Account Holder");
			    entreeGroup1.add(radioButton5);
			    entreePanel2.add(radioButton6 = new JRadioButton("Operator"));
			    radioButton6.setActionCommand("Operator");
			    entreeGroup1.add(radioButton6); 
			    
			    Container content = f.getContentPane( );
			    content.setLayout(new GridLayout(3, 1));
			    //content.add(lab1);
			    content.add(entreePanel);
			    content.add(lab2);
			    content.add(entreePanel2);
			    	    
			    radioButton1.addActionListener(new ActionListener() {
			      public void actionPerformed(ActionEvent ae) {
			        entree = 
			          entreeGroup.getSelection().getActionCommand( );
			        }});
			    radioButton2.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent ae) {
				        entree = 
				          entreeGroup.getSelection().getActionCommand( );
				    }});
			    radioButton3.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent ae) {
				        entree = 
				          entreeGroup.getSelection().getActionCommand( );
				    }});
			    
			    radioButton4.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent ae) {
				        entree2 = 
				  	          entreeGroup1.getSelection().getActionCommand( );
				   }});
			    radioButton5.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent ae) {
				        entree2 = 
				  	          entreeGroup1.getSelection().getActionCommand( );
				   }});
			    radioButton6.addActionListener(new ActionListener() {
				      public void actionPerformed(ActionEvent ae) {
				        entree2 = 
				  	          entreeGroup1.getSelection().getActionCommand( );
				   }});
			      
			   f.setVisible(false);
			   
			    Object[] message = {
			    		lab1,
			    		content,
					};
				int option = JOptionPane.showConfirmDialog(content, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION)
				{
				    value1 = entree.toUpperCase();
				    value2 = entree2.toUpperCase();
				    System.out.println("[Selected Environment] :"+value1);
				    System.out.println("[Selected User Roles ] :"+value2);	    
				}
				else if(option == JOptionPane.CANCEL_OPTION) {
					System.out.println("Test Execution stopped...");
				}
			
			if(value1.equals("DEV")) {
			InputStream ip = this.getClass().getClassLoader().getResourceAsStream("config_dev.properties");
			//System.out.println(ip);
			prop.load(ip);
				if(value2.equals("ADMIN")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("admin_email");
					String config_password = prop.getProperty("admin_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("ACCOUNT HOLDER") || value2.equals("AH") ) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("ah_email");
					String config_password = prop.getProperty("ah_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("OPERATOR")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("op_email");
					String config_password = prop.getProperty("op_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else {
					System.out.println("Invalid Roles");
				}	
			}
			else if(value1.equals("TEST")) {
				InputStream ip = this.getClass().getClassLoader().getResourceAsStream("config_test.properties");
				//System.out.println(ip);
				prop.load(ip);
				if(value2.equals("ADMIN")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("admin_email");
					String config_password = prop.getProperty("admin_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("ACCOUNT HOLDER") || value2.equals("AH") ) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("ah_email");
					String config_password = prop.getProperty("ah_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("OPERATOR")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("op_email");
					String config_password = prop.getProperty("op_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else {
					System.out.println("Invalid Roles");
				}
			}
			else if(value1.equals("UAT")) {
				InputStream ip = this.getClass().getClassLoader().getResourceAsStream("config_UAT.properties");
				//System.out.println(ip);
				prop.load(ip);
				if(value2.equals("ADMIN")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("admin_email");
					String config_password = prop.getProperty("admin_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("ACCOUNT HOLDER") || value2.equals("AH") ) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("ah_email");
					String config_password = prop.getProperty("ah_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else if(value2.equals("OPERATOR")) {
					String config_url = prop.getProperty("baseURL");
					String config_email = prop.getProperty("op_email");
					String config_password = prop.getProperty("op_password");
					env_url = config_url;
					env_email = config_email;
					env_password = config_password;
				}
				else {
					System.out.println("Invalid Roles");
				}
			}
			else {
				System.out.println("You have entered invalid Environment name!!!");
			}
			

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*Class.forName("com.mysql.jdbc.Driver");	
	Connection conn = DriverManager.getConnection("jdbc:mysql://agjunction-dev.c6kwtjahqoig.us-west-1.rds.amazonaws.com:3306/test_agj_firmware","agjunction_dev","!CC0H:06$0~8|'j");
	System.out.println("test");
	Statement sta = conn.createStatement();
	String Sql = "select * from wcl_table_firmware";
	ResultSet rs = sta.executeQuery(Sql);
	while (rs.next()) {
		System.out.println(rs.getString("id"));
	}*/

}
