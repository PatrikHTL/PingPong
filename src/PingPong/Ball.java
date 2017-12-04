package PingPong;

import gui.Zeichnung;

public class Ball {
    private static Double Xcord=100.0;
    private static Double Ycord=100.0;
    private static Double Xspeed=1.0;
    private static Double Yspeed=1.0;

    public static void setCord(Double xcord, Double ycord) {    //Setzen beider Coordinaten (Y,X)
        Xcord = xcord;                                          //Klasse static weil es nur 1 BaLL Gibt
        Ycord = ycord;
        //Zeichnung.setBallposition();
    }

    public static Double getXcord() {
        return Xcord;
    }

    public static Double getYcord() {
        return Ycord;
    }

    public static Double getXspeed() {
        return Xspeed;
    }

    public static Double getYspeed() {
        return Yspeed;
    }

    public static void calcNextPosition(){
        Ycord+=Yspeed;
        Xcord+=Xspeed;
        //Zeichnung.setBallposition();
    }

    public static void BallBounced(){
        double buffer;
        buffer=Yspeed;
        Yspeed=Xspeed;
        Xspeed=buffer;
    }
}
