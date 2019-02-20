package services;

import java.sql.Connection;
import java.util.List;

import dao.ComputerDAO;
import model.Computer;

public class ComputerServices {

  private static ComputerServices instance;

  ComputerDAO computerDAO = ComputerDAO.getInstance();

  /**
   * . Constructeu vide des services de computer
   */

  private ComputerServices() {
  }

  /**
   * . Instancie le singleton ComputerServices
   *
   * @return instance
   */
  public static ComputerServices getInstance() {
    if (instance == null) {
      instance = new ComputerServices();
    }
    return instance;
  }

  /**
   * . Affiche la liste des ordinateur dans la BDD
   *
   * @param conn : la connexion � la BDD
   * @param page : la page s�lectionn�
   *
   * @return List<Computer>
   */
  public List<Computer> listComputers(Connection conn, int page) {
    return ComputerDAO.listComputers(conn, page);
  }

  /**
   * . Cr�e un ordinateur dans la BDD
   *
   * @param conn : la connexion � la BDD
   * @param name : nom de l'ordinateur
   * @param intro : date d'introduction
   * @param discon : date d'arr�t de production
   * @param compId : id de la companie
   */

  public void createComputer(Connection conn, String name, String intro, String discon,
      int compId) {
    ComputerDAO.createComputer(conn, name, intro, discon, compId);
  }

  /**
   * . Met � jour un ordinateur dans la BDD
   *
   * @param conn : la connexion � la BDD
   * @param name : nom de l'ordinateur
   * @param newName : nouveau nom de l'ordinateur
   * @param newIntro : nouvelle date d'introduction
   * @param newDiscon : nouvelle date d'arr�t de production
   * @param newCompanyId : nouvel id de companie
   */

  public void updateComputer(Connection conn, String name, String newName, String newIntro,
      String newDiscon, int newCompanyId) {
    ComputerDAO.updatePC(conn, name, newName, newIntro, newDiscon, newCompanyId);
  }

  /**
   * . Supprime un ordinateur dans la BDD
   *
   * @param conn : la connexion � la BDD
   * @param name : nom de l'ordinateur
   */

  public void deleteComputer(Connection conn, String name) {
    ComputerDAO.deleteComputer(conn, name);
  }

  /**
   * . Affiche les d�tails d'un ordinateur dans la BDD
   *
   * @param conn : la connexion � la BDD
   * @param name : le nom de l'ordinateur
   */

  public void showComputer(Connection conn, String name) {
    ComputerDAO.listDetails(conn, name);
  }
}
