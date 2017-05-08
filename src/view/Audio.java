package view;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

public class Audio extends Thread{

public enum typeAudio {
    CO,
    DECO,
    RECEP
}

    MediaPlayer m;

    public Audio (typeAudio t) throws URISyntaxException {
        if (t == typeAudio.CO){
            connectAud();
        }else if (t == typeAudio.DECO){
            deconnectAud();
        }else{
            notifAud();
        }
    }

    private void connectAud() throws URISyntaxException {
            URL u = getClass().getResource("/resources/connect.wav");
            Media hit = new Media(u.toURI().toString());
            JFXPanel j = new JFXPanel() ;
            m = new MediaPlayer(hit);
    }

    private void deconnectAud() throws URISyntaxException {
        URL u = getClass().getResource("/resources/deconnect.wav");
        Media hit = new Media(u.toURI().toString());
        JFXPanel j = new JFXPanel() ;
        m = new MediaPlayer(hit);
    }

    private void notifAud() throws URISyntaxException {
        URL u = getClass().getResource("/resources/AH.wav");
        Media hit = new Media(u.toURI().toString());
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
