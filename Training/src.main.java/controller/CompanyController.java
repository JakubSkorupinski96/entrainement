package controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import model.Company;
import services.CompanyServices;

@Component("companyController")
public class CompanyController {

  private static CompanyController instance;

  //private static Logger logger = LoggerFactory.getLogger(CompanyController.class);

  @Autowired
  CompanyServices companyServices;

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
   * @param page : la page sélectionné
   *
   * @return List<Company>
   */

  public List<Company> listCompanies(int page) {
    return companyServices.listCompanies(page);
  }

  /**
   * . Liste toutes les companies
   *
   * @return List<Compan>
   */

  public List<Company> listAll() {
    return companyServices.listAll();
  }
  
  public void deleteCompany(String name) throws SQLException {
    companyServices.deleteCompany(name);
  }
}
