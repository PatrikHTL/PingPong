package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Ball {                            //Diese KLasse ist zum berechnen und darstellen des Balles zuständig
    private double Xcord=350;
    private double Ycord=250;
    private double Xspeed=3.0;
    private double Yspeed=3.0;
    private double schlaegerspeed;
    private MainFrame main;

    public Ball(MainFrame main) {
        this.main=main;
    }

    public void setCord(double xcord, double ycord) {    //Setzen beider Koordinaten (Y,X)
        Xcord = xcord;
        Ycord = ycord;
    }
    public void setSpeed(double Xspeed, double Yspeed) {    //Setzen beider Koordinaten (Y,X)
        this.Xspeed = Xspeed;
        this.Yspeed = Yspeed;
    }
    public void resetBall(){        //Zurücksetzen des Balles ca in die Mitte des Spielfeldes
        Xcord=450;
        Ycord=350;
        Yspeed=3;
        Xspeed=3;
    }

    public double getXcord() {
        return Xcord;
    }       //Getter zum Abrufen der Koordinaten

    public double getYcord() {
        return Ycord;
    }

    public double getXspeed() {
        return Xspeed;
    }

    public double getYspeed() {
        return Yspeed;
    }

    public void calcNextPosition(){                 //Neue Position errechnen

        schlaegerspeed=main.meinSchlaeger.getYspeed();
        Ycord+=Yspeed;                              //Dazu wird nur die Ballgeschwindigkeit(in Pixel) zu den Koordinaten addiert
        Xcord+=Xspeed;
       // main.repaintAll();
    }

    public void ballBounced(){
        Yspeed = Yspeed * -1;                       //Beim Aufprallen des Balles wird die entsprechende Geschwindigkeit invertiert

    }

    public void paintBall(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval((int)Xcord,(int)Ycord,20,20);


    }

    public void ballBouncedX() {                    //Bewegt sich ein Schläger während der Ball aufschlägt wird der Ball in diese Richtung beschleunigt


            if (schlaegerspeed == 0) {

                Xspeed = Xspeed * -1;
            }
            if (schlaegerspeed >0) {
                Yspeed++;
                Xspeed = Xspeed * -1;
            }
            if (schlaegerspeed <0 ) {
                Yspeed--;
                Xspeed = Xspeed * -1;
            }



    }

}
