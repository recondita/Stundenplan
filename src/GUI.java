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
	JPanel po = new JPanel();
	JPanel pc = new JPanel();
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
		setSize(800, 800);
		setResizable(true);
		setMenuBar(mL.ml());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(sT.name);
		pu.setPreferredSize(new Dimension(50, 50));
		po.setPreferredSize(new Dimension(20, 20));
		oben();
		unten();
		add("North", (po));
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

	public void oben()
	{
		po.setLayout(new GridLayout(1, 3));
		po.add(fach);
		fach.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				modul(gUIFACH);
			}
		});
		po.add(lehrer);
		lehrer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				modul(gUILEHRER);
			}
		});
		po.add(klasse);
		klasse.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				modul(gUIKLASSE);
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
		pc.removeAll();
		pc.add(j);
		validate();
	}

	public static void main(String args[])
	{
		new GUI();
	}
}
