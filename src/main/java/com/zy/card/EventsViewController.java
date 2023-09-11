package com.zy.card;

import com.zy.card.util.HandCards;
import com.zy.card.util.PlaySound;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.zy.card.Obj.*;

public class EventsViewController implements Initializable {
    @FXML
    private AnchorPane Coin_Pane ;
    @FXML
    private AnchorPane Infobar;
    @FXML
    private AnchorPane EventsMainBack;
    @FXML
    private ImageView SmallHero;
    @FXML
    private ImageView EventBoxImage;
    @FXML
    private ImageView PictureofEvent;
    @FXML
    private Label NameofEvent;
    @FXML
    private Text DetailsOfEvent;

    @FXML
    private Button ButtonSetting;
    @FXML
    private Button EventChoose1;
    @FXML
    private Button EventChoose2;
    @FXML
    private Button ButtonMap;
    @FXML
    private Button ButtonCardYouhave;
    @FXML
    private ImageView Coin;
    @FXML
    private ImageView Bloodgauge;

    private List<Stage> openStages = new ArrayList<>();

    private Scene currentScene = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image CoinFrame01 = new Image(getClass().getResourceAsStream("res/Image/CoinFrame.png"),80,30,false,true);
        BackgroundImage CoinFrame02 = new BackgroundImage(CoinFrame01, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background CoinFrame03 = new Background(CoinFrame02);
        Coin_Pane.setBackground(CoinFrame03);

        Image Infobar01 = new Image(getClass().getResourceAsStream("res/Image/Infobar.jpg"),970,30,false,true);
        BackgroundImage Infobar02 = new BackgroundImage(Infobar01, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        Background Infobar03 = new Background(Infobar02);
        Infobar.setBackground(Infobar03);

        Image pictureofevent = new Image(getClass().getResourceAsStream("res/Image/奥恩煅炉.png"),200,200,false,true);
        PictureofEvent.setImage(pictureofevent);

        Image EventChooseButton01 = new Image(getClass().getResourceAsStream("res/Image/EventChooseButton.png"),240,36,false,true);
        BackgroundImage EventChooseButton001 = new BackgroundImage(EventChooseButton01,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        EventChoose1.setBackground(new Background(EventChooseButton001));
        EventChoose2.setBackground(new Background(EventChooseButton001));

        EventChoose1.setAlignment(Pos.CENTER);
        EventChoose2.setAlignment(Pos.CENTER);

        EventChoose1.setText("随机获得一张强大的手牌");
        EventChoose1.setStyle("-fx-text-fill: red;");
        EventChoose2.setText("回复30点血量");
        EventChoose2.setStyle("-fx-text-fill: green;");


        Image MapBackground = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/inhabit_back.png"),960,540,false,false);
        BackgroundImage mapBack = new BackgroundImage(MapBackground,
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));

        Background mapback = new Background(mapBack);
        EventsMainBack.setBackground(mapback);

        Image eventboximage =new Image(getClass().getResourceAsStream("res/Image/EventBox.png"),665,407,false,true);
        EventBoxImage.setImage(eventboximage);

        Image coin = new Image(getClass().getResourceAsStream("res/Image/Coin.png"),20,20,false,true);
        Coin.setImage(coin);

        Image bloodgauge = new Image(getClass().getResourceAsStream("res/Image/BloodGauge.png"),200,40,false,true);
        Bloodgauge.setImage(bloodgauge);

        Image smallHero = new Image(getClass().getResourceAsStream("res/Image/SmallHero.png"),60,60,false,true);
        SmallHero.setImage(smallHero);

        Image ButtonSetting01 = new Image(getClass().getResourceAsStream("res/Image/setting.png"),31,31,false,true);
        ImageView ButtonSetting02 = new ImageView(ButtonSetting01);
        ButtonSetting.setGraphic(ButtonSetting02);
        ButtonSetting.setStyle("-fx-background-color: transparent;");

        Image ButtonMap01 = new Image(getClass().getResourceAsStream("res/Image/Mapicon.png"),30,30,false,true);
        ImageView ButtonMap02 = new ImageView(ButtonMap01);
        ButtonMap.setStyle("-fx-background-color: transparent;");
        ButtonMap.setGraphic(ButtonMap02);

        Image ButtonCardYouhave01 = new Image(getClass().getResourceAsStream("res/Image/CardYouhaveicon.png"),35,35,false,true);
        ImageView ButtonCardYouhave02 = new ImageView(ButtonCardYouhave01);
        ButtonCardYouhave.setGraphic(ButtonCardYouhave02);
        ButtonCardYouhave.setStyle("-fx-background-color: transparent;");

        addListeners_EventsView();
    }

    public void addListeners_EventsView(){


        //-------------------------------------------------------------------------------------------------------
        ButtonMap.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonMap.setEffect(new DropShadow());
            }
        });
        ButtonMap.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonMap.setEffect(null);
            }
        });
        ButtonMap.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonMap);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        ButtonMap.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonMap);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
//        ButtonMap.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });
//-------------------------------------------------------------------------------------------------------
        ButtonSetting.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonSetting.setEffect(new DropShadow());
            }
        });
        ButtonSetting.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonSetting.setEffect(null);
            }
        });
        ButtonSetting.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonSetting);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        ButtonSetting.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonSetting);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
//        ButtonSetting.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });

//-------------------------------------------------------------------------------------------------------
        ButtonCardYouhave.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonCardYouhave.setEffect(new DropShadow());
            }
        });
        ButtonCardYouhave.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonCardYouhave.setEffect(null);
            }
        });
        ButtonCardYouhave.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonCardYouhave);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        ButtonCardYouhave.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonCardYouhave);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
//        ButtonCardYouhave.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });

//----------------------------------------------------------------------------------------------------------------------

        EventChoose1.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                EventChoose1.setEffect(new DropShadow());
            }
        });

        EventChoose1.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                EventChoose1.setEffect(null);
            }
        });

        EventChoose1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(HelloApplication.Sound_music.isSelected()){
                    PlaySound.playMusic();}
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose1);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });

        EventChoose1.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose1);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

         //点击后作出反应
         EventChoose1.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {
                EventStage.close();
                MapStage.show();
                allObjects.getAllHoldingCards().add(new HandCards("巫妖之祸",2,40));
             }
         });

        //--------------------------------------------------------------------------------------------------------------

        EventChoose2.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                EventChoose2.setEffect(new DropShadow());
            }
        });

        EventChoose2.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                EventChoose2.setEffect(null);
            }
        });

        EventChoose2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(HelloApplication.Sound_music.isSelected()){
                    PlaySound.playMusic();}
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose2);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });

        EventChoose2.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose2);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

// 点击后作出反应
         EventChoose2.setOnMouseClicked(new EventHandler<MouseEvent>() {
             @Override
             public void handle(MouseEvent mouseEvent) {
                 EventStage.close();
                 MapStage.show();
                 if(allObjects.getHero().getHP()+30>=allObjects.getHero().getMAX_HP())
                 {
                     allObjects.getHero().setHP(allObjects.getHero().getMAX_HP());
                 }else{
                     allObjects.getHero().setHP(allObjects.getHero().getHP()+30);
                 }
             }
         });
        //--------------------------------------------------------------------------------------------------------------

        ButtonSetting.setOnAction(new EventHandler<ActionEvent>() {
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

    private void closeScene (Scene scene){
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        }
    }

    private void openScene () {
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
