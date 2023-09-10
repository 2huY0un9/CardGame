package com.zy.card;


import com.zy.card.util.HandCards;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.zy.card.Obj.MapStage;
import static com.zy.card.Obj.allObjects;


public class FightViewController implements Initializable {
    @FXML
    private AnchorPane Coin_Pane ;
    @FXML
    private AnchorPane Infobar;
    @FXML
    private AnchorPane FightMainBack;
    @FXML
    private ImageView SmallHero;
    @FXML
    private Button ButtonSetting;
    @FXML
    private Button ButtonMap;
    @FXML
    private Button ButtonCardYouhave;
    @FXML
    private Button CardToBeGet;
    @FXML
    private Button CardDeserted;
    @FXML
    private Button ButtonNextStep;
    @FXML
    private ImageView Coin;
    @FXML
    private ImageView Bloodgauge;
    @FXML
    private ImageView SkillPointsGauge;
    private HBox handcardsArea;
    private HBox enemyArea;

    private Scene currentScene = null;

    private List<Stage> openStages = new ArrayList<>();

    private Canvas canvas = new Canvas(960,540);
    private AnimationTimer animationTimer;
    private GraphicsContext gc ;

    private List<HandCards> handCardsList = new ArrayList<>();

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

        Image fightbackground = new Image(getClass().getResourceAsStream("res/Image/FightBackground.png"),970,546,false,true);
        BackgroundImage MainBack = new BackgroundImage(fightbackground,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        FightMainBack.setBackground(new Background(MainBack));


        Image coin = new Image(getClass().getResourceAsStream("res/Image/Coin.png"),20,20,false,true);
        Coin.setImage(coin);

        Image bloodgauge = new Image(getClass().getResourceAsStream("res/Image/BloodGauge.png"),200,40,false,true);
        Bloodgauge.setImage(bloodgauge);

        Image smallHero = new Image(getClass().getResourceAsStream("res/Image/SmallHero.png"),60,60,false,true);
        SmallHero.setImage(smallHero);

        Image skillpointsgauge = new Image(getClass().getResourceAsStream("res/Image/SkillPoints.png"),80,84,false,true);
        SkillPointsGauge.setImage(skillpointsgauge);

        Image ButtonNextStep01 = new Image(getClass().getResourceAsStream("res/Image/ButtonNextStep.png"),100,40,false,true);
        BackgroundImage buttonnextStep001 = new BackgroundImage(ButtonNextStep01,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        ButtonNextStep.setStyle("-fx-background-color: transparent;");
        ButtonNextStep.setBackground(new Background(buttonnextStep001));
        ButtonNextStep.setText("结束回合");
        ButtonNextStep.setAlignment(Pos.CENTER);
        ButtonNextStep.setStyle("-fx-text-fill: white;");


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

        Image ButtonCardToBeGet01 = new Image(getClass().getResourceAsStream("res/Image/CardToBeGeticon.png"),60,60,false,true);
        ImageView ButtonCardToBeGet02 = new ImageView(ButtonCardToBeGet01);
        CardToBeGet.setGraphic(ButtonCardToBeGet02);
        CardToBeGet.setStyle("-fx-background-color: transparent;");

        Image ButtonCardDeserted01 = new Image(getClass().getResourceAsStream("res/Image/CardDesertedicon.png"),60,60,false,true);
        ImageView ButtonCardDeserted02 = new ImageView(ButtonCardDeserted01);
        CardDeserted.setStyle("-fx-background-color: transparent;");
        CardDeserted.setGraphic(ButtonCardDeserted02);

        addListeners_FightView();

        handcardsArea = new HBox();
        handcardsArea.setAlignment(Pos.CENTER);
        handcardsArea.setPrefSize(500,140);
        handcardsArea.setLayoutY(400);
        handcardsArea.setLayoutX(230);

        FightMainBack.getChildren().add(handcardsArea);
    }

//废案
//    public void DrawHandCards(){
//        SnapshotParameters pic = new SnapshotParameters();
//        pic.setFill(Color.TRANSPARENT);
//        int i=0;
//        int size = Obj.allObjects.getHandCardsArray().size();
//        for (HandCards hc:Obj.allObjects.getHandCardsArray()){
//            WritableImage img = hc.snapshot(pic,null);
//            gc.drawImage(img,480-(size*0.5)*100+i*100,380);
//            i++;
//        }
//    }

    public void addListeners_FightView(){

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
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
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
        ButtonMap.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Stage originalStage = (Stage) FightMainBack.getScene().getWindow();
                originalStage.hide();
                MapStage.show();
            }
        });
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
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
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
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
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


//-------------------------------------------------------------------------------------------------------
        CardToBeGet.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CardToBeGet.setEffect(new DropShadow());
            }
        });
        CardToBeGet.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CardToBeGet.setEffect(null);
            }
        });
        CardToBeGet.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),CardToBeGet);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
                scaleTransition.play();
            }
        });
        CardToBeGet.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),CardToBeGet);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
//        CardToBeGet.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });


//-------------------------------------------------------------------------------------------------------
        CardDeserted.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CardDeserted.setEffect(new DropShadow());
            }
        });
        CardDeserted.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CardDeserted.setEffect(null);
            }
        });
        CardDeserted.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),CardDeserted);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
                scaleTransition.play();
            }
        });
        CardDeserted.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),CardDeserted);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
//        CardDeserted.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });


//-------------------------------------------------------------------------------------------------------
        ButtonNextStep.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonNextStep.setEffect(new DropShadow());
            }
        });
        ButtonNextStep.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonNextStep.setEffect(null);
            }
        });
        ButtonNextStep.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonNextStep);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
                scaleTransition.play();
            }
        });
        ButtonNextStep.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonNextStep);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });

        //点击后作出反应
        ButtonNextStep.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                //回合结束
                allObjects.getHandCardsArray().clear();
                handcardsArea.getChildren().clear();
                PauseTransition delay = new PauseTransition(Duration.seconds(2));
                // 在等待时间结束后执行逻辑代码
                delay.setOnFinished(e -> {

                    allObjects.DrawCards();
                    for (HandCards hc:allObjects.getHandCardsArray())
                    {
//                        hc.setLayoutX(480-allObjects.getHandCardsArray().size()*0.5*100+i*100);
//                        hc.setLayoutY(400);
                        if (!handcardsArea.getChildren().contains(hc)) {
                            handcardsArea.getChildren().add(hc);
                        }
                    }
                });
                // 启动延迟
                delay.play();
            }
        });


//-------------------------------------------------------------------------------------------------------

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


