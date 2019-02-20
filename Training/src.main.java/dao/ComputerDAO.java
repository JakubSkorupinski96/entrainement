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

import model.Computer;

public class ComputerDAO {

  private static ComputerDAO instance;

  private static Logger logger = LoggerFactory.getLogger(ComputerDAO.class);

  private static final String INSERT_COMPUTER = "INSERT INTO computer (NAME,INTRODUCED,DISCONTINUED,COMPANY_ID) VALUES (?,?,?,?)";
  private static final String DELETE_BY_NAME = "DELETE FROM computer WHERE NAME = ?";
  private static final String SELECT_COMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name = ?";
  private static final String UPDATE_BY_NAME = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE name = ?";
  private static final String SELECT_COMPUTERS = "SELECT id, name, introduced, discontinued, company_id FROM computer";

  /**
   * . Constructeur vide du DAO de computer
   */

  private ComputerDAO() {
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
   * @param conn   : la connexion à la BDD
   * @param name   : nom de l'ordinateur
   * @param intro  : date d'introduction de l'ordinateur
   * @param discon : date d'arrêt de production de l'ordinateur
   * @param compId : id de la companie
   */

  public static void createComputer(Connection conn, String name, String intro, String discon,
      int compId) {
    String insert = INSERT_COMPUTER;
    try {
      PreparedStatement preparedS = conn.prepareStatement(insert);
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
   * @param conn : la connexion à la BDD
   * @param name : nom de l'ordinateur
   */

  public static void deleteComputer(Connection conn, String name) {
    String delete = DELETE_BY_NAME;
    try {
      PreparedStatement preparedS = conn.prepareStatement(delete);
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
   * @param conn : La connection à la BDD
   * @param name : nom de l'ordinateur
   */

  public static void listDetails(Connection conn, String name) {
    String select = SELECT_COMPUTER;
    try {
      PreparedStatement preparedS = conn.prepareStatement(select);
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
   * @param conn : la connexion à la BDD
   * @param name : nom de l'ordinateur
   * @param newName : nouveau nom
   * @param newIntro : nouvelle date d'introduction
   * @param newDiscon : nouvelle date d'arrêt de production
   * @param newCompanyId : nouvelle id de companie
   */
  public static void updatePC(Connection conn, String name, String newName, String newIntro,
      String newDiscon, int newCompanyId) {
    String update = UPDATE_BY_NAME;
    try {

      PreparedStatement preparedS = conn.prepareStatement(update);
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
   * @param conn : la connexion à la BDD
   * @param page : la page sélectionné
   *
   * @return List<Computer>
   */

  public static List<Computer> listComputers(Connection conn, int page) {
    List<Computer> computers = new ArrayList<>();
    Statement stmt;
    try {
      stmt = conn.createStatement();
      // ResultSet rs = stmt.executeQuery(SELECT_COMPUTERS);
      ResultSet rs = stmt.executeQuery(SELECT_COMPUTERS + " Limit " + (page - 1) * 25 + ", " + 25);
      while (rs.next()) {
        Computer computer = new Computer();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        Date introDate = rs.getDate("introduced");
        Date discontinuedDate = rs.getDate("discontinued");
        int companyId = rs.getInt("company_id");
        computer.setId(id);
        computer.setName(name);
        computer.setIntroDate(introDate);
        computer.setDiscontinuedDate(discontinuedDate);
        computer.setCompany(companyId);
        System.out.println(computer.toString());
        computers.add(computer);
      }
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    return computers;
  }
}
