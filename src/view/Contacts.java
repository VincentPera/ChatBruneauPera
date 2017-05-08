package view;

import controller.Controller;
import model.Utilisateur;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

public class Contacts extends Fenetre {

    private JPanel panel;
    private HashMap<String,JPanel> cont;

    public boolean containsUser(String pseudo){
        return cont.containsKey(pseudo);
    }

    public Contacts(){
        super();
        cont = new HashMap<>();
        this.initComponents();
    }

    public void initComponents(){
        try {
            this.setTitle("Contacts");
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(600, 600));
            panel = new JPanel();
            panel.setLayout(new GridLayout(12, 1));
            panel.setBorder(new EmptyBorder(20, 20, 20, 20));
            panel.setOpaque(true);
            JLabel title = new JLabel("Liste des contacts en ligne");
            title.setHorizontalAlignment(SwingConstants.CENTER);
            panel.add(title,BorderLayout.NORTH);

            /*Utilisateur user = Controller.getController().getModel().getUtilActu();
            addNewUser(user);*/

            this.add(panel);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            this.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    Controller.getController().disconnect();
                    System.exit(0);
                }

                @Override
                public void windowClosed(WindowEvent e) {

                }

                @Override
                public void windowIconified(WindowEvent e) {

                }

                @Override
                public void windowDeiconified(WindowEvent e) {

                }

                @Override
                public void windowActivated(WindowEvent e) {

                }

                @Override
                public void windowDeactivated(WindowEvent e) {

                }
            });
            this.pack();
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewUser(Utilisateur user) {
        Message msg = (Message) Fenetre.createGui(Fenetre.TypeFenetre.MESSAGE, user);
        Controller.getController().addUserToChat(user.getPseudo(), msg);
        PanelUserContact panelUser = new PanelUserContact(user, msg);
        Controller.getController().addUserToPanel(user.getPseudo(), panelUser);
        panel.add(panelUser);
        panel.revalidate();
        cont.put(user.getPseudo(),panelUser);
    }

    public void removeUser(String u){
        PanelUserContact p = (PanelUserContact) cont.get(u);
        panel.remove(p);
        cont.remove(u);
        p.revalidate();
        panel.repaint();
        panel.revalidate();
    }





}
