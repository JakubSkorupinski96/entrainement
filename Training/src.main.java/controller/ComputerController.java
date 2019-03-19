package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import model.Computer;
import services.ComputerServices;

@Component("ComputerController")
public class ComputerController {

  private static ComputerController instance;

  @Autowired
  ComputerServices computerServices;

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

//  /**
//   * . Affiche la liste des ordinateurs dans la BDD
//   *
//   * @param page : la page sélectionné
//   * @return List<Computer>
//   */
//  public ArrayList<Computer> list(int page) {
//    return computerServices.listComputers(page);
//  }

  /**
   * . Crée un ordinateur dans la BDD
   *
   * @param name   : nom de l'ordinateur
   * @param intro  : date d'introduction
   * @param discon : date d'arrêt de production
   * @param compId : id de la companie
   * @throws ComputerNameException : name cannot be empty
   * @throws ComputerDateCoherenceException : introduction cannot be greater than discontinued
   */
  public void create(String name, String intro, String discon, int compId)
      throws ComputerNameException, ComputerDateCoherenceException {
    computerServices.createComputer(name, intro, discon, compId);
  }

  /**
   * . Met à jour un ordinateur dans la BDD
   *
   * @param name         : nom de l'ordinateur
   * @param newName      : nouveau nom de l'ordinateur
   * @param newIntro     : nouvelle date d'introduction
   * @param newDiscon    : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvel id de companie
   * @throws ComputerNameException : name cannot be empty
   * @throws ComputerDateCoherenceException : introduction cannot be greater than discontinued
   */
  public void update(String name, String newName, String newIntro, String newDiscon,
      int newCompanyId) throws ComputerNameException, ComputerDateCoherenceException {
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

  public int countAll() {
    return computerServices.countAll();
  }

  /**
   * . Returns the number of computers matching search criterias
   *
   * @param name : company name
   *
   * @return String
   */

  public int countSearch(String name) {
    return computerServices.countSearch(name);
  }
}
