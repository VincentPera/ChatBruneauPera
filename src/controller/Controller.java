package controller;

import model.*;
import network.*;
import view.*;
import view.Message;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;

public class Controller implements Facade{

    private Model model;
    private Network network;
    private Fenetre login;
    private Contacts contact;
    private HashMap<String, PanelUserContact> userPan;
    private HashMap<String, Message> userChat;
    private static Controller c = new Controller();

    private Controller() {
        model = new Model();
        userPan = new HashMap<>();
        userChat = new HashMap<>();
        login = Fenetre.createGui(Fenetre.TypeFenetre.LOGIN);
    }

    public static Controller getController() {
        return c;
    }

    public Contacts getContacts(){return contact;}

    public void addUserToChat(String name, Message msg) {
        this.userChat.put(name, msg);
    }

    public void addUserToPanel(String name, PanelUserContact panel) {
        this.userPan.put(name, panel);
    }

    public void disconnect() {
        network.sendDisconnect();
    }

    public static void launch() {
        Controller.getController();
    }

    @Override
    public void sendToUser(String destPseudo, network.Message msg) {
        network.getSocket(destPseudo).sendMsg(msg);
    }

    public Model getModel() {
        return model;
    }

    public void addUser(String name, InetAddress ip) {
        if (!model.contains(name)) {
            Utilisateur u = new Utilisateur(name, ip);
            model.addUtil(u);
            contact.addNewUser(u);
        }
    }

    public void removeUser(String nom){
            contact.removeUser(nom);
    }

    @Override
    public String getCurrentUserPseudo() {
        return model.getUtilActu().getPseudo();
    }

    public void deliverMessage(network.Message msg) {
        userChat.get(msg.getSrcPseudo()).addMessage(msg.getData(), msg.getSrcPseudo());
        Audio audio = new Audio(Audio.typeAudio.RECEP);
        audio.start();
    }

    @Override
    public void connect(String pseudo) {
        try {
            if (model.connection(pseudo, Inet4Address.getLocalHost())) {
                contact = (Contacts) Fenetre.createGui(Fenetre.TypeFenetre.CONTACTS);
                network = new Network();
                login.setVisible(false);
                Audio audio = new Audio(Audio.typeAudio.RECEP);
                audio.start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void addInfo(String dest, String Message) {
        userChat.get(dest).addMessage(Message, "ChatSystem");
    }

    public static void main(String[] args) {
        launch();
    }

    public void sendFileToUser(File file, String pseudo) {
        network.getSocket(pseudo).sendFile(file, pseudo);
    }
}
