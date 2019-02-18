package model;
import java.sql.Date;

import dao.CompanyDAO;

public class Computer {
	
	public int id;
	public String name;
	public Date intro_date;
	public Date discontinued_date;
	public int companyId;

	public Computer(){
    	
    }
    
    public Computer(int id, String name, Date intro_date, Date discontinued_date, int companyId){
    	this.id = id;
    	this.name = name;
    	this.intro_date = intro_date;
    	this.discontinued_date = discontinued_date;
    	this.companyId = companyId;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getIntro_date() {
		return intro_date;
	}

	public void setIntro_date(Date intro_date) {
		this.intro_date = intro_date;
	}

	public Date getDiscontinued_date() {
		return discontinued_date;
	}

	public void setDiscontinued_date(Date discontinued_date) {
		this.discontinued_date = discontinued_date;
	}

	public int getCompany() {
		return companyId;
	}

	public void setCompany(int companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", intro_date=" + intro_date + ", discontinued_date=" + discontinued_date
				+ ", company=" + companyId + "]";
	}
}