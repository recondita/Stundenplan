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

	public void machLehrer(String name)
	{
		FileWriter fw = lehrerFW(name);
		if (fw != null)
		{
			try
			{
				fw.write("");
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void schreibeLehrerEigeschaften(String name, int mindestStunden,
			int maximalStunden, String[] fach)
	{
		String faecher = "";
		for (int i = 0; i < fach.length; i++)
		{
			faecher = faecher + fach[i] + ",";
		}

		FileWriter fw = lehrerFW(name);
		try
		{
			fw.write("minh:" + mindestStunden + "\n" + "maxh:" + maximalStunden
					+ "\n" + "faecher:" + faecher);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fehler beim Schreiben der Eigenschaften " + e);
		}
	}

	public FileWriter lehrerFW(String name)
	{
		try
		{
			return new FileWriter(new File(pfad + sep + "Lehrer" + sep + name
					+ ".lehrer"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fehler beim Anlegen des Filewriters " + e);
			return null;
		}
	}

	public void printLehrer(Datenbank db)
	{
		machLehrer("Fank");
		machLehrer("Hund");
		machLehrer("Boje");
		db.schreibeLehrerEigeschaften("B. Fank", 30, 40, gebeLehrerListe());
		String[] test = gebeLehrerListe();
		for (int i = 0; i < test.length; i++)
		{
			String[] splittArray = test[i].split("\\.lehrer");
			String temp=splittArray[0];
			System.out.println(temp);
			temp = "";
		}
	}

	public static void main(String args[])
	{
		System.out.println(System.getProperty("user.dir"));
		Datenbank testDatenbank = new Datenbank();
		testDatenbank.printLehrer(testDatenbank);
	}
}
