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
		}
	}

	public String[] gebeLehrerListe()
	{
		try
		{
			return lehrerVerzeichnis.list();
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			return null;
		}
	}

	public static void main(String args[])
	{
		Datenbank test = new Datenbank();
		try
		{
			Thread.sleep(100);
		} catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;
		System.out.println(test.gebeLehrerListe());
	}
}
