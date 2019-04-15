package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import dto.CompanyDTO;
import model.Company;

@Component("companyMapper")
public class CompanyMapper implements RowMapper {

  private static CompanyMapper instance;

  /**
   * . CompanyMapper instance getter
   * @return : CompanyMapper instance
   */

  public static CompanyMapper getInstance() {
    if (instance == null) {
      instance = new CompanyMapper();
    }
    return instance;
  }

  /**
   * . CompanyMapper constructor
   */

  private CompanyMapper() {

  }

  /**
   * . company to company DTO converter
   * @param company : company to be converted
   * @return : companyDTO
   */

  public CompanyDTO companyToDTO(Company company) {
    CompanyDTO companyDTO = new CompanyDTO();
    companyDTO.setId(company.getId());
    companyDTO.setName(company.getName());
    return companyDTO;
  }

  /**
   * . companyDTO to company converter
   *
   * @param companyDTO : DTO to be converted
   * @return : Company
   */
  public Company dtoToCompany(CompanyDTO companyDTO) {
    Company company = new Company();
    company.setId(companyDTO.getId());
    company.setName(company.getName());
    return company;
  }

  /**
   * . convertes multiple companies to DTOs
   * @param companies : companies to be converted
   * @return : ArrayList<CompanyDTO>
   */
  public List<CompanyDTO> companiesToDTOs(List<Company> companies) {
    ArrayList<CompanyDTO> companyDTOs = new ArrayList<>();
    for (Company company : companies) {
      CompanyDTO companyDTO = companyToDTO(company);
      companyDTOs.add(companyDTO);
    }
    return companyDTOs;
  }

  /**
   * . convertes multiple DTOs to companies
   * @param companyDTOs : DTO to be converted
   * @return : ArrayList<List>
   */
  public List<Company> dTOsToCompanies(List<CompanyDTO> companyDTOs) {
    ArrayList<Company> companies = new ArrayList<>();
    for (CompanyDTO companyDTO : companyDTOs) {
      Company company = dtoToCompany(companyDTO);
      companies.add(company);
    }
    return companies;
  }

  @Override
  public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
    Company company = new Company();
    company.setId(rs.getInt(1));
    company.setName(rs.getString(2));
    return company;
  }

}
