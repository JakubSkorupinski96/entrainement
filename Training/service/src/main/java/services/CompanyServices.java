package services;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.CompanyJDBCTemplate;
import model.Company;
import model.QCompany;

@Service("companyServices")
public class CompanyServices {

  private static CompanyServices instance;

  @Autowired
  CompanyJDBCTemplate companyJDBC;

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
   * @param page : la page s�lectionn�
   *
   * @return List<Company>
   */
//  public List<Company> listCompanies(int page) {
//    return companyDAO.listCompanies(page);
//  }

  /**
   * . liste toutes les companies
   *
   * @return List<Comapny>
   */

  public List<Company> listAll() {
    return companyJDBC.listCompanies();
  }

  /**
   * . Deletes a company and all its computers
   * @param name : company name
   * @throws SQLException : exception
   */
  public void deleteCompany(String name) throws SQLException {
    companyJDBC.delete(name);
  }
}
