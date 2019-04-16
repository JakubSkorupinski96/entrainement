package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dto.ComputerDTO;
import exception.ComputerDateCoherenceException;
import exception.ComputerNameException;
import mapper.ComputerMapper;
import model.Company;
import model.Computer;
import services.CompanyServices;
import services.ComputerServices;

@RestController
@RequestMapping(value = "/webServices/computer")
public class ComputerRestController {

  private static Logger logger = LoggerFactory.getLogger(ComputerRestController.class);
  
  @Autowired
  ComputerServices computerServices;
  
//  @Autowired
//  CompanyServices companyServices;
  
  private static ComputerMapper computerMapper = ComputerMapper.getInstance();
  private ArrayList<Computer> computers;
  private ArrayList<ComputerDTO> computerDTOs;
  
  @GetMapping(value = "/ListComputers")
  public List<ComputerDTO> listAll(){
    
    computers = new ArrayList<Computer>(computerServices.listAllComputers());
    computerDTOs = computerMapper.computersToDTOs(computers);
    
    return computerDTOs; 
  }
  
  @PostMapping(value = "/Add")
  public ComputerDTO addComputer(@RequestBody ComputerDTO add) {
    
    String errorMessage;
    
    Computer computer = new Computer();
    ComputerDTO computerDTO = new ComputerDTO();
    String introduced = add.getIntroduced();
    introduced += " 00:00:00";
    String discontinued = add.getDiscontinued();
    discontinued += " 00:00:00";

    try {
      computer = computerServices.createComputerREST(add.getName(), introduced, discontinued, add.getCompanyId());
      computerDTO = computerMapper.computerToDTO(computer);
    } catch (NumberFormatException e) {
      errorMessage = "The selected company doesn't have an id";
      logger.error(errorMessage);
    } catch (ComputerNameException e) {
      errorMessage = "Computer name cannot be empty";
      logger.error(errorMessage);
    } catch (ComputerDateCoherenceException e) {
      errorMessage = "Introduced cannot be greater than Discontinued";
      logger.error(errorMessage);
    }

    return computerDTO;
  }
  
  //TO DO: Check multi delete
  @DeleteMapping(value = "/DeleteComputer")
  public void post(@RequestBody ComputerDTO delete) {
    System.out.println(delete.getName());
    List<String> items = Arrays.asList(delete.getName().split(","));
    for (String item : items) {
      computerServices.deleteComputer(item);
    }
  }
  
  
  @PatchMapping(value = "/EditComputer")
  public ComputerDTO edit(@RequestBody ComputerDTO edit) {
    
    String errorMessage;
    
    String introduced = edit.getIntroduced();
    introduced += " 00:00:00";
    
    String discontinued = edit.getDiscontinued();
    discontinued += " 00:00:00";

    try {
      computerServices.updateComputerREST(edit.getId(), edit.getName(), introduced, discontinued, edit.getCompanyId());
    } catch (NumberFormatException e) {
      errorMessage = "The selected company doesn't have an id";
      logger.error(errorMessage);
    } catch (ComputerNameException e) {
      errorMessage = "Computer name cannot be empty";
      logger.error(errorMessage);
    } catch (ComputerDateCoherenceException e) {
      errorMessage = "Introduced cannot be greater than Discontinued";
      logger.error(errorMessage);
    }


    return edit;
  }

}
