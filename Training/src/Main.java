import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import dao.Computer;



public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String URL = "jdbc:mysql://localhost:3306/computer-database-db";
		String USER = "root";
		String PASS = "";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("First catch");
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT name FROM computer WHERE company_id = 1");
			while (rs.next()) {
				  String name = rs.getString("name");
				  System.out.println(name + "\n");
				}
			//dao.Computer.createComputer(conn,"IBM 5100",null,null,1);
			dao.Computer.deleteComputer(conn,"IBM 5100");
			dao.Computer.listDetails(conn, "IBM 5100");
		} catch (SQLException e) {
			System.out.println("lol");
			e.printStackTrace();
		}
		
	}

}
