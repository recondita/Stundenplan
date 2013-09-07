import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Beschreiben Sie hier die Klasse Menuleiste.
 * 
 * @author Felix Sch&uuml;tze
 * @version (eine Versionsnummer oder ein Datum)
 */
public class Menuleiste
{
	Strings sT;
	GUI gUI;

	public Menuleiste(Strings sT, GUI gUI)
	{
		this.sT = sT;
		this.gUI = gUI;
		ml();
	}

	public MenuBar ml()
	{ // Menüleiste anlegen
		MenuBar Menuleiste = new MenuBar(); // Eine Menüleiste anlegen
		Menu sprachen = new Menu(sT.sprachen);
		MenuItem deutsch = new MenuItem("Deutsch");
		deutsch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				spracheAendern(1);
				gUI.dispose();
				new GUI();
			}
		});
		sprachen.add(deutsch);
		MenuItem englisch = new MenuItem("English");
		englisch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				spracheAendern(2);
				gUI.dispose();
				new GUI();
			}
		});
		sprachen.add(englisch);
		Menuleiste.add(sprachen);

		return Menuleiste;
	}

	public void spracheAendern(int s)
	{
		try
		{
			FileWriter fw = new FileWriter("sprache.sp");
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(""+s);
			bw.close();
		} catch (Exception e)
		{

		}
	}
}
