package mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import model.Company;
import model.Computer;

import dto.ComputerDTO;

@Component("ComputerMapper")
public class ComputerMapper implements RowMapper {

  Computer computer;
  Company company;

  private static ComputerMapper instance;

  /**
   * . ComputerMapper instance getter
   *
   * @return ComputerMapper
   */
  public static ComputerMapper getInstance() {
    if (instance == null) {
      instance = new ComputerMapper();
    }
    return instance;
  }

  /**
   * . Contructeur de ComputerMapper
   */
  private ComputerMapper() {
  }

  /**
   * . computer to DTO converter
   *
   * @param computer : computer to be converted
   * @return : ComputerDTO
   */

  public ComputerDTO computerToDTO(Computer computer) {
    ComputerDTO computerDTO = new ComputerDTO();
    computerDTO.setId(computer.getId());
    computerDTO.setName(computer.getName());
    computerDTO.setIntroduced(
        computer.getIntroduced() == null ? null : computer.getIntroduced().toString());
    computerDTO.setDiscontinued(
        computer.getDiscontinued() == null ? null : computer.getDiscontinued().toString());
    computerDTO.setCompanyName(computer.getCompanyName());
    return computerDTO;
  }

  /**
   * . DTO to computer converter
   *
   * @param computerDTO :  DTO to be converted
   * @return : Computer
   */
  public Computer dtoToComputer(ComputerDTO computerDTO) {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Computer computer = new Computer();
    computer.setId(computerDTO.getId());
    computer.setName(computerDTO.getName());
    computer.setIntroduced(LocalDate.parse(computerDTO.getIntroduced(), formatter));
    computer.setDiscontinued(LocalDate.parse(computerDTO.getDiscontinued(), formatter));
    computer.setCompanyName(computerDTO.getCompanyName());
    return computer;
  }

  /**
   * . convertes multiple computers to DTOs
   * @param computers : computers to be converted
   * @return ArrayList<computerDTO>
   */
  public ArrayList<ComputerDTO> computersToDTOs(ArrayList<Computer> computers) {
    ArrayList<ComputerDTO> computerDTOs = new ArrayList<>();
    for (Computer computer : computers) {
      ComputerDTO computerDTO = computerToDTO(computer);
      computerDTOs.add(computerDTO);
    }
    return computerDTOs;
  }

  /**
   * . convertes multiple DTOs to computers
   * @param computerDTOs : DTOs to be converted
   * @return : ArrayList<Computer>
   */
  public ArrayList<Computer> dtosToComputers(ArrayList<ComputerDTO> computerDTOs) {
    ArrayList<Computer> computers = new ArrayList<>();
    for (ComputerDTO computerDTO : computerDTOs) {

      Computer computer = dtoToComputer(computerDTO);
      computers.add(computer);
    }
    return computers;
  }

  @Override
  public Computer mapRow(ResultSet rs, int rowNum) throws SQLException {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Computer computer = new Computer();
    computer.setId(rs.getInt(1));
    computer.setName(rs.getString(2));
    if (rs.getString("introduced") != null) {
      computer.setIntroduced(LocalDate.parse(rs.getString(3).substring(0, 19), formatter));
    }
    if (rs.getString("discontinued") != null) {
      computer.setDiscontinued(LocalDate.parse(rs.getString(4).substring(0, 19), formatter));
    }
    computer.setCompanyName(rs.getString(7));
    return computer;
  }

}
