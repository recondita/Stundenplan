import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUILehrer extends JPanel
{
	Datenbank db = new Datenbank();
	Strings sT;
	JLabel beschreibung[] = new JLabel[4];
	JTextField eingabe[] = new JTextField[4];
	JLabel ausgabe = new JLabel();
	JButton start;
	JComboBox auswahl = new JComboBox();
	JPanel p = new JPanel();
	String Fach_liste = "";

	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT)
	{
		sT = new Strings();
		start = new JButton(sT.speichern);
		setLayout(new GridLayout(5, 2));
		p.setLayout(new GridLayout(1, 2));
		for (int i = 0; i < 3; i++)
		{
			beschreibung[i] = new JLabel();
			eingabe[i] = new JTextField();
			add(beschreibung[i]);
			add(eingabe[i]);
		}
		beschreibung[3] = new JLabel();
		eingabe[3] = new JTextField();
		add(beschreibung[3]);
		p.add(auswahl);
		add(p);
		p.add(eingabe[3]);
		for (int i = 0; i < db.gebeFaecherListe().length; i++)
		{
			auswahl.addItem(db.gebeFaecherListe()[i]);
		}
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
		dropDown();
		add(ausgabe);
		add(start);
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				listener();
			}
		});
	}

	public void dropDown()
	{
		auswahl.addItemListener(new ItemListener()
		{
			public void itemStateChanged(ItemEvent e)
			{
				JComboBox selectedChoice = (JComboBox) e.getSource();
				Fach_liste=Fach_liste+", "+selectedChoice.getSelectedItem();
				eingabe[3].setText(Fach_liste);
				validate();
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
						&& !eingabe[2].getText().equals("")
						&& !eingabe[3].getText().equals(""))
				{
					String[] temp = eingabe[3].getText().split("\\, ");
					db.schreibeLehrerEigeschaften(eingabe[0].getText(),
							Integer.parseInt(eingabe[1].getText()),
							Integer.parseInt(eingabe[2].getText()), temp);
					for (int i = 0; i < 4; i++)
					{
						eingabe[i].setText("");
					}
					Fach_liste="";
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