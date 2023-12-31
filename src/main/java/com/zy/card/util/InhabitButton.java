package com.zy.card.util;

import com.zy.card.CardGameButton;
import com.zy.card.HelloApplication;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class InhabitButton extends Button {

    //导入图片
    private  static final String btn_blood =
            CardGameButton.class.getResource("/com/zy/card/res/Image/addBlood_button.png").toExternalForm();
    private  static final String btn_attack=
            CardGameButton.class.getResource("/com/zy/card/res/Image/addAttack_button.png").toExternalForm();
    private static String btn_blood_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_blood + "'); " +
                    "-fx-text-fill: gray;" +
                    "-fx-font-size: 18px;";
    private static String btn_attack_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_attack + "'); " +
                    "-fx-text-fill: gray;"+
                    "-fx-font-size: 18px;";
    public  InhabitButton (String text){
        setPrefWidth(268);
        setPrefHeight(240);
        setStyle(btn_blood_style);
        addListeners();
    }
    public InhabitButton (){
        setPrefWidth(268);
        setPrefHeight(240);
        setStyle(btn_attack_style);
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
                if(HelloApplication.Sound_music.isSelected()){PlaySound.playMusic();}
                setLayoutY(getLayoutY()+10);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),InhabitButton.this);
                scaleTransition.setToX(0.9);
                scaleTransition.setToY(0.9);
                scaleTransition.play();
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLayoutY(getLayoutY()-10);
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),InhabitButton.this);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                scaleTransition.play();
            }
        });
    }
}
