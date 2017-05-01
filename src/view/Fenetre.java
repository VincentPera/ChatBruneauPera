package view;

import model.Utilisateur;

import javax.swing.*;

public class Fenetre extends JFrame {

    public enum TypeFenetre{
        LOGIN,
        MESSAGE,
        CONTACTS
    }

    public static Fenetre createGui(TypeFenetre typeWindows, Utilisateur user) {
        return switchCreate(typeWindows, user);
    }

    public static Fenetre createGui(TypeFenetre typeWindows) {
        return switchCreate(typeWindows, null);
    }

    private static Fenetre switchCreate(TypeFenetre typeWindows, Utilisateur user) {
        switch(typeWindows) {
            case LOGIN   :   return new Login();
            case CONTACTS : return new Contacts();
            case MESSAGE: return new Message(user);
            default :
                System.out.println("Element de GUI inexistant !");
                return null;
        }
    }

    public Fenetre() {
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
    }
}