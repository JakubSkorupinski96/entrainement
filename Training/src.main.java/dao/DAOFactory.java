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

  
  
}
