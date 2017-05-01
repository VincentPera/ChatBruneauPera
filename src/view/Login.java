package view;

import controller.Controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Login extends Fenetre {

    public Login() {
        super();
        initComponents();
    }

    public void initComponents() {
        this.setTitle("Connexion au Chat");
        this.setPreferredSize(new Dimension(500,200));
        this.setLayout(new BorderLayout());

        JLabel label = new JLabel("Entrer un pseudo pour vous connecter");
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel bottom = new JPanel(new BorderLayout());

        JTextField username = new JTextField();
        username.setSize(new Dimension(500, 100));
        username.setHorizontalAlignment(SwingConstants.CENTER);


        JButton login = new JButton("Se connecter au Chat");
        login.setMargin(new Insets(20,20,20,20));

        ActionListener connect = e -> {
            this.connect(username,label);
        };

        login.addActionListener(connect);



        this.add(label,BorderLayout.CENTER);

        bottom.add(username,BorderLayout.CENTER);
        bottom.add(login,BorderLayout.EAST);

        this.add(bottom,BorderLayout.SOUTH);

        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getRootPane().setDefaultButton(login);


    }

    private void connect(JTextField username,JLabel label){
        if(username.getText() != null && username.getText().length() != 0) {
            try {
                Controller.getController().connect(username.getText());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            label.setText("Vous n'avez rien saisi ! Entrez un pseudo pour vous connecter ");
        }
    }


}
