//Cette classe permet l'exécution des taches (sauvegarde, compression et copie) sous forme de service.

//Imporation des différents packages et classes qui seront utiles
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class Services_tools {
	
	//Définition de toutes les variables qui seront utilisées dans la classe des ersvices
	Fichier fichier = new Fichier("./configurations/param.conf");
	
	FileReader fileReader;
	LineNumberReader lineNumbReader;
	
	Connexion connect = new Connexion();
	Data donnes = new Data();
	static DateHeure DateHeure  = new DateHeure();
	
	Notifications notification = new Notifications();
	
	private String ServicePath;
	
	private String ServiceName;
	

	
	
	//Définition d'une variable commande pour l'exécution des commandes cmd
	private String cmd ="";
	//Définition d'un Timer pour la 
	Timer timer = new Timer();
	
	//Les méthodes accesseurs et mutateurs de la variables commandes
	public String getCmd()
	{
		return cmd;
	}
	
	
	public void setCmd(String com)
	{
		this.cmd = com;
	}
	
	//Les méthodes accesseurs et mutateurs de la variables servicepath (Emplacement dans lequel un service ou tache va prendre 
	//ou stocker les données)
	public String getServicePath(){
		return ServicePath;
	}
	
	public void setServicePath(String ServicePath)
	{
		this.ServicePath = ServicePath;
	}
	
	public void setServiceName(String ServiceName){
		
		this.ServiceName = ServiceName;
	}
	
	public String getServiceName(){
		
		return ServiceName;
	}
	
	//Méthode pour la mise sur pied de le commande de save des bases de données
	public String CommandSave(String DBase, String date ) throws Exception{
		
		//Lecture du fichier des paramètres de configuration
		donnes.setData();
		int i = 0;
		while(i<donnes.Services.length){
			if(donnes.Services[i].matches("backup")){
				donnes.setServiceName(donnes.Services[i]);
			}
			i++;
		}
		cmd = "sqlcmd -S "+donnes.getServerName()+" -U "+donnes.getUserName()+" -P "+donnes.getPassword()+" -Q \"BACKUP DATABASE "+
		DBase+" TO DISK=N\'"+donnes.getSavePath().substring(11)+"\\"+DBase+"_"+donnes.getStrutureName().trim()+"_"+donnes.getServiceName()+"_"+date+".bak\' " +
		"WITH NOFORMAT, INIT, NAME=N\'"+DBase+"-COMPLETE BASE\', SKIP, STATS=10\"";
		
		return cmd;
		 
	}
	
	
	//Methode pour la mise sur pied de la commande de compression
	public String CommandArchive(String date) throws Exception{
	
		donnes.setData();
		int i = 0;
		while(i<donnes.Services.length){
			if(donnes.Services[i].matches("SAVEBD")){
				donnes.setServiceName(donnes.Services[i]);
			}
			i++;
		}
		//Vérification de l'existence des fichiers à compresser
		cmd= "rar a -r "+donnes.getArchivePath().substring(8)+"\\"+donnes.getServiceName()+"_"+donnes.getStrutureName()+"_"+
		date+".zip "+donnes.getSavePath().substring(11)+"\\*_backup_*"+date+"_*.bak" ;
		return cmd;
		
	
	}
	
	public Process ExecuteCommand(String Comand) throws Exception{
	
		if(Comand!=null){
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(Comand);
			return process;
		} else {
			return null;
		}
		
		
		
	}
	
	public void run_save() throws Exception{
		fileReader = new FileReader(fichier.getFilePath());
		lineNumbReader = new LineNumberReader(fileReader);
		String ligne = null;
		String datHeure = DateHeure.AfficheDateHeure("yyyy_MM_dd_HH_mm_ss");
		notification.setNotifName("BACKUP-TOOLS Sauvegarde");
		notification.setNotifMessage("Début de la sauvegarde le "+ new Date());
		notification.DisplayNotifcations();
		while((ligne=lineNumbReader.readLine())!=null){
			if(lineNumbReader.getLineNumber()>=18){
				setCmd(CommandSave(ligne, datHeure));
				Process p = ExecuteCommand(getCmd());
				if(p.isAlive()){
					notification.setNotifMessage("Sauvegarde de "+ ligne);
					notification.DisplayNotifcations();
					p.waitFor();
				}
				if(p.exitValue()==0){
					p.destroy();
					
				}
			}
				
				
		}
		notification.setNotifName("BACKUP-TOOLS Sauvegarde");
		notification.setNotifMessage("Fin de la sauvegarde le "+ new Date());
		notification.DisplayNotifcations();
	}
	
	
	public void run_archive() throws Exception{
		String DatHeure = DateHeure.AfficheDateHeure("yyyy_MM_dd");
		
		setCmd(CommandArchive(DatHeure));
		Process p = ExecuteCommand(getCmd());
		
		notification.setNotifName("BACKUP-TOOLS Compression");
		notification.setNotifMessage("Début de la compression le "+ new Date());
		if(p.isAlive()){
			notification.DisplayNotifcations();
			p.waitFor();
		}
		if(p.exitValue()==0){
			p.destroy();
		
			notification.setNotifMessage("Fin de la compression le "+ new Date());
			notification.DisplayNotifcations();
		}
		
	}
	
	public void Run_CopyNAS() throws Exception{
		
	}
	
	
	public  void Run_Task() throws Exception{
		donnes.setData();
			TimerTask task_save = new TimerTask() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						run_save();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			};
			System.out.println(donnes.getHeure());
			timer.schedule(task_save, DateHeure.ConvertHeure(donnes.getHeure()), 86400000);
		
			TimerTask task_archive = new TimerTask(){

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
					
						run_archive();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			};
		String Ecart = "03:00:00";
		timer.schedule(task_archive, DateHeure.AdditionDate(donnes.getHeure(), Ecart), 86400000);
		
		
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Services_tools service = new Services_tools();
		String datHeure = DateHeure.AfficheDateHeure("yyyy_MM_dd_HH_mm_ss");
		Data data = new Data();
		data.setData();
		data.fileReader = new FileReader("./configurations/param.conf");
		data.lineNumbReader = new LineNumberReader(data.fileReader);
		data.ListDataBase = new String [data.contenu.getRowCount()];
		service.Run_Task();
	
	}

}
