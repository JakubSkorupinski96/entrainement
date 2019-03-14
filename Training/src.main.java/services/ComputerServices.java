package services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.ComputerDAO;
import dao.ComputerJDBCTemplate;
import dto.ComputerDTO;
import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;
import mapper.ComputerMapper;
import model.Computer;
import validator.ComputerValidator;

@Service("computerServices")
public class ComputerServices {

  private static ComputerServices instance;

  @Autowired
  ComputerDAO computerDAO;
  
  @Autowired
  ComputerJDBCTemplate ComputerJdbc;
  
  private ComputerValidator computerValidator = new ComputerValidator();

  /**
   * . Constructeu vide des services de computer
   */

  @Autowired
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
   * @param page : la page sélectionné
   *
   * @return List<Computer>
   */
  public ArrayList<Computer> listComputers(int page) {
    return computerDAO.listComputers(page);
  }

  /**
   * . Crée un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   * @param intro : date d'introduction
   * @param discon : date d'arrêt de production
   * @param compId : id de la companie
   * @throws ComputerNameException 
   * @throws ComputerDateCoherenceException 
   */

  public void createComputer(String name, String intro, String discon,
      int compId) throws ComputerNameException, ComputerDateCoherenceException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    computerValidator.validateComputerName(name);
    computerValidator.validateComputerDates(LocalDate.parse(intro, formatter), LocalDate.parse(discon, formatter));
    //computerDAO.createComputer(name, intro, discon, compId);
    ComputerJdbc.create(name, intro, discon, compId);
  }

  /**
   * . Met à jour un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   * @param newName : nouveau nom de l'ordinateur
   * @param newIntro : nouvelle date d'introduction
   * @param newDiscon : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvel id de companie
   * @throws ComputerNameException 
   * @throws ComputerDateCoherenceException 
   */

  public void updateComputer(String name, String newName, String newIntro,
      String newDiscon, int newCompanyId) throws ComputerNameException, ComputerDateCoherenceException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    computerValidator.validateComputerName(name);
    computerValidator.validateComputerDates(LocalDate.parse(newIntro, formatter), LocalDate.parse(newDiscon, formatter));
    //computerDAO.updatePC(name, newName, newIntro, newDiscon, newCompanyId);
    ComputerJdbc.update(newName, newIntro, newDiscon, newCompanyId, name);
  }

  /**
   * . Supprime un ordinateur dans la BDD
   *
   * @param name : nom de l'ordinateur
   */

  public void deleteComputer(String name) {
    //computerDAO.deleteComputer(name);
    ComputerJdbc.delete(name);
  }

  /**
   * . Affiche les détails d'un ordinateur dans la BDD
   *
   * @param name : le nom de l'ordinateur
   */

  public void showComputer(String name) {
    //computerDAO.listDetails(name);
    ComputerJdbc.show(name);
  }

  /**
   *. Liste touts les ordinateurs
   *
   * @return ArrayList<Computer>
   */

  public List<Computer> listAllComputers() {
    return ComputerJdbc.listComputers();
  }

  /**
   * . Searches for all computer from said company
   *
   * @param name : the company's
   *
   * @return List<Computer>
   */

  public List<Computer> searchComputers(String name) {
    return ComputerJdbc.listSearched(name);
  }

  /**
   * . Returns the number of computers in the BD
   *
   * @return String
   */

  public int countAll() {
    //return computerDAO.countAll();
    return ComputerJdbc.countAll();
  }
  /**
   * . Returns the number of computers matching search criterias
   *
   * @param name : company name
   *
   * @return String
   */

  public int countSearch(String name) {
    return ComputerJdbc.countSearched(name);
  }
}
