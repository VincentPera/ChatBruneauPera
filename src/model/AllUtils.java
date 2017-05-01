package model;

/**
 * Created by Pera on 10/04/2017.
 */
import java.util.ArrayList;

public class AllUtils {

    private ArrayList<Utilisateur> listUtil;

    public AllUtils() {
        this.listUtil = new ArrayList<>();
    }

    public void addUtil(Utilisateur u) {
        listUtil.add(u);
    }

    public boolean checkDispo(String Ppseudo) {
        return true;
    } //TODO

    public boolean contains(String Ppseudo) {
        return listUtil.contains(Ppseudo);
    }
}
