
public class Database {
	private String NameBase;
	
	public Database(String db)
	{
		super();
		this.NameBase = db;
	}
	
	public String getNameBase(){
		
		return NameBase;
	}
	
	public void setNameBase(String NameBase){
		this.NameBase = NameBase;
	}
}
