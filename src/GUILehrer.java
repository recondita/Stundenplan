import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUILehrer extends JScrollPane
{
	JPanel panel = new JPanel();
	Datenbank db = new Datenbank();
	int fach_anzahl = (db.gebeFaecherListe().length);
	Strings sT;
	JLabel beschreibung[] = new JLabel[4];
	JTextField eingabe[] = new JTextField[3];
	JCheckBox[] auswahl = new JCheckBox[fach_anzahl];
	JLabel ausgabe = new JLabel();
	JButton start;
	JPanel unten = new JPanel();
	JPanel mitte = new JPanel();
	JPanel oben = new JPanel();
	String Fach_liste = "";

	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT)
	{
		setViewportView(panel);
		// p[0] = new JPanel();
		sT = new Strings();
		start = new JButton(sT.speichern);
		panel.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(3, 2));
		unten.setLayout(new GridLayout(1, 1));
		unten.setPreferredSize(new Dimension(25, 25));
		mitte.setLayout(new GridLayout(fach_anzahl, 2));
		oben.setPreferredSize(new Dimension(100, 100));
		panel.add("North", (oben));
		panel.add("Center", (mitte));
		panel.add("South", (unten));
		// p[0].setLayout(new GridLayout(1, 2));
		for (int i = 0; i < 3; i++)
		{
			beschreibung[i] = new JLabel();
			eingabe[i] = new JTextField();
			oben.add(beschreibung[i]);
			oben.add(eingabe[i]);
		}
		beschreibung[3] = new JLabel();
		// eingabe_fach[0] = new JTextField();
		mitte.add(beschreibung[3]);
		// auswahl[0] = new JComboBox();
		// p[0].add(auswahl[0]);
		// mitte.add(p[0]);
		// p[0].add(eingabe_fach[0]);
		for (int i = 0; i < fach_anzahl; i++)
		{
			auswahl[i] = new JCheckBox(db.gebeFaecherListe()[i]);
			if (i != 0)
			{
				mitte.add(new JPanel());
			}
			mitte.add(auswahl[i]);
		}
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
		// dropDown();
		unten.add(ausgabe);
		unten.add(start);
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				listener();
			}
		});
	}

	public void listener()
	{
		try
		{
			if (Integer.parseInt(eingabe[1].getText()) <= Integer
					.parseInt(eingabe[2].getText()))
			{
				if (!eingabe[0].getText().equals("")
						&& !eingabe[1].getText().equals("")
						&& !eingabe[2].getText().equals(""))
				{
					int f = 0;
					for (int i = 0; i < fach_anzahl; i++)
					{
						if (auswahl[i].isSelected())
						{
							f++;
						}
					}
					String[] temp = new String[f];
					for (int i = 0; i < fach_anzahl; i++)
					{
						if (auswahl[i].isSelected())
						{
							temp[i] = db.gebeFaecherListe()[i];
						}
					}
					db.schreibeLehrerEigeschaften(eingabe[0].getText(),
							Integer.parseInt(eingabe[1].getText()),
							Integer.parseInt(eingabe[2].getText()), temp);
					for (int i = 0; i < 3; i++)
					{
						eingabe[i].setText("");
					}
					for (int i = 0; i < fach_anzahl; i++)
					{
						auswahl[i].setSelected(false);
					}
					Fach_liste = "";
				} else
				{
					// ausgabe.setText(sT.allefelder);
				}
			} else
			{
				// ausgabe.setText(sT.minmax);
			}
		} catch (Exception e)
		{
			// ausgabe.setText(sT.nurzahlen);
		}
	}
}