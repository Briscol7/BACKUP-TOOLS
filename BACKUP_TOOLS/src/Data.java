import java.io.*;

public class Data {
	private String ServerName;
	private String UserName;
	private String DataBaseName;
	private String DataBase;
	private String Password;
	private String PortLine;
	private int Port;
	private String StrutureName;
	private String RepeatState;
	private String Heure;
	private String ServiceName;
	private String SavePath;
	private String ArchivePath;
	private String NasPath;
	Fichier fichier = new Fichier("./configurations/param.conf");
	String Services[];
	String ListDataBase[];
	int NumberBD;
	
	FileReader fileReader;
	LineNumberReader lineNumbReader;
	DataFile dataFile = new DataFile();
	FileContent contenu = new FileContent();
	
	
	public void setServerName(String ServerName){
		this.ServerName = ServerName;
	}
	
	public void setUserName(String UserName){
		this.UserName = UserName;
	}
	
	public void setDataBase(String DataBase){
		this.DataBase = DataBase;
	}
	
	public void setDataBaseName(String DataBaseName){
		this.DataBaseName = DataBaseName;
	}
	
	public void setPassword(String Password){
		this.Password = Password;
	}
	
	public void setPort(String PortLine){
		this.Port = Integer.parseInt(PortLine);
	}
	
	public void setPortLine(String PortLine){
		this.PortLine = PortLine;
	}
	
	public void setStrutureName(String StrutureName){
		this.StrutureName = StrutureName;
	}
	
	public void setRepeatState(String RepeatState){
		this.RepeatState = RepeatState;
	}
	
	public void setHeure(String Heure){
		this.Heure = Heure;
	}
	
	
	public void setSavePath(String SavePath){
		
		this.SavePath = SavePath;
		
	}
	
	public void setArchivePath(String ArchivePath){
		this.ArchivePath = ArchivePath;
	}
	
	public void setNasPath(String NasPath){
		this.NasPath = NasPath;
	}
	
	
	public void setServiceName(String ServiceName){
		this.ServiceName = ServiceName;
	}
	
	
	public String getServerName(){
		return ServerName;
	}
	
	public String getUserName(){
		return  UserName;
	}
	
	public String getDataBase(){
		return  DataBase;
	}
	
	public String getDataBaseName(){
		return DataBaseName;
	}
	
	public String getPassword(){
		return Password;
	}
	
	public int getPort(){
		return Port;
	}
	
	public String getStrutureName(){
		return StrutureName;
	}
	
	public String getRepeatState(){
		return RepeatState;
	}
	
	public String getHeure(){
		return Heure;
	}
	
	
	public String getSavePath(){
		return SavePath;
	}
	
	public String getArchivePath(){
		return ArchivePath;
	}
	
	public String getNasPath(){
		return NasPath;
	}
	
	public String getServiceName(){
		return ServiceName;
	}
	
	public String getPortLine(){
		return PortLine;
	}
	

	
	public void setData() throws Exception{
		fileReader = new FileReader(fichier.getFilePath());
		lineNumbReader = new LineNumberReader(fileReader);
		Services = new String[3];
		String ligne=null;
		int i = 0;
		
		
		while(((ligne=lineNumbReader.readLine())!=null)){
			dataFile.setLineFile(ligne);
			contenu.AddFileLine(dataFile);
			switch(lineNumbReader.getLineNumber()){
			case 2:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setServerName(dataFile.getLineFile().split(":")[1]);
			case 3:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setUserName(dataFile.getLineFile().split(":")[1]);
			case 4:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setPassword(dataFile.getLineFile().split(":")[1]);
			
			case 5:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setPortLine(dataFile.getLineFile().split(":")[1]);
			case 7:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setStrutureName(dataFile.getLineFile().split(":")[1]);
			case 8:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setRepeatState(dataFile.getLineFile().split(":")[1]);
			case 9:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setHeure(dataFile.getLineFile().substring(6));
				
			case 10:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setServiceName(dataFile.getLineFile().split(":")[1]);
				Services[i]= getServiceName();
			case 11:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setServiceName(dataFile.getLineFile().split(":")[1]);
				Services[i+1]= getServiceName();
			case 12:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setServiceName(dataFile.getLineFile().split(":")[1]);
				Services[i+2]= getServiceName();
			case 14:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setSavePath(dataFile.getLineFile());
			case 15:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setArchivePath(dataFile.getLineFile());
			case 16:
				dataFile.setLineFile(contenu.getValueAtLine(lineNumbReader.getLineNumber()));
				setNasPath(dataFile.getLineFile());
		
				
			}
		}
		
	}
	
	public int getNumberOfBD() throws Exception{
		fileReader = new FileReader(fichier.getFilePath());
		lineNumbReader = new LineNumberReader(fileReader);
		NumberBD = 0;
		String ligne=null;
		
		while((ligne=lineNumbReader.readLine())!=null){
			if(lineNumbReader.getLineNumber()>=19){
				NumberBD++;
			}
			
		}
		return NumberBD;
	}
	
	public String [] ListOfBD() throws Exception{
		fileReader = new FileReader(fichier.getFilePath());
		lineNumbReader = new LineNumberReader(fileReader);
		String ligne=null;
		int i = 0;
		ListDataBase = new String[getNumberOfBD()];
		while((ligne=lineNumbReader.readLine())!=null){
			if(lineNumbReader.getLineNumber()>=19){
			
				System.out.println(ligne);
			}
			
		}
		return ListDataBase;
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Data data = new Data();
		
		data.setData();
		
		System.out.println(data.getServerName());
		data.setPort(data.getPortLine());
		System.out.println(data.getPort());
		System.out.println(data.getDataBase());
		System.out.println(data.getStrutureName());
		System.out.println(data.getHeure());
		for(int i=0;i<data.Services.length;i++){
			
				System.out.println(data.Services[i]);
			
		}
		System.out.println(data.getSavePath().substring(11));
		System.out.println(data.getArchivePath().substring(8));
		//System.out.println(data.getNumberOfBD());
		data.ListOfBD();
		
	}

}
