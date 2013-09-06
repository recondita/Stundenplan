import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUILehrer extends JPanel
{
	Strings sT;
	JLabel beschreibung[] = new JLabel[4];
	JTextField eingabe[] = new JTextField[4];
	JLabel ausgabe = new JLabel();
	JButton start;

	/**
	 * @author: Felix Schütze
	 */
	public GUILehrer(Strings sT)
	{
		sT = new Strings();
		start = new JButton(sT.speichern);
		setLayout(new GridLayout(5, 2));
		for (int i = 0; i < 4; i++)
		{
			beschreibung[i] = new JLabel();
			eingabe[i] = new JTextField();
			add(beschreibung[i]);
			add(eingabe[i]);
		}
		beschreibung[0].setText(sT.lehrername);
		beschreibung[1].setText(sT.minstunden);
		beschreibung[2].setText(sT.maxstunden);
		beschreibung[3].setText(sT.faecher);
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

	public void listener()
	{
		try
		{
			if (Integer.parseInt(eingabe[1].getText()) < Integer
					.parseInt(eingabe[2].getText()))
			{
				if (!eingabe[0].getText().equals("")
						&& !eingabe[1].getText().equals("")
						&& !eingabe[2].getText().equals("")
						&& !eingabe[3].getText().equals(""))
				{
					Datenbank db = new Datenbank();
					String[] temp = eingabe[3].getText().split("\\,");
					db.schreibeLehrerEigeschaften(eingabe[0].getText(),
							Integer.parseInt(eingabe[1].getText()),
							Integer.parseInt(eingabe[2].getText()), temp);
					for (int i = 0; i < 4; i++)
					{
						eingabe[i].setText("");
					}
				} else
				{
					ausgabe.setText(sT.allefelder);
				}
			} else
			{
				ausgabe.setText(sT.minmax);
			}
		} catch (Exception e)
		{
			ausgabe.setText(sT.nurzahlen);
		}
	}
}