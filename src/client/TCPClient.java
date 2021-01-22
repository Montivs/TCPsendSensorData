package client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    private final String hostname;
    private final int port;

    public TCPClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public static void main(String[]args) throws IOException {

        if(args.length != 2){
            System.out.println("missing parameters: hostname, portnumber");
            return;
        }

        String hostname = args[0];
        int portnumber = Integer.parseInt(args[1]);
        TCPClient tcpClient = new TCPClient(hostname,portnumber);


        long timeStamp = System.currentTimeMillis();
        float value = (float) 42.0;
        String sensorName = "Sensor A";

        tcpClient.sendSensorData(timeStamp,value,sensorName);

    }

    private void sendSensorData(long timeStamp, float value, String sensorName) throws IOException {
        Socket socket = new Socket(this.hostname, this.port);

        OutputStream os = socket.getOutputStream();

        DataOutputStream daos = new DataOutputStream(os);
        daos.writeLong(timeStamp);
        daos.writeFloat(value);
        daos.writeUTF(sensorName);

        daos.close();

    }



}
