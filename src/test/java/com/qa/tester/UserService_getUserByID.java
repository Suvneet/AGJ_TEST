package com.qa.tester;

import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.testng.annotations.*;


public class UserService_getUserByID {
  @Test
  public void f() throws ClassNotFoundException, SQLException {
	  
	  	Class.forName("com.mysql.jdbc.Driver");	
		Connection conn = DriverManager.getConnection("jdbc:mysql://agjunction-dev.c6kwtjahqoig.us-west-1.rds.amazonaws.com:3306/test_agj_firmware","agjunction_dev","!CC0H:06$0~8|'j");
		System.out.println("test");
		java.sql.Statement sta = conn.createStatement();
		String Sql = "select * from wcl_table_firmware";
		ResultSet rs = sta.executeQuery(Sql);
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		//System.out.println("Row count is "+row_count);
		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner = new ArrayList<String>();
		int row_count = 0;
		while (rs.next()) {
		    for(int i=1; i<=columnsNumber; i++){
		        inner.add(rs.getString(i));
		        row_count++;
		    }                   
		}
		outer.add(inner);
		System.out.println("Row count is "+row_count);
  }
  		
  
}
