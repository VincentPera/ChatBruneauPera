package view;

import controller.Controller;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelUserContact extends JPanel{

    private Utilisateur user;
    private JLabel name;
    private JButton discuter;
    private Message msgView;

    public PanelUserContact(Utilisateur user, Message msgView) {
        this.user = user;
        this.name = new JLabel(user.getPseudo());
        this.discuter = new JButton("Chatter avec "+ user.getPseudo() +" !");
        this.msgView = msgView;
        initComponents();
    }

    private void initComponents() {
        this.setLayout(new GridLayout(1, 2));
        this.setOpaque(true);


        discuter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                msgView.setVisible(true);
                Controller.getController().stopCligno(discuter);
            }
        });

        this.add(name);
        this.add(discuter);
    }


    public void changeButton(){
        this.discuter.setBackground(new Color(255,153,0));
        System.out.println("lol");
    }


}

