package controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ComputerControllerTest {

  private static final String URL = "jdbc:mysql://localhost:3306/computer-database-db-test?zeroDateTimeBehavior=convertToNull";
  private static final String USER = "root";
  private static final String PASS = "";

  ComputerController computerController = ComputerController.getInstance();
  Connection conn;

  private static Logger logger = LoggerFactory.getLogger(ComputerControllerTest.class);

  @Before
  public void setUp() throws Exception {

    try {
      Class.forName("com.mysql.jdbc.Driver");
    } catch (ClassNotFoundException e1) {
      logger.error("JDBC error");
      e1.printStackTrace();
    }

    try {
      conn = DriverManager.getConnection(URL, USER, PASS);
      System.out.println("Bonjour utilisateur");
    } finally {

    }
  }

  @Test
  public void createComputerTest() {
    computerController.create(conn, "test PC", null, null, 3);
  }

}
