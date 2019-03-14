package model;

import java.time.LocalDate;

public class Computer {

  private int id;
  private String name;
  private LocalDate introduced;
  private LocalDate discontinued;
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
   * @param id               : id de l'ordinateur
   * @param name             : nom de l'ordinateur
   * @param introDate        : date d'introduction de l'ordinateur
   * @param discontinuedDate : date d'arrêt de production de l'ordinateur
   * @param company          : companie associé à l'id_company de l'ordinateur
   * @param companyName      : nom de cette companie
   */
  public Computer(int id, String name, LocalDate introDate, LocalDate discontinuedDate, Company company,
      String companyName) {
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

  public LocalDate getIntroduced() {
    return this.introduced;
  }

  public void setIntroduced(LocalDate introDate) {
    this.introduced = introDate;
  }

  public LocalDate getDiscontinued() {
    return discontinued;
  }

  public void setDiscontinued(LocalDate discontinuedDate) {
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

  public static class Builder {

    private int id;
    private String name;
    private LocalDate introduced;
    private LocalDate discontinued;
    private Company company;
    private String companyName;

    /**
     * . Builder's constructor
     *
     * @param id : computer's id
     */

    public Builder(int id) {
      this.id = id;
    }

    /**
     * . Adds a computer name to the builder
     *
     * @param name : computer's name
     * @return Builder
     */

    public Builder computerName(String name) {
      this.name = name;
      return this;
    }

    /**
     * . Adds an introduction date to the builder
     *
     * @param introduced : a computer's introduction date
     * @return Builder
     */

    public Builder computerIntroduced(LocalDate introduced) {
      this.introduced = introduced;
      return this;
    }

    /**
     * . Adds a discontinuation date to the builder
     *
     * @param discontinued : a computer's discontinuation date
     * @return Builder
     */

    public Builder computerDiscontinued(LocalDate discontinued) {
      this.discontinued = discontinued;
      return this;
    }

    /**
     * . Adds a computer's company to the builder
     *
     * @param company : a computer's company
     * @return Builder
     */

    public Builder computerCompany(Company company) {
      this.company = company;
      return this;
    }

    /**
     * . Adds a computer's company's name to the builder
     *
     * @param companyName : the company name
     * @return Builder
     */

    public Builder computerCompanyName(String companyName) {
      this.companyName = companyName;
      return this;
    }

    /**
     * . Builds a computer with the builder's values
     * @return ComputerS
     */

    public Computer build() {
      Computer computer = new Computer();
      computer.id = this.id;
      computer.name = this.name;
      computer.introduced = this.introduced;
      computer.discontinued = this.discontinued;
      computer.company = this.company;
      computer.companyName = this.companyName;
      return computer;
    }
  }
}