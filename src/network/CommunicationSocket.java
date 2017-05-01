package network;

import controller.Controller;

import java.io.*;
import java.net.*;

public class CommunicationSocket extends Thread{

    protected volatile boolean running = true;
    private ServerSocket socketServer;
    private Socket socketClient;
    private ObjectOutputStream writer;
    private ObjectInputStream reader;
    private int portSocketLocal;
    private int portSocketDest;
    private InetAddress destip;
    private int type = 0;

    public CommunicationSocket(InetAddress destip, int port, int type) {
        try {
            this.type =  type;
            this.destip = destip;
            if (this.type == 1) {
                this.socketServer = new ServerSocket(port);
                this.portSocketLocal = port;
            } else {
                this.socketClient = new Socket(destip, port);
                writer = new ObjectOutputStream(socketClient.getOutputStream());
                reader = new ObjectInputStream(socketClient.getInputStream());
                this.portSocketDest = port;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.start();
    }

    public void run() {
        try {
            if (type == 1 && running) {
                socketClient = socketServer.accept();
                writer = new ObjectOutputStream(socketClient.getOutputStream());
                reader = new ObjectInputStream(socketClient.getInputStream());
            }

            while (running) {
                Message receveid = (Message)reader.readObject();
                if(receveid.getType() == Message.DataType.File) {
                    Controller.getController().addInfo(receveid.getSrcPseudo(), "Début de réception du fichier : " + receveid.getData());
                    Message received2 = (Message)reader.readObject();
                    int length = Integer.parseInt(received2.getData());
                    OutputStream receivedFile = new FileOutputStream(System.getProperty("user.home") + "/Downloads/" + receveid.getData());
                    InputStream in = socketClient.getInputStream();
                    byte[] bytes = new byte[16*1024];
                    int cptSize = 0;
                    int count;
                    while (cptSize < length && (count = in.read(bytes)) > 0) {
                        receivedFile.write(bytes, 0, count);
                        cptSize += count;
                    }
                    receivedFile.close();
                    Controller.getController().addInfo(receveid.getSrcPseudo(), "Fichier reçu : " + System.getProperty("user.home") + "/Downloads/" + receveid.getData());
                } else {
                    Controller.getController().deliverMessage(receveid);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(Message msg) {
        try {
            writer.writeObject(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendFile(File file, String destPseudo) {
        try {
            Controller.getController().addInfo(destPseudo, "Début du transfert du fichier : " + file.getName());
            Message msg = new Message(Message.DataType.File, file.getName(), destPseudo, Controller.getController().getCurrentUserPseudo());
            this.sendMsg(msg);
            Message msg2 = new Message(Message.DataType.File, file.length() + "", destPseudo, Controller.getController().getCurrentUserPseudo());
            this.sendMsg(msg2);
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = socketClient.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.flush();
            in.close();
            Controller.getController().addInfo(destPseudo, "Fichier envoyé");
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeSocket() {
        try {
            running = false;
            if (type == 1) {
                socketServer.close();
                socketClient.close();
            } else {
                socketClient.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
