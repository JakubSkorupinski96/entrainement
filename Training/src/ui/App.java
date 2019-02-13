package ui;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class App {
	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final static String USER = "root";
	private final static String PASS = "";
	
	
	private static void interfaceCases(Connection conn, Scanner scan) {
		switch(scan.next()) {
			case "exit":
				System.out.println("Au revoir");
				System.exit(0);
				break;
			case "showCompanies":
				dao.CompanyDAO.listCompanies(conn);
				break;
			case "createPC":
				String createName = returnPcName(scan);
				dao.ComputerDAO.createComputer(conn,createName,null,null,Integer.parseInt(scan.next()));
				break;
			case "deletePC":
				String deleteName  = returnPcName(scan);
				dao.ComputerDAO.deleteComputer(conn, deleteName);
				break;
			case "updatePC":
				String updateName = returnPcName(scan);
				String NewUpdatename = returnPcName(scan);
				dao.ComputerDAO.updatePC(conn,updateName,NewUpdatename,null,null,Integer.parseInt(scan.next()));
				break;
			case "showComputers":
				dao.ComputerDAO.listComputers(conn);
				break;
			case "showPCDetails":
				String name = returnPcName(scan);
				System.out.println("results for " + name + ":");
				dao.ComputerDAO.listDetails(conn, name);
				break;
			case "syntax":
				System.out.println("[method] [args]");
				System.out.println("createPC [Pc Name] [Pc Name] ... end [Company ID]");
				System.out.println("deletePC [Pc Name] [Pc Name] ... end ");
				System.out.println("showPCDetails [Pc Name] [Pc Name] ... end ");
				System.out.println("updatePC [Pc Name] [Pc Name] ... end [new Pc Name] [new Pc Name] ... end [company_id]");
				break;
			case "methods":
				System.out.println("showCompanies");
				System.out.println("showComputers");
				System.out.println("createPC");
				System.out.println("deletePC");
				System.out.println("updatePC");
				System.out.println("showPCDetails");
				System.out.println("exit");
				break;
			default:
				System.out.println("Invalid command");
		}
	}
	
	public static String returnPcName(Scanner scan) {
		String name = "";
		String current_scan = scan.next();
		int index = 0;
		
		System.out.println(current_scan);
		while (!current_scan.equals("end")) {  //|| non nul
			name += current_scan + " ";
			current_scan = scan.next();
		}
		return name;
	}
	
	public static void main(String[] args) {

		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("JDBC error");
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Bonjour utilisateur");
			while (true) {
				//dao.ComputerDAO.updatePC(conn, "IBN 5100", "IBM 5100", new Date(0), "1996-02-01 12:01:33", 9);
				System.out.println("Entrer votre commande:");
				Scanner in = new Scanner(System.in);  
				interfaceCases(conn,in);
				//System.in.read();
			}
		} catch (SQLException e) {
			System.out.println("connection error");
			e.printStackTrace();
		}
		
	}
}
