public class Generator
{
	GUI gUI;
	Lehrer[] lehrer;
	Klasse[][] klasse;
	
	//Lehrer,Tag,Stunde
	boolean [][][] lehrerFrei;
	int [][] prioritaetLehrer;
	
	
	public Generator(GUI gUI, Lehrer[] lehrer, Klasse[][] klasse, Fach[] Fach)
	{
		this.gUI=gUI;
		this.lehrer=lehrer;
		this.klasse=klasse;
		//fach=new int[5*10*lehrer.length*klasse.length][5][10][lehrer.length][klasse.length];
		lehrerFrei=new boolean[lehrer.length][5][10];
		prioritaetLehrer= new int[klasse.length][lehrer.length];
	}
	
	public void generieren()
	{
		// [klasse][Wochentag][Stude]=fach;
		//boolean [][][][][] fach = new boolean[5][10][lehrer.length][klasse.length];
	}

}