package gui;

import PingPong.Ball;
import PingPong.Bot;
import PingPong.Schlaeger;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
//import defauld.Zeichnung_2;
import java.awt.event.*;
import java.security.Key;

import javax.swing.*;
import javax.swing.event.MenuEvent;
//import defauld.MainFrame_2;
import javax.swing.event.MenuListener;


public class MainFrame extends JFrame{
	// Konstanten
	public static final int _AM = 0;
	public static final int _FM = 1;

	private JPanel contentPane;

	// Menü
	private JMenuItem mi_beenden;



	public Zeichnung zeich;
	private JLabel lb_status;
	public Schlaeger meinSchlaeger, gegnerSchlaeger;
	public Ball ball;
	public Score score;
	private boolean wPressed;
	private boolean sPressed;
	private String s;
	private String n;

	public MainFrame() {

		Object[] possibilities = {"Single-Mod", "Multi-Mod", "Multi-Mod online"};
		s = (String) JOptionPane.showInputDialog(
				null,
				"Which mode would you choose?\n",
				"Start Game",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				"Single-Mod");

				if(s.equals("Multi-Mod online")) {

					JTextField online = new JTextField();
					JTextField ip = new JTextField();
					Object[] possibilities1 = {"Server", "Client"};
					Object[] message1 = {"IP-Adresse", ip};

					n = (String) JOptionPane.showInputDialog(
							null,
							"Server/Client \n",
							"Login",
							JOptionPane.PLAIN_MESSAGE,
							null,
							possibilities1,
							"Single-Mod");
					// Bei Client soll das 3. Fenster, sprich ip adresse aufgerufen werden

					if (n.equals("Client")) {
						JOptionPane pane = new JOptionPane(message1,
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.DEFAULT_OPTION);
						pane.createDialog(null, "Login").setVisible(true);
					}
				}

		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			FrameInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		Thread zeichen = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					zeich.repaint();
				}
			}
		});
		zeichen.start();
		Thread moveball=new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ball.calcNextPosition();
					double Xcord = ball.getXcord();
					double Ycord = ball.getYcord()+20;
					if (Ycord > 620 || Ycord < 36) {
						ball.ballBounced();
					}
					if (Xcord < meinSchlaeger.getXcord()+20) {
						if(Ycord>meinSchlaeger.getYcord() && Ycord<meinSchlaeger.getYcord()+100){
							ball.ballBouncedX();
						}else {
							score.incScoreB();
							ball.resetBall();
						}
					} else if (Xcord > gegnerSchlaeger.getXcord()-20) {
						if(Ycord>gegnerSchlaeger.getYcord()&& Ycord<gegnerSchlaeger.getYcord()+100){
							ball.ballBouncedX();
						}else {
							score.incScoreA();
							ball.resetBall();
						}
					}
				}
			}
		});
		moveball.start();
		if(s.equals("Single-Mod")) {
			new Bot(MainFrame.this);
		}
	}


	private void FrameInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setDoubleBuffered(true);
		contentPane.setFocusable(true);
		contentPane.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {

			}

			@Override
			public void keyPressed(KeyEvent e) {


				int key = e.getKeyCode();

				if (key == KeyEvent.VK_LEFT && meinSchlaeger.getYcord()>=11  ) {
					meinSchlaeger.setYcord(meinSchlaeger.getYcord()-5);
				}

				if (key == KeyEvent.VK_RIGHT && meinSchlaeger.getYcord()<=515) {
					meinSchlaeger.setYcord(meinSchlaeger.getYcord()+5);
				}

				if (key == KeyEvent.VK_UP  && meinSchlaeger.getYcord()>=11 ) {
					meinSchlaeger.setYcord(meinSchlaeger.getYcord()-5);
				}

				if (key == KeyEvent.VK_DOWN && meinSchlaeger.getYcord()<=515) {
					meinSchlaeger.setYcord(meinSchlaeger.getYcord()+5);
				}

				if (key == KeyEvent.VK_W && gegnerSchlaeger.getYcord()>=11 && s.equals("Multi-Mod")) {
					gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()-5);
				}

				if (key == KeyEvent.VK_S && gegnerSchlaeger.getYcord()<=515 && s.equals("Multi-Mod")) {
					gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()+5);
				}

				if (key == KeyEvent.VK_A  && gegnerSchlaeger.getYcord()>=11 && s.equals("Multi-Mod")) {
					gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()-5);
				}

				if (key == KeyEvent.VK_D && gegnerSchlaeger.getYcord()<=515 && s.equals("Multi-Mod")) {
					gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()+5);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

			}
		});

		setSize(new Dimension(1000, 700));
		setTitle("PingPong");

		/*
		 *  Menü
		 */


		meinSchlaeger = new Schlaeger(this,50);
		gegnerSchlaeger = new Schlaeger(this, 930);
		ball= new Ball(this);
		score= new Score(this);
		zeich = new Zeichnung(this);
		contentPane.add(zeich, java.awt.BorderLayout.CENTER);

		lb_status = new JLabel("Statuszeile");
		contentPane.add(lb_status, BorderLayout.SOUTH);
	}



	private class help_menuListener implements MenuListener {

		@Override
		public void menuSelected(MenuEvent e) {

			JDialog helpJDialog = new JDialog();
			helpJDialog.setTitle("Hilfe");
			helpJDialog.setSize(500,100);
			helpJDialog.setLocation(GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint());
			helpJDialog.setModal(true);
			helpJDialog.add(new JLabel("Bitte lesen Sie die Anleitung oder behalten Sie gefundene Fehler für sich!"));
			helpJDialog.setVisible(true);
		}

		@Override
		public void menuDeselected(MenuEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void menuCanceled(MenuEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
