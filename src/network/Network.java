package network;

import controller.Controller;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.HashMap;

public class Network extends Thread{

    private DatagramSocket socket_envoi;
    private DatagramSocket socket_recep;
    private int portNum = 15530;
    private int cptSockect = 1;
    private HashMap<String, CommunicationSocket> userNSocket;

    public Network() {
        try {
            userNSocket = new HashMap<>();
            socket_envoi = new DatagramSocket();
            socket_envoi.setBroadcast(true);
            NetworkInterface.getNetworkInterfaces();
            ControlMessage controlMessage = new ControlMessage(Controller.getController().getCurrentUserPseudo(), networkUtils.getLocalHostLANAddress(), -1, "hello");
            byte[] data = networkUtils.convertObjToData(controlMessage);
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), portNum);
            socket_envoi.send(packet);
            socket_envoi.setBroadcast(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.start();
    }

    public CommunicationSocket getSocket(String username) {
        return userNSocket.get(username);
    }

    public void sendDisconnect() {
        try {
            socket_envoi.setBroadcast(true);
            ControlMessage controlMessage = new ControlMessage(Controller.getController().getCurrentUserPseudo(), networkUtils.getLocalHostLANAddress(), -1, "bye");
            byte[] data = networkUtils.convertObjToData(controlMessage);
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName("255.255.255.255"), portNum);
            socket_envoi.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        try {
            socket_recep = new DatagramSocket(portNum);
            byte[] incomingData = new byte[1024];
            while (true) {
                DatagramPacket packet_entrant = new DatagramPacket(incomingData, incomingData.length);
                socket_recep.receive(packet_entrant);
                byte[] dataReceive = packet_entrant.getData();
                ControlMessage controlMessage1 = networkUtils.convertDataToControlMessage(dataReceive);
                if (controlMessage1.getUserName().equals(Controller.getController().getCurrentUserPseudo())) {
                    continue;
                }
                if (controlMessage1.getData().equals("hello")) {
                    int newPortForReceive = portNum + cptSockect;
                    cptSockect++;
                    ControlMessage controlMessageSocket = new ControlMessage(Controller.getController().getCurrentUserPseudo(), networkUtils.getLocalHostLANAddress(), newPortForReceive, "socket_created");
                    byte[] data = networkUtils.convertObjToData(controlMessageSocket);
                    DatagramPacket packet = new DatagramPacket(data, data.length, controlMessage1.getUserAdresse(), portNum);
                    socket_envoi.send(packet);
                    CommunicationSocket newComSock = new CommunicationSocket(controlMessage1.getUserAdresse(), newPortForReceive, 1);
                    userNSocket.remove(controlMessage1.getUserName());
                    userNSocket.put(controlMessage1.getUserName(), newComSock);
                    Controller.getController().addUser(controlMessage1.getUserName(), controlMessage1.getUserAdresse());
                } else if (controlMessage1.getData().equals("socket_created")) {
                        int newPortForReceive = portNum + cptSockect;
                        cptSockect++;
                        CommunicationSocket newComSock = new CommunicationSocket(controlMessage1.getUserAdresse(), controlMessage1.getPort(), 2);
                        userNSocket.remove(controlMessage1.getUserName());
                        userNSocket.put(controlMessage1.getUserName(), newComSock);
                        ControlMessage controlMessageSocket = new ControlMessage(Controller.getController().getCurrentUserPseudo(), networkUtils.getLocalHostLANAddress(), newPortForReceive, "socket_created");
                        byte[] data = networkUtils.convertObjToData(controlMessageSocket);
                        DatagramPacket packet = new DatagramPacket(data, data.length, controlMessage1.getUserAdresse(), portNum);
                        socket_envoi.send(packet);
                        Controller.getController().addUser(controlMessage1.getUserName(), controlMessage1.getUserAdresse());
                } else if(controlMessage1.getData().equals("bye")) {
                    userNSocket.get(controlMessage1.getUserName()).closeSocket();
                    userNSocket.remove(controlMessage1.getUserName());
                    Controller.getController().removeUser(controlMessage1.getUserName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
