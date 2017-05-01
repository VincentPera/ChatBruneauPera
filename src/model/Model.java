package model;

/**
 * Created by Pera on 10/04/2017.
 */
import java.net.InetAddress;

public class Model {

    private AllUtils allU;

    private Utilisateur utilActu;

    public Model() {
        allU = new AllUtils();
    }

    public boolean contains(String name) {
        return allU.contains(name);
    }

    public boolean connection(String Ppseudo, InetAddress Padd) {
        if (allU.checkDispo(Ppseudo)) {
            utilActu = new Utilisateur(Ppseudo, Padd);
            return true;
        } else {
            return false;
        }
    }

    public Utilisateur getUtilActu() {
        return utilActu;
    }

    public void addUtil(Utilisateur u) {
        this.allU.addUtil(u);
    }
}
