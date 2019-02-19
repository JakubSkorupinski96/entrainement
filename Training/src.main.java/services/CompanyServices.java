package services;

import java.sql.Connection;
import java.util.List;

import dao.CompanyDAO;
import model.Company;

public class CompanyServices {
	
	private static CompanyServices instance;
	
	CompanyDAO companyDAO = CompanyDAO.getInstance();
    
    private CompanyServices(){	
    }
    
    public static CompanyServices getInstance(){
        if(instance == null){
            instance = new CompanyServices();
        }
        return instance;
    }
	
	public List<Company> listCompanies(Connection conn, int page){
		return companyDAO.listCompanies(conn, page);
	}
}
