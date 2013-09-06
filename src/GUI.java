import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI extends JFrame
{
	/**
	 * Eine GUI in Ehren kann keiner verwehren!
	 * by me;
	 */
	Strings sT = new Strings();
	GUIFach gUIFACH=new GUIFach(sT);
	GUIKlasse gUIKLASSE=new GUIKlasse(sT);
	GUILehrer gUILEHRER=new GUILehrer(sT);
	Kontroller kONTROLLER;
	Menuleiste mL = new Menuleiste();
	JTabbedPane pc = new JTabbedPane();
	JPanel pu = new JPanel();
	JButton fach = new JButton(sT.fach);
	JButton lehrer = new JButton(sT.lehrer);
	JButton klasse = new JButton(sT.klasse);
	JButton start = new JButton(sT.start);
	JLabel laeuft = new JLabel(sT.laeuft);

	public GUI()
	{
		setLayout(new BorderLayout());
		setLocation(200, 50);
		setSize(800, 300);
		setResizable(true);
		setMenuBar(mL.ml());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(sT.name);
		pu.setPreferredSize(new Dimension(50, 50));
		pc.addTab("Fach erstellen", gUIFACH);
		pc.addTab("Lehrer erstellen", gUILEHRER);
		pc.addTab("Klasse erstellen", gUIKLASSE);
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
		kONTROLLER = new Kontroller(this);
		start.setEnabled(false);
		modul(laeuft);
	}

	public void modul(JComponent j)
	{
		remove(pc);
		pc.removeAll();
		pc.add(j);
		add("Center", (pc));
		validate();
	}

	public static void main(String args[])
	{
		new GUI();
	}
}
