package com.zy.card.util;

import com.zy.card.CardGameButton;
import com.zy.card.HelloApplication;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class ChooseButton extends Button {
    private  static final String btn =
            CardGameButton.class.getResource("/com/zy/card/res/Image/button_choose.png").toExternalForm();

    private static String btn_setting_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-text-fill: black;"+
                    "-fx-font-size: 18px;";

    public ChooseButton(String text){
        setText(text);
        setPrefWidth(140);
        setPrefHeight(92);
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
                if(HelloApplication.Sound_music.isSelected()){
                    PlaySound.playMusic();}
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ChooseButton.this);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLayoutY(getLayoutY()-10);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100), ChooseButton.this);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });
    }
}
