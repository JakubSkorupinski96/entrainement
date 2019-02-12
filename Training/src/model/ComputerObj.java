package model;
import java.sql.Date;

import dao.CompanyDAO;

public class ComputerObj {
	
	private static ComputerObj instance;
	public String name;
	public Date intro_date;
	public Date discontinued_date;
	public CompanyDAO company;
    
    private ComputerObj(){
    	
    }
    
    public static ComputerObj getInstance(){
        if(instance == null){
            instance = new ComputerObj();
        }
        return instance;
    }
}