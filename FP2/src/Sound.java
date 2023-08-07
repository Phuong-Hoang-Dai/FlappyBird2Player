
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.UnsupportedAudioFileException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nauth.203
 */
public class Sound {
    Clip clip;
    File [] soundURL = new File [30];
    FloatControl fc;
    private static List<Clip> playingClips = new ArrayList<>();
    public Sound(){
        soundURL[0] = new File ("res/sound/funny2.wav");
        soundURL[1] = new File ("res/sound/coin.wav");
        soundURL[2] = new File ("res/sound/life.wav");
        soundURL[3] = new File ("res/sound/freeze.wav");
        soundURL[4] = new File ("res/sound/blocked.wav");
        soundURL[5] = new File ("res/sound/gameover.wav");
    }
    
    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
            playingClips.add(clip);
        }catch (Exception e){
            
        }
    }
    
    public void play(){
        clip.start();
    }
    public void loop(){
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    public void stop(){
        clip.stop();
         for (Clip clip : playingClips) {
            if (clip.isRunning()) {
                clip.stop();
            }
        }
        playingClips.clear();
    }
}
    

