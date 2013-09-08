import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUILehrer extends JScrollPane
{
	GUI gUI;
	Datenbank db = new Datenbank();
	JPanel panel = new JPanel();
	JScrollPane lehrer_liste = new JScrollPane();
	int lehrer_anzahl = (db.gebeLehrerListe().length);
	JButton[] lehrer_auswahl = new JButton[lehrer_anzahl];
	JPanel lehrer = new JPanel();
	int fach_anzahl = (db.gebeFaecherListe().length);
	Strings sT;
	JLabel beschreibung[] = new JLabel[4];
	JTextField eingabe[] = new JTextField[3];
	JCheckBox[] auswahl = new JCheckBox[fach_anzahl];
	JPanel[] p_stufe = new JPanel[fach_anzahl];
	JTextField[][] stufe = new JTextField[fach_anzahl][2];
	JButton ausgabe;
	JButton start;
	JPanel center = new JPanel();
	JPanel unten = new JPanel();
	JPanel mitte = new JPanel();
	JPanel leer = new JPanel();
	JPanel leer_oben = new JPanel();
	JPanel oben = new JPanel();
	String aktuellerName;

	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT, GUI gUI)
	{
		this.gUI = gUI;
		setViewportView(panel);
		this.sT = sT;
		start = new JButton(sT.speichern);
		panel.setLayout(new BorderLayout());
		oben.setLayout(new GridLayout(3, 2));
		unten.setLayout(new GridLayout(1, 1));
		unten.setPreferredSize(new Dimension(25, 25));
		mitte.setLayout(new GridLayout(fach_anzahl, 2));
		leer.setLayout(new BorderLayout());
		leer_oben.setPreferredSize(new Dimension(25, 25));
		leer_oben.setLayout(new GridLayout(1, 1));
		leer.add("North", (leer_oben));
		lehrer.setLayout(new GridLayout(lehrer_anzahl + 1, 1));
		lehrer_liste.setPreferredSize(new Dimension(250, 250));
		lehrer_liste.setViewportView(lehrer);
		JButton neuerLehrer = new JButton(sT.neuerlehrer);
		neuerLehrer.setBackground(new Color(0, 154, 205));
		neuerLehrer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				eingabe[0].setText("");
				aktuellerName = "";
				eingabe[1].setText("");
				eingabe[2].setText("");
				for (int j = 0; j < fach_anzahl; j++)
				{
					resetFach(j);
				}
			}
		});
		lehrer.add(neuerLehrer);
		for (int i = 0; i < lehrer_anzahl; i++)
		{
			lehrer_auswahl[i] = new JButton();
			lehrer_auswahl[i].setText(db.gebeLehrerListe()[i]);
			lehrer_auswahl[i].setBackground(Color.white);
			lehrer_auswahl[i].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0)
				{
					Object Quelle = arg0.getSource();
					for (int i = 0; i < lehrer_auswahl.length; i++)
					{
						if (Quelle == lehrer_auswahl[i])
						{
							Lehrer lehrer = db.lehrerAuslesen(lehrer_auswahl[i]
									.getText());
							eingabe[0].setText(lehrer.name);
							aktuellerName = lehrer.name;
							eingabe[1].setText(lehrer.minstunden + "");
							eingabe[2].setText(lehrer.maxstunden + "");
							for (int j = 0; j < auswahl.length; j++)
							{
								if ((lehrer.vonFaecher[j] != 0)
										& (lehrer.bisFaecher[j] != 0))
								{
									auswahl[j].setSelected(true);
									stufe[j][0].setText(lehrer.vonFaecher[j]
											+ "");
									stufe[j][1].setText(lehrer.bisFaecher[j]
											+ "");
								} else
								{
									resetFach(j);
								}
							}
						}
					}
				}
			});
			lehrer.add(lehrer_auswahl[i]);
		}
		leer.add("South", (lehrer_liste));
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
		leer_oben.add(beschreibung[3]);
		for (int i = 0; i < fach_anzahl; i++)
		{
			p_stufe[i] = new JPanel();
			p_stufe[i].setLayout(new GridLayout(1, 2));
			auswahl[i] = new JCheckBox(db.gebeFaecherListe()[i]);
			stufe[i][0] = new JTextField(sT.vonStufe);
			stufe[i][1] = new JTextField(sT.bisStufe);
			for (int f = 0; f < 2; f++)
			{
				p_stufe[i].add(stufe[i][f]);
			}
			mitte.add(auswahl[i]);
			mitte.add(p_stufe[i]);
		}
		klickLeer();
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
		ausgabe = new JButton(sT.loeschen);
		unten.add(ausgabe);
		ausgabe.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				loesche();
			}
		});
		unten.add(start);
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				listener();
			}
		});
	}

	public void loesche()
	{
		db.loescheLehrer(eingabe[0].getText());
		gUI.aktualisieren();
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
							vonStufe[f] = Integer.parseInt(stufe[i][0]
									.getText());
							bisStufe[f] = Integer.parseInt(stufe[i][1]
									.getText());
							f++;
						}
					}
					db.schreibeLehrerEigeschaften(eingabe[0].getText(),
							Integer.parseInt(eingabe[1].getText()),
							Integer.parseInt(eingabe[2].getText()), fach,
							vonStufe, bisStufe);

					if (!eingabe[0].getText().equals(aktuellerName))
					{
						db.loescheLehrer(aktuellerName);
					}
					aktuellerName = "";
					for (int i = 0; i < 3; i++)
					{
						eingabe[i].setText("");
					}
					for (int i = 0; i < fach_anzahl; i++)
					{
						auswahl[i].setSelected(false);
					}
					for(int i=0;i<fach_anzahl;i++)
					{
						stufe[i][0].setText(sT.vonStufe);
						stufe[i][1].setText(sT.bisStufe);
					}
					gUI.aktualisieren();

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

	public void resetFach(int j)
	{
		stufe[j][0].setText(sT.vonStufe);
		stufe[j][1].setText(sT.bisStufe);
		auswahl[j].setSelected(false);
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
						if (auswahl[i2].isSelected())
						{
							stufe[i2][f2].setText("");
						}
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
					if (e.getStateChange() == ItemEvent.SELECTED)
					{
						stufe[i2][0].setEditable(true);
						stufe[i2][1].setEditable(true);
					} else
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