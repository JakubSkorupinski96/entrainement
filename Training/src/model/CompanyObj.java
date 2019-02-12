package model;
public class CompanyObj {
	
	private static CompanyObj instance;
	int id;
	String name;
    
    private CompanyObj(){
    	
    }
    
    public static CompanyObj getInstance(){
        if(instance == null){
            instance = new CompanyObj();
        }
        return instance;
    }
}
