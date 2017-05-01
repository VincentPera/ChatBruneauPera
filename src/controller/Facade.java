package controller;

import network.Message;

import java.io.File;
import java.net.InetAddress;

public interface Facade {

    /**
     * Disconnect the user and close the apps
     */
    void disconnect();



    /**
     * Send the msg to the user, selected by name
     * @param destPseudo name of the user to send
     * @param msg message to be send
     */
    void sendToUser(String destPseudo, Message msg);

    /**
     * Send a file to the selected user
     * @param selectedFile file to send
     * @param pseudo the name of the des
     */
    void sendFileToUser(File selectedFile, String pseudo);

    /**
     * Print the selected message in the gui or as text
     * @param msg the message to be print
     */
    void deliverMessage(network.Message msg);

    /**
     * Connect the user of the app, launch the network as well,
     * USED WHEN YOU CLICK ON LOGIN on the first window
     * @param pseudo the name of the main user
     */
    void connect(String pseudo);

    /**
     * Add the user to the list of existing user
     * @param name the name of the user to add
     * @param ip the ip of the user to add
     */
    void addUser(String name, InetAddress ip);

    /**
     * The name of the current user of the app
     * @return the name
     */
    String getCurrentUserPseudo();

}