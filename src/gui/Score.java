package gui;


import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Score {
    private final double Xcord;
    private double Ycord;
    private Zeichnung zeich;
    private int scoreA=0;
    private int scoreB=0;

    public Score(MainFrame main, double Xcord) {
        this.Xcord=Xcord;
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
        g.setColor(Color.GREEN);
        g.drawString(Integer.toString(scoreA) +" : "+ Integer.toString(scoreB),50,10);
    }
}
