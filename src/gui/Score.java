package gui;


import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;


public class Score {
    private double Ycord;
    private Zeichnung zeich;
    private int scoreA=0;
    private int scoreB=0;
    private static Font sanSerifFont = new Font("Serif", Font.BOLD, 24);

    public Score(MainFrame main) {
        this.zeich=main.zeich;
    }

    public void setYcord(double ycord){
        Ycord=ycord;
        zeich.repaint();
    }

    public void incScoreA(){
        scoreA++;
    }

    public void incScoreB(){
        scoreB++;
    }


    public void paintScore(Graphics g){
        g.setColor(Color.BLACK);

        g.setFont(sanSerifFont);
        FontMetrics fm = g.getFontMetrics();
        int w = fm.stringWidth("and");
        int h = fm.getAscent();
        g.drawString(Integer.toString(scoreA) +" : "+ Integer.toString(scoreB), 100,100);
    }
}
