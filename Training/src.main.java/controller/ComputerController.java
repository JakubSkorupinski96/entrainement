package controller;

import java.sql.Connection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.ComputerDAO;
import model.Computer;
import services.ComputerServices;


public class ComputerController {

	private static ComputerController instance;
    
	private static Logger logger = LoggerFactory.getLogger(ComputerController.class);
	
	ComputerServices computerServices = ComputerServices.getInstance();
	
	private ComputerController() {	
	}
	
    public static ComputerController getInstance(){
        if(instance == null){
            instance = new ComputerController();
        }
        return instance;
    }
    
	public List<Computer> list(Connection conn, int page){
		return computerServices.listComputers(conn, page);
	}
	
	public void create(Connection conn, String name, String intro, String discon, int comp_id) {
		computerServices.createComputer(conn, name, intro, discon, comp_id);
	}
	
	public void update(Connection conn, String name, String newName, String newIntro, String newDiscon, int newCompanyId) {
		computerServices.updateComputer(conn, name, newName, newIntro, newDiscon, newCompanyId);
	}
	
	public void delete(Connection conn, String name) {
		computerServices.deleteComputer(conn, name);
	}
	
	public void show(Connection conn, String name) {
		computerServices.showComputer(conn, name);
	}
}
