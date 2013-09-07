public class Lehrer {

	String name;
	int minstunden;
	int maxstunden;
	int [] vonFaecher;
	int [] bisFaecher;
	
	public Lehrer(String name, int minimalstunden, int maximalstunden,int [] vonFaecher, int [] bisFaecher)
	{
		this.name=name;
		this.minstunden=minimalstunden;
		this.maxstunden=maximalstunden;
		this.vonFaecher=vonFaecher;
		this.bisFaecher=bisFaecher;
	}
}
