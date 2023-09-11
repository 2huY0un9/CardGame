package com.zy.card;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.zy.card.util.InhabitButton;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.zy.card.Obj.*;


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

    private Scene currentScene = null;

    private List<Stage> openStages = new ArrayList<>();

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

        CardGameButton setting = new CardGameButton(1);
        inhabitPane.getChildren().add(setting);
        inhabitPane.setRightAnchor(setting, 20.0);
        inhabitPane.setTopAnchor(setting, 5.0);


        blood.setOnAction(actionEvent -> {
            RestStage.close();
            MapStage.show();
            RestStage=null;
            //回复15点血量
            if(allObjects.getHero().getHP()+15>=allObjects.getHero().getMAX_HP())
            {
                allObjects.getHero().setHP(allObjects.getHero().getMAX_HP());
            }else{
                allObjects.getHero().setHP(allObjects.getHero().getHP()+15);
            }

        });

        attack.setOnAction(actionEvent -> {
            RestStage.close();
            MapStage.show();
            RestStage=null;
            //最大战技点加一
            allObjects.setMax_ActionPoint(allObjects.getMax_ActionPoint()+1);

        });
        setting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentScene != null) {
                    // 如果当前存在 Scene，关闭它并设置为 null
                    closeScene(currentScene);
                    currentScene = null;
                } else {
                    // 如果当前没有 Scene，创建一个新的 Scene 并打开它
                    openScene();
                }
            }
        });
    }
    private void closeScene(Scene scene) {
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        }
    }

    private void openScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("setting-view.fxml"));
        AnchorPane settingPane = null;
        try {
            settingPane = fxmlLoader.load();
            settingPane.setScaleX(0);
            settingPane.setScaleY(0);

            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.5), settingPane);
            scaleIn.setToX(1);
            scaleIn.setToY(1);
            scaleIn.play();

            // 创建一个透明的场景，包含缩放后的内容
            Scene scene = new Scene(settingPane, Color.TRANSPARENT);

            // 创建一个透明的窗口
            Stage dialogStage = new Stage(StageStyle.TRANSPARENT);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Setting");

            // 设置关闭按钮的处理程序，关闭 Scene 并将 currentScene 设置为 null
            dialogStage.setOnCloseRequest(event -> {
                closeScene(scene);
                currentScene = null;
            });

            dialogStage.show();
            currentScene = scene;
            openStages.add(dialogStage);
            dialogStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
