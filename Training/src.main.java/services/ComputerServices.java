package services;

import java.sql.Connection;
import java.util.List;

import dao.ComputerDAO;
import model.Computer;

public class ComputerServices {

	private static ComputerServices instance;
    
    private ComputerServices(){	
    }
    
    public static ComputerServices getInstance(){
        if(instance == null){
            instance = new ComputerServices();
        }
        return instance;
    }
	
	
	ComputerDAO computerDAO = ComputerDAO.getInstance();
	
	public List<Computer> listComputers(Connection conn){
		return computerDAO.listComputers(conn);
	}
	
	public void createComputer(Connection conn, String name, String intro, String discon, int comp_id) {
		computerDAO.createComputer(conn, name, intro, discon, comp_id);
	}
	
	public void updateComputer(Connection conn, String name, String newName, String newIntro, String newDiscon, int newCompanyId) {
		computerDAO.updatePC(conn, name, newName, newIntro, newDiscon, newCompanyId);
	}
	
	public void deleteComputer(Connection conn, String name) {
		computerDAO.deleteComputer(conn, name);
	}
	
	public void showComputer(Connection conn, String name) {
		computerDAO.listDetails(conn, name);
	}
}