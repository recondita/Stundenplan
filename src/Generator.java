public class Generator
{
	GUI gUI;
	Lehrer[] lehrer;
	Klasse[][] klassen;
	Fach[] faecher;

	// Lehrer,Tag,Stunde
	boolean[][][] lehrerFrei;
	int[][] prioritaetLehrer;

	public Generator(GUI gUI, Datenbank db)
	{
		this.gUI = gUI;
		lehrer = new Lehrer[db.gebeLehrerListe().length];
		for (int i = 0; i < db.gebeLehrerListe().length; i++)
		{
			lehrer[i] = db.lehrerAuslesen(i);
		}
		faecher = new Fach[db.gebeFaecherListe().length];
		for (int i = 0; i < db.gebeFaecherListe().length; i++)
		{
			faecher[i] = db.fachAuslesen(i);
		}
		klassen = new Klasse[db.gebeKlassenListe().length][db
				.gebeKlassenListe()[0].length];
		for (int i = 0; i < db.gebeKlassenListe().length; i++)
		{
			if (db.gebeKlassenListe()[i] != null)
			{
				for (int j = 0; j < db.gebeKlassenListe()[i].length; j++)
				{

					klassen[i][j] = db.klasseAuslesen(i,
							db.gebeKlassenListe()[i][j]);
				}
			}
		}

		// fach=new
		// int[5*10*lehrer.length*klasse.length][5][10][lehrer.length][klasse.length];
		// lehrerFrei=new boolean[lehrer.length][5][10];
		// prioritaetLehrer= new int[klasse.length][lehrer.length];
	}

	public void generieren()
	{
		// [klasse][Wochentag][Stude]=fach;
		// boolean [][][][][] fach = new
		// boolean[5][10][lehrer.length][klasse.length];
	}

	public int[][] zufallsFaecherPlan(int stufe, int klasse)
	{
		int[] fachStunden = klassen[stufe][klasse].fachStunden.clone();
		int[][] stunden = new int[10][5];
		for (int i = 0; i < stunden.length; i++)
		{
			for (int j = 0; j < stunden[j].length; j++)
			{
				int[] fachIndex = new int[faecher.length];
				int verfuegbar = 0;
				for (int k = 0; k < fachStunden.length; k++)
				{
					if (fachStunden[k] > 0)
					{
						fachIndex[verfuegbar] = k;
						verfuegbar++;
					}
				}
				if (verfuegbar > 0)
				{
					int fach = fachIndex[(int) Math.random() * verfuegbar];
					stunden[i][j] = fach;
					fachStunden[fach]--;
				}
			}

		}
		return stunden;
	}

	public int[][] lehrerPlan(int stufe, int klasse, int[][] faecherPlan)
	{
		int[][] lehrerPlan = new int[faecherPlan.length][faecherPlan[0].length];
		for (int i = 0; i < lehrerPlan.length; i++)
		{
			for (int j = 0; j < lehrerPlan[j].length; j++)
			{
				if (klassen[stufe][klasse].fachLehrer[faecherPlan[i][j]] >= 0)
				{
					lehrerPlan[i][j] = klassen[stufe][klasse].fachLehrer[faecherPlan[i][j]];
				} else
				{
					// soll später für
					// performence
					// ausgelagert und
					// nurnoch ein,al
					// ausgeführt werden
					int[] moeglicheLehrer = new int[lehrer.length];
					int menge = 0;
					for (int k = 0; k < lehrer.length; k++)
					{
						if ((lehrer[k].vonFaecher[faecherPlan[i][j]] >= stufe)
								& (lehrer[k].bisFaecher[faecherPlan[i][j]] <= stufe))
						{
							moeglicheLehrer[menge] = k;
							menge++;
						}
					}
					int zufall = (int) Math.random() * menge;
					lehrerPlan[i][j] = moeglicheLehrer[zufall];
				}
			}
		}

		return lehrerPlan;
	}
}