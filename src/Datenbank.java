/**
 @author: Felix Schütze, Jan Hofmeier
 */
import java.io.*;

public class Datenbank
{
	String sep = System.getProperty("file.separator");
	String zumbruch = System.getProperty("line.separator");
	String pfad;

	private String[] faecherListe;
	private String[] lehrerListe;

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

	public void aktualisiereFaecherListe()
	{
		faecherListe = leseFaecherListe();
	}

	public void aktualisiereLehrerListe()
	{
		faecherListe = leseLehrerListe();
	}

	private String[] leseLehrerListe()
	{
		try
		{
			String[] Liste = new String[lehrerVerzeichnis.list().length];
			for (int i = 0; i < lehrerVerzeichnis.list().length; i++)
			{
				String[] splittArray = lehrerVerzeichnis.list()[i]
						.split("\\.lehrer");
				String temp = "";
				for (int j = 0; j < splittArray.length; j++)
				{
					temp = temp + splittArray[j];
				}
				Liste[i] = temp;
				temp = "";
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
		if (faecherListe == null)
		{
			aktualisiereFaecherListe();
		}
		return faecherListe.clone();
	}

	public String[] gebeLehrerListe()
	{
		if (lehrerListe == null)
		{
			aktualisiereLehrerListe();
		}
		return lehrerListe.clone();
	}

	private String[] leseFaecherListe()
	{
		try
		{
			String[] Liste = new String[faecherVerzeichnis.list().length];
			for (int i = 0; i < faecherVerzeichnis.list().length; i++)
			{
				String[] splittArray = faecherVerzeichnis.list()[i]
						.split("\\.fach");
				String temp = "";
				for (int j = 0; j < splittArray.length; j++)
				{
					temp = temp + splittArray[j];
				}
				Liste[i] = temp;
				temp = "";
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
		FileWriter fw = fachFW(name);
		int anzahl = gebeFaecherListe().length;
		if (fw != null)
		{
			try
			{
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write("" + anzahl);
				bw.close();
			} catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			aktualisiereFaecherListe();
		}
	}

	public void schreibeLehrerEigeschaften(String name, int mindestStunden,
			int maximalStunden, String[] fach, int[] vonStufe, int[] bisStufe)
	{
		String faecher = "";
		for (int i = 0; i < fach.length; i++)
		{
			faecher = faecher + fach[i] + "," + vonStufe[i] + "-" + bisStufe[i]
					+ ",";
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

	public void schreibeKlassenEigeschaften(String name, int stufe,
			int fachStunden[], String fachLehrer[], String klassenlehrer)
	{

		String temp = "Klassenlehrer:" + klassenlehrer + zumbruch;

		String fachListe[] = gebeFaecherListe();

		if ((fachStunden.length < fachListe.length)
				| (fachLehrer.length < fachListe.length))
		{
			System.out.println("Arrays zu klein --> Felix ist Schuld");
		}

		for (int i = 0; i < fachListe.length; i++)
		{
			temp = temp + fachListe[i] + ":" + fachStunden[i] + ","
					+ fachLehrer[i] + zumbruch;
		}

		FileWriter fw = klassenFW(stufe, name);
		try
		{
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(temp);
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

	public FileWriter fachFW(String name)
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

	public FileWriter klassenFW(int stufe, String name)
	{
		try
		{
			return new FileWriter(new File(pfad + sep + "Klassen" + sep + stufe
					+ sep + name + ".klasse"));
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Fehler beim Anlegen des Filewriters " + e);
			return null;
		}
	}

	public int fachToInt(String name)
	{
		int f = 0;
		String[] faecherListe = gebeFaecherListe();
		while (!faecherListe[f].equals(name))
		{
			f++;
			if (f >= faecherListe.length)
			{
				return -1;
			}
		}
		return f;
	}

	public int lehrerToInt(String name)
	{
		int f = 0;
		String[] liste = gebeLehrerListe();
		while (!liste[f].equals(name))
		{
			f++;
			if (f >= liste.length)
			{
				return -1;
			}
		}
		return f;
	}

	public Lehrer lehrerAuslesen(String name)
	{
		String tempminh = null;
		String tempmaxh = null;
		String[] tempfaecher = new String[1];
		try
		{
			FileReader fr = new FileReader(new File(pfad + sep + "Lehrer" + sep
					+ name + ".lehrer"));
			BufferedReader br = new BufferedReader(fr);
			String temp = br.readLine();
			while (temp != null)
			{
				String split[] = temp.split("\\:");
				if (split.length > 1)
				{
					if (split[0].equals("minh"))
					{
						tempminh = split[1];
					} else
					{
						if (split[0].equals("maxh"))
						{
							tempmaxh = split[1];
						} else
						{
							if (split[0].equals("faecher"))
							{
								tempfaecher = split[1].split("\\,");
							}
						}
					}
				}
				temp = br.readLine();

			}
			br.close();
		} catch (Exception e)
		{
			System.out.println(e);
			tempminh = null;
		}

		int[] vonFaecher = new int[gebeFaecherListe().length];
		int[] bisFaecher = new int[gebeFaecherListe().length];

		for (int i = 0; i < vonFaecher.length; i++)
		{
			vonFaecher[0] = 0;
			bisFaecher[0] = 0;
		}

		for (int i = 0; i < tempfaecher.length; i = i + 3)
		{
			int index = fachToInt(tempfaecher[i]);
			vonFaecher[index] = Integer.parseInt(tempfaecher[i + 1]);
			bisFaecher[index] = Integer.parseInt(tempfaecher[i + 2]);
		}

		return new Lehrer(name, Integer.parseInt(tempminh),
				Integer.parseInt(tempmaxh), vonFaecher, bisFaecher);
	}

	public Klasse klasseAuslesen(int stufe, String name)
	{
		int klassenLehrer=-1;
		int[] fachStunden= new int[gebeFaecherListe().length];
		int[] fachLehrer= new int[gebeFaecherListe().length];
		try
		{
			FileReader fr = new FileReader(new File(pfad + sep + "Klassen"
					+ sep + stufe + sep + name + ".lehrer"));
			BufferedReader br = new BufferedReader(fr);
			String temp = br.readLine();
			while (temp != null)
			{
				String split[] = temp.split("\\:");
				if(split[0].equals("Klassenlehrer"))
				{
					klassenLehrer=lehrerToInt(split[1]);
				}
				else
				{
					int index=fachToInt(split[0]);
					if(index>=0)
					{
						String fachSplit []= split[1].split("\\,");
						fachStunden[index]=Integer.parseInt(fachSplit[0]);
						if(fachSplit.length>1)
						{
							fachLehrer[index]=lehrerToInt(fachSplit[1]);
						}
					}
				}
				br.readLine();
			}
			br.close();

			return new Klasse(stufe,fachStunden, fachLehrer, klassenLehrer);
		} catch (Exception e)
		{
			return null;
		}
		
	}

	public void print()
	{
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
		Lehrer testLehrer = testDatenbank.lehrerAuslesen("Scheibe");
		System.out.println(testLehrer);
	}
}
