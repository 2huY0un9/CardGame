package com.zy.card;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class CardGameButton extends Button {

    //导入图片
    private  static final String btn_pressed =
            CardGameButton.class.getResource("/com/zy/card/res/Image/normal_button_1.png").toExternalForm();
    private  static final String btn_free=
            CardGameButton.class.getResource("/com/zy/card/res/Image/normal_button_1.png").toExternalForm();
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
                setStyle(btn_pressed_style);
                setLayoutY(getLayoutY()+10);
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setStyle(btn_free_style);
                setLayoutY(getLayoutY()-10);
            }
        });
    }
}
