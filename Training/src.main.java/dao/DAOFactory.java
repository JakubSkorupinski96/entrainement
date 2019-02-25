package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {

  private static DAOFactory instance;
  private Connection conn;

  private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
  private static final String USER = "root";
  private static final String PASS = "";

  /**
   * . Constructeur vide du DAO de computer
   */

  private DAOFactory() {
    System.out.println("start");
    startConnection();
  }

  /**
   * . instancie le singleton ComputerDAO
   *
   * @return instance
   */
  public static DAOFactory getInstance() {
    if (instance == null) {
      instance = new DAOFactory();
    }
    return instance;
  }

  /**
   * . Démarre la connexion à la BDD
   */

  public void startConnection() {

      try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e1) {
      //logger.error("JDBC error");
      e1.printStackTrace();
    }

    try {
      this.conn = DriverManager.getConnection(URL, USER, PASS);
      System.out.println("here" + this.conn);
    } catch (SQLException e) {
      //logger.error("connection error");
      e.printStackTrace();
    }
    System.out.println(conn);
  }

  /**
   * . Récupére la connexion
   *
   * @return Connection
   */
  public Connection getConnection() {
    System.out.println(this.conn);
    return this.conn;
  }
}
