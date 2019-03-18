package dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

import mapper.ComputerMapper;
import model.Computer;

@Component("ComputerJdbcTemplate")
public class ComputerJDBCTemplate{
  
  private HikariDataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  private ComputerMapper computerMappper;
  
  @Autowired
  public ComputerJDBCTemplate(HikariDataSource dataSource, ComputerMapper computerMappper) {
    this.dataSource = dataSource;
    this.computerMappper = computerMappper;
  }
  
  private static final String INSERT_COMPUTER = "INSERT INTO computer (NAME,INTRODUCED,DISCONTINUED,COMPANY_ID) VALUES (?,?,?,?)";
  private static final String DELETE_BY_NAME = "DELETE FROM computer WHERE NAME = ?";
  private static final String SELECT_COMPUTER = "SELECT id, name, introduced, discontinued, company_id FROM computer WHERE name = ?";
  private static final String UPDATE_BY_NAME = "UPDATE computer SET name=?, introduced=?, discontinued=?, company_id=? WHERE name = ?";
  private static final String SELECT_COMPUTERS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued, "
      + "computer.company_id, company.id, company.name FROM computer computer LEFT JOIN company company ON computer.company_id = company.id ";
  private static final String SEARCH_COMPUTERS = "SELECT computer.id, computer.name, computer.introduced, computer.discontinued,"
      + "computer.company_id, company.id, company.name FROM computer computer LEFT JOIN company company ON computer.company_id = company.id"
      + " WHERE company.name LIKE ?";
  private static final String COUNT_ALL = "SELECT COUNT(id) AS computers FROM computer";
  private static final String COUNT_SEARCH = "SELECT COUNT(computer.id) AS computers FROM computer computer LEFT JOIN company company ON "
      + "computer.company_id = company.id WHERE company.name LIKE ?";
  
  private static Logger logger = LoggerFactory.getLogger(ComputerJDBCTemplate.class);

  public void setDataSource() {
    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
  }
  
  public void create(String name, String intro, String discon, int compId) {
    setDataSource();
    jdbcTemplate.update( INSERT_COMPUTER, name, intro, discon, compId);
    logger.info("computer created");
  }
  
  public void delete(String name) {
    setDataSource();
    jdbcTemplate.update(DELETE_BY_NAME,name);
    logger.info("cumputer " + name + " deleted");
  }
  
  public void show(String name) {
    setDataSource();
    jdbcTemplate.update(SELECT_COMPUTER,name);
  }
  
  public void update(String newName, String newIntro, String newDiscon, int newCompanyId, String name) {
    setDataSource();
    jdbcTemplate.update(UPDATE_BY_NAME,newName, newIntro, newDiscon, newCompanyId, name);
    logger.info("computer " + name + " updated to " + newName);
  }
  
  public int countAll() {
    setDataSource();
    return jdbcTemplate.queryForObject(COUNT_ALL, Integer.class);
  }
  
  public List<Computer> listComputers(){
    setDataSource();
    //System.out.println(jdbcTemplate.queryForList(SELECT_COMPUTERS,computerMappper));
    return jdbcTemplate.query(SELECT_COMPUTERS,computerMappper);
  }
  
  public List<Computer> listSearched(String name){
    setDataSource();
    return jdbcTemplate.query(SEARCH_COMPUTERS, new Object[] {name}, computerMappper);
  }
  
  public int countSearched(String name) {
    return jdbcTemplate.queryForObject(COUNT_SEARCH, new Object[] {name}, Integer.class);
  }
  
}
