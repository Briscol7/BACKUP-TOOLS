import java.io.File;

import javax.swing.JOptionPane;


public class Lancement_Backup {
	
	static Config_Connexion_Frame connexion_frame;
	Connexion connected = new Connexion();
	Config_frame Conf_frame;
	static Fichier fichier = new Fichier("./configurations/param.conf");
	Configuration conf;
	
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(fichier.getFilePath());
		if(!file.exists())
		{
			file.createNewFile();
			/*if(fichier.NombreLigneFichier(file.getPath())==0){
			 int result = JOptionPane.showConfirmDialog(null, "Informations de connexion introuvables","Information",
					JOptionPane.INFORMATION_MESSAGE);
			
			}*/
			
			connexion_frame = new Config_Connexion_Frame();
		}
		else{
			System.out.println("COOL");
		}
	}

}
