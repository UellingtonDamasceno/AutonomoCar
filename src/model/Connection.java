package model;

import model.exceptions.OfflineException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Observable;
import util.Settings;

/**
 *
 * @author uellington
 */
public class Connection extends Observable implements Runnable {

    private final String IP = Settings.DefaultConnection.DEFAULT_IP.getStringValue();
    private final int PACKET_SIZE = Settings.DefaultConnection.DEFAULT_PACKET_SIZE.getIntegerValue();
    private final int PORT = Settings.DefaultConnection.DEFAULT_PORT.getIntegerValue();

    private DatagramSocket datagramSocket;
    private boolean online;

    public Connection() {
        this.online = false;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void initialize() throws SocketException {
        this.datagramSocket = new DatagramSocket();
        this.datagramSocket.setBroadcast(true);

        this.online = true;
        new Thread(this).run();
    }

    public void send(String message) throws IOException, OfflineException {
        if (!this.online) {
            throw new OfflineException();
        } else {
            DatagramPacket packet;
            InetAddress ip = InetAddress.getByName(this.IP);
            packet = new DatagramPacket(message.getBytes(), PACKET_SIZE, ip, PORT);
            this.datagramSocket.send(packet);
        }
    }

    @Override
    public void run() {
        DatagramPacket packet;
        while (this.online) {
            try {
                packet = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
                this.datagramSocket.receive(packet);

                String message = new String(packet.getData());
                System.out.println("Recebido: "+ message);
            } catch (IOException ex) {
            }
        }
    }

}
