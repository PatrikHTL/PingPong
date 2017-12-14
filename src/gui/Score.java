package gui;


import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;


public class Score {
    private MainFrame main;
    private int scoreA=0;
    private int scoreB=0;
    private static Font sanSerifFont = new Font("Serif", Font.BOLD, 100);

    public Score(MainFrame main) {
        this.main=main;
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
        g.drawString(Integer.toString(scoreA) +" : "+ Integer.toString(scoreB), 407,100);
    }
}
