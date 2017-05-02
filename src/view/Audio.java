package view;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.spi.AudioFileReader;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Audio extends Thread{

public enum typeAudio {
    CO,
    DECO,
    RECEP
}

    private URL u1;
    private AudioClip s1;

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
        try {
            AudioInputStream A = AudioSystem.getAudioInputStream(new File("./ressources/connect.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deconnectAud() {
        try {
            AudioInputStream A = AudioSystem.getAudioInputStream(new File("./ressources/deconnect.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void notifAud() {
        try {

            AudioInputStream A = AudioSystem.getAudioInputStream(new File("./ressources/notif.wav"));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play() {
        s1.play();
    }

    public void run(){
        this.play();
    }

}
