package com.zy.card;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;

import java.net.URL;
import java.util.ResourceBundle;

public class CodexController extends ImageView implements Initializable{
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

        Image monster_1 = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/Character/monster_1.png"),256,256,false,false);
        ImageView monster_1_1 = new ImageView(monster_1);
        CodexPane.getChildren().add(monster_1_1);
        CodexPane.setRightAnchor(monster_1_1, 10.0);
        CodexPane.setTopAnchor(monster_1_1, 10.0);

        Image monster_2 = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/Character/monster_2.png"),256,256,false,false);
        ImageView monster_2_2 = new ImageView(monster_2);
        CodexPane.getChildren().add(monster_2_2);
        CodexPane.setRightAnchor(monster_2_2, 10.0);
        CodexPane.setTopAnchor(monster_2_2, 290.0);

        Image monster_3 = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/Character/monster_3.png"),256,256,false,false);
        ImageView monster_3_3 = new ImageView(monster_3);
        CodexPane.getChildren().add(monster_3_3);
        CodexPane.setRightAnchor(monster_3_3, 10.0);
        CodexPane.setTopAnchor(monster_3_3, 580.0);

        Image monster_4 = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/Character/monster_4.png"),256,256,false,false);
        ImageView monster_4_4 = new ImageView(monster_4);
        CodexPane.getChildren().add(monster_4_4);
        CodexPane.setRightAnchor(monster_4_4, 10.0);
        CodexPane.setTopAnchor(monster_4_4, 900.0);
    }
}
