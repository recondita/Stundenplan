import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUIFach extends JPanel
{
	Strings sT;
	JPanel p = new JPanel();
	Datenbank db = new Datenbank();
	JLabel beschreibung;
	JTextField eingabe = new JTextField();
	JPanel leer = new JPanel();
	JButton speichern;
	GUI gUI;

	/**
	 * @author: Felix Schütze
	 */
	public GUIFach(Strings sT, GUI gUI)
	{
		this.gUI = gUI;
		p.setPreferredSize(new Dimension(80, 80));
		this.sT = sT;
		beschreibung = new JLabel(sT.facheingeben);
		speichern = new JButton(sT.speichern);
		setLayout(new BorderLayout());
		p.setLayout(new GridLayout(2, 2));
		add("North", (p));
		p.add(beschreibung);
		p.add(eingabe);
		p.add(leer);
		p.add(speichern);
		speichern.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				listener();
			}
		});
	}

	public void listener()
	{
		if (!eingabe.getText().equals(""))
		{
			db.machFach(eingabe.getText());
			eingabe.setText("");
			db.aktualisiereKlassenListe();
			db.aktualisiereFaecherListe();
			db.aktualisiereLehrerListe();
			setVisible(false);
			validate();
			setVisible(true);
		}
	}
}
