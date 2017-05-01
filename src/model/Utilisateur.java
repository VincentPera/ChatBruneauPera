package model;

import java.net.InetAddress;

public class Utilisateur {

    public enum Status {
        EnLigne,
    }

    private String pseudo;

    private InetAddress AddIp;

    private Status status;

    public Utilisateur(String pseudo, InetAddress AddIp) {
        this.pseudo = pseudo;
        this.AddIp = AddIp;
        this.status = Status.EnLigne;
    }

    public String getPseudo() {
        return pseudo;
    }



}