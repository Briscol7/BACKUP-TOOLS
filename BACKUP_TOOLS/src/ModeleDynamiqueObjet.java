import java.util.ArrayList;
import java.util.List;
import javax.swing.table.*;


public class ModeleDynamiqueObjet extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Database>  DBase = new ArrayList<Database>();
	private String [] entete = {"Base"};
	
	public ModeleDynamiqueObjet(){
		
		
		DBase.add(new Database(""));
		
	}
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return entete.length;
	}

	public int getRowCount() {
		// TODO Auto-generated method stub
		return DBase.size();
	}

	public Object getValueAt(int rowIndex, int ColumnIndex) {
		// TODO Auto-generated method stub
		switch(ColumnIndex){
		case 0 :
			return DBase.get(rowIndex).getNameBase();
		default :
			return null;
		}
		
	}
	
	public void AddDatabase(Database db)
	{
		DBase.add(db);
		fireTableRowsInserted(DBase.size() -1, DBase.size() -1);
	}
	
	public void RemoveDatabase(int rowIndex){
		DBase.remove(rowIndex);
		
		fireTableRowsDeleted(rowIndex, rowIndex);
	}
	
	public boolean isValideDatabase(Database database){
		
		for(int i=0; i< getRowCount();i++){
			System.out.println("LIgnes"+getRowCount());
			if(database.equals(getValueAt(i,0))){
				System.out.println(" OK "+database); 
				return false;
			} 
		}
		
		return true;
	}
	
	public int DBExiste(Database T[], Database db){
		for (int i=0;i<T.length;i++){
			if(db==T[i]) return i;
			
				
		}
		return -1;	
	}
	

}
