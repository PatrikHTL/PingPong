package gui;

import java.awt.*;

import converter.Diagramm;
import gui.MainFrame;

public class Zeichnung extends Canvas {
	// Randbereich in %
	private static final int BORDER_PERCENT = 5;
	// xmin, xmax, deltax
	private static final double XMIN = -0.2;
	private static final double XMAX = 5 * Math.PI; // 2,5 Perioden
	private static final double DELTAX = Math.PI / 180.0; // 1�
	// ymin, ymax
	private static final double YMIN = -1.0;
	private static final double YMAX = 1.0;

	// Referenz f�r Callback
	private MainFrame fr;
	private Diagramm diag;
	private Diagramm diag2;

	public Zeichnung(MainFrame mainFrame) {
		super();
		this.fr = mainFrame;
		setBackground(Color.WHITE);
		diag = new Diagramm(this, BORDER_PERCENT, XMIN, XMAX, YMIN, YMAX);
	}

	public void paint(Graphics g) {
		double xval, yval;

		// Zeichnen der Funktionslinie
		diag.newLine();  // neue Linie beginnen
		g.setColor(Color.GREEN);
		xval = 0.0;
		switch (fr.get_zstat()) {
			case MainFrame._AM:
				while (xval <= XMAX - DELTAX) {
					//				tr娥rf					signalamp					signalf
					yval =Math.sin(fr.get_value1()*xval)*(0.5+0.01*fr.get_value2()*Math.sin(fr.get_value3()*xval));
					diag.nextLine(g, xval, yval);
					xval = xval + DELTAX;
				}
				break;
			case MainFrame._FM:
				while (xval <= XMAX - DELTAX) {
					//				tr娥rf					signalamp					signalf
					yval = Math.sin((fr.get_value1()+0.01*fr.get_value2()*Math.sin(fr.get_value3()*xval))*xval);
					diag.nextLine(g, xval, yval);
					xval = xval + DELTAX;
				}
				break;
		}

		//Zeichnen des Standardsinus
		diag.newLine();
		g.setColor(Color.RED);
		xval = 0.0;
		while (xval <= XMAX - DELTAX) {

			yval =0.01*fr.get_value2()*Math.sin(fr.get_value3()*xval);
			diag.nextLine(g, xval, yval);
			xval = xval + DELTAX;
		}

		// Zeichnen des Koordinatenkreuzes
		g.setColor(Color.BLACK);
		diag.draw_coord(g);
	}
}
