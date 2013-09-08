import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.plaf.InsetsUIResource;

public class GUI extends JFrame
{
	/**
	 * Eine GUI in Ehren kann keiner verwehren!
	 * by me;
	 * Das DropDown Menü ist Dynamisch, aber gelöscht
	 */
	Datenbank db=new Datenbank();
	Strings sT = new Strings();
	Generator gENERATOR;
	//GUIKlasse gUIKLASSE=new GUIKlasse(sT, this, db);
	//GUIFach gUIFach=new GUIFach(sT, this, db);
	//GUILehrer gUILEHRER=new GUILehrer(sT, this, db);
	Menuleiste mL = new Menuleiste(sT, this);
	JTabbedPane pc = new JTabbedPane();
	JPanel pu = new JPanel();
	JButton fach = new JButton(sT.fach);
	JButton lehrer = new JButton(sT.lehrer);
	JButton klasse = new JButton(sT.klasse);
	JButton start = new JButton(sT.start);
	JLabel laeuft = new JLabel(sT.laeuft);
	JProgressBar fortschritt =new JProgressBar();

	public GUI()
	{
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(10,10,5,5)); 
		//UIManager.put("TabbedPane.selected", Color.blue);
		pc.updateUI();
		setLayout(new BorderLayout());
		setLocation(200, 50);
		setSize(1100, 700);
		setResizable(true);
		setMenuBar(mL.ml());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(sT.name);
		laeuft.setHorizontalAlignment(JLabel.CENTER);
		laeuft.setVerticalAlignment(JLabel.CENTER);
		pu.setPreferredSize(new Dimension(50, 50));
		pc.addTab(sT.fach, new GUIFach(sT, this, db));
		pc.addTab(sT.lehrer, new GUILehrer(sT, this, db));
		pc.addTab(sT.klasse, new GUIKlasse(sT, this, db));
		unten();
		add("South", (pu));
		add("Center", (pc));
		setVisible(true);
	}

	public void unten()
	{
		pu.add(start);
		start.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				start();
			}
		});
	}

	public void start()
	{
		//gENERATOR = new Generator(this);
		start.setEnabled(false);
		remove(pc);
		add("Center", (laeuft));
		add("North",(fortschritt));
		fortschritt.setValue(10);
		validate();
	}
	
	public void aktualisieren()
	{
		db.aktualisiereKlassenListe();
		db.aktualisiereFaecherListe();
		db.aktualisiereLehrerListe();
		pc.removeAll();
		pc.addTab(sT.fach, new GUIFach(sT, this, db));
		pc.addTab(sT.lehrer, new GUILehrer(sT, this, db));
		pc.addTab(sT.klasse, new GUIKlasse(sT, this, db));
	}

	public static void main(String args[])
	{
		new GUI();
	}
}
