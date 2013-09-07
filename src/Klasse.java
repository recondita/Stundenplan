
public class Klasse
{
	int klassenLehrer;
	int[] fachStunden;
	int[] fachLehrer;
	int stufe;
	
	public Klasse(int stufe, int[] fachStunden, int[] fachLehrer, int klassenLehrer)
	{
		this.stufe=stufe;
		this.fachStunden=fachStunden;
		this.fachLehrer=fachLehrer;
		this.klassenLehrer=klassenLehrer;
	}
}
