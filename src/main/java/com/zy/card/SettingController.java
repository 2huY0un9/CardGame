package com.zy.card;

import com.zy.card.util.SettingButton;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import com.zy.card.util.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController extends SettingButton implements Initializable {

    @FXML
    private AnchorPane settingPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/setting_back.png"),500,300,false,false);
        BackgroundImage MapBack = new BackgroundImage(MapBackground,null,null,null,null);
        Background mapback = new Background(MapBack);
        settingPane.setBackground(mapback);
        HelloApplication.Background_music = new SettingButton("Background Music");
        // 添加到AnchorPane
        settingPane.getChildren().add(HelloApplication.Background_music);
        AnchorPane.setRightAnchor(HelloApplication.Background_music, 160.0);
        AnchorPane.setTopAnchor(HelloApplication.Background_music, 10.0);

        HelloApplication.Sound_music = new SettingButton("Sound");

        if(HelloApplication.musicplay) {HelloApplication.Background_music.setSelected(true);}
        else {HelloApplication.Background_music.setSelected(false);}//ture能播放

        if(HelloApplication.soundplay) {HelloApplication.Sound_music.setSelected(true);}
        else {HelloApplication.Sound_music.setSelected(false);}//ture能播放

        settingPane.getChildren().add(HelloApplication.Sound_music);
        AnchorPane.setRightAnchor(HelloApplication.Sound_music, 160.0);
        AnchorPane.setTopAnchor(HelloApplication.Sound_music, 80.0);

        HelloApplication.Exit = new CardGameButton("EXIT",true);
        settingPane.getChildren().add(HelloApplication.Exit);
        AnchorPane.setRightAnchor(HelloApplication.Exit, 160.0);
        AnchorPane.setTopAnchor(HelloApplication.Exit, 220.0);


        ImageView imageView = new ImageView();
        imageView.setFitWidth(180); // 设置宽度为 180
        imageView.setFitHeight(64); // 设置高度为 64

        Image image = new Image(getClass().getResource("/com/zy/card/res/Image/backv.png").toExternalForm());
        imageView.setImage(image);
        settingPane.getChildren().add(imageView);
        AnchorPane.setRightAnchor(imageView, 160.0);
        AnchorPane.setTopAnchor(imageView, 150.0);

        Slider volumeSlider = new Slider(50, 100, 75); // 设置滑动条的最小值、最大值和初始值
        volumeSlider.setShowTickLabels(false); // 显示刻度标签
        volumeSlider.setShowTickMarks(true); // 显示刻度
        settingPane.getChildren().add(volumeSlider);
        AnchorPane.setRightAnchor(volumeSlider, 180.0);
        AnchorPane.setTopAnchor(volumeSlider, 170.0);


        HelloApplication.Exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit(); // 关闭整个程序
            }
        });
        HelloApplication.Background_music.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (HelloApplication.Background_music.isSelected()) {
                    // 播放音乐
                    MusicPlayer.playMusic(MusicPlayer.BGM);
                    HelloApplication.musicplay=true;
                } else {
                    // 停止音乐
                    MusicPlayer.stopMusic();
                    HelloApplication.musicplay=false;
                }
            }
        });
        HelloApplication.Sound_music.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(HelloApplication.Sound_music.isSelected())
                {
//                    HelloApplication.Sound_music.setSelected(true);
                    HelloApplication.soundplay=true;
                }
                else
                {
                    HelloApplication.soundplay=false;
                    HelloApplication.Sound_music.setSelected(false);

                }
            }
        });
        volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                double fvolume = newValue.doubleValue();
                float volume = (float) fvolume;
                MusicPlayer.setVolume(volume);
                System.out.println(volume);
            }
        });
    }
}
