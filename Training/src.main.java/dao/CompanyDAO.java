package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;

public class CompanyDAO {
	
	private static CompanyDAO instance;
    
	private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
    private CompanyDAO(){
    	
    }
    
    public static CompanyDAO getInstance(){
        if(instance == null){
            instance = new CompanyDAO();
        }
        return instance;
    }
	
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
			logger.error("erreur de liste");
			e.printStackTrace();
		}
		return companies;
	}
}