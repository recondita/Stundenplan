public class Generator
{
	GUI gUI;
	Lehrer[] lehrer;
	Klasse[][] klassen;
	Fach[] faecher;
	
	//Lehrer,Tag,Stunde
	boolean [][][] lehrerFrei;
	int [][] prioritaetLehrer;
	
	
	public Generator(GUI gUI, Lehrer[] lehrer, Klasse[][] klasse, Fach[] fach)
	{
		this.gUI=gUI;
		this.lehrer=lehrer;
		this.klassen=klasse;
		this.faecher=fach;
		//fach=new int[5*10*lehrer.length*klasse.length][5][10][lehrer.length][klasse.length];
		//lehrerFrei=new boolean[lehrer.length][5][10];
		//prioritaetLehrer= new int[klasse.length][lehrer.length];
	}
	
	public void generieren()
	{
		// [klasse][Wochentag][Stude]=fach;
		//boolean [][][][][] fach = new boolean[5][10][lehrer.length][klasse.length];
	}

	
	private Stundenplan generiereZufallsPlan(int stufe, int klasse)
	{
		int [] fachStunden= klassen[stufe][klasse].fachStunden.clone();
		int[][] stunden=new int[10][5];
		for (int i=0; i< stunden.length; i++)
		{
			for(int j=0; i<stunden[j].length; j++)
			{
				int[]fachIndex= new int [faecher.length];
				int verfuegbar=0;
				for(int k=0; k<fachStunden.length;k++)
				{
					if(fachStunden[k]>0)
					{
						fachIndex[verfuegbar]=k;
						verfuegbar++;
					}
				}
				if(verfuegbar>0)
				{
				int fach=fachIndex[(int)Math.random()*verfuegbar];
				stunden[i][j]=fach;
				fachStunden[fach]--;				
			}
		}
		
		
		return null;
	}
}