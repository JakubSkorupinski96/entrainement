package model;

public class Company {

  private int id;
  private String name;

  /**
   * . Constructeur vide de Company
   */

  public Company() {
  }

  /**
   * . Constructeur de Company
   *
   * @param id   : l'id de la companie
   * @param name : nom de la companie
   */
  public Company(int id, String name) {
    this.id = id;
    this.name = name;
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

  @Override
  public String toString() {
    return "Company [id=" + id + ", name=" + name + "]";
  }
}
