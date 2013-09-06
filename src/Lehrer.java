public class Lehrer {

	String name;
	int minstunden;
	int maxstunden;
	boolean [] faecher;
	
	public Lehrer(String name, int minimalstunden, int maximalstunden, boolean [] faecher)
	{
		this.name=name;
		this.minstunden=minimalstunden;
		this.maxstunden=maximalstunden;
		this.faecher=faecher;
	}
}
