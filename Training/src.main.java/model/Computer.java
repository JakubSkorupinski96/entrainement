package model;

import java.sql.Date;

public class Computer {

  private int id;
  private String name;
  private Date introduced;
  private Date discontinued;
  private Company company;
  private String companyName;
  

  /**
   * . Constructeur vide de computer
   */
  public Computer() {
  }

  /**
   * . Constructeur de computer
   *
   * @param id : id de l'ordinateur
   * @param name : nom de l'ordinateur
   * @param introDate : date d'introduction de l'ordinateur
   * @param discontinuedDate : date d'arrêt de production de l'ordinateur
   * @param companyId : id de la companie associé
   */
  public Computer(int id, String name, Date introDate, Date discontinuedDate, Company company, String companyName) {
    this.id = id;
    this.name = name;
    this.introduced = introDate;
    this.discontinued = discontinuedDate;
    this.company = company;
    this.companyName = companyName;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getIntroduced() {
    return this.introduced;
  }

  public void setIntroduced(Date introDate) {
    this.introduced = introDate;
  }

  public Date getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(Date discontinuedDate) {
    this.discontinued = discontinuedDate;
  }
  
  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
  
  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  @Override
  public String toString() {
    return "Computer [id=" + id + ", name=" + name + ", intro_date=" + introduced
        + ", discontinued_date=" + discontinued + ", company=" + companyName + " ]";
  }
}