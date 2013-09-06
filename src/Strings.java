public class Strings
{
	int sprache = 1;

	String name;
	String fach;
	String lehrer;
	String klasse;
	String start;
	String sprachen;
	String laeuft;
	String lehrername;
	String minstunden;
	String maxstunden;
	String faecher;
	String speichern;
	String allefelder;
	String nurzahlen;
	String minmax;

	public Strings()
	{
		if (sprache == 1)
			deutsch();
		if (sprache == 2)
			englisch();
	}

	public void deutsch()
	{
		name = "Recondita Stundenplan";
		fach = "Fach erstellen";
		lehrer = "Lehrer erstellen";
		klasse = "Klasse erstellen";
		start = "Start";
		sprachen = "Sprache";
		laeuft = "Stundenplan wird erstellt...";
		lehrername = "Name des Lehrers:";
		minstunden = "Minimale Anzahl Stunden:";
		maxstunden = "Maximale Anzahl Stunden:";
		faecher = "M�gliche F�cher:";
		speichern = "Speichern";
		allefelder = "Bitte alle Felder ausf�llen!";
		nurzahlen = "Bitte bei der Stundenanzahl nur Zahlen eingeben";
		minmax = "Maximale Stunden m�ssen die Minimalen �bertreffen";
	}

	public void englisch()
	{
		name = "Recondita Timetable";
		fach = "Create Subject";
		lehrer = "Create Teacher";
		klasse = "Create Class";
		start = "Start";
		sprachen = "Language";
		laeuft = "Timetable is going to be created";
		lehrername = "Name of the teacher:";
		minstunden = "Minimal number of lessons:";
		maxstunden = "Maximal number of lessons:";
		faecher = "Possible lessons:";
		speichern = "Save";
		allefelder = "Please fill in all Fields";
		nurzahlen = "Please only insert numbers";
		minmax = "Max must be bigger than min";
	}
}