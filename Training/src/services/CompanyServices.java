package services;

import java.sql.Connection;
import java.util.List;

import dao.CompanyDAO;
import model.Company;

public class CompanyServices {
	
	private static CompanyServices instance;
    
    private CompanyServices(){	
    }
    
    public static CompanyServices getInstance(){
        if(instance == null){
            instance = new CompanyServices();
        }
        return instance;
    }
	
	CompanyDAO companyDAO = CompanyDAO.getInstance();
	
	public List<Company> listCompanies(Connection conn){
		return companyDAO.listCompanies(conn);
	}
}
