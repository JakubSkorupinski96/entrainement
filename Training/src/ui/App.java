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
				createPcUserInterface(conn);
				//String createName = returnPcName(scan);
				//dao.ComputerDAO.createComputer(conn,createName,null,null,Integer.parseInt(scan.next()));
				break;
			case "deletePC":
				deletePcUserInterface(conn);
				//String deleteName  = returnPcName(scan);
				//dao.ComputerDAO.deleteComputer(conn, deleteName);
				break;
			case "updatePC":
				updatePcUserInterface(conn);
				//String updateName = returnPcName(scan);
				//String NewUpdatename = returnPcName(scan);
				//dao.ComputerDAO.updatePC(conn,updateName,NewUpdatename,null,null,Integer.parseInt(scan.next()));
				break;
			case "showComputers":
				dao.ComputerDAO.listComputers(conn);
				break;
			case "showPCDetails":
				showPcDetailsUserInterface(conn);
				//String name = returnPcName(scan);
				//System.out.println("results for " + name + ":");
				//dao.ComputerDAO.listDetails(conn, name);
				break;
//			case "syntax":
//				System.out.println("[method] [args]");
//				System.out.println("createPC [Pc Name] [Pc Name] ... end [Company ID]");
//				System.out.println("deletePC [Pc Name] [Pc Name] ... end ");
//				System.out.println("showPCDetails [Pc Name] [Pc Name] ... end ");
//				System.out.println("updatePC [Pc Name] [Pc Name] ... end [new Pc Name] [new Pc Name] ... end [company_id]");
//				break;
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
		while (!current_scan.equals("end") || scan.hasNext("end")) {  //|| non nul
			name += current_scan + " ";
			current_scan = scan.next();
		}
		return name;
	}
	
	public static void createPcUserInterface(Connection conn) {
		System.out.println("Entrer le nom du nouvel ordinateur:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		System.out.println("Entrer la date de création de l'ordinateur:");
		Scanner introScan = new Scanner(System.in);
		String introStr = introScan.nextLine();
		System.out.println("Entrer la date d'arret de production de l'ordinateur:");
		Scanner decScan = new Scanner(System.in);
		String decStr = decScan.nextLine();
		System.out.println("Entrer l'id de la companie (peut être 'null'):");
		Scanner idScan = new Scanner(System.in);
		String idStr = decScan.nextLine();
		dao.ComputerDAO.createComputer(conn,nameStr,introStr,decStr,Integer.parseInt(idStr));
	}
	
	public static void updatePcUserInterface(Connection conn) {
		System.out.println("Entrer le nom de l'ordinateur à mofifier:");
		Scanner oldNameScan = new Scanner(System.in);
		String oldNameStr = oldNameScan.nextLine();
		System.out.println("Entrer le nouveau nom de l'ordinateur:");
		Scanner newNameScan = new Scanner(System.in);
		String newNameStr = newNameScan.nextLine();
		System.out.println("Entrer la nouvelle date de creation de l'ordinateur:");
		Scanner introScan = new Scanner(System.in);
		String introStr = introScan.nextLine();
		System.out.println("Entrer la nouvelle date d'arret de production de l'ordinateur:");
		Scanner decScan = new Scanner(System.in);
		String decStr = decScan.nextLine();
		System.out.println("Entrer le nouvel id de la companie (peut être 'null'):");
		Scanner idScan = new Scanner(System.in);
		String idStr = decScan.nextLine();
		dao.ComputerDAO.updatePC(conn,oldNameStr,newNameStr,introStr,decStr,Integer.parseInt(idStr));
	}
	
	public static void deletePcUserInterface(Connection conn) {
		System.out.println("quelle critere? (name, date_intro, date_dis, companyId):");
		Scanner response = new Scanner(System.in);
		
		System.out.println("Entrer le nom de l'ordinateur à supprimer:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		dao.ComputerDAO.deleteComputer(conn, nameStr);
	}
	
	public static void deleteCases(String delete) {
		switch(delete) {
			case "name":
				
		}
			
	}
	public static void showPcDetailsUserInterface(Connection conn) {
		System.out.println("Entrer le nom de l'ordinateur:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		dao.ComputerDAO.listDetails(conn, nameStr);
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
