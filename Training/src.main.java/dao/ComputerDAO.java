package dao;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import model.Computer;

public class ComputerDAO {

  private static ComputerDAO instance;
  private Connection conn;

  private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

  private static final String INSERT_COMPUTER = "INSERT INTO computer (NAME,INTRODUCED,DISCONTINUED,COMPANY_ID) VALUES (?,?,?,?)";
  private static final String DELETE_BY_NAME = "DELETE FROM computer WHERE NAME = ?";
  private static final String SELECT_COMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name = ?";
  private static final String UPDATE_BY_NAME = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE name = ?";
  private static final String SELECT_COMPUTERS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
      + "computer.company_id, company.id, company.name FROM computer computer LEFT JOIN company company ON computer.company_id = company.id ";
  private static final String SEARCH_COMPUTERS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued,"
      + "computer.company_id, company.id, company.name FROM computer computer LEFT JOIN company company ON computer.company_id = company.id"
      + " WHERE company.name LIKE ?";
  private static final String COUNT_ALL = "SELECT COUNT(id) AS computers FROM computer";
  private static final String COUNT_SEARCH = "SELECT COUNT(computer.id) AS computers FROM computer computer LEFT JOIN company company ON "
      + "computer.company_id = company.id WHERE company.name LIKE ?";
  //private static final String ORDER_BY_ID_DESCENDING = "";    //TODO: kete

  /**
   * . Constructeur vide du DAO de computer
   */

  private ComputerDAO() {
    this.conn = DAOFactory.getInstance().getConnection();
  }

  /**
   * . instancie le singleton ComputerDAO
   *
   * @return instance
   */
  public static ComputerDAO getInstance() {
    if (instance == null) {
      instance = new ComputerDAO();
    }
    return instance;
  }

  /**
   * . Crée un ordinateur dans la BDD
   *
   * @param name   : nom de l'ordinateur
   * @param intro  : date d'introduction de l'ordinateur
   * @param discon : date d'arrêt de production de l'ordinateur
   * @param compId : id de la companie
   */

  public void createComputer(String name, String intro, String discon, int compId) {
    String insert = INSERT_COMPUTER;
    try {
      PreparedStatement preparedS = this.conn.prepareStatement(insert);
      preparedS.setString(1, name);
      preparedS.setTimestamp(2, Timestamp.valueOf(intro));
      preparedS.setTimestamp(3, Timestamp.valueOf(discon));
      preparedS.setInt(4, compId);
      preparedS.executeUpdate();

      System.out.println("OK");
    } catch (SQLException e) {
      logger.error("erreur de création");
      e.printStackTrace();
    }
  }

  /**
   * . Supprime un ordinateur dans la BDD à partir de son nom
   *
   * @param name : nom de l'ordinateur
   */

  public void deleteComputer(String name) {
    String delete = DELETE_BY_NAME;
    try {
      PreparedStatement preparedS = this.conn.prepareStatement(delete);
      preparedS.setString(1, name);
      preparedS.executeUpdate();
      System.out.println("Deleted");
    } catch (SQLException e) {
      logger.error("erreur de suppression");
      e.printStackTrace();
    }
  }

  /**
   * . Liste les details d'un ordinateur à l'aide de son nom
   *
   * @param name : nom de l'ordinateur
   */

  public void listDetails(String name) {
    String select = SELECT_COMPUTER;
    try {
      PreparedStatement preparedS = this.conn.prepareStatement(select);
      preparedS.setString(1, name);
      ResultSet rs = preparedS.executeQuery();
      while (rs.next()) {
        String pcName = rs.getString("name");
        Date dateIntro = rs.getDate(3);
        Date dateDiscon = rs.getDate(4);
        int compId = rs.getInt(5);
        System.out.println(pcName + " " + dateIntro + " " + dateDiscon + " " + compId);
      }
    } catch (SQLException e) {
      System.out.println("Fatal Error: Select");
      logger.error("erreur de sélection");
      e.printStackTrace();
    }
  }

  /**
   * . Met à jour un ordinateur dans la BDD à travers son nom
   *
   * @param name         : nom de l'ordinateur
   * @param newName      : nouveau nom
   * @param newIntro     : nouvelle date d'introduction
   * @param newDiscon    : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvelle id de companie
   */
  public void updatePC(String name, String newName, String newIntro, String newDiscon,
      int newCompanyId) {
    String update = UPDATE_BY_NAME;
    try {

      PreparedStatement preparedS = this.conn.prepareStatement(update);
      preparedS.setString(1, newName);
      preparedS.setTimestamp(2, Timestamp.valueOf(newIntro));
      preparedS.setTimestamp(3, Timestamp.valueOf(newDiscon));
      preparedS.setInt(4, newCompanyId);
      preparedS.setString(5, name);
      preparedS.executeUpdate();

      System.out.println("updated");
    } catch (SQLException e) {
      logger.error("erreur de mise à jour");
      e.printStackTrace();
    }
  }

