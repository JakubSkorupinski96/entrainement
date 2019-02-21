package services;

import java.sql.Connection;
import java.util.List;

import dao.CompanyDAO;
import model.Company;

public class CompanyServices {

  private static CompanyServices instance;

  CompanyDAO companyDAO = CompanyDAO.getInstance();

  /**
   * . Constructeur vide des services de company
   */
  private CompanyServices() {
  }

  /**
   * . Constructeur des services de company
   *
   * @return instance
   */
  public static CompanyServices getInstance() {
    if (instance == null) {
      instance = new CompanyServices();
    }
    return instance;
  }

  /**
   * . Retourne la liste des companies
   *
   * @param conn : la connexion � la BDD
   * @param page : la page s�lectionn�
   *
   * @return List<Company>
   */
  public List<Company> listCompanies(int page) {
    return companyDAO.listCompanies(page);
  }
}
