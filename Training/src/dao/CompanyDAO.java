package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyDAO {

	int id;
	String name;
	
	public CompanyDAO(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public static void listCompanies(Connection conn) {
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM company");
			while (rs.next()) {
				  String name = rs.getString("name");
				  System.out.println(name + "\n");
				}
		} catch (SQLException e) {
			System.out.println("company list error");
			e.printStackTrace();
		}
	}
}
