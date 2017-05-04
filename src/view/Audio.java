package view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;

public class Audio extends Thread{

public enum typeAudio {
    CO,
    DECO,
    RECEP
}

    MediaPlayer m;

    public Audio (typeAudio t){
        if (t == typeAudio.CO){
            connectAud();
        }else if (t == typeAudio.DECO){
            deconnectAud();
        }else{
            notifAud();
        }
    }

    private void connectAud() {
            String bip = "src/resources/connect.wav";
            Media hit = new Media(new File(bip).toURI().toString());
            JFXPanel j = new JFXPanel() ;
            m = new MediaPlayer(hit);
    }

    private void deconnectAud() {
        String bip = "src/resources/deconnect.wav";
        Media hit = new Media(new File(bip).toURI().toString());
        JFXPanel j = new JFXPanel() ;
        m = new MediaPlayer(hit);
    }

    private void notifAud() {
        String bip = "src/resources/notif.wav";
        Media hit = new Media(new File(bip).toURI().toString());
        JFXPanel j = new JFXPanel();
        m = new MediaPlayer(hit);
    }

    public void play() {
        m.play();
    }

    public void run(){
        this.play();
    }

}
