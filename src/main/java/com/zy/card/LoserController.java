package com.zy.card;

import com.zy.card.util.WinButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zy.card.Obj.MenuStage;

public class LoserController implements Initializable {
    @FXML
    private AnchorPane LosePane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WinButton exit =new WinButton("EXIT");
        LosePane.getChildren().add(exit);
        LosePane.setRightAnchor(exit, 400.0);
        LosePane.setTopAnchor(exit, 480.0);


        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/loser.png"),960,540,false,false);
        BackgroundImage mapBack = new BackgroundImage(MapBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));
        Background mapback = new Background(mapBack);
        LosePane.setBackground(mapback);

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // 获取按钮所在的 Stage
                Stage stage1 = (Stage) exit.getScene().getWindow();
                stage1.close();
            }
        });

    }

}
