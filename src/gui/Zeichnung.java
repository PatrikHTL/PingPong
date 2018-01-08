package gui;

import java.awt.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import PingPong.Ball;
import PingPong.Schlaeger;
import gui.MainFrame;

import javax.swing.*;

public class Zeichnung extends JPanel {
	// Randbereich in %
	private static final int BORDER_PERCENT = 5;
	// xmin, xmax, deltax

	// Referenz f�r Callback
	private MainFrame fr;
	private Schlaeger  meinSchlaeger, gegnerSchlaeger;
	private Ball ball;
	private Score score;
	private static Lock lock;

	public Zeichnung(MainFrame mainFrame) {
		super();
		this.fr = mainFrame;
		setBackground(Color.BLACK);
		this.meinSchlaeger=mainFrame.meinSchlaeger;
		this.gegnerSchlaeger=mainFrame.gegnerSchlaeger;
		this.ball=mainFrame.ball;
		this.score=mainFrame.score;
		lock = new ReentrantLock();
	}

	@Override
    public void paint(Graphics g) {
        super.paint(g);
        lock.lock();
        double xval, yval;

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
        lock.unlock();

    }




}
