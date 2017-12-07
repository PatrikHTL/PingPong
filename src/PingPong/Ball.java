package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Ball {
    private double Xcord=100.0;
    private double Ycord=100.0;
    private double Xspeed=1.0;
    private double Yspeed=1.0;
    private MainFrame main;
    private Zeichnung zeich;

    public Ball(MainFrame main) {
        this.main=main;
        this.zeich=main.zeich;
    }

    public void setCord(double xcord, double ycord) {    //Setzen beider Coordinaten (Y,X)
        Xcord = xcord;                                          //Klasse static weil es nur 1 BaLL Gibt
        Ycord = ycord;
    }

    public double getXcord() {
        return Xcord;
    }

    public double getYcord() {
        return Ycord;
    }

    public double getXspeed() {
        return Xspeed;
    }

    public double getYspeed() {
        return Yspeed;
    }

    public void calcNextPosition(){
        Ycord+=Yspeed;
        Xcord+=Xspeed;
        zeich.repaint();
    }

    public void BallBounced(){
        double buffer;
        buffer=Yspeed;
        Yspeed=Xspeed;
        Xspeed=buffer;
    }

    public void paintBall(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect((int)Xcord,(int)Ycord,5,5);

    }
}
