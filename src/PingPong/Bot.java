package PingPong;

import gui.MainFrame;

public class Bot extends Thread{
    MainFrame main;
    private double level;
    private double diff;
    public Bot(MainFrame main,double level) {
        this.main=main;
        this.start();
        this.level=level/100;
    }

    @Override
    public void run() {
        while(true){
            double Y = main.ball.getYcord()+50;

            diff=(main.ball.getYcord()-main.gegnerSchlaeger.getYcord());
            if(Y<=570 && Y>=10){
                if(diff>0) {
                    //schlaeger unter ball:
                    main.gegnerSchlaeger.setYcord(main.gegnerSchlaeger.getYcord()+level);
                }
                if(diff<0){
                    //schlaeger ober ball:
                    main.gegnerSchlaeger.setYcord(main.gegnerSchlaeger.getYcord()-level);
                }
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
