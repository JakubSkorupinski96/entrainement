package dto;


public class ComputerDTO {

  private int id;
  private int companyId;
  private String name;
  private String introduced;
  private String discontinued;
  private String companyName;

  /**
   * . Constructeur de computerDTO
   *
   */

  public ComputerDTO() {
  }

  /**
   * . Constructeur de computerDTO
   *
   * @param id : id du computer
   * @param name : nom du computer
   * @param introduced : date d'introduction du computer
   * @param discontinued : date d'arr�t de production du computer
   * @param companyId : company Id
   */

  public ComputerDTO(int id, String name, String introduced, String discontinued, int companyId) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.companyId = companyId;
  }

  /**
   * . Constructeur de computerDTO
   *
   * @param id : id du computer
   * @param name : nom du computer
   * @param introduced : date d'introduction du computer
   * @param discontinued : date d'arr�t de production du computer
   * @param company : compagnie associer au computer
   * @param companyName : nom de la compagnie
   */

  public ComputerDTO(int id, String name, String introduced, String discontinued, int companyId, String companyName) {
    this.id = id;
    this.name = name;
    this.introduced = introduced;
    this.discontinued = discontinued;
    this.companyId = companyId;
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

  public String getCompanyName() {
    return this.companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public int getCompanyId() {
    return this.companyId;
  }

  public void setCompanyId(int companyId) {
    this.companyId = companyId;
  }

}
