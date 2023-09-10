package com.zy.card.util;

import javafx.event.EventHandler;
import javafx.scene.control.CheckBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class SettingButton extends  CheckBox {

    //导入图片
    private  static final String btn_pressed =
            SettingButton.class.getResource("/com/zy/card/res/Image/check1.png").toExternalForm();
    private  static final String btn_free=
            SettingButton.class.getResource("/com/zy/card/res/Image/check1.png").toExternalForm();

    private static String btn_pressed_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-text-fill: gray;" +
                    "-fx-font-size: 18px;" +
                    "-fx-alignment: center;"+
                    "-fx-padding: 2px 8px 2px 20px;";
    private static String btn_free_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free + "'); " +
                    "-fx-background-size: cover; " +
                    "-fx-text-fill: gray;"+
                    "-fx-font-size: 18px;"+
                    "-fx-padding: 2px 8px 2px 20px;"+
                    "-fx-alignment: center;";
    ;
    ;
    public SettingButton(String text){
        setText(text);
        setPrefWidth(180);
        isSelected();
        setPrefHeight(64);
        setStyle(btn_free_style);
        addListeners();
    }
    public SettingButton(){
        setPrefWidth(180);
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
