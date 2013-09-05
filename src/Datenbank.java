/**
 @author: Felix Schütze, Jan Hofmeier
 */
import java.io.*;

public class Datenbank
{
	String sep = System.getProperty("file.separator");
	String pfad;
	File lehrerVerzeichnis;

	public Datenbank(String pfad)
	{
		this.pfad = pfad;
		lehrerVerzeichnis = new File(pfad + sep + "Lehrer");
	}
	
	public Datenbank()
	{
		this.pfad = ".";
		lehrerVerzeichnis = new File(pfad + sep + "Lehrer");
	}

	public String[] gebeLehrerListe()
	{
		return lehrerVerzeichnis.list();
	}
	
	public static void main(String args[])
	{
		Datenbank test=new Datenbank();
		System.out.println(test.gebeLehrerListe());
	}
}
