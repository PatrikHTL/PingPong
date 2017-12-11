package gui;

import java.awt.*;

import PingPong.Ball;
import PingPong.Schlaeger;
import gui.MainFrame;

public class Zeichnung extends Canvas {
	// Randbereich in %
	private static final int BORDER_PERCENT = 5;
	// xmin, xmax, deltax

	// Referenz f�r Callback
	private MainFrame fr;
	private Schlaeger  meinSchlaeger, gegnerSchlaeger;
	private Ball ball;
	private Score score;

	public Zeichnung(MainFrame mainFrame) {
		super();
		this.fr = mainFrame;
		setBackground(Color.WHITE);
		this.meinSchlaeger=mainFrame.meinSchlaeger;
		this.gegnerSchlaeger=mainFrame.gegnerSchlaeger;
		this.ball=mainFrame.ball;
		this.score=mainFrame.score;
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
		g.drawLine(50,10,950,10);
		g.drawLine(50,620,950,620);
		g.drawLine(50,10,50,620);
		g.drawLine(950,10,950,620);
		g.drawLine(500,10,500,620);

		score.paintScore(g);
		ball.paintBall(g);
		meinSchlaeger.paintSchlaeger(g);
		gegnerSchlaeger.paintSchlaeger(g);


	}




}
