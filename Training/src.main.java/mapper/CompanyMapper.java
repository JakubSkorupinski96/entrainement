package mapper;

import java.util.ArrayList;
import java.util.List;

import model.Company;

public class CompanyMapper {
  
  Company company;
  
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

}
