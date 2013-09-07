import java.awt.*;
import java.awt.event.*;

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
		this.sT=sT;
		this.gUI=gUI;
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
				sT.sprache=1;
				System.err.println("Warum wird dieser ActionListener nicht ausgeführt?");
				gUI.repaint();
			}
		});
		sprachen.add(deutsch);
		MenuItem englisch = new MenuItem("English");
		englisch.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				sT.sprache=2;
				gUI.repaint();
			}
		});
		sprachen.add(englisch);
		Menuleiste.add(sprachen);

		return Menuleiste;
	}

}
