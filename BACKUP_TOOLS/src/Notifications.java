import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.*;
public class Notifications {
	
	private String NotifName;
	private String NotifMessage;

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Notifications notif = new Notifications();
		notif.setNotifMessage("Bonjour la famille");
		notif.setNotifName("Essai Notifications");
		notif.DisplayNotifcations();
	}
	
	public void setNotifName(String NotifName){
		
		this.NotifName = NotifName;
	}
	
	public String getNotifName(){
		
		return NotifName;
	}
	
	public void setNotifMessage(String NotifMessage){
		
		this.NotifMessage = NotifMessage;
	}
	
	public String getNotifMessage(){
		
		return NotifMessage;
	}
	
	
	public void DisplayNotifcations() throws Exception{
	
		if(SystemTray.isSupported()){
			try{
				this.displayTray();
			} catch(AWTException ex){
				
			}catch(MalformedURLException ex){
				
			}
		} else{
			System.err.println("Ce système ne supporte pas les tray");
		}
	}
	
	
	public void displayTray() throws AWTException, MalformedURLException, Exception{
		SystemTray tray = SystemTray.getSystemTray();
		
		Image image = Toolkit.getDefaultToolkit().createImage("./configurations/ico.png");
		TrayIcon trayIcon = new TrayIcon(image, "Démo JAVA AWT TRAY");
		
		trayIcon.setImageAutoSize(true);
		trayIcon.setToolTip("BACKUP-TOOLS");
	
		tray.add(trayIcon);
		
		trayIcon.displayMessage(getNotifName(), getNotifMessage(), MessageType.INFO);
		Thread.sleep(5000);
		tray.remove(trayIcon);
		//System.exit(0);
	}
	

}
