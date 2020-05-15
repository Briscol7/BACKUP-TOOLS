import java.util.ArrayList;
import java.util.List;


public class FileContent {
	private List<DataFile> FileLine = new ArrayList<DataFile>();
	
	public void AddFileLine(DataFile datafile ){
		FileLine.add(datafile);
	}
	
	public int getRowCount(){
		return  FileLine.size();
	}
	
	public String getValueAtLine(int lineNumber){
		return FileLine.get(lineNumber-1).getLineFile();
	}
	
}
