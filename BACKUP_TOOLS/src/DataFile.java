import java.io.*;



public class DataFile {

	private String LineFile;
	
	
	public String getLineFile(){
		return LineFile;
	}
	
	public void setLineFile(String LineFile){
		this.LineFile = LineFile;
	}

	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		DataFile df = new DataFile();
		FileContent fc = new FileContent();
		FileReader fileReader = new FileReader("./configurations/param.conf");
		LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
		String ligne;
		
		
		ligne=null;
		int i = 0;
		while(((ligne=lineNumberReader.readLine())!=null)){
			
			df.setLineFile(ligne);
			fc.AddFileLine(df);
			switch(lineNumberReader.getLineNumber()){
			case 2: df.setLineFile(fc.getValueAtLine(lineNumberReader.getLineNumber()));
			
			case 6: df.setLineFile(fc.getValueAtLine(lineNumberReader.getLineNumber()));
			
			default:
				break;
			}
			
			
		}
		
		
		
		
		
	}

}
