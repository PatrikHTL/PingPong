package gui;


import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;


public class Score {                    //Diese Klasse ist für die Anzeige des der Scores zuständig
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

    public int getScoreA() {
        return scoreA;
    }

    public void setScoreA(int scoreA) {
        this.scoreA = scoreA;
    }

    public int getScoreB() {
        return scoreB;
    }

    public void setScoreB(int scoreB) {
        this.scoreB = scoreB;
    }

    public void paintScore(Graphics g){
        g.setColor(Color.WHITE);
        g.setFont(sanSerifFont);
        g.drawString(Integer.toString(scoreA) +" : "+ Integer.toString(scoreB), 407,100);
    }
}
