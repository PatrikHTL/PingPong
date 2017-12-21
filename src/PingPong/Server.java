package PingPong;

import gui.MainFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket client;
    private ServerSocket server;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private MainFrame main;
    private Ball ball;
    private Schlaeger meinSchläger;
    private Schlaeger gegnerSchläger;

    public Server(MainFrame main) {
        this.main=main;
        this.ball=main.ball;
        this.meinSchläger=main.meinSchlaeger;
        this.gegnerSchläger=main.gegnerSchlaeger;
        try {
            server = new ServerSocket(46893);
            System.out.println("Awaiting Connection...");
            System.out.println("Your IP: "+ InetAddress.getLocalHost().getHostAddress());
            client = server.accept();

            outStream = new ObjectOutputStream(client.getOutputStream());
            inStream = new ObjectInputStream(client.getInputStream());
            refresh.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    Thread refresh = new Thread(new Runnable() {
        @Override
        public void run() {
            while (client.isConnected()) {
                try {
                    outStream.writeObject(meinSchläger.getYcord() + ";" + ball.getXcord() + ";" + ball.getYcord() + ";" + ball.getXspeed() + ";" + ball.getYspeed());

                    gegnerSchläger.setYcord((double)inStream.readObject());
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
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }}});
}
