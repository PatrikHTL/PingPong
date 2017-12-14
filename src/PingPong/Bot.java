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
            main.gegnerSchlaeger.setYcord(main.ball.getYcord()-50);
            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
