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
	Strings sT = new Strings();

	public Menuleiste()
	{
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

			}
		});
		sprachen.add(deutsch);
		Menuleiste.add(sprachen);

		return Menuleiste;
	}

}
