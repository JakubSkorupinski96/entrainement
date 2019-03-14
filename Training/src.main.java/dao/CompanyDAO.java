package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import model.Company;
import spring.SpringConfig;

@Component("CompanyDAO")
public class CompanyDAO {

  private static CompanyDAO instance;
  private Connection conn;

  private static Logger logger = LoggerFactory.getLogger(CompanyDAO.class);

  private static final String SELECT_ALL = "SELECT id, name FROM company";
  private static final String DELETE_COMPANY = "DELETE FROM company WHERE name = ?";
  private static final String DELETE_COMPUTERS = "DELETE computer FROM computer computer JOIN company company ON computer.company_id = company.id WHERE company.name = ?"; 

  /**
   * . Constructeur vide de la DAO de company
   */
  
  @Autowired
  private CompanyDAO() {
    try {
      this.conn = SpringConfig.getConnection();
    } catch (SQLException e) {
      logger.error("companyDAO instance error");
    }
  }

  /**
   * . Constructeur de la DAO de company
   *
   * @return instance
   */
  public static CompanyDAO getInstance() {
    if (instance == null) {
      instance = new CompanyDAO();
    }
    return instance;
  }

  /**
   * . Renvoie la liste des companies dans la BDD
   *
   * @param page : la page choisi
   * @return List<Company>
   */

  public List<Company> listCompanies(int page) {
    List<Company> companies = new ArrayList<>();
    Statement stmt;
    try {
      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery(SELECT_ALL + " Limit " + (page - 1) * 25 + ", " + 25);
      while (rs.next()) {
        Company company = new Company();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        company.setId(id);
        company.setName(name);
        System.out.println(company.toString());
        companies.add(company);
      }
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    return companies;
  }

  /**
   * . Liste toutes les companies
   *
   * @return List<Company>
   */

  public List<Company> listAll() {
    List<Company> companies = new ArrayList<>();
    Statement stmt;
    try {
      stmt = this.conn.createStatement();
      ResultSet rs = stmt.executeQuery(SELECT_ALL);
      while (rs.next()) {
        Company company = new Company();
        int id = rs.getInt("id");
        String name = rs.getString("name");
        company.setId(id);
        company.setName(name);
        System.out.println(company.toString());
        companies.add(company);
      }
    } catch (SQLException e) {
      logger.error("erreur de liste");
      e.printStackTrace();
    }
    return companies;
  }
  
  public void deleteCompany(String name) throws SQLException {
    
    PreparedStatement deleteComputers = null;
    PreparedStatement deleteCompany = null;
    
    try {
      conn.setAutoCommit(false);
      deleteComputers = conn.prepareStatement(DELETE_COMPUTERS);
      deleteCompany = conn.prepareStatement(DELETE_COMPANY);
  
      deleteComputers.setString(1, name);
      deleteComputers.executeUpdate();
      deleteCompany.setString(1, name);
      deleteCompany.executeUpdate();
      System.out.println(deleteComputers);
      System.out.println(deleteCompany);
      conn.commit();
    
    } catch (SQLException e ) {
      logger.error("delete error");
      conn.rollback();
    } finally {
      conn.setAutoCommit(true);
    }
    
  }

}
