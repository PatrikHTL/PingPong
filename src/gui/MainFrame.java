package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
//import defauld.Zeichnung_2;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollBar;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.event.MenuEvent;
//import defauld.MainFrame_2;
import javax.swing.event.MenuListener;


public class MainFrame extends JFrame {
	// Konstanten
	public static final int _AM = 0;
	public static final int _FM = 1;
	private static final String[] z_string = { "Amplitudenmodulation", "Frequenzmodulation" };
	private static final String[] lb_string = {"Tr�gerfreq: ","Signalamp: ","Signalfreq: "," Hz "," % "," Hz "};

	private JPanel contentPane;

	// Men�
	private JMenuBar menubar;
	private JMenu menu_datei, menu_mod, menu_hilfe;
	private JMenuItem mi_beenden;
	private JRadioButtonMenuItem rbmi_am, rbmi_fm;

	// Zeichenfeld
	private Zeichnung zeich;
	private int z_status = _AM;
	private JLabel lb_status;
	private JLabel lb_wert1;
	private JLabel lb_wert2;
	private JLabel lb_wert3;
	
	// SCrollBars
	private JPanel sbPanel;
	private JScrollBar sb1, sb2, sb3;
	
	//startvariablen
	private int value1=50;
	private int value2=25;
	private int value3=1;
	
	
	public MainFrame() {
		try {
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			FrameInit();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void FrameInit() throws Exception {
		contentPane = (JPanel) getContentPane();
		contentPane.setLayout(new BorderLayout());
		setSize(new Dimension(400, 300));
		setTitle("SignalModulator");

		/*
		 *  Men�
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
		
		menu_mod = new JMenu("Modulation");
		menu_mod.getPopupMenu().setLightWeightPopupEnabled(false);
		rbmi_am = new JRadioButtonMenuItem("Amplitudenmodulation");
		rbmi_am.addActionListener(new b_ActionListener());
		rbmi_am.setSelected(true);
		rbmi_fm = new JRadioButtonMenuItem("Frequenzmodulation");
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
		
		        
		/*
		 *  Inhalt Links
		 */
		
		// Zeichenfeld
		//JPanel p_bottom = new JPanel(new BorderLayout());
		zeich = new Zeichnung(this);
		

		/*
		 *  Inhalt Rechts
		 */
		
		// Titel Beschriftungsfeld
		
		JLabel lb_title = new JLabel();
		lb_title.setText("Signalgenerator");
		lb_title.setFont(new Font("ARIAL", Font.BOLD, 10));
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);

		
		//Scrollbar werte darunter
		lb_wert1 = new JLabel();
		lb_wert2 = new JLabel();
		lb_wert3 = new JLabel();
		
		// Scrollbars
				
		sb1 = new JScrollBar(1, value1, 1, 0, 101);//tr�gerfreq
		sb2 = new JScrollBar(1, value2, 1, 0, 51);//signalamp
		sb3 = new JScrollBar(1, value3, 1, 0, 11);//signalfreq
		
		sbPanel = new JPanel();
		sbPanel.setLayout(new GridLayout(2,3));
		sbPanel.add(sb1);
		sbPanel.add(sb2);
		sbPanel.add(sb3);
        sbPanel.add(lb_wert1);
        sbPanel.add(lb_wert2);
        sbPanel.add(lb_wert3);
        
		//Scrollbar AdjustmentListeners
		sb1.addAdjustmentListener(new sb_AdjustmentListener());
		sb2.addAdjustmentListener(new sb_AdjustmentListener());
		sb3.addAdjustmentListener(new sb_AdjustmentListener());
		
		

        
		/* 
		 *  Splitters
		 */
		
		// Vertical Setting/Label Splitter
		
		JSplitPane sp_label_scroll = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		sp_label_scroll.setEnabled(true);
		sp_label_scroll.add(lb_title, JSplitPane.TOP);
		sp_label_scroll.add(sbPanel, JSplitPane.BOTTOM);

		// Main Horizontal Splitter
		
		JSplitPane sp_plot_settings = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		sp_plot_settings.setResizeWeight(0.7);
		sp_plot_settings.setEnabled(true);
		sp_plot_settings.add(zeich, JSplitPane.LEFT);
		sp_plot_settings.add(sp_label_scroll, JSplitPane.RIGHT);
		
		contentPane.add(sp_plot_settings, java.awt.BorderLayout.CENTER);
		
		
		/*
		 *  Statusbar
		 */
		
		lb_status = new JLabel("Statuszeile");
		lb_status.setText(z_string[z_status]);
		contentPane.add(lb_status, BorderLayout.SOUTH);
		
		
		
		sb_AdjustmentHandler();
	}



	public int get_zstat() {
		return z_status;
	}
	
	public int get_value1() {
		return value1;
	}

	public int get_value2() {
		return value2;
	}

	public int get_value3() {
		return value3;
	}

	
	public void b_ActionHandler(ActionEvent e) {
		if (e.getSource() == rbmi_am) {
			z_status = _AM;
		} else if(e.getSource() == rbmi_fm){
			z_status = _FM;
		}
		lb_status.setText(z_string[z_status]);

		// Zeichnung aktualisieren
		zeich.repaint();
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
	        helpJDialog.add(new JLabel("Bitte lesen Sie die Anleitung oder behalten Sie gefundene Fehler f�r sich!"));
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
	
	private void sb_AdjustmentHandler() {
		value1 = sb1.getValue();
		value2 = sb2.getValue();
		value3 = sb3.getValue();
		
		zeich.repaint();
		lb_wert1.setText(lb_string[0]+Integer.toString(value1) + lb_string[3]);
		lb_wert2.setText(lb_string[1]+Integer.toString(value2) + lb_string[4]);
		lb_wert3.setText(lb_string[2]+ Integer.toString(value3) + lb_string[5]);
	}
	
	private class sb_AdjustmentListener implements AdjustmentListener {

		@Override
		public void adjustmentValueChanged(AdjustmentEvent e) {
			sb_AdjustmentHandler();
		}

		

	}
}
