package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Schlaeger {
    private final double Xcord;
    private double Ycord;
    private Zeichnung zeich;

    public Schlaeger(MainFrame main, double Xcord) {    //Constructer weil 2 Schläger existieren
        this.Xcord=Xcord;
        this.zeich=main.zeich;
    }

    public void setYcord(double ycord){
        Ycord=ycord;
        zeich.repaint();
    }

    public double getXcord() {
        return Xcord;
    }

    public double getYcord() {
        return Ycord;
    }
    public void paintSchlaeger(Graphics g){
        g.setColor(Color.GREEN);
        g.drawRect((int)Xcord, (int)Ycord, 10, 30);
    }
}
