package services;

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
   * @param page : la page sélectionné
   *
   * @return List<Company>
   */
  public List<Company> listCompanies(int page) {
    return companyDAO.listCompanies(page);
  }

  /**
   * . liste toutes les companies
   *
   * @return List<Comapny>
   */

  public List<Company> listAll() {
    return companyDAO.listAll();
  }
}
