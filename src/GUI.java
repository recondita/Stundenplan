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
	Strings sT = new Strings();
	GUIKlasse gUIKLASSE=new GUIKlasse(sT, this);
	GUILehrer gUILEHRER=new GUILehrer(sT, this);
	GUIFach gUIFACH=new GUIFach(sT, this);
	Generator gENERATOR;
	Menuleiste mL = new Menuleiste(sT, this);
	JTabbedPane pc = new JTabbedPane();
	JPanel pu = new JPanel();
	JButton fach = new JButton(sT.fach);
	JButton lehrer = new JButton(sT.lehrer);
	JButton klasse = new JButton(sT.klasse);
	JButton start = new JButton(sT.start);
	JLabel laeuft = new JLabel(sT.laeuft);

	public GUI()
	{
		UIManager.put("TabbedPane.contentBorderInsets", new InsetsUIResource(10,10,5,5)); 
		//UIManager.put("TabbedPane.selected", Color.blue);
		pc.updateUI();
		setLayout(new BorderLayout());
		setLocation(200, 50);
		setSize(900, 700);
		setResizable(true);
		setMenuBar(mL.ml());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle(sT.name);
		laeuft.setHorizontalAlignment(JLabel.CENTER);
		laeuft.setVerticalAlignment(JLabel.CENTER);
		pu.setPreferredSize(new Dimension(50, 50));
		pc.addTab(sT.fach, gUIFACH);
		pc.addTab(sT.lehrer, gUILEHRER);
		pc.addTab(sT.klasse, gUIKLASSE);
		pc.setBackgroundAt(0, new Color(0,154,205));
		pc.setBackgroundAt(1, new Color(0,178,238));
		pc.setBackgroundAt(2, new Color(0,191,255));
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
		validate();
	}

	public static void main(String args[])
	{
		new GUI();
	}
}
