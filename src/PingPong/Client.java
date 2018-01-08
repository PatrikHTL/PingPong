package PingPong;

import gui.MainFrame;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket server;
    private ObjectOutputStream outStream;
    private ObjectInputStream inStream;
    private MainFrame main;
    private Ball ball;
    private Schlaeger meinSchläger;
    private Schlaeger gegnerSchläger;

    public Client(MainFrame main, String ip) {
        this.main=main;
        this.ball=main.ball;
        this.meinSchläger=main.meinSchlaeger;
        this.gegnerSchläger=main.gegnerSchlaeger;
        try {
            server = new Socket(ip, 46893);
            outStream = new ObjectOutputStream(server.getOutputStream());
            inStream = new ObjectInputStream(server.getInputStream());
            refresh.start();
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
                    String got=(String)inStream.readObject();
                    String[] cords = got.split(";");
                    gegnerSchläger.setYcord(530-Double.parseDouble(cords[0]));
                    ball.setCord(900-Double.parseDouble(cords[1]),610-Double.parseDouble(cords[2]));
                    ball.setSpeed(-Double.parseDouble(cords[3]),-Double.parseDouble(cords[4]));

                    outStream.writeObject(meinSchläger.getYcord());

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
