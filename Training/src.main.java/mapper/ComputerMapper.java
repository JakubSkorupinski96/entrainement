package mapper;

import java.sql.Date;
import java.time.LocalDate;

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
   * @param companyName : nom de la compagnie
   *
   * @return Company
   */

  public Company createCompany(int id, String companyName) {
    return company = new Company(id, companyName);
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
  public Computer createComputer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company, String companyName) {
    return computer = new Computer(id, name, introduced, discontinued, company, companyName);
  }

}
