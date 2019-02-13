package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Company;

public class CompanyDAO {
	
	public static List<Company> listCompanies(Connection conn) {
		List<Company>companies = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM company");
			while (rs.next()) {
				Company company = Company.getInstance();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				company.setId(id);
				company.setName(name);
				System.out.println(company.toString());
				companies.add(company);
				}
		} catch (SQLException e) {
			System.out.println("company list error");
			e.printStackTrace();
		}
		return companies;
	}
}
