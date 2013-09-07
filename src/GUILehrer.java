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
	JPanel[] p_stufe = new JPanel[fach_anzahl];
	JTextField[][] stufe = new JTextField[fach_anzahl][2];
	JLabel ausgabe = new JLabel();
	JButton start;
	JPanel center = new JPanel();
	JPanel unten = new JPanel();
	JPanel mitte = new JPanel();
	JPanel leer = new JPanel();
	JPanel oben = new JPanel();

	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT)
	{
		setViewportView(panel);
		sT = new Strings();
		start = new JButton(sT.speichern);
		panel.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(3, 2));
		unten.setLayout(new GridLayout(1, 1));
		unten.setPreferredSize(new Dimension(25, 25));
		mitte.setLayout(new GridLayout(fach_anzahl * 2, 1));
		leer.setLayout(new GridLayout(fach_anzahl * 2, 1));
		center.setLayout(new GridLayout(1, 2));
		center.add(leer);
		center.add(mitte);
		oben.setPreferredSize(new Dimension(100, 100));
		panel.add("North", (oben));
		panel.add("Center", (center));
		panel.add("South", (unten));
		for (int i = 0; i < 3; i++)
		{
			beschreibung[i] = new JLabel();
			eingabe[i] = new JTextField();
			oben.add(beschreibung[i]);
			oben.add(eingabe[i]);
		}
		beschreibung[3] = new JLabel();
		leer.add(beschreibung[3]);
		for (int i = 0; i < fach_anzahl; i++)
		{
			p_stufe[i] = new JPanel();
			p_stufe[i].setLayout(new GridLayout(1, 4));
			auswahl[i] = new JCheckBox(db.gebeFaecherListe()[i]);
			stufe[i][0] = new JTextField(sT.vonStufe);
			stufe[i][1] = new JTextField(sT.bisStufe);
			for (int f = 0; f < 2; f++)
			{
				p_stufe[i].add(stufe[i][f]);
			}
			for (int f = 0; f < 2; f++)
			{
				p_stufe[i].add(new JPanel());
			}
			mitte.add(auswahl[i]);
			mitte.add(p_stufe[i]);
		}
		klickLeer();
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
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
					String[] fach = new String[arrayZaehler()];
					int[] vonStufe = new int[arrayZaehler()];
					int[] bisStufe = new int[arrayZaehler()];
					int f = 0;
					for (int i = 0; i < fach_anzahl; i++)
					{
						if (auswahl[i].isSelected())
						{
							fach[f] = db.gebeFaecherListe()[i];
							f++;
						}
					}
					db.schreibeLehrerEigeschaften(eingabe[0].getText(),
							Integer.parseInt(eingabe[1].getText()),
							Integer.parseInt(eingabe[2].getText()), fach,
							vonStufe, bisStufe);
					for (int i = 0; i < 3; i++)
					{
						eingabe[i].setText("");
					}
					for (int i = 0; i < fach_anzahl; i++)
					{
						auswahl[i].setSelected(false);
					}

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

	public void klickLeer()
	{
		for (int i = 0; i < fach_anzahl; i++)
		{
			for (int f = 0; f < 2; f++)
			{
				final int i2 = i;
				final int f2 = f;
				stufe[i][f].addFocusListener(new java.awt.event.FocusAdapter()
				{
					public void focusGained(java.awt.event.FocusEvent evt)
					{

						stufe[i2][f2].setText("");
					}
				});
			}
		}
		for (int i = 0; i < fach_anzahl; i++)
		{
			final int i2 = i;
			stufe[i2][0].setEditable(false);
			stufe[i2][1].setEditable(false);
			auswahl[i].addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if(e.getStateChange() == ItemEvent.SELECTED)
					{
						stufe[i2][0].setEditable(true);
						stufe[i2][1].setEditable(true);
					}
					else
					{
						stufe[i2][0].setEditable(false);
						stufe[i2][1].setEditable(false);
					}
				}
			});
		}
	}

	public int arrayZaehler()
	{
		int f = 0;
		for (int i = 0; i < fach_anzahl; i++)
		{
			if (auswahl[i].isSelected())
			{
				f++;
			}
		}
		return f;
	}
}