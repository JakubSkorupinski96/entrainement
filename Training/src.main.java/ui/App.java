package ui;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import dao.CompanyDAO;
import dao.ComputerDAO;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices;

public class App {
	private final static String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	private final static String USER = "root";
	private final static String PASS = "";
	
	//Expression regex pour les dates
	private final static String REGEX_DATE = "\\d{4}-[0-1][0-9]-[0-3][0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]";
	
	private static CompanyServices companyServices = CompanyServices.getInstance();
	private static ComputerServices computerServices = ComputerServices.getInstance();
	
	/**
	 * Renvoie différentes méthodes par rapport aux caractères entrées
	 * 
	 * @param conn : Connexion a la BDD
	 * @param scan : chaine de caractères entrées par l'utilisateur
	 */
	
	private static void interfaceCases(Connection conn, Scanner scan) {
		switch(scan.next()) {
			case "exit":
				System.out.println("Au revoir");
				System.exit(0);
				break;
			case "showCompanies":
				companyServices.listCompanies(conn);
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
				computerServices.listComputers(conn);
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
		while (!current_scan.equals("end") || scan.hasNext("end")) {
			name += current_scan + " ";
			current_scan = scan.next();
		}
		return name;
	}
	
	
	/**
	 * Cree un nouvel ordinateur avec les donnees entrees
	 * 
	 * @param conn : Connexion a la BDD
	 */
	public static void createPcUserInterface(Connection conn) {
		String introStr = "";
		String decStr = "";
		System.out.println("Entrer le nom du nouvel ordinateur:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		do {
			System.out.println("Entrer la date de création de l'ordinateur:");
			Scanner introScan = new Scanner(System.in);
			introStr = introScan.nextLine();
			if(!introStr.matches(REGEX_DATE)) {
				System.out.println("Mauvais format de date, veuillez reessayer");
			}
		}while(!introStr.matches(REGEX_DATE));
		do {
			System.out.println("Entrer la date d'arret de production de l'ordinateur:");
			Scanner decScan = new Scanner(System.in);
			decStr = decScan.nextLine();
			if(!introStr.matches(REGEX_DATE)) {
				System.out.println("Mauvais format de date, veuillez reessayer");
			}
		}while(!introStr.matches(REGEX_DATE));
		System.out.println("Entrer l'id de la companie:");
		Scanner idScan = new Scanner(System.in);
		String idStr = idScan.nextLine();
		computerServices.createComputer(conn,nameStr,introStr,decStr,Integer.parseInt(idStr));
	}
	
	/**
	 * Mets a jour un ordinateur dans la BDD a partir du nom 
	 * 
	 * @param conn : Connexion a la BDD
	 */
	
	public static void updatePcUserInterface(Connection conn) {
		String introStr = "";
		String decStr = "";
		System.out.println("Entrer le nom de l'ordinateur à mofifier:");
		Scanner oldNameScan = new Scanner(System.in);
		String oldNameStr = oldNameScan.nextLine();
		System.out.println("Entrer le nouveau nom de l'ordinateur:");
		Scanner newNameScan = new Scanner(System.in);
		String newNameStr = newNameScan.nextLine();
		do {
			System.out.println("Entrer la nouvelle date de creation de l'ordinateur:");
			Scanner introScan = new Scanner(System.in);
			introStr = introScan.nextLine();
			if(!introStr.matches(REGEX_DATE)) {
				System.out.println("Mauvais format de date, veuillez reessayer");
			}
		}while(!introStr.matches(REGEX_DATE));
		do {
			System.out.println("Entrer la nouvelle date d'arret de production de l'ordinateur:");
			Scanner decScan = new Scanner(System.in);
			decStr = decScan.nextLine();
			if(!decStr.matches(REGEX_DATE)) {
				System.out.println("Mauvais format de date, veuillez reessayer");
			}
		}while(!decStr.matches(REGEX_DATE));
		System.out.println("Entrer le nouvel id de la companie:");
		Scanner idScan = new Scanner(System.in);
		String idStr = idScan.nextLine();
		computerServices.updateComputer(conn,oldNameStr,newNameStr,introStr,decStr,Integer.parseInt(idStr));
	}
	
	/**
	 * Supprime un ordinateur dans la BDD a partir de son nom
	 * 
	 * @param conn : Connexion a la BDD
	 */
	
	public static void deletePcUserInterface(Connection conn) {
		//System.out.println("quelle critere? (name, date_intro, date_dis, companyId):");
		//Scanner response = new Scanner(System.in);
		//String query = response.nextLine();
		System.out.println("Entrer le nom de l'ordinateur à supprimer:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		computerServices.deleteComputer(conn, nameStr);
	}
	
	
	/**
	 * Affiche les détails d'un ordinateur dans la BDD à partir de son nom
	 * 
	 * @param conn : Connexion a la BDD
	 */
	
	public static void showPcDetailsUserInterface(Connection conn) {
		System.out.println("Entrer le nom de l'ordinateur:");
		Scanner nameScan = new Scanner(System.in);
		String nameStr = nameScan.nextLine();
		computerServices.showComputer(conn, nameStr);
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
