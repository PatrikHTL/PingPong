package PingPong;

import gui.Zeichnung;

public class Schlaeger {
    private final double Xcord;
    private double Ycord;

    public Schlaeger(double Xcord) {    //Constructer weil 2 Schläger existieren
        this.Xcord=Xcord;
        Zeichnung.setSchalegerPosition();
    }

    public void setYcord(double ycord){
        Ycord=ycord;
        Zeichnung.setSchalegerPosition();
    }

    public double getXcord() {
        return Xcord;
    }

    public double getYcord() {
        return Ycord;
    }
}
