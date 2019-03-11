package mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import model.Company;
import model.Computer;

import dto.ComputerDTO;

public class ComputerMapper {

  Computer computer;
  Company company;

  private static ComputerMapper instance;
  
  public static ComputerMapper getInstance() {
    if (instance == null) {
      instance =  new ComputerMapper();
    }
    return instance;
  }
  
  /**
   * . Contructeur de ComputerMapper
   */
  private ComputerMapper() {
  }
  
  public ComputerDTO computerToDTO(Computer computer){
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(computer.getId());
    computerDTO.setName(computer.getName());
    computerDTO.setIntroduced(computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
    computerDTO.setDiscontinued(computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
    computerDTO.setCompanyName(computer.getCompanyName());
    return computerDTO;
  }
  
  public Computer DTOToComputer(ComputerDTO computerDTO) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Computer computer = new Computer();
    computer.setId(computerDTO.getId());
    computer.setName(computerDTO.getName());
    computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced(),formatter));
    computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued(),formatter));
    computer.setCompanyName(computerDTO.getCompanyName());
    return computer;
  }
  
  public ArrayList<ComputerDTO> computersToDTOs (ArrayList<Computer> computers){
    ArrayList<ComputerDTO> computerDTOs = new ArrayList<>();
    for (Computer computer : computers) {
      ComputerDTO computerDTO = computerToDTO(computer);
      computerDTOs.add(computerDTO);
    }
    return computerDTOs;
  }
  
  public ArrayList<Computer> DTOsToComputers (ArrayList<ComputerDTO> computerDTOs){
    ArrayList<Computer> computers = new ArrayList<>();
    for (ComputerDTO computerDTO : computerDTOs) {
      
      Computer computer = DTOToComputer(computerDTO);
      computers.add(computer);
    }
    return computers;
  }

}
