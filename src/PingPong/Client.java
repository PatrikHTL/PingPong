package PingPong;

import gui.MainFrame;
import gui.Score;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {                       //Diese Klasse verwaltet die Socket Verbindung als Client
    private Socket server;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private MainFrame main;
    private Ball ball;
    private Schlaeger meinSchläger;
    private Schlaeger gegnerSchläger;
    private Score score;

    public Client(MainFrame main, String ip) {
        this.main=main;
        this.ball=main.ball;
        this.meinSchläger=main.meinSchlaeger;
        this.gegnerSchläger=main.gegnerSchlaeger;
        this.score=main.score;
        try {
            server = new Socket(ip, 46893);
            outStream = new ObjectOutputStream(server.getOutputStream());
            inStream = new ObjectInputStream(server.getInputStream());
            refresh.start();                                                    //Nach erfolgreichem Verbindungsaufbau startet der Listener
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to Connect!!!");

        }
    }
    Thread refresh = new Thread(new Runnable() {
        @Override
        public void run() {
            while (server.isConnected()) {
                try {
                    String got=(String)inStream.readObject();                                   //Wartet auf Koordinaten des Servers
                    String[] cords = got.split(";");
                    gegnerSchläger.setYcord(Double.parseDouble(cords[0]));      //530
                    ball.setCord(900-Double.parseDouble(cords[1]),Double.parseDouble(cords[2])); //610
                    ball.setSpeed(-Double.parseDouble(cords[3]),Double.parseDouble(cords[4]));  //Aktualisiert entsprechen die Positionen
                    score.setScoreB(Integer.parseInt(cords[5]));
                    score.setScoreA(Integer.parseInt(cords[6]));

                    outStream.writeObject(meinSchläger.getYcord());                             //Und antwortet mit der Schlägerposition

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                    try {
                        server.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.out.println("Failed to Close Server!!!");
                    }
                }
            }}});
}
