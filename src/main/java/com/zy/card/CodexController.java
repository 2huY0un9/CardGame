package com.zy.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CodexController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private AnchorPane CodexPane;
    @FXML
    private ScrollPane Scroll;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Scroll.setStyle("-fx-hbar-policy: NEVER;");
        Scroll.setBackground(Background.EMPTY);
        Scroll.getStyleClass().add("edge-to-edge");

        //实验
        Image CodexBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/codex_background.jpg"),980,1200,false,false);
        BackgroundImage MapBack = new BackgroundImage(CodexBackground,null,null,null,null);
        Background mapback = new Background(MapBack);
        CodexPane.setBackground(mapback);
    }
}
