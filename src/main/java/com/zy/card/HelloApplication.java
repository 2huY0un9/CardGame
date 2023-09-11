package com.zy.card;

import com.zy.card.util.SettingButton;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {

    public static boolean musicplay=true;

    public static boolean soundplay=true;

    public static SettingButton Sound_music = new SettingButton();

    public static SettingButton Background_music = new SettingButton();

    public static CardGameButton Exit = new CardGameButton();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 540);
        stage.setResizable(false);
        stage.setTitle("The Road to EI Dorado");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
        Obj.MenuStage = stage;
        Sound_music.setSelected(true);
        Background_music.setSelected(true);
    }

    public static void main(String[] args) {
        launch();
    }
}