package dto;


public class ComputerDTO {

  private String id;
  private String name;
  private String introduced;
  private String discontinued;
  private String company;
  private String companyName;

  /**
   * . Constructeur de computerDTO
   *
   * @param id : id du computer
   * @param name : nom du computer
   * @param introduced : date d'introduction du computer
   * @param discontinued : date d'arrêt de production du computer
   * @param company : compagnie associer au computer
   * @param companyName : nom de la compagnie
   */

  public ComputerDTO(String id, String name, String introduced, String discontinued, String company, String companyName) {
    this.id = id;
    this.companyName = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.company = company;
    this.companyName = companyName;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIntroduced() {
    return this.introduced;
  }

  public void setIntroduced(String introduced) {
    this.introduced = introduced;
  }

  public String getDiscontinued() {
    return this.discontinued;
  }

  public void setDiscontinued(String discontinued) {
    this.discontinued = discontinued;
  }

  public String getCompany() {
    return this.company;
  }

  public void setCompany(String company) {
    this.company = company;
  }

  public String getCompanyName() {
    return this.companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

}
