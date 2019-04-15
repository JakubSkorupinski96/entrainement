package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dto.CompanyDTO;
import dto.ComputerDTO;
import mapper.CompanyMapper;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices;

@RestController
@RequestMapping(value = "/webServices/company")
public class CompanyRestController {
  
  @Autowired
  CompanyServices companyServices;
  
//  @Autowired
//  CompanyServices companyServices;
  
  private static CompanyMapper companyMapper = CompanyMapper.getInstance();
  private List<Company> companies;
  private ArrayList<CompanyDTO> companyDTOs;
  
  @GetMapping(value = "/ListAll")
  public List<CompanyDTO> listAll() {
    companies = companyServices.listAll();
    return companyMapper.companiesToDTOs(companies);
  }

  /**
   * . Deletes a company and all associated computers
   * @param name : the company's name
   * @throws SQLException : SQLException
   */
  
  @DeleteMapping(value= "/delete")
  public void deleteCompany(@RequestBody ComputerDTO delete) throws SQLException {
    companyServices.deleteCompany(delete.getName());
  }

}
