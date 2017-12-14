package gui;

import PingPong.Ball;
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
	private JMenuBar menubar;
	private JMenu menu_datei, menu_mod, menu_hilfe;
	private JMenuItem mi_beenden;
	private JRadioButtonMenuItem rbmi_am, rbmi_fm;


	public Zeichnung zeich;
	private JLabel lb_status;
	public Schlaeger meinSchlaeger, gegnerSchlaeger;
	public Ball ball;
	public Score score;
	private boolean wPressed;
	private boolean sPressed;
	private String GameMode;
	private String[] gamemod={"Single-Player","Multi-Player","Online"};


	public MainFrame() {

		Object[] possibilities = {"Single-Mod", "Multi-Mod", "Multi-Mod online"};
		String s = (String) JOptionPane.showInputDialog(
				null,
				"Which mode would you choose?\n",
				"Start Game",
				JOptionPane.PLAIN_MESSAGE,
				null,
				possibilities,
				"ham");

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
					if (Xcord < meinSchlaeger.getXcord()) {
						if(Ycord>meinSchlaeger.getYcord() && Ycord<meinSchlaeger.getYcord()+100){
							ball.ballBouncedX();
						}else {
							score.incScoreB();
							ball.resetBall();
						}
					} else if (Xcord > gegnerSchlaeger.getXcord()) {
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
	}


	private void FrameInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setDoubleBuffered(true);
		setSize(new Dimension(1000, 700));
		setTitle("PingPong");

		/*
		 *  Menü
		 */

		menubar = new JMenuBar();

		menu_datei = new JMenu("Datei");
		menu_datei.getPopupMenu().setLightWeightPopupEnabled(false);
		mi_beenden = new JMenuItem("Beenden");
		mi_beenden.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		menu_datei.add(mi_beenden);
		menubar.add(menu_datei);

		menu_mod = new JMenu("Modus");
		menu_mod.getPopupMenu().setLightWeightPopupEnabled(false);
		rbmi_am = new JRadioButtonMenuItem("Single-Mod");
		rbmi_am.addActionListener(new b_ActionListener());
		rbmi_am.setSelected(true);
		rbmi_fm = new JRadioButtonMenuItem("Multiplayer-Mod");
		rbmi_fm.addActionListener(new b_ActionListener());
		ButtonGroup btngrp = new ButtonGroup();
		btngrp.add(rbmi_am);
		btngrp.add(rbmi_fm);
		menu_mod.add(rbmi_am);
		menu_mod.add(rbmi_fm);
		menubar.add(menu_mod);

		menu_hilfe = new JMenu("Hife");
		menubar.add(menu_hilfe);
		menu_hilfe.addMenuListener(new help_menuListener());
		setJMenuBar(menubar);

        meinSchlaeger = new Schlaeger(this,50);
        gegnerSchlaeger = new Schlaeger(this, 930);
        ball= new Ball(this);
		score= new Score(this);
		zeich = new Zeichnung(this);
		contentPane.add(zeich, java.awt.BorderLayout.CENTER);

		lb_status = new JLabel("Statuszeile");
		contentPane.add(lb_status, BorderLayout.SOUTH);
	}

	public void b_ActionHandler(ActionEvent e) {
		if (e.getSource() == rbmi_am) {

		} else if(e.getSource() == rbmi_fm){

		}
	}

	private class b_ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			b_ActionHandler(e);
		}
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

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("KeyPressed");
		if(e.getKeyChar() == 'w'){
			wPressed=true;
		}
		if(e.getKeyChar() == 's'){
			sPressed=true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("KeyReleased");
		if(e.getKeyChar() == 'w'){
			wPressed=false;
		}
		if(e.getKeyChar() == 's'){
			sPressed=false;
		}
	}
}
