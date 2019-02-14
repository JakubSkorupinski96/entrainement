package model;
public class Company {
	
	private static Company instance;
	int id;
	String name;
    
    private Company(){
    	
    }
    
    public static Company getInstance(){
        if(instance == null){
            instance = new Company();
        }
        return instance;
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

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}
}
