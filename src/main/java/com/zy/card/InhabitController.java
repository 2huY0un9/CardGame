package com.zy.card;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import com.zy.card.util.InhabitButton;

import java.net.URL;
import java.util.ResourceBundle;

import static com.zy.card.Obj.MapStage;
import static com.zy.card.Obj.RestStage;


public class InhabitController implements Initializable {

    @FXML
    private AnchorPane inhabitPane;
    @FXML
    private AnchorPane Coin_Pane ;
    @FXML
    private AnchorPane Infobar;
    @FXML
    private ImageView FightBackground;
    @FXML
    private ImageView SmallHero;
    @FXML
    private ImageView Coin;
    @FXML
    private ImageView Bloodgauge;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/inhabit_back.png"),960,540,false,false);
        BackgroundImage mapBack = new BackgroundImage(MapBackground,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));

        Background mapback = new Background(mapBack);
        inhabitPane.setBackground(mapback);
        Image CoinFrame01 = new Image(getClass().getResourceAsStream("res/Image/CoinFrame.png"),80,30,false,true);
        BackgroundImage CoinFrame02 = new BackgroundImage(CoinFrame01, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background CoinFrame03 = new Background(CoinFrame02);
        Coin_Pane.setBackground(CoinFrame03);

        Image Infobar01 = new Image(getClass().getResourceAsStream("res/Image/InfoBar.png"),970,30,false,true);
        BackgroundImage Infobar02 = new BackgroundImage(Infobar01, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background Infobar03 = new Background(Infobar02);
        Infobar.setBackground(Infobar03);

        Image coin = new Image(getClass().getResourceAsStream("res/Image/Coin.png"),20,20,false,true);
        Coin.setImage(coin);

        Image bloodgauge = new Image(getClass().getResourceAsStream("res/Image/BloodGauge.png"),180,36,false,true);
        Bloodgauge.setImage(bloodgauge);

        Image smallHero = new Image(getClass().getResourceAsStream("res/Image/SmallHero.png"),60,60,false,true);
        SmallHero.setImage(smallHero);

        InhabitButton blood =new InhabitButton("ad");
        inhabitPane.getChildren().add(blood);
        inhabitPane.setRightAnchor(blood, 525.0);
        inhabitPane.setTopAnchor(blood, 150.0);

        InhabitButton attack =new InhabitButton();
        inhabitPane.getChildren().add(attack);
        inhabitPane.setRightAnchor(attack, 125.0);
        inhabitPane.setTopAnchor(attack, 150.0);

        blood.setOnAction(actionEvent -> {
            RestStage.close();
            MapStage.show();
            RestStage=null;
            //还没写逻辑代码

        });

        attack.setOnAction(actionEvent -> {
            RestStage.close();
            MapStage.show();
            RestStage=null;
            //还没写逻辑代码

        });
    }
}
