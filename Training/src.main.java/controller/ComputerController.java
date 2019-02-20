package controller;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Computer;
import services.ComputerServices;

public class ComputerController {

  private static ComputerController instance;

  private static Logger logger = LoggerFactory.getLogger(ComputerController.class);

  ComputerServices computerServices = ComputerServices.getInstance();

  /**
   * . Constructeur vide du controlleur de computer
   */

  private ComputerController() {
  }

  /**
   * . Instancie le singleton computeurController
   *
   * @return instance
   */
  public static ComputerController getInstance() {
    if (instance == null) {
      instance = new ComputerController();
    }
    return instance;
  }

  /**
   * . Affiche la liste des ordinateurs dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param page : la page sélectionné
   * @return List<Computer>
   */
  public List<Computer> list(Connection conn, int page) {
    return computerServices.listComputers(conn, page);
  }

  /**
   * . Crée un ordinateur dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param name : nom de l'ordinateur
   * @param intro : date d'introduction
   * @param discon : date d'arrêt de production
   * @param compId : id de la companie
   */
  public void create(Connection conn, String name, String intro, String discon, int compId) {
    computerServices.createComputer(conn, name, intro, discon, compId);
  }

  /**
   * . Met à jour un ordinateur dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param name : nom de l'ordinateur
   * @param newName : nouveau nom de l'ordinateur
   * @param newIntro : nouvelle date d'introduction
   * @param newDiscon : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvel id de companie
   */
  public void update(Connection conn, String name, String newName, String newIntro,
      String newDiscon, int newCompanyId) {
    computerServices.updateComputer(conn, name, newName, newIntro, newDiscon, newCompanyId);
  }

  /**
   * . Supprime un ordinateur dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param name : nom de l'ordinateur
   */
  public void delete(Connection conn, String name) {
    computerServices.deleteComputer(conn, name);
  }

  /**
   * . Affiche les détails d'un ordinateur dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param name : le nom de l'ordinateur
   */
  public void show(Connection conn, String name) {
    computerServices.showComputer(conn, name);
  }
}
