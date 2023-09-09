package com.zy.card;

import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

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

        Image pictureofevent = new Image(getClass().getResourceAsStream("res/Image/TestChooseBG.png"),200,200,false,true);
        PictureofEvent.setImage(pictureofevent);

        Image EventChooseButton01 = new Image(getClass().getResourceAsStream("res/Image/EventChooseButton.png"),240,36,false,true);
        BackgroundImage EventChooseButton001 = new BackgroundImage(EventChooseButton01,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,null);
        EventChoose1.setBackground(new Background(EventChooseButton001));
        EventChoose2.setBackground(new Background(EventChooseButton001));

        EventChoose1.setAlignment(Pos.CENTER);
        EventChoose2.setAlignment(Pos.CENTER);

        EventChoose1.setText("dsf;kjdslkfjlskjflkjal");
        EventChoose1.setStyle("-fx-text-fill: red;");
        EventChoose2.setText("fdsfdsfds");
        EventChoose2.setStyle("-fx-text-fill: green;");


        Color backgroundColor = Color.BLACK;
        BackgroundFill backgroundFill = new BackgroundFill(backgroundColor, null, null);
        Background blackBackground = new Background(backgroundFill);
        EventsMainBack.setBackground(blackBackground);

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
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose1);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
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

// 点击后作出反应
// EventChoose1.setOnMouseClicked(new EventHandler<MouseEvent>() {
//     @Override
//     public void handle(MouseEvent mouseEvent) {
//
//     }
// });

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
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), EventChoose2);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
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
// EventChoose2.setOnMouseClicked(new EventHandler<MouseEvent>() {
//     @Override
//     public void handle(MouseEvent mouseEvent) {
//
//     }
// });
    }
}
