import javax.swing.*;

import java.awt.*;

public class GUILehrer extends JPanel
{
	Strings sT;
	JLabel beschreibung[]=new JLabel[4];
	JTextField eingabe[]=new JTextField[4];
	JLabel ausgabe=new JLabel();
    JButton start;
	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT)
	{
		sT= new Strings();
		start=new JButton(sT.start);
		setLayout(new GridLayout(5,2));
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
		for(int i=0;i<4;i++)
		{
			beschreibung[i]=new JLabel();
			eingabe[i]=new JTextField();
			add(beschreibung[i]);
			add(eingabe[i]);
		}
		add(ausgabe);
		add(start);
		
	}
}
//(String name, int mindestStunden,
//int maximalStunden, String[] fach)