package org.project.jobportal;

import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
	public static void main(String[] args) throws Exception {
		System.err.println("\t\t\t\t\t\t WELCOME");
		HomePage homepg = new HomePage();
		DatabaseProvider database = new DatabaseProvider();
		
		try {
			database.connect();
			database.init();
			homepg.loginDetails();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
/*	finally {
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
				e.printStackTrace();
				}
			}
		}
		if(pstmt!=null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}*/
	}
}
