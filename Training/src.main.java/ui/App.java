package ui;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static Logger logger = LoggerFactory.getLogger(App.class);
	
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
				break;
			case "deletePC":
				deletePcUserInterface(conn);
				break;
			case "updatePC":
				updatePcUserInterface(conn);
				break;
			case "showComputers":
				computerServices.listComputers(conn);
				break;
			case "showPCDetails":
				showPcDetailsUserInterface(conn);
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
				logger.error("Mauvais format de date, veuillez reessayer");
			}
		}while(!introStr.matches(REGEX_DATE));
		do {
			System.out.println("Entrer la date d'arret de production de l'ordinateur:");
			Scanner decScan = new Scanner(System.in);
			decStr = decScan.nextLine();
			if(!decStr.matches(REGEX_DATE)) {
				logger.error("Mauvais format de date, veuillez reessayer");
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
				logger.error("Mauvais format de date, veuillez reessayer");
			}
		}while(!introStr.matches(REGEX_DATE));
		do {
			System.out.println("Entrer la nouvelle date d'arret de production de l'ordinateur:");
			Scanner decScan = new Scanner(System.in);
			decStr = decScan.nextLine();
			if(!decStr.matches(REGEX_DATE)) {
				logger.error("Mauvais format de date, veuillez reessayer");
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
			logger.error("JDBC error");
			e1.printStackTrace();
		}
		
		try {
			Connection conn = DriverManager.getConnection(URL, USER, PASS);
			System.out.println("Bonjour utilisateur");
			while (true) {
				logger.info("apllication démarre");
				System.out.println("Entrer votre commande:");
				Scanner in = new Scanner(System.in);  
				interfaceCases(conn,in);
				//System.in.read();
			}
		} catch (SQLException e) {
			logger.error("connection error");
			e.printStackTrace();
		}
		
	}
}
