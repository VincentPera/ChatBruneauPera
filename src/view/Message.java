package view;

import controller.Controller;
import model.Utilisateur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.net.Inet4Address;
import java.net.UnknownHostException;

public class Message extends Fenetre {

    private Utilisateur dest;
    private JTextArea discussion;
    private Message mess;

    public Message(Utilisateur user) {
        super();
        this.dest = user;
        initComponents();
    }

    public void initComponents() {
        try {
            mess = this;
            this.setTitle("Chat avec "+ dest.getPseudo());
            this.setLayout(new BorderLayout());
            this.setPreferredSize(new Dimension(500, 500));

            JPanel panel = new JPanel();
            panel.setLayout(new BorderLayout());
            panel.setOpaque(true);
            panel.setPreferredSize(new Dimension(500, 500));
            discussion = new JTextArea();
            discussion.setEditable(false);
            discussion.setRows(20);
            discussion.setColumns(30);
            discussion.setLineWrap(true);



            JScrollPane sc = new JScrollPane(discussion);

            JTextArea sendtext = new JTextArea();
            sendtext.setRows(3);
            sendtext.setLineWrap(true);

            JButton send = new JButton("Envoyer le message");
            send.addActionListener(e -> {

                if(Controller.getController().getContacts().containsUser(dest.getPseudo())){
                    Controller.getController().sendToUser(dest.getPseudo(), new network.Message(network.Message.DataType.Text,
                            sendtext.getText(),
                            dest.getPseudo(),
                            Controller.getController().getCurrentUserPseudo()));
                    discussion.append(Controller.getController().getCurrentUserPseudo() + " : " + sendtext.getText() + "\n");
                    sendtext.setText("");
                }else{
                    closeConv();
                }

            });
            JButton sendFiles = new JButton("Envoyer un fichier");
            sendFiles.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                    int result = fileChooser.showOpenDialog(mess);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                        // send file
                        Controller.getController().sendFileToUser(selectedFile, dest.getPseudo());
                    }
                }
            });

            JPanel haut = new JPanel();
            haut.setLayout(new BorderLayout());
            haut.setOpaque(false);
            haut.add(sc, BorderLayout.CENTER);
            JPanel right = new JPanel();
            right.setLayout(new BorderLayout());
            right.setOpaque(false);
            right.add(send, BorderLayout.CENTER);
            right.add(sendFiles, BorderLayout.SOUTH);
            JPanel mid = new JPanel();
            mid.setLayout(new BorderLayout());
            mid.add(sendtext,BorderLayout.CENTER);
            panel.add(haut, BorderLayout.NORTH);
            panel.add(mid,BorderLayout.CENTER);
            panel.add(right, BorderLayout.EAST);

            this.add(panel);
            this.setResizable(false);
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

            this.addWindowListener(new WindowListener() {
                @Override
                public void windowOpened(WindowEvent e) {

                }

                @Override
                public void windowClosing(WindowEvent e) {
                    Message.super.setVisible(false);
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
            this.setVisible(false);
            this.getRootPane().setDefaultButton(send);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, fall back to cross-platform
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // not worth my time
            }
        }
        try {
            Utilisateur user = new Utilisateur("Coucou", Inet4Address.getLocalHost());
            Message msg = new Message(user);
            msg.setVisible(true);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

    }

    public void addMessage(String data, String source) {
        discussion.append(source + " : " + data + "\n");
        discussion.revalidate();
    }

    public void closeConv(){
        discussion.append("ChatSystem : l'utilisateur est déconnecté.\n");
        discussion.revalidate();
    }

}
