package middleware;

import model.exceptions.OfflineException;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.Observable;
import org.json.JSONObject;
import util.Settings;

/**
 *
 * @author uellington
 */
public class Connection extends Observable implements Runnable {

    private static Connection connection;
    private InetAddress sendIP;
    private final String BROADCAST_IP = Settings.DefaultConnection.DEFAULT_IP.getStringValue();
    private final int PACKET_SIZE = Settings.DefaultConnection.DEFAULT_PACKET_SIZE.getIntegerValue();
    private int port;
    private String localIp;

    private DatagramSocket datagramSocket;
    private boolean online;

    private Connection() {
        this.online = false;
    }

    public static synchronized Connection getInstance() {
        return (connection == null) ? connection = new Connection() : connection;
    }

    public boolean isOnline() {
        return this.online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public void initialize(int port) throws SocketException, UnknownHostException {
        this.port = port;
        this.localIp = InetAddress.getLocalHost().getHostAddress();
        this.sendIP = InetAddress.getByName(this.BROADCAST_IP);
        this.datagramSocket = new DatagramSocket(port);
        this.datagramSocket.setBroadcast(true);

        this.online = true;
        new Thread(this).start();
    }

    public void send(String message) throws IOException, OfflineException {
        if (!this.online) {
            throw new OfflineException();
        } else {
            DatagramPacket packet;
            
            packet = new DatagramPacket(message.getBytes(),
                    message.getBytes().length,
                    this.sendIP,
                    this.getIpSend());
            
            this.datagramSocket.send(packet);
//            System.out.println("Enviado: " + message);
        }
    }

    private int getIpSend() {
//        return 10000 + "";
        return (port == 9999) ? 10000 : 9999;
    }

    @Override
    public void run() {
        DatagramPacket packet;
        while (this.online) {
            try {
                packet = new DatagramPacket(new byte[PACKET_SIZE], PACKET_SIZE);
                this.datagramSocket.receive(packet);
                JSONObject jsonMessage = new JSONObject(new String(packet.getData()));

                if (!jsonMessage.getString("id").equals(this.getIp())) {
                    this.setChanged();
                    this.notifyObservers(jsonMessage);
//                    System.out.println("Recebido: " + jsonMessage.toString());
                }
            } catch (IOException ex) {
            }
        }
    }

    public String getIp() {
        return localIp + this.getIpSend();
    }

    private String getIpOnLinux() throws SocketException {
        Enumeration nis = null;
        InetAddress ia;
        nis = NetworkInterface.getNetworkInterfaces();

        String ip = null;
        while (nis.hasMoreElements()) {
            NetworkInterface ni = (NetworkInterface) nis.nextElement();
            Enumeration ias = ni.getInetAddresses();

            while (ias.hasMoreElements()) {
                ia = (InetAddress) ias.nextElement();

                if (ia.getHostAddress().contains("")) {
                    ip = ia.getHostAddress();
                }
                System.out.println("Teste " + ia.getHostAddress());
                if (!ni.getName().equals("lo")) {
                    // System.out.println(ia.getHostAddress());
                }
            }
        }
        return ip;

    }
}
