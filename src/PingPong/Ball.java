package PingPong;

import gui.MainFrame;
import gui.Zeichnung;

import java.awt.*;

public class Ball {
    private double Xcord=350;
    private double Ycord=250;
    private double Xspeed=3.0;
    private double Yspeed=3.0;
    private double schlaegerspeed;
    private MainFrame main;

    public Ball(MainFrame main) {
        this.main=main;
    }

    public void setCord(double xcord, double ycord) {    //Setzen beider Coordinaten (Y,X)
        Xcord = xcord;
        Ycord = ycord;
    }
    public void resetBall(){
        Xcord=450;
        Ycord=350;
        Yspeed=3.0;
        Xspeed=3.0;
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

        schlaegerspeed=main.meinSchlaeger.getYspeed();
        Ycord+=Yspeed;
        Xcord+=Xspeed;
       // main.repaintAll();
    }

    public void ballBounced(){
        Yspeed = Yspeed * -1;

    }

    public void paintBall(Graphics g) {
        g.setColor(Color.RED);
        g.drawOval((int)Xcord,(int)Ycord,20,20);

    }

    public void ballBouncedX() {


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
