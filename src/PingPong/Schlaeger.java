package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Schlaeger {
    private final double Xcord;
    private double Ycord;
    private MainFrame main;

    public Schlaeger(MainFrame main, double Xcord) {    //Constructer weil 2 Schläger existieren
        this.main=main;
        this.Xcord=Xcord;
        Ycord=300;
    }

    public void setYcord(double ycord){
        Ycord=ycord;
    }

    public double getXcord() {
        return Xcord;
    }

    public double getYcord() {
        return Ycord;
    }

    public void paintSchlaeger(Graphics g){
        g.setColor(Color.GREEN);
        g.drawRect((int)Xcord, (int)Ycord, 20, 100);
    }
}
