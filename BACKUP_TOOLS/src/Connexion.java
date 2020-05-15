import java.sql.*;
public class Connexion  {
	

	String Url;
	Statement state;
	Connection Connect;
	Data donnes = new Data();
	Fichier fichier = new Fichier("./configurations/param.conf");
	

	
	public Connection getConnection(){
		
		return Connect;
	}
	
	public String getUrl(){
		return Url;
	}
	
	
	public void setUrl(String url){
		this.Url = url;
	}
	
	
	public String ServUrl() throws Exception{
		donnes.setData();
		donnes.setPort(donnes.getPortLine());
		String url = "jdbc:sqlserver:"+"//"+donnes.getServerName()+":"+donnes.getPort()+
				";user="+donnes.getUserName()+";password="+donnes.getPassword();
		return url;
	}
	
	public Connection Connecte(String url) throws Exception{
		Connect = null;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connect = DriverManager.getConnection(url);
		return Connect;
	}
	
	public Statement statement (Connection con) throws Exception{
		state = con.createStatement();
		return state;
	}
	
	public void Disconnect(Statement state) throws Exception{
		state.close();
	}
	
	public void DisconnectConnection(Connection conect) throws Exception{
		conect.close();
	}
	

	public static void main(String[]args) throws Exception{
		Fichier fichier = new Fichier("./configurations/param.conf");
		//String [] element_fichier = fichier.ReadFile(fichier.getFilePath(), fichier.NombreLigneFichier(fichier.getFilePath()));
		
		Connexion con = new Connexion();
		int i=0;
		con.setUrl(con.ServUrl());
		//con.ServUrl(element_fichier[i+1].split(":")[1], element_fichier[i+2].split(":")[1], element_fichier[i+3].split(":")[1], "master");
		//String url="jdbc:sqlserver://TEST-PC;databasename=BDCOMGEST;user=sa;password=Pa55w0rd";
		con.Connecte(con.getUrl());
		con.DisconnectConnection(con.getConnection());
		//System.out.println("SERV :"+con.ServName);
	}
}
