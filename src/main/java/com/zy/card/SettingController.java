package com.zy.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    @FXML
    private AnchorPane settingPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/setting_back.png"),500,300,false,false);
        BackgroundImage MapBack = new BackgroundImage(MapBackground,null,null,null,null);
        Background mapback = new Background(MapBack);

        settingPane.setBackground(mapback);
    }
}
