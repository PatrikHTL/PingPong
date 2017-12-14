package PingPong;

import gui.MainFrame;

public class Bot extends Thread{
    MainFrame main;
    public Bot(MainFrame main) {
        this.main=main;
        this.start();
    }

    @Override
    public void run() {
        while(true){
            double Y = main.ball.getYcord()-50;
            if(Y<=520 && Y>=11){
                main.gegnerSchlaeger.setYcord(Y);
            }
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
