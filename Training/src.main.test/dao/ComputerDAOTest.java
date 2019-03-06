package dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import model.Computer;

public class ComputerDAOTest {

  private ComputerDAO computerDAO = ComputerDAO.getInstance();
  
  @Before
  public void setUp(){
    
  }
  
  
  @Test
  public void daoCountAllComputersTest() {
    List<Computer> computers = computerDAO.listAllComputers();
    int computerNb = Integer.parseInt(computerDAO.countAll());
    assertEquals(computerNb, computers.size());
  }
  
  @Test
  public void insertComputerTest() {
    String name = "testComputer1";
    List<Computer> computers = computerDAO.listAllComputers();
    computerDAO.createComputer(name, "2019-01-01 00:00:00", "2019-02-02 00:00:00", 3);
    int count = Integer.parseInt(computerDAO.countAll());
    assertEquals(count,computers.size()+1);
  }
  

}
