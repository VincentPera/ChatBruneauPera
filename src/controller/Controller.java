package controller;

import model.*;
import network.*;
import view.*;
import view.Message;

import javax.swing.*;
import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URISyntaxException;
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

    public HashMap<String, PanelUserContact> getUserPan(){
        return userPan;
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

    public void deliverMessage(network.Message msg) throws URISyntaxException {
        if(userChat.get(msg.getSrcPseudo()).getClosed())userPan.get(msg.getSrcPseudo()).changeButton();
        userChat.get(msg.getSrcPseudo()).addMessage(msg.getData(), msg.getSrcPseudo());
    }

    @Override
    public void connect(String pseudo) throws URISyntaxException {
        try {
            if (model.connection(pseudo, Inet4Address.getLocalHost())) {
                contact = (Contacts) Fenetre.createGui(Fenetre.TypeFenetre.CONTACTS);
                network = new Network();
                login.setVisible(false);
                Audio audio = new Audio(Audio.typeAudio.CO);
                audio.start();
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void addInfo(String dest, String Message) throws URISyntaxException {
        userChat.get(dest).addMessage(Message, "ChatSystem");
    }

    public static void main(String[] args) {
        launch();
    }

    public void sendFileToUser(File file, String pseudo) {
        network.getSocket(pseudo).sendFile(file, pseudo);
    }

    public void stopCligno(JButton j){
        j.setBackground(new JButton().getBackground());
    }
}
