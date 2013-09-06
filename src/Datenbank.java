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
		try
		{
			this.pfad = ".";
			lehrerVerzeichnis = new File(pfad + sep + "Lehrer");
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fehler beim Lesen des Verzeichnisses");
		}
	}

	public String[] gebeLehrerListe()
	{
		try
		{
			return lehrerVerzeichnis.list();
		} catch (Exception e)
		{
			System.out.println("Lesefehler");
			return null;
		}
	}

	public static void main(String args[])
	{
		System.out.println(System.getProperty("user.dir"));
		Datenbank testDatenbank = new Datenbank();
		String[] test = testDatenbank.gebeLehrerListe();
		for (int i = 0; i < test.length; i++)
		{
			String[] splittArray = test[i].split(".");
			System.out.println(splittArray[0]); //sinnloses Kommentar
		}
	}
}
