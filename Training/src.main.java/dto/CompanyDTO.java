package dto;

public class CompanyDTO {
  
  private int id;
  private String name;
  
  public CompanyDTO() {
    
  }
  
  public CompanyDTO(int id, String name) {
    this.id = id;
    this.name = name;
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

}
