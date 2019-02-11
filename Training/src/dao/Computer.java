package dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Computer {

	public String name;
	public Date intro_date;
	public Date discontinued_date;
	public Company company;
	
	public Computer(String name, Company company) {
		this.name = name;
		this.company = company;
	}
	
	public static void createComputer (Connection conn, String name, Date intro, Date discon, int comp_id) {
		String insert = "INSERT INTO computer (NAME,INTRODUCED,DISCONTINUED,COMPANY_ID) VALUES (?,?,?,?)";
		try {
			PreparedStatement preparedS = conn.prepareStatement(insert);
			preparedS.setString(1, name);
			preparedS.setDate(2, intro);
			preparedS.setDate(3, discon);
			preparedS.setInt(4, comp_id);
			preparedS.executeUpdate();

			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println("Fatal Error");
			e.printStackTrace();
		}
	}
	
	//work?
	public static void deleteComputer (Connection conn, String name) {
		String delete = "delete from computer where NAME = ?";
		try {
			PreparedStatement preparedS = conn.prepareStatement(delete);
			preparedS.setString(1, name);
			System.out.println("Deleted");
		} catch (SQLException e) {
			System.out.println("Fatal Error");
			e.printStackTrace();
		}
	}
	
	public static void listDetails(Connection conn, String name) {
		String select = "SELECT * FROM computer WHERE name = ?";
		try {
			PreparedStatement preparedS = conn.prepareStatement(select);
			preparedS.setString(1, name);
			ResultSet rs = preparedS.executeQuery();
			while (rs.next()) {
				  String pcName = rs.getString("name");
				  Date date_intro = rs.getDate(3);
				  Date date_discon = rs.getDate(4);
				  int comp_id = rs.getInt(5);
				  System.out.println(name + " " + date_intro + " " + date_discon + " " + comp_id);
				}
		} catch (SQLException e) {
			System.out.println("Fatal Error: Select");
			e.printStackTrace();
		}
	}
}
