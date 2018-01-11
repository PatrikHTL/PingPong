package PingPong;

import gui.MainFrame;

public class Bot extends Thread {
    MainFrame main;
    private double level;
    private double diff;

    public Bot(MainFrame main, double level) {
        this.main = main;
        this.start();
        this.level = level / 100;
    }

    @Override
    public void run() {
        while (true) {
            double Y = main.ball.getYcord() + 50;
            diff = (main.ball.getYcord() - main.gegnerSchlaeger.getYcord());
                                                                                //Schläger wird mit der Geschwindigkeit entsprechend des Levels zur aktuellen Y Position des Balls bewegt
            if (diff > 0) {
                //schlaeger unter ball:
                if(main.gegnerSchlaeger.getYcord()>=520) {
                    main.gegnerSchlaeger.setYcord(520);
                }
                    main.gegnerSchlaeger.setYcord(main.gegnerSchlaeger.getYcord() + level);

            }
            if (diff < 0) {
                //schlaeger ober ball:
                if(main.gegnerSchlaeger.getYcord()<=10){
                    main.gegnerSchlaeger.setYcord(10);
                }
                main.gegnerSchlaeger.setYcord(main.gegnerSchlaeger.getYcord() - level);
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
