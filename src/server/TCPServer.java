package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class TCPServer {
    private final int port;
    public final static int PORTNUMBER = 4444;

    public TCPServer(int port) {
        this.port = port;
    }

    public static void main(String[]args) throws IOException, InterruptedException {
        TCPServer tcpServer = new TCPServer(PORTNUMBER);

        tcpServer.receiveSensorData();


    }
    private void receiveSensorData() throws IOException {
        Socket socket = this.acceptSocket();
        InputStream is = socket.getInputStream();

        DataInputStream dais = new DataInputStream(is);

        long timeStamp = dais.readLong();
        float value = dais.readFloat();
        String sensorName = dais.readUTF();

        Date date = new Date(timeStamp);


        System.out.println("timeStamp == " + date);
        System.out.println("value ==" + value);
        System.out.println("sensorName == " + sensorName);


    }

    private Socket acceptSocket() throws IOException {
        ServerSocket srvSocket = new ServerSocket(this.port);
        System.out.println("server socket created");
        return srvSocket.accept();
    }
}
