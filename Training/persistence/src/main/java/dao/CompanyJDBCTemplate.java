package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;
import com.zaxxer.hikari.HikariDataSource;

import mapper.CompanyMapper;
import model.Company;
import model.QCompany;
import model.QComputer;

@Component("CompanyJdbcTemplate")
public class CompanyJDBCTemplate {

  private HikariDataSource dataSource;
  private JdbcTemplate jdbcTemplate;
  private CompanyMapper companyMapper;
  private SessionFactory sessionFactory;

  /**
   * . JDBCTemplate autowired constructor
   *
   * @param dataSource : connection datasource
   * @param companyMapper : company Mapper
   * @param sessionFactory : session factory
   */

  @Autowired
  public CompanyJDBCTemplate(HikariDataSource dataSource, CompanyMapper companyMapper, SessionFactory sessionFactory) {
    this.dataSource = dataSource;
    this.companyMapper = companyMapper;
    this.sessionFactory = sessionFactory;
  }

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
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QCompany company = QCompany.company;
    List<Company> companies = queryF.selectFrom(company).fetch();
    session.close();
    return companies;
  }

  /**
   * . Deletes the selected comapany and all associated computers
   * @param name : the company's name
   */

  @Transactional
  public void delete(String name) {

    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QCompany company = QCompany.company;
    QComputer computer = QComputer.computer;
    queryF.delete(computer).where(computer.company.name.eq(name)).execute();
    queryF.delete(company).where(company.name.eq(name)).execute();
    session.close();
  }
  
  @Transactional
  public void deleteREST(int id) {

    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QCompany company = QCompany.company;
    QComputer computer = QComputer.computer;
    queryF.delete(computer).where(computer.company.id.eq(id)).execute();
    queryF.delete(company).where(company.id.eq(id)).execute();
    session.close();
  }

}
