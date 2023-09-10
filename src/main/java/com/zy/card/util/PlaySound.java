package com.zy.card.util;

import com.zy.card.CardGameButton;

import javax.sound.sampled.*;
import java.io.IOException;

public class PlaySound {
    static Clip clip2 = null;
    public void playSound() {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getClass().getResourceAsStream("/com/zy/card/res/Music/Sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void playMusic()
    {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(CardGameButton.class.getResource("/com/zy/card/res/Music/Sound.wav"));
            clip2 = AudioSystem.getClip();
            clip2.open(audioInputStream);
            clip2.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public static void stopMusic()
    {
        if (clip2  != null && clip2 .isRunning()) {
            clip2 .stop();
            clip2 .close();
            clip2  =  null; // 释放资源
        }
    }
}
