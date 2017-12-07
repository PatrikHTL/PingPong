package gui;

import java.awt.*;

import PingPong.Ball;
import PingPong.Schlaeger;
import converter.Diagramm;
import gui.MainFrame;

public class Zeichnung extends Canvas {
	// Randbereich in %
	private static final int BORDER_PERCENT = 5;
	// xmin, xmax, deltax

	// Referenz f�r Callback
	private MainFrame fr;
	private Diagramm diag;
	private Schlaeger  meinSchlaeger, gegnerSchlaeger;
	private Ball ball;

	public Zeichnung(MainFrame mainFrame) {
		super();
		this.fr = mainFrame;
		setBackground(Color.WHITE);
		diag = new Diagramm(this, BORDER_PERCENT, -1, 1, -0.5, 0.5);
		this.meinSchlaeger=mainFrame.meinSchlaeger;
		this.gegnerSchlaeger=mainFrame.gegnerSchlaeger;
		this.ball=fr.ball;
	}

	public void paint(Graphics g) {
		double xval, yval;


		// Zeichnen der Funktionslinie
		//diag.newLine();  // neue Linie beginnen
		//g.setColor(Color.GREEN); 	//Color bestimmen
		//g.drawRect(100,120,10,10); //rechteck zeichnen
		//diag.nextLine(g, xval, yval);	//linie fortsetzen


		// Zeichnen des Spielfeldes
		g.setColor(Color.BLACK);
		diag.draw_field(g);

		ball.paintBall(g);
		meinSchlaeger.paintSchlaeger(g);
		gegnerSchlaeger.paintSchlaeger(g);
	}




}
