package com.zy.card.util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {

    public static final String BGM = MusicPlayer.class.getResource("/com/zy/card/res/Music/music.wav").getPath();
    // bgm文件路径
    static Clip music = null; // 声明Clip接口
    static File sourceFile = null; // 声明文件变量

    private static FloatControl volumeControl = null;

    private static boolean isMusicPlaying = false; // 音乐是否正在播放的状态变量

    /**
     * 音乐播放方法
     */
    public static void playMusic(String path) {
        try {
            music = AudioSystem.getClip(); // 获取可用于播放音频文件或音频流的数据流
            sourceFile = new File(path); // 获取文件
            AudioInputStream ais = AudioSystem.getAudioInputStream(sourceFile); // 获得指定格式的音频输入流
            music.open(ais); // 打开数据流
            music.start(); // 开始播放音乐
            isMusicPlaying = true;

            while (!music.isOpen()) {
                Thread.sleep(10); // 等待音频流打开
            }

            if (music.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
                volumeControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
            } else {
                // 处理不支持音量控制的情况
                System.out.println("音量控制不支持");
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 停止音乐播放
     */
    public static void stopMusic() {
        if (music != null && music.isRunning()) {
            music.stop();
            music.close();
            music = null; // 释放资源
        }
    }

    /**
     * 设置音量
     * @param fvolume 音量值，范围 0.0 - 1.0
     */
    public static void setVolume(float fvolume) {
        if (volumeControl != null) {
            fvolume = fvolume/100;
            float volume = (float) fvolume;
            if (volume >= 0.0f && volume <= 1.0f) {
                float gain = volumeControl.getMinimum() + (volumeControl.getMaximum() - volumeControl.getMinimum()) * volume;
                volumeControl.setValue(gain);
            } else {
                System.out.println("音量值超出范围");
            }
        }
    }
}