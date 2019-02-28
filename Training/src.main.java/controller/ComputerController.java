package controller;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import model.Computer;
import services.ComputerServices;

public class ComputerController {

  private static ComputerController instance;

  //private static Logger logger = LoggerFactory.getLogger(ComputerController.class);

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
   * @param page : la page sélectionné
   * @return List<Computer>
   */
  public List<Computer> list(int page) {
    return computerServices.listComputers(page);
  }

  /**
   * . Crée un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   * @param intro : date d'introduction
   * @param discon : date d'arrêt de production
   * @param compId : id de la companie
   */
  public void create(String name, String intro, String discon, int compId) {
    computerServices.createComputer(name, intro, discon, compId);
  }

  /**
   * . Met à jour un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   * @param newName : nouveau nom de l'ordinateur
   * @param newIntro : nouvelle date d'introduction
   * @param newDiscon : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvel id de companie
   */
  public void update(String name, String newName, String newIntro,
      String newDiscon, int newCompanyId) {
    computerServices.updateComputer(name, newName, newIntro, newDiscon, newCompanyId);
  }

  /**
   * . Supprime un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   */
  public void delete(String name) {
    computerServices.deleteComputer(name);
  }

  /**
   * . Affiche les détails d'un ordinateur dans la BDD
   *
   * @param name : le nom de l'ordinateur
   */
  public void show(String name) {
    computerServices.showComputer(name);
  }

  /**
   * . Liste tous les ordinateurs
   *
   * @return List<Computer>
   */

  public List<Computer> listAll() {
    return computerServices.listAllComputers();
  }

  /**
   * . Searches for all computer from said company
   *
   * @param name : the company's
   *
   * @return List<Computer>
   */
  public List<Computer> search(String name) {
    return computerServices.searchComputers(name);
  }

  /**
   * . Returns the number of computers in the BD
   *
   * @return String
   */

  public String countAll() {
    return computerServices.countAll();
  }

  /**
   * . Returns the number of computers matching search criterias
   *
   * @param name : company name
   *
   * @return String
   */

  public String countSearch(String name) {
    return computerServices.countSearch(name);
  }
}
