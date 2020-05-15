import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.*;
import java.util.*;


public class test {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		FileReader fileReader = new FileReader("./Configurations/param.conf");
		LineNumberReader lineNumber = new LineNumberReader(fileReader);
		FileWriter fw = new FileWriter("./Configurations/param.conf");
		
		DataFile df = new DataFile();
		String ligne=lineNumber.readLine();
		while((ligne=lineNumber.readLine())!=null){
			df.setLineFile(ligne);
			System.out.println(df.getLineFile());
			
			
		}
	}

}
