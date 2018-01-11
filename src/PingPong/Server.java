package PingPong;

import gui.MainFrame;
import gui.Score;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {                                       //Diese KLasse verwaltet die Socketverbindung als Server
    private Socket client;
    private ServerSocket server;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private MainFrame main;
    private Ball ball;
    private Schlaeger meinSchläger;
    private Schlaeger gegnerSchläger;
    private Score score;

    public Server(MainFrame main) {

        this.main=main;
        this.ball=main.ball;
        this.meinSchläger=main.meinSchlaeger;
        this.gegnerSchläger=main.gegnerSchlaeger;
        this.score=main.score;
        try {
            server = new ServerSocket(46893);
            System.out.println("Awaiting Connection...");
            System.out.println("Your IP: "+ InetAddress.getLocalHost().getHostAddress());   //Gibt eigene IP in Konsole aus
            client = server.accept();

            outStream = new ObjectOutputStream(client.getOutputStream());
            inStream = new ObjectInputStream(client.getInputStream());
            refresh.start();                                                                //Startet Serverthread
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Thread refresh = new Thread(new Runnable() {
        @Override
        public void run() {
            while (client.isConnected()) {
                try {           //Server sendet in rägelmäßigen abständen die Koordinaten um Server und Client zu syncronisieren
                    outStream.writeObject(meinSchläger.getYcord() + ";" + ball.getXcord() + ";" + ball.getYcord() + ";" + ball.getXspeed() + ";" + ball.getYspeed()+ ";" +score.getScoreA() + ";" + score.getScoreB());

                    gegnerSchläger.setYcord((double)inStream.readObject());     //Aktualisiert die position des Gegners (Antwort des Clients)
                    try {
                        server.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                        System.out.println("Failed to Close Server!!!");
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}});
}
