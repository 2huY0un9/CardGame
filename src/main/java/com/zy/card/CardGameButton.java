package com.zy.card;

import com.zy.card.util.PlaySound;
import com.zy.card.util.SettingButton;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class CardGameButton extends Button {

    //导入图片
    private  static final String btn_pressed =
            CardGameButton.class.getResource("/com/zy/card/res/Image/normal_button_1.png").toExternalForm();
    private  static final String btn_free=
            CardGameButton.class.getResource("/com/zy/card/res/Image/normal_button_1.png").toExternalForm();

    private  static final String btn_free_1=
            CardGameButton.class.getResource("/com/zy/card/res/Image/check1.png").toExternalForm();

    private  static final String btn_setting=
            SettingButton.class.getResource("/com/zy/card/res/Image/setting.png").toExternalForm();
    private static String btn_pressed_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free + "'); " +
                    "-fx-text-fill: gray;" +
                    "-fx-font-size: 18px;";
    private static String btn_free_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free + "'); " +
                    "-fx-text-fill: gray;"+
                    "-fx-font-size: 18px;";

    private static String btn_setting_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_setting + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-text-fill: gray;"+
                    "-fx-font-size: 18px;";

    private static String btn_free_style_1 =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free_1 + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-text-fill: gray;" +
                    "-fx-font-size: 18px;" +
                    "-fx-alignment: center;";
    //                    "-fx-padding: 2px 8px 2px 20px;";
    public  CardGameButton (String text){
        setText(text);
        setPrefWidth(150);
        setPrefHeight(64);
        setStyle(btn_free_style);
        addListeners();
    }
    public  CardGameButton (){
        setPrefWidth(150);
        setPrefHeight(64);
        setStyle(btn_free_style);
        addListeners();
    }

    public  CardGameButton (String text,boolean f){
        setPrefWidth(180);
        setPrefHeight(64);
        setStyle(btn_free_style_1);
        addListeners();
        setText(text);
    }
    public  CardGameButton (int num){
        setPrefWidth(40);
        setPrefHeight(40);
        setStyle(btn_setting_style);
        addListeners();
    }

    public void addListeners()
    {
        this.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(new DropShadow());
            }
        });
        this.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setEffect(null);
            }
        });
        this.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                setStyle(btn_pressed_style);
                setLayoutY(getLayoutY()+10);
                if(HelloApplication.Sound_music.isSelected()){PlaySound.playMusic();}
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),CardGameButton.this);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
//                setStyle(btn_free_style);
                setLayoutY(getLayoutY()-10);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), CardGameButton.this);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });
    }
}
