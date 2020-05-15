import java.io.*;

import javax.swing.JOptionPane;

public class Fichier {
	
	private String FilePath;
	private int FileLength;
	FileReader fileReader;
	LineNumberReader lineNumberReader;
	public String [] line;
	String ligne;
	
	
	public Fichier(String FilePath){
		super();
		this.FilePath = FilePath;
	}
	public String getFilePath()
	{
		return FilePath;
	}
	
	public void setFilePath(String filePath){
		this.FilePath = filePath;
	}
	
	public int getFileLenth(){
		return FileLength;
	}
	
	public void setFileLength(int filelength){
		this.FileLength = filelength;
	}
	
	
	
	public int getNumberLine(String filePath) throws Exception
	{
		int nbreLine = 0;
		File file = new File(filePath);
		FileReader filereader = new FileReader(file);
		BufferedReader buffReader = new BufferedReader(filereader);
		//String lignes="";
		while((buffReader.readLine())!=null)
		{
			nbreLine++;
			
		}
		buffReader.close();
		return nbreLine;
	}
	
	public String getValueAtLine(String filePath,int Nberline) throws Exception {
		fileReader = new FileReader(filePath);
		lineNumberReader = new LineNumberReader(fileReader);
		int i = 0;
		while((ligne = lineNumberReader.readLine())!=null){
			i = lineNumberReader.getLineNumber();
			if(i==Nberline){
				ligne = lineNumberReader.readLine();
			}
			
		}
		return ligne;
	}
	
	public String[] ReadFile(String filePath) throws Exception
	{
		if(filePath!=null){
			File file = new File(filePath);
			FileReader filereader = new FileReader(file);
			BufferedReader buffReader = new BufferedReader(filereader);
			String lignes="";
			setFileLength(getNumberLine(filePath));
			int i = 0;
			if(file.exists())
			{
				line = new String[getFileLenth()];
				while((lignes = buffReader.readLine())!=null)
				{
					
					if(lignes.charAt(0)=='[')
					{
						//System.out.println("Bonjour");
					}
					else
					{
						
						line[i] = lignes;
						
					}
					i++;
				}
				buffReader.close();
			}
			
			return line;
		}
		else{
			JOptionPane.showMessageDialog(null, "Erreur de fichier de configuration");
			return null;
		}
		
	}
	
	public String getValueAt(int lineIndex){
		
		 return line[lineIndex];
		
	}
	
	
	public void setValueAt(int lineIndex, String text){
		
		  line[lineIndex] = text;
		
	}
	
	
	
	public void WriteFile(String FilePath) throws Exception{
		
		
		
	}
	
	
}
