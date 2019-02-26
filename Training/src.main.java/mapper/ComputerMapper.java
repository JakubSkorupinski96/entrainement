package mapper;

import java.sql.Date;

import model.Company;
import model.Computer;

public class ComputerMapper {

  Computer computer;
  Company company;

  /**
   * . Contructeur de ComputerMapper
   */
  public ComputerMapper() {
  }

  /**
   * . Cr�e une company
   *
   * @param id : id de la compagnie
   * @param name : nom de la compagnie
   *
   * @return Company
   */

  public Company createCompany(int id, String name) {
    return company = new Company(id, name);
  }

  /**
   * . Cr�e un computer
   *
   * @param id : id du computer
   * @param name : nom du computer
   * @param introduced : date de sortie du computer
   * @param discontinued : date d'arr�t de production du computer
   * @param company : compagnie associ� au computer
   * @param companyName : nom de la compagnie
   *
   * @return Computer
   */
  public Computer createComputer(int id, String name, Date introduced, Date discontinued, Company company, String companyName) {
    return computer = new Computer(id, name, introduced, discontinued, company, companyName);
  }

}
