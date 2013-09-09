import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class GUIKlasse extends JScrollPane
{
	GUI gUI;
	Datenbank db;
	JPanel panel = new JPanel();
	int lehrer_anzahl;
	String[][] klassenliste;
	JScrollPane[] lehrer_liste;
	JButton[][] lehrer_auswahl;
	JTabbedPane tab = new JTabbedPane();
	JPanel lehrer[];
	int fach_anzahl;
	Strings sT;
	JLabel beschreibung[] = new JLabel[4];
	JTextField eingabe[] = new JTextField[3];
	JCheckBox[] auswahl;
	JPanel[] p_stufe;
	JTextField[] stufe;
	JComboBox[] lehrer_wahl;
	JComboBox klassenlehrer;
	String[] lehrerliste;
	JLabel ausgabe = new JLabel();
	JButton start;
	JPanel center = new JPanel();
	JPanel unten = new JPanel();
	JPanel mitte = new JPanel();
	JPanel leer = new JPanel();
	JPanel leer_oben = new JPanel();
	JPanel oben = new JPanel();
	JButton[] neu;
	int[] zaehler;
	JPanel neuestufe = new JPanel();
	JTextField stufeneu;

	/**
	 * @author: Felix Schütze
	 */
	public GUIKlasse(Strings sT, GUI gUI, Datenbank db)
	{
		this.db = db;
		this.gUI = gUI;
		lehrer_anzahl = (db.gebeLehrerListe().length);
		klassenliste = db.gebeKlassenListe();
		lehrer_liste = new JScrollPane[klassenliste.length];
		lehrer_auswahl = new JButton[klassenliste.length][klassenliste[0].length];
		lehrer = new JPanel[klassenliste.length];
		fach_anzahl = (db.gebeFaecherListe().length);
		auswahl = new JCheckBox[fach_anzahl];
		p_stufe = new JPanel[fach_anzahl];
		stufe = new JTextField[fach_anzahl];
		lehrer_wahl = new JComboBox[fach_anzahl];
		klassenlehrer = new JComboBox(db.gebeLehrerListe());
		lehrerliste = new String[lehrer_anzahl + 1];
		neu = new JButton[klassenliste.length];
		zaehler = new int[fach_anzahl];
		setViewportView(panel);
		sT = new Strings();
		for (int i = 0; i < lehrer_anzahl; i++)
		{
			lehrerliste[i + 1] = db.gebeLehrerListe()[i];
		}
		lehrerliste[0] = "";
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
		tab.addTab("+", neuestufe);
		neuestufe.setLayout(new BorderLayout());
		stufeneu = new JTextField(sT.neuestufe);
		stufeneu.setPreferredSize(new Dimension(25, 25));
		stufeneu.addFocusListener(new java.awt.event.FocusAdapter()
		{
			public void focusGained(java.awt.event.FocusEvent evt)
			{
				stufeneu.setText("");
			}
		});
		stufeneu.addKeyListener(new KeyListener()
		{
			public void keyTyped(KeyEvent e)
			{
			}

			public void keyReleased(KeyEvent e)
			{
			}

			public void keyPressed(KeyEvent e)
			{
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_ENTER)
				{
					neueStufe();
				}
			}
		});
		neuestufe.add("North", (stufeneu));

		for (int i = 1; i < klassenliste.length; i++)
		{
			if (klassenliste[i] != null)
			{
				lehrer_liste[i] = new JScrollPane();
				lehrer[i] = new JPanel();
				lehrer[i].setLayout(new GridLayout(lehrer_anzahl, 1));
				lehrer_liste[i].setPreferredSize(new Dimension(150, 250));
				lehrer_liste[i].setViewportView(lehrer[i]);
				tab.addTab(sT.stufe_anzeigen + " " + i, lehrer_liste[i]);
				neu[i] = new JButton(sT.neueklasse);
				lehrer[i].add(neu[i]);
				neu[i].setBackground(new Color(0, 154, 205));
				final int i2 = i;
				neu[i].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent arg0)
					{
						neueKlasse(i2);
					}
				});
				for (int j = 0; j < klassenliste[i].length; j++)
				{
					if (klassenliste[i][j] != null)
					{
						lehrer_auswahl[i][j] = new JButton();
						lehrer_auswahl[i][j].setText(klassenliste[i][j]);
						lehrer_auswahl[i][j].setBackground(Color.white);
						lehrer_auswahl[i][j]
								.addActionListener(new ActionListener()
								{
									public void actionPerformed(ActionEvent arg0)
									{

									}
								});

						lehrer[i].add(lehrer_auswahl[i][j]);
					}
				}

			}
		}
		try
		{
			tab.setSelectedIndex(1);
		} catch (Exception e)
		{
		}
		leer.add("South", (tab));
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
			if (i != 1)
			{
				oben.add(eingabe[i]);
			} else
			{
				oben.add(klassenlehrer);
			}
		}
		eingabe[2].setEditable(false);
		beschreibung[3] = new JLabel();
		leer_oben.add(beschreibung[3]);
		for (int i = 0; i < fach_anzahl; i++)
		{
			zaehler[i] = 0;
		}

		for (int i = 0; i < fach_anzahl; i++)
		{
			final int i2 = i;
			// richtigeLehrer(i);
			auswahl[i] = new JCheckBox(db.gebeFaecherListe()[i]);
			// p_stufe[i].add(auswahl[i]);
			auswahl[i].addItemListener(new ItemListener()
			{

				@Override
				public void itemStateChanged(ItemEvent e)
				{
					richtigeLehrer(i2);
					zaehler[i2]++;
					validate();
				}

			});

			p_stufe[i] = new JPanel();
			p_stufe[i].setLayout(new GridLayout(1, 2));
			stufe[i] = new JTextField(sT.stundenanzahl);

			// falls die leeren indexe störn hier weitermachen

			p_stufe[i].add(stufe[i]);
			mitte.add(auswahl[i]);
			mitte.add(p_stufe[i]);
		}
		klickLeer();
		beschreibung[0].setText(sT.klassenname);
		beschreibung[1].setText(sT.klassenlehrer);
		beschreibung[2].setText(sT.stufe);
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

	public void neueStufe()
	{
		db.neueStufe(stufeneu.getText());
		gUI.aktualisieren(2);
	}

	public void richtigeLehrer(int i)
	{
		String[] lehrerliste = db.gebeLehrerListe();
		Lehrer[] lehrer = new Lehrer[lehrerliste.length];
		for (int f = 0; f < lehrerliste.length; f++)
		{
			lehrer[f] = db.lehrerAuslesen(f);
		}
		int intStufe = Integer.parseInt(eingabe[2].getText());
		String[] fachLehrerListe = new String[lehrerliste.length];
		int fachLehrer = 0;
		for (int j = 0; j < lehrerliste.length; j++)
		{
			if ((intStufe >= lehrer[j].vonFaecher[i])
					& (intStufe <= lehrer[j].bisFaecher[i]))
			{
				fachLehrerListe[fachLehrer] = lehrerliste[j];
				fachLehrer++;
			}
		}
		String[] fachLehrerListeGekuerzt = new String[fachLehrer + 1];
		for (int k = 1; k < fachLehrerListeGekuerzt.length; k++)
		{
			fachLehrerListeGekuerzt[k] = fachLehrerListe[k - 1];
		}
		lehrer_wahl[i] = new JComboBox(fachLehrerListeGekuerzt);
		if (zaehler[i] == 0)
		{
			p_stufe[i].add(lehrer_wahl[i]);
		}
		final int i2 = i;
		lehrer_wahl[i].addItemListener(new ItemListener()
		{

			@Override
			public void itemStateChanged(ItemEvent e)
			{
				richtigeLehrer(i2);
				validate();
			}

		});
	}

	public void neueKlasse(int i2)
	{
		// gUI.aktualisieren(2);
		// tab.setSelectedIndex(tab.getSelectedIndex());
		eingabe[2].setText("" + i2);
	}

	public void listener()
	{
		// try
		// {
		if (!eingabe[0].getText().equals("")
				&& !eingabe[2].getText().equals(""))
		{
			String[] fach = new String[arrayZaehler()];
			int[] vonStufe = new int[fach_anzahl];
			String[] bisStufe = new String[arrayZaehler()];
			int f = 0;
			for (int i = 0; i < fach_anzahl; i++)
			{
				if (auswahl[i].isSelected())
				{
					String s = "";
					Object object = lehrer_wahl[i].getSelectedItem();// Hier lag der Fehler
					if (object != null)
						s = object.toString();
					else
						s = "";
					fach[f] = db.gebeFaecherListe()[i];
					bisStufe[f] = s;
					f++;
					vonStufe[db.fachToInt(db.gebeFaecherListe()[i])] = Integer
							.parseInt(stufe[i].getText());
				}
				else
				{
					vonStufe[db.fachToInt(db.gebeFaecherListe()[i])] = 0;
				}
			}
			Object object = klassenlehrer.getSelectedItem();
			db.schreibeKlassenEigeschaften(eingabe[0].getText(),
					Integer.parseInt(eingabe[2].getText()), vonStufe, bisStufe,
					object.toString());
			gUI.aktualisieren(2);

		} else
		{
			// ausgabe.setText(sT.allefelder);
		}

		// } catch (Exception e)
		// {
		// System.out.println(e);
		// }
	}

	public void klickLeer()
	{
		for (int i = 0; i < fach_anzahl; i++)
		{
			final int i2 = i;
			stufe[i].addFocusListener(new java.awt.event.FocusAdapter()
			{
				public void focusGained(java.awt.event.FocusEvent evt)
				{
					if (auswahl[i2].isSelected())
					{
						stufe[i2].setText("");
					}
				}
			});
		}
		for (int i = 0; i < fach_anzahl; i++)
		{
			final int i2 = i;
			stufe[i2].setEditable(false);
			auswahl[i].addItemListener(new ItemListener()
			{
				public void itemStateChanged(ItemEvent e)
				{
					if (e.getStateChange() == ItemEvent.SELECTED)
					{
						stufe[i2].setEditable(true);
					} else
					{
						stufe[i2].setEditable(false);
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
