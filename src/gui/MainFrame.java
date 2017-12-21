package gui;

import PingPong.*;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.*;

import javax.swing.*;


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
	private double schlaegerspeed=5;
	private boolean up;
	private boolean down;
	private boolean upgegner;
	private boolean downgegner;
	private String n;
	private int level;

	public MainFrame() {

		Object[] possibilities = {"Single-Player", "Multi-Player", "Network"};
		s = (String) JOptionPane.showInputDialog(
				null,
				"Which mode would you choose?\n",
				"Start Game",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				"Single-Player");


				if(s.equals("Network")) {

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
							"Single-Player");
					// Bei Client soll das 3. Fenster, sprich ip adresse aufgerufen werden

					if (n.equals("Client")) {
						JOptionPane pane = new JOptionPane(message1,
								JOptionPane.PLAIN_MESSAGE,
								JOptionPane.DEFAULT_OPTION);
						pane.createDialog(null, "Login").setVisible(true);
						new Client(MainFrame.this, message1.toString());
					}
					else{
						new Server(MainFrame.this);
					}
				}

		if(s.equals("Single-Player")) {

			JTextField text = new JTextField();
			Object[] possibilities1levels = {"Noob", "Beginner", "Tryhard", "Advanced", "Pro", "God"};
			Object[] message1 = {"Level", text};

			n = (String) JOptionPane.showInputDialog(
					null,
					"Choose your Level, young Padawan \n",
					"Level",
					JOptionPane.PLAIN_MESSAGE,
					null,
					possibilities1levels,
					"Single-Player");

			switch (n){
				case "Noob": 	level=10;
								break;
				case "Beginner": 	level=10;
					break;
				case "Tryhard": 	level=50;
					break;
				case "Advanced": 	level=70;
					break;
				case "Pro": 		level=90;
					break;
				case "God": 		level=97;
					break;


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
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					ball.calcNextPosition();
					double Xcord = ball.getXcord();
					double Ycord = ball.getYcord()+20;
					if (Ycord > 620 || Ycord < 36) {
						ball.ballBounced();
					}
					if ((Xcord < meinSchlaeger.getXcord()+20)&& (Xcord > meinSchlaeger.getXcord()+15)&& (Ycord>meinSchlaeger.getYcord()&& Ycord<meinSchlaeger.getYcord()+100)) {
							ball.ballBouncedX();
					}else if(ball.getXcord()<= meinSchlaeger.getXcord()-20){
						score.incScoreB();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ball.resetBall();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if ((Xcord > gegnerSchlaeger.getXcord()-20)&& (Xcord > gegnerSchlaeger.getXcord()-15)&& (Ycord>gegnerSchlaeger.getYcord() && Ycord<gegnerSchlaeger.getYcord()+100)) {
						ball.ballBouncedX();
					}
					else if(ball.getXcord()>= gegnerSchlaeger.getXcord()+20){
							score.incScoreA();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						ball.resetBall();
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					if(down==true && meinSchlaeger.getYcord() <=515){
						meinSchlaeger.setYcord(meinSchlaeger.getYcord()+schlaegerspeed);
					}

					if(downgegner==true && gegnerSchlaeger.getYcord() <=515 && s.equals("Multi-Player")){
						gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()+schlaegerspeed);
					}
					if(up==true && meinSchlaeger.getYcord()>=11){
						meinSchlaeger.setYcord(meinSchlaeger.getYcord()-schlaegerspeed);
					}
					if(upgegner==true && gegnerSchlaeger.getYcord()>=11 && s.equals("Multi-Player")){
						gegnerSchlaeger.setYcord(gegnerSchlaeger.getYcord()-schlaegerspeed);
					}
				}
			}
		});
		moveball.start();
		if(s.equals("Single-Player")) {
			new Bot(MainFrame.this,level);
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

				if (key == KeyEvent.VK_LEFT) {
				    up=true;
				}

				if (key == KeyEvent.VK_RIGHT) {
					down=true;
				}

				if (key == KeyEvent.VK_UP) {
					up=true;
				}

				if (key == KeyEvent.VK_DOWN) {
					down=true;
				}

				if (key == KeyEvent.VK_W) {
					upgegner=true;
				}

				if (key == KeyEvent.VK_S) {
					downgegner=true;
				}

				if (key == KeyEvent.VK_A) {
					upgegner=true;
				}

				if (key == KeyEvent.VK_D) {
					downgegner=true;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {

				int key = e.getKeyCode();

				if (key == KeyEvent.VK_LEFT) {
					up=false;
				}

				if (key == KeyEvent.VK_RIGHT) {
					down=false;
				}

				if (key == KeyEvent.VK_UP) {
					up=false;
				}

				if (key == KeyEvent.VK_DOWN) {
					down=false;
				}

				if (key == KeyEvent.VK_W) {
					upgegner=false;
				}

				if (key == KeyEvent.VK_S) {
					downgegner=false;
				}

				if (key == KeyEvent.VK_A) {
					upgegner=false;
				}

				if (key == KeyEvent.VK_D) {
					downgegner=false;
				}

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

		lb_status = new JLabel(s);
		contentPane.add(lb_status, BorderLayout.SOUTH);
	}
}
