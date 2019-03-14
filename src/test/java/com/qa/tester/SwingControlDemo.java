package com.qa.tester;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class SwingControlDemo {
	
	public static String entree;
	public static String entree2;
	public static void main(String[] args) {
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
	    
	    //Component parent = null;
		int option = JOptionPane.showConfirmDialog(content, message, "Enter all your values", JOptionPane.OK_CANCEL_OPTION);
		if (option == JOptionPane.OK_OPTION)
		{
		    String value1 = entree.toUpperCase();
		    String value2 = entree2.toUpperCase();
		    System.out.println(value1);
		    System.out.println(value2);	    
		}

	
	   
  }

}
