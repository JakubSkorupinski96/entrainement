package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DAOFactory {

  private static DAOFactory instance;
  private Connection conn;

  private static final String PROPERTY_FILE_NAME = "C:\\Users\\jakub\\git\\repository\\Training\\db.properties";

  private static Logger logger = LoggerFactory.getLogger(DAOFactory.class);
  
  private static HikariConfig config = new HikariConfig();
  private static HikariDataSource ds;
  
  static List<String> data = getConnectionURL(PROPERTY_FILE_NAME);
  private static String URL = data.get(0);;
  private static String USERNAME = data.get(1);
  private static String PASSWORD = data.get(2);

  
  static {
    config.setJdbcUrl(URL);
    config.setUsername(USERNAME);
    config.setPassword(PASSWORD);
    config.setDriverClassName("com.mysql.jdbc.Driver");
    config.addDataSourceProperty( "cachePrepStmts" , "true" );
    config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
    config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );
    ds = new HikariDataSource( config );
}
  
  
  /**
   * . Constructeur vide du DAO de computer
   */

  private DAOFactory() {
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
   * @param data : données nécessaire à la connexion (0=URL, 1=user et 2=password)
   */

  public void startConnection(List<String> data) {

      try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e1) {
      logger.error("JDBC error");
      e1.printStackTrace();
    }

    try {
      this.conn = DriverManager.getConnection(data.get(0), data.get(1), data.get(2));
    } catch (SQLException e) {
      logger.error("connection error");
      e.printStackTrace();
    }
  }

//  /**
//   * . Récupére la connexion
//   *
//   * @return Connection
//   */
//  public Connection getConnection() {
//    return this.conn;
//  }

  /**
   * . Gets connection info from a properties file
   *
   * @param propertyFile : name of the file with DB info
   *
   * @return List<String>
   */

  public static List<String> getConnectionURL(String propertyFile) {

    Properties prop = new Properties();
    //InputStream input = null;
    List<String> data = new ArrayList<>();

    try (InputStream input = new FileInputStream(propertyFile);) {
      prop.load(input);
      data.add(prop.getProperty("dbURL"));
      data.add(prop.getProperty("dbUser"));
      data.add(prop.getProperty("dbPassword"));

    } catch (IOException e) {
      e.printStackTrace();
      logger.error("Properties file error");
    }
    return data;
  }

  public static Connection getConnection() throws SQLException {
      return ds.getConnection();
  }
  
  
}
