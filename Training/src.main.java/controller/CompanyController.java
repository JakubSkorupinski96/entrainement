package controller;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.Company;
import services.CompanyServices;

public class CompanyController {

  private static CompanyController instance;

  private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

  CompanyServices companyServices = CompanyServices.getInstance();

  /**
   * . Constructeur vide du controlleur de Company
   */
  private CompanyController() {
  }

  /**
   * . Constructeur du controlleur de Company
   *
   * @return instance
   */
  public static CompanyController getInstance() {
    if (instance == null) {
      instance = new CompanyController();
    }
    return instance;
  }

  /**
   * . Retourne la liste des companies dans la BDD
   *
   * @param conn : la connexion à la BDD
   * @param page : la page sélectionné
   *
   * @return List<Company>
   */

  public List<Company> listCompanies(Connection conn, int page) {
    return companyServices.listCompanies(conn, page);
  }
}
