package com.zy.card;

import com.zy.card.util.WinButton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WinController implements Initializable {
    @FXML
    private AnchorPane WinPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        WinButton exit =new WinButton("EXIT");
        WinPane.getChildren().add(exit);
        WinPane.setRightAnchor(exit, 400.0);
        WinPane.setTopAnchor(exit, 450.0);
        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/win_back.png"),960,540,false,false);
        BackgroundImage mapBack = new BackgroundImage(MapBackground, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));
        Background mapback = new Background(mapBack);
        WinPane.setBackground(mapback);

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
