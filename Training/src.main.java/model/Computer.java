package model;

import java.sql.Date;

public class Computer {

  public int id;
  public String name;
  public Date intro_date;
  public Date discontinued_date;
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
    this.intro_date = introDate;
    this.discontinued_date = discontinuedDate;
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

  public Date getIntroDate() {
    return intro_date;
  }

  public void setIntroDate(Date introDate) {
    this.intro_date = introDate;
  }

  public Date getDiscontinuedDate() {
    return discontinued_date;
  }

  public void setDiscontinuedDate(Date discontinuedDate) {
    this.discontinued_date = discontinuedDate;
  }

  public int getCompany() {
    return companyId;
  }

  public void setCompany(int companyId) {
    this.companyId = companyId;
  }

  @Override
  public String toString() {
    return "Computer [id=" + id + ", name=" + name + ", intro_date=" + intro_date
        + ", discontinued_date=" + discontinued_date + ", company=" + companyId + "]";
  }
}