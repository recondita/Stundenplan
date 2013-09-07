public class Generator
{
	GUI gUI;
	Lehrer[] lehrer;
	Klasse[] klasse;
	

	
	
	public Generator(GUI gUI, Lehrer[] lehrer, Klasse[] klassen)
	{
		this.gUI=gUI;
		this.lehrer=lehrer;
		this.klasse=klassen;
		//fach=new int[5*10*lehrer.length*klasse.length][5][10][lehrer.length][klasse.length];
	}
	
	public void generieren()
	{
		// [klasse][Wochentag][Stude]=fach;
		//boolean [][][][][] fach = new boolean[5][10][lehrer.length][klasse.length];
	}

	public void Einzeplan()	
	{
		for(int tag=0; tage<5; tage++)
		{
			for(int stunde=0; stunde<10; stunde++)
			{
				
			}
		}
	}
	
	public int[] tag(int stunde, int fach)