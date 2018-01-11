package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Schlaeger {                                //Diese KLasse is für Darstellung und berechnung der Schläger zuständig
    private final double Xcord;
    private double Ycord;
    private double Yspeed;
    private MainFrame main;

    public Schlaeger(MainFrame main, double Xcord) {    //Constructer weil 2 Schläger existieren
        this.main=main;
        this.Xcord=Xcord;
        Ycord=300;
    }


    public void setYcord(double ycord){
        Yspeed=-(Ycord-ycord);
        Ycord=ycord;
    }

    public double getXcord() {
        return Xcord;
    }           //Getter für Koordinaten

    public double getYcord() {
        return Ycord;
    }

    public void paintSchlaeger(Graphics g){
        g.setColor(Color.GREEN);
        g.fillRect((int)Xcord, (int)Ycord, 20, 100);
    }

    public double getYspeed(){
        return Yspeed;
    }
}
