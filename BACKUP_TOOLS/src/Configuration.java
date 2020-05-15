import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.*;

public class Configuration {
	 
	static Fichier fichier = new Fichier("./configurations/param.conf");
	Connexion connected = new Connexion();
	

	public String[] ListDataBase() throws Exception
	{
		connected.ServUrl();
		String req ="SELECT COUNT(name) AS C FROM sys.databases";
		connected.Connecte(connected.ServUrl());
		connected.state = connected.statement(connected.getConnection());
		ResultSet res = connected.state.executeQuery(req);
		//ResultSetMetaData rsm = res.getMetaData();
		//res.first();
		int nbreline=0;
		if(res.next()){
			nbreline = Integer.parseInt(res.getString(1));
		}
		
		req ="SELECT name FROM sys.databases";
		res = connected.state.executeQuery(req);
		if(res!=null){
			String [] liste = new String[nbreline];
			int j=0;
			while(res.next())
			{
				liste[j] = res.getString(1);
				System.out.println(j+" "+liste[j]);
				j++;
			}
			
			connected.DisconnectConnection(connected.getConnection());
			connected.Disconnect(connected.state);
			return liste;
		}
		else{
			return null;
		}
		
		
	}
	
	
	
	
	
	public static void main(String[]args) throws Exception{
		
		
		FileReader fileReader = new FileReader("./configurations/param.conf");
		LineNumberReader lineNumberRead = new LineNumberReader(fileReader);
		int i = 0;
		while(i<fichier.getNumberLine("./configurations/param.conf")){
			switch(i){
			case 1 : System.out.println( lineNumberRead.readLine());
			case 2 : System.out.println( lineNumberRead.readLine());
			}
			i++;
		}
	
	}
}
