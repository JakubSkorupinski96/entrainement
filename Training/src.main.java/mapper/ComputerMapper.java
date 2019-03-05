package mapper;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
   * . Crée une company
   *
   * @param id : id de la compagnie
   * @param companyName : nom de la compagnie
   *
   * @return Company
   */

  public Company createCompany(int id, String companyName) {
    return company = new Company(id, companyName);
  }
  
  public List<String> companyToDTO(Company company) {
    List<String> listCompany = new ArrayList<>();
    String id = Integer.toString(company.getId());
    String name = company.getName();
    listCompany.add(id);
    listCompany.add(name);
    return listCompany;
  }

  /**
   * . Crée un computer
   *
   * @param id : id du computer
   * @param name : nom du computer
   * @param introduced : date de sortie du computer
   * @param discontinued : date d'arrêt de production du computer
   * @param company : compagnie associé au computer
   * @param companyName : nom de la compagnie
   *
   * @return Computer
   */
  public Computer createComputer(int id, String name, LocalDate introduced, LocalDate discontinued, Company company, String companyName) {
    return computer = new Computer(id, name, introduced, discontinued, company, companyName);
  }
  
  public List<String> computerToDTO(Computer computer){
    List<String> listComputer = new ArrayList<>();
    String id = Integer.toString(computer.getId());
    String name = computer.getName();
    String introduced = computer.getIntroduced().toString();
    String discontinued = computer.getDiscontinued().toString();
    String companyName = computer.getCompanyName();
    listComputer.add(id);
    listComputer.add(name);
    listComputer.add(introduced);
    listComputer.add(discontinued);
    listComputer.add(companyName);
    return listComputer;
  }

}
