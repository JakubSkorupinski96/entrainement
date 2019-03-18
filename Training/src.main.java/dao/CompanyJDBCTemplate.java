package dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zaxxer.hikari.HikariDataSource;

import mapper.CompanyMapper;
import model.Company;

@Component("CompanyJdbcTemplate")
public class CompanyJDBCTemplate {

  private HikariDataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  private CompanyMapper companyMapper;

  /**
   * . JDBCTemplate autowired constructor
   *
   * @param dataSource : connection datasource
   * @param companyMapper : company Mapper
   */

  @Autowired
  public CompanyJDBCTemplate(HikariDataSource dataSource, CompanyMapper companyMapper) {
    this.dataSource = dataSource;
    this.companyMapper = companyMapper;
  }

  private static final String SELECT_ALL = "SELECT id, name FROM company";
  private static final String DELETE_COMPANY = "DELETE FROM company WHERE name = ?";
  private static final String DELETE_COMPUTERS = "DELETE computer FROM computer computer JOIN company company ON computer.company_id = "
      + "company.id WHERE company.name = ?";

  /**
   * . DataSource setter
   */

  public void setDataSource() {
    this.jdbcTemplate = new JdbcTemplate(this.dataSource);
  }


  /**
   * . Lists all companies in the DB
   * @return List<Company>
   */

  public List<Company> listCompanies() {
    setDataSource();
    return jdbcTemplate.query(SELECT_ALL, companyMapper);
  }

  /**
   * . Deletes the selected comapany and all associated computers
   * @param name : the company's name
   */

  @Transactional
  public void delete(String name) {
    setDataSource();
    jdbcTemplate.update(DELETE_COMPANY, name);
    jdbcTemplate.update(DELETE_COMPUTERS, name);
  }

}
