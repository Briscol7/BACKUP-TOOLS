import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class DateHeure {
	
	Calendar calendrier;
	
	public String AfficheDateHeure(String forma) throws Exception{
		
		calendrier = Calendar.getInstance();
		DateFormat dateformat = new SimpleDateFormat(forma, Locale.FRENCH);
		String date = dateformat.format(calendrier.getTime());
		return date;
	}
	
	
	public Date ConvertHeure(String date) throws Exception{
		DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		calendrier = Calendar.getInstance();
		calendrier.setTime(dateformat.parse(date));
		return calendrier.getTime();	
	}
	
	public Date ConvertDate(String date) throws Exception{
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd", Locale.FRENCH);
		return dateformat.parse(date);
	}
	
	public Date AdditionDate(String dated, String datef) throws Exception{
		
		calendrier = Calendar.getInstance();
		DateFormat dateformat = new SimpleDateFormat("HH:mm:ss");
		calendrier.setTime(dateformat.parse(dated));
		Date datedeb = calendrier.getTime();
		calendrier.setTime(dateformat.parse(datef));
		Date datefin = calendrier.getTime();
		int heur = datedeb.getHours() + datefin.getHours();
		int min = datedeb.getMinutes() + datefin.getMinutes();
		int sec = datedeb.getSeconds() + datefin.getSeconds();
		String Resultdate = heur+":"+min+":"+sec;
		calendrier.setTime(dateformat.parse(Resultdate));
		return calendrier.getTime();
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		DateHeure dt = new DateHeure();
		Data data = new Data();
		Fichier file = new Fichier("./configurations/param.conf");
		
		int i=0;
		data.setData();
		String heure = data.getHeure();
		
		System.out.println(heure);
		System.out.println(dt.AfficheDateHeure("HH:mm:ss"));
		String Ecart = "03:30:00";
		System.out.println(dt.ConvertHeure(heure));
		System.out.println(dt.AdditionDate(heure, Ecart));
	}

}
