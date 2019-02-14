package dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Computer;

public class ComputerDAO {
		
	private static ComputerDAO instance;
    
    private ComputerDAO(){
    	
    }
    
    public static ComputerDAO getInstance(){
        if(instance == null){
            instance = new ComputerDAO();
        }
        return instance;
    }
	
	public static void createComputer (Connection conn, String name, String intro, String discon, int comp_id) {
		String insert = "INSERT INTO computer (NAME,INTRODUCED,DISCONTINUED,COMPANY_ID) VALUES (?,?,?,?)";
		try {		
			PreparedStatement preparedS = conn.prepareStatement(insert);
			preparedS.setString(1, name);
			preparedS.setTimestamp(2,Timestamp.valueOf(intro));
			preparedS.setTimestamp(3,Timestamp.valueOf(discon));
			preparedS.setInt(4, comp_id);
			preparedS.executeUpdate();

			System.out.println("OK");
		} catch (SQLException e) {
			System.out.println("Fatal Error");
			e.printStackTrace();
		}
	}
	
	//marche?
	public static void deleteComputer (Connection conn, String name) {
		String delete = "DELETE FROM computer WHERE NAME = ?";
		//String delete = "DELETE FROM computer WHERE ";
		//delete += query;
		try {
			//Statement stmt = conn.createStatement();
			PreparedStatement preparedS = conn.prepareStatement(delete);
			preparedS.setString(1, name);
			preparedS.executeUpdate();
			//stmt.executeUpdate(delete);
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
				  System.out.println(pcName + " " + date_intro + " " + date_discon + " " + comp_id);
				}
		} catch (SQLException e) {
			System.out.println("Fatal Error: Select");
			e.printStackTrace();
		}
	}
	
	public static void updatePC(Connection conn, String name, String newName, String newIntro, String newDiscon, int newCompanyId) {
		String update = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE name = ?";
		try {
			
			PreparedStatement preparedS = conn.prepareStatement(update);
			preparedS.setString(1, newName);
			preparedS.setTimestamp(2,Timestamp.valueOf(newIntro));
			preparedS.setTimestamp(3,Timestamp.valueOf(newDiscon));
			preparedS.setInt(4, newCompanyId);
			preparedS.setString(5, name);
			preparedS.executeUpdate();

			System.out.println("updated");
		} catch (SQLException e) {
			System.out.println("Update Error");
			e.printStackTrace();
		}
	}
	
	public static void listComputers(Connection conn) {
		List<Computer> computers = new ArrayList<>();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM computer");
			while (rs.next()) {
				Computer computer = Computer.getInstance();
				int id = rs.getInt("id");
				String name = rs.getString("name");
				Date intro_date = rs.getDate("introduced");
				Date discontinued_date = rs.getDate("discontinued");
				int companyId = rs.getInt("company_id");
				computer.setId(id);
				computer.setName(name);
				computer.setIntro_date(intro_date);
				computer.setDiscontinued_date(discontinued_date);
				computer.setCompany(companyId);
				System.out.println(computer.toString());
				computers.add(computer);
				}
		} catch (SQLException e) {
			System.out.println("computer list error");
			e.printStackTrace();
		}
	}
}
