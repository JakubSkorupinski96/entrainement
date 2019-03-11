package mapper;

import java.util.ArrayList;

import dto.CompanyDTO;
import model.Company;


public class CompanyMapper {
  
  private static CompanyMapper instance;
  
  public static CompanyMapper getInstance() {
    if (instance == null) {
      instance =  new CompanyMapper();
    }
    return instance;
  }
  
  private CompanyMapper() {
    
  }
  
  public CompanyDTO companyToDTO(Company company) {
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setId(company.getId());
    companyDTO.setName(company.getName());
    return companyDTO;
  }
  
  public Company DTOToCompany (CompanyDTO companyDTO) {
    Company company = new Company();
    company.setId(companyDTO.getId());
    company.setName(company.getName());
    return company;
  }
  
  public ArrayList<CompanyDTO> companiesToDTOs(ArrayList<Company> companies){
    ArrayList<CompanyDTO> companyDTOs = new ArrayList<>();
    for (Company company : companies) {
      CompanyDTO companyDTO = companyToDTO(company);
      companyDTOs.add(companyDTO);
    }
    return companyDTOs;
  }
  
  public ArrayList<Company> DTOsToCompanies(ArrayList<CompanyDTO> companyDTOs){
    ArrayList<Company> companies = new ArrayList<>();
    for (CompanyDTO companyDTO : companyDTOs) {
      Company company = DTOToCompany(companyDTO);
      companies.add(company);
    }
    return companies;
  }
  
}
