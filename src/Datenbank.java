/**
 @author: Felix Schütze, Jan Hofmeier
 */
import java.io.*;

public class Datenbank
{
	String sep = System.getProperty("file.separator");
	String zumbruch = System.getProperty("line.separator");
	String pfad;

	File lehrerVerzeichnis;
	File faecherVerzeichnis;

	public Datenbank(String pfad)
	{
		this.pfad = pfad;
		lehrerVerzeichnis = new File(pfad + sep + "Lehrer");
		lehrerVerzeichnis = new File(pfad + sep + "Faecher");
	}

	public Datenbank()
	{
		try
		{
			this.pfad = ".";
			lehrerVerzeichnis = new File(pfad + sep + "Lehrer");
			faecherVerzeichnis = new File(pfad + sep + "Faecher");
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
			String[] Liste=new String[lehrerVerzeichnis.list().length];
			for (int i = 0; i < lehrerVerzeichnis.list().length; i++)
			{
				String[] splittArray = lehrerVerzeichnis.list()[i].split("\\.lehrer");
				String temp="";
				for(int j=0;j<splittArray.length;j++)
				{
					temp=temp+splittArray[j];
				}
				Liste[i]=temp;
				temp="";
			}
			return Liste;
		} catch (Exception e)
		{
			System.out.println("Lesefehler");
			return null;
		}
	}

	public String[] gebeFaecherListe()
	{
		try {
			String[] Liste=new String[faecherVerzeichnis.list().length];
			for (int i = 0; i < faecherVerzeichnis.list().length; i++)
			{
				String[] splittArray = faecherVerzeichnis.list()[i].split("\\.fach");
				String temp="";
				for(int j=0;j<splittArray.length;j++)
				{
					temp=temp+splittArray[j];
				}
				Liste[i]=temp;
				temp="";
			}
			return Liste;
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

	public void machFach(String name)
	{
		FileWriter fw = faecherFW(name);
		int anzahl = gebeFaecherListe().length + 1;
		if (fw != null)
		{
			try
			{
				fw.write("" + anzahl);
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
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("minh:" + mindestStunden + zumbruch + "maxh:"
					+ maximalStunden + zumbruch + "faecher:" + faecher);
			bw.close();
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

	public FileWriter faecherFW(String name)
	{
		try
		{
			return new FileWriter(new File(pfad + sep + "Faecher" + sep + name
					+ ".fach"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fehler beim Anlegen des Filewriters " + e);
			return null;
		}
	}

	/**
	 * public Lehrer auslesen(String name) { FileReader fr=new FileReader(new
	 * File(pfad + sep + "Lehrer" + sep + name)); BufferedReader br = new
	 * BufferedReader(fr); String temp=br.readLine();
	 * 
	 * return Lehrer(name,4,5); }
	 */

	public void print()
	{
		machFach("Deutsch");
		machFach("Mathe");
		machFach("Englisch");
		machLehrer("Boje");
		machLehrer("Hund");
		for (int i = 0; i < gebeLehrerListe().length; i++)
		{
			System.out.println(gebeLehrerListe()[i]);
		}
		for (int i = 0; i < gebeFaecherListe().length; i++)
		{
			System.out.println(gebeFaecherListe()[i]);
		}
	}

	public static void main(String args[])
	{
		System.out.println(System.getProperty("user.dir"));
		Datenbank testDatenbank = new Datenbank();
		testDatenbank.print();
	}
}
