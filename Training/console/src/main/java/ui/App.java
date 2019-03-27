package ui;

import java.sql.SQLException;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CompanyController;
import controller.ComputerController;
import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;

public class App {

  // Expression regex pour les dates
  private static final String REGEX_DATE = "\\d{4}-[0-1][0-9]-[0-3][0-9]\\s[0-2][0-9]:[0-5][0-9]:[0-5][0-9]";

  private static CompanyController companyController = CompanyController.getInstance();
  private static ComputerController computerController = ComputerController.getInstance();

  private static Logger logger = LoggerFactory.getLogger(App.class);

  /**
   * . Renvoie diff�rentes m�thodes par rapport aux caract�res entr�es
   *
   * @param scan : chaine de caract�res entr�es par l'utilisateur
   */

  private static void interfaceCases(Scanner scan) {
    switch (scan.next()) {
    case "exit":
      System.out.println("Au revoir");
      System.exit(0);
      break;
    case "showCompanies":
      System.out.println("Quelle page?:");
      Scanner companyPageScan = new Scanner(System.in);
      int companyPage = Integer.parseInt(companyPageScan.nextLine());
      //companyController.listCompanies(companyPage);
      break;
    case "createPC":
      createPcUserInterface();
      break;
    case "deletePC":
      deletePcUserInterface();
      break;
    case "updatePC":
      updatePcUserInterface();
      break;
    case "showComputers":
      System.out.println("Quelle page?:");
      Scanner computerPageScan = new Scanner(System.in);
      int computerPage = Integer.parseInt(computerPageScan.nextLine());
      //computerController.list(computerPage);
      break;
    case "showPCDetails":
      showPcDetailsUserInterface();
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
    case "deleteCompany":
      System.out.println("Which company?:");
      Scanner companyDelete = new Scanner(System.in);
      try {
        companyController.deleteCompany(companyDelete.next());
      } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
      break;
    default:
      System.out.println("Invalid command");
    }
  }

  /**
   * . Cree un nouvel ordinateur avec les donnees entrees
   *
   */
  public static void createPcUserInterface() {
    String introStr = "";
    String decStr = "";
    System.out.println("Entrer le nom du nouvel ordinateur:");
    Scanner nameScan = new Scanner(System.in);
    String nameStr = nameScan.nextLine();
    nameScan.close();
    do {
      System.out.println("Entrer la date de cr�ation de l'ordinateur:");
      Scanner introScan = new Scanner(System.in);
      introStr = introScan.nextLine();
      if (!introStr.matches(REGEX_DATE)) {
        logger.error("Mauvais format de date, veuillez reessayer");
      }
      introScan.close();
    } while (!introStr.matches(REGEX_DATE));
    do {
      System.out.println("Entrer la date d'arret de production de l'ordinateur:");
      Scanner decScan = new Scanner(System.in);
      decStr = decScan.nextLine();
      if (!decStr.matches(REGEX_DATE)) {
        logger.error("Mauvais format de date, veuillez reessayer");
      }
      decScan.close();
    } while (!introStr.matches(REGEX_DATE));
    System.out.println("Entrer l'id de la companie:");
    Scanner idScan = new Scanner(System.in);
    String idStr = idScan.nextLine();
    idScan.close();
    try {
      computerController.create(nameStr, introStr, decStr, Integer.parseInt(idStr));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ComputerNameException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ComputerDateCoherenceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * . Mets a jour un ordinateur dans la BDD a partir du nom
   *
   */

  public static void updatePcUserInterface() {
    String introStr = "";
    String decStr = "";
    System.out.println("Entrer le nom de l'ordinateur � mofifier:");
    Scanner oldNameScan = new Scanner(System.in);
    String oldNameStr = oldNameScan.nextLine();
    oldNameScan.close();
    System.out.println("Entrer le nouveau nom de l'ordinateur:");
    Scanner newNameScan = new Scanner(System.in);
    String newNameStr = newNameScan.nextLine();
    newNameScan.close();
    do {
      System.out.println("Entrer la nouvelle date de creation de l'ordinateur:");
      Scanner introScan = new Scanner(System.in);
      introStr = introScan.nextLine();
      if (!introStr.matches(REGEX_DATE)) {
        logger.error("Mauvais format de date, veuillez reessayer");
      }
      introScan.close();
    } while (!introStr.matches(REGEX_DATE));
    do {
      System.out.println("Entrer la nouvelle date d'arret de production de l'ordinateur:");
      Scanner decScan = new Scanner(System.in);
      decStr = decScan.nextLine();
      if (!decStr.matches(REGEX_DATE)) {
        logger.error("Mauvais format de date, veuillez reessayer");
      }
      decScan.close();
    } while (!decStr.matches(REGEX_DATE));
    System.out.println("Entrer le nouvel id de la companie:");
    Scanner idScan = new Scanner(System.in);
    String idStr = idScan.nextLine();
    idScan.close();
    try {
      computerController.update(oldNameStr, newNameStr, introStr, decStr,
          Integer.parseInt(idStr));
    } catch (NumberFormatException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ComputerNameException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ComputerDateCoherenceException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  /**
   * . Supprime un ordinateur dans la BDD a partir de son nom
   *
   */

  public static void deletePcUserInterface() {
    System.out.println("Entrer le nom de l'ordinateur � supprimer:");
    Scanner nameScan = new Scanner(System.in);
    String nameStr = nameScan.nextLine();
    nameScan.close();
    computerController.delete(nameStr);
  }

  /**
   * . Affiche les d�tails d'un ordinateur dans la BDD � partir de son nom
   *
   */

  public static void showPcDetailsUserInterface() {
    System.out.println("Entrer le nom de l'ordinateur:");
    Scanner nameScan = new Scanner(System.in);
    String nameStr = nameScan.nextLine();
    nameScan.close();
    computerController.show(nameStr);
  }

  /**
   * . Lancement de l'app
   *
   * @param args : arguments �ventuels
   */

  public static void main(String[] args) {
      System.out.println("Bonjour utilisateur");
      while (true) {
        logger.info("apllication d�marre");
        System.out.println("Entrer votre commande:");
        Scanner in = new Scanner(System.in);
        interfaceCases(in);
      }


  }
}
