package dao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.querydsl.jpa.hibernate.HibernateQueryFactory;

import model.Company;
import model.QCompany;
import model.Computer;
import model.QComputer;

@Component("ComputerJdbcTemplate")
public class ComputerJDBCTemplate {

  private SessionFactory sessionFactory;

  /**
   * . Constructor injection (spring)
   *
   * @param dataSource : dataSource
   * @param computerMappper : computerMapper
   * @param sessionFactory : session Factory
   */

  @Autowired
  public ComputerJDBCTemplate(SessionFactory sessionFactory) {
    this.sessionFactory = sessionFactory;
  }

  private static Logger logger = LoggerFactory.getLogger(ComputerJDBCTemplate.class);

  /**
   * . Creates a computer
   *
   * @param name   : computer's name
   * @param intro  : computer's introduction date
   * @param discon : computer's discontinuation date
   * @param compId : computer's company's ID
   */

  public void create(String name, String intro, String discon, int compId) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    QCompany qCompany = QCompany.company;
    Company company = queryF.selectFrom(qCompany).where(qCompany.id.eq(compId)).fetchOne();
    Computer comp = new Computer();
    computer.name.as(name);
    comp.setName(name);
    comp.setIntroduced(LocalDate.parse(intro, formatter));
    comp.setDiscontinued(LocalDate.parse(discon, formatter));
    comp.setCompany(company);
    session.save(comp);
    logger.info("computer created");
    session.close();
  }
  
  
  public Computer createComputer(String name, String intro, String discon, int compId) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    QCompany qCompany = QCompany.company;
    Company company = queryF.selectFrom(qCompany).where(qCompany.id.eq(compId)).fetchOne();
    Computer comp = new Computer();
    computer.name.as(name);
    comp.setName(name);
    comp.setIntroduced(LocalDate.parse(intro, formatter));
    comp.setDiscontinued(LocalDate.parse(discon, formatter));
    comp.setCompany(company);
    session.save(comp);
    logger.info("computer created");
    session.close();
    return comp;
  }
  

  /**
   * . Deletes a computer
   *
   * @param name : computer's name
   */

  public void delete(String name) {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    System.out.println(computer);
    queryF.delete(computer).where(computer.name.eq(name)).execute();
    logger.info("cumputer " + name + " deleted");
    session.close();
  }
  
  
  public Computer deleteComputer(String name) {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    System.out.println(computer);
    queryF.delete(computer).where(computer.name.eq(name)).execute();
    logger.info("cumputer " + name + " deleted");
    session.close();
    return null;
  }
  

  /**
   * . Shows all computer information
   *
   * @param name : computer's name
   */

  public void show(String name) {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    Computer comp = queryF.selectFrom(computer).where(computer.name.eq(name)).fetchOne();
    logger.info(comp.toString());
    session.close();
  }

  /**
   * . Updates an existing computer
   *
   * @param newName      : computer's new name
   * @param newIntro     : new date
   * @param newDiscon    : new date
   * @param newCompanyId : new company ID
   * @param name         : old name
   */

  public void update(String newName, String newIntro, String newDiscon, int newCompanyId,
      String name) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    Computer comp = queryF.selectFrom(computer).where(computer.name.eq(name)).fetchOne();
    computer.name.as(name);
    comp.setName(newName);
    comp.setIntroduced(LocalDate.parse(newIntro, formatter));
    comp.setDiscontinued(LocalDate.parse(newDiscon, formatter));
    queryF.update(computer).where(computer.name.eq(name)).set(computer.name, newName).set(computer.introduced, LocalDate.parse(newIntro, formatter))
    .set(computer.discontinued, LocalDate.parse(newDiscon, formatter)).set(computer.company.id, newCompanyId).execute();
    logger.info("computer " + name + " updated to " + newName);
    session.close();
  }
  
  
  public void updateREST(String newName, String newIntro, String newDiscon, int newCompanyId,
      int id) {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:SS");
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    Computer comp = queryF.selectFrom(computer).where(computer.id.eq(id)).fetchOne();
    //computer.name.as(name);
    comp.setName(newName);
    comp.setIntroduced(LocalDate.parse(newIntro, formatter));
    comp.setDiscontinued(LocalDate.parse(newDiscon, formatter));
    queryF.update(computer).where(computer.id.eq(id)).set(computer.name, newName).set(computer.introduced, LocalDate.parse(newIntro, formatter))
    .set(computer.discontinued, LocalDate.parse(newDiscon, formatter)).set(computer.company.id, newCompanyId).execute();
    logger.info("computer " + id + " updated to " + newName);
    session.close();
  }
  

  /**
   * . Counts all computers in the DB
   *
   * @return : int
   */

  public int countAll() {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    int count = (int) queryF.selectFrom(computer).fetchCount();
    session.close();
    return count;
  }

  /**
   * . Lists all computers
   *
   * @return : List<Computer>
   */

  public List<Computer> listComputers() {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    List<Computer> computers = queryF.selectFrom(computer).fetch();
    session.close();
    return computers;
  }

  /**
   * . lists all search results
   *
   * @param name : computer/company name
   * @return : List<Computer>
   */
  public List<Computer> listSearched(String name) {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;
    List<Computer> computers = queryF.selectFrom(computer).where(company.name.like(name).or(computer.name.like(name))).fetch();
    session.close();
    return computers;
  }

  /**
   * . Counts all search results
   *
   * @param name : computer/company name
   * @return : int
   */
  public int countSearched(String name) {
    
    Session session = this.sessionFactory.openSession();
    HibernateQueryFactory queryF = new HibernateQueryFactory(session);
    QComputer computer = QComputer.computer;
    QCompany company = QCompany.company;
    int count = (int) queryF.selectFrom(computer).where(company.name.like(name).or(computer.name.like(name))).fetchCount();
    session.close();
    return count;
  }

}