  /**
   * .
   *
   * Retourne la liste des ordinateurs dans la BDD
   *
   * @param page : la page sélectionné
   *
   * @return List<Computer>
   */

  public List<Computer> listComputers(int page) {
    List<Computer> computers = new ArrayList<>();
    Statement stmt;
    try {
      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery(SELECT_COMPUTERS + " Limit " + (page - 1) * 25 + ", " + 25);
      while (rs.next()) {
        Company company = new Company();
        Computer computer = new Computer();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Date introDate = rs.getDate("introduced");
        Date discontinuedDate = rs.getDate("discontinued");
        int companyId = rs.getInt("company_id");
        String companyName = rs.getString("company.name");
        company.setId(companyId);
        company.setName(companyName);
        computer.setId(id);
        computer.setName(name);
        computer.setIntroduced(introDate);
        computer.setDiscontinued(discontinuedDate);
        computer.setCompany(company);
        computer.setCompanyName(companyName);
        System.out.println(computer.toString());
        computers.add(computer);
      }
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    return computers;
  }

  /**
   * . Liste tous les ordinateurs
   *
   * @return List<Computer>
   */

  public List<Computer> listAllComputers() {
    List<Computer> computers = new ArrayList<>();
    Statement stmt;
    try {
      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery(SELECT_COMPUTERS);
      while (rs.next()) {
        Company company = new Company();
        Computer computer = new Computer();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Date introDate = rs.getDate("introduced");
        Date discontinuedDate = rs.getDate("discontinued");
        int companyId = rs.getInt("company_id");
        String companyName = rs.getString("company.name");
        company.setId(companyId);
        company.setName(companyName);
        computer.setId(id);
        computer.setName(name);
        computer.setIntroduced(introDate);
        computer.setDiscontinued(discontinuedDate);
        computer.setCompany(company);
        computer.setCompanyName(companyName);
        computers.add(computer);
      }
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    return computers;
  }

  /**
   * . Searches for all computer from said company
   *
   * @param name : the company's
   *
   * @return List<Computer>
   */

  public List<Computer> searchComputer(String name) {
    List<Computer> computers = new ArrayList<>();
    String search = SEARCH_COMPUTERS;
    try {
      PreparedStatement preparedS = this.conn.prepareStatement(search);
      preparedS.setString(1, "%" + name + "%");
      System.out.println("stat" + preparedS);
      ResultSet rs = preparedS.executeQuery();
      System.out.println(rs);
      System.out.println("stat" + preparedS);
      while (rs.next()) {
        Company company = new Company();
        Computer computer = new Computer();
        int id = rs.getInt("id");
        String computerName = rs.getString("name");
        Date introDate = rs.getDate("introduced");
        Date discontinuedDate = rs.getDate("discontinued");
        int companyId = rs.getInt("company_id");
        String companyName = rs.getString("company.name");
        company.setId(companyId);
        company.setName(companyName);
        computer.setId(id);
        computer.setName(computerName);
        computer.setIntroduced(introDate);
        computer.setDiscontinued(discontinuedDate);
        computer.setCompany(company);
        computer.setCompanyName(companyName);
        computers.add(computer);
      }
    } catch (SQLException e) {
      logger.error("search error");
      e.printStackTrace();
    }
    return computers;
  }

  /**
   * . Returns the number of computers in the BD
   *
   * @return String
   */

  public String countAll() {
    String count = "";
    Statement stmt;
    try {
      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery(COUNT_ALL);
      rs.next();
      count += rs.getString("computers");
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    System.out.println(count);
    return count;
  }

  /**
   * . Returns the number of computers matching search criterias
   *
   * @param name : company name
   *
   * @return String
   */

  public String countSearch(String name) {
    String count = "";
    try {
      PreparedStatement preparedS = this.conn.prepareStatement(COUNT_SEARCH);
      preparedS.setString(1, "%" + name + "%");
      ResultSet rs = preparedS.executeQuery();
      rs.next();
      count += rs.getString("computers");
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    System.out.println(count);
    return count;
  }
}
