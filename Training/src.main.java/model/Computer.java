package model;

import java.sql.Date;

public class Computer {

  public int id;
  public String name;
  public Date introduced;
  public Date discontinued;
  public int companyId;

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
  public Computer(int id, String name, Date introDate, Date discontinuedDate, int companyId) {
    this.id = id;
    this.name = name;
    this.introduced = introDate;
    this.discontinued = discontinuedDate;
    this.companyId = companyId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getIntroduced() {
    return introduced;
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

  public int getCompany() {
    return companyId;
  }

  public void setCompany(int companyId) {
    this.companyId = companyId;
  }

  @Override
  public String toString() {
    return "Computer [id=" + id + ", name=" + name + ", intro_date=" + introduced
        + ", discontinued_date=" + discontinued + ", company=" + companyId + "]";
  }
}