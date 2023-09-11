package com.zy.card.util;

import com.zy.card.CardGameButton;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;

public class WinButton extends Button {
    //导入图片
    private  static final String btn_press =
            CardGameButton.class.getResource("/com/zy/card/res/Image/Button_win.png").toExternalForm();
    private  static final String btn_free=
            CardGameButton.class.getResource("/com/zy/card/res/Image/addAttack_button.png").toExternalForm();
    private static String btn_press_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_press + "'); " +
                    "-fx-text-fill: white;" +
                    "-fx-font-size: 18px;";
    private static String btn_free_style =
            "-fx-background-color: transparent; " +
                    "-fx-background-image: url('" + btn_free + "'); " +
                    "-fx-text-fill: white;"+
                    "-fx-font-size: 18px;";
    public  WinButton (String text){
        setText(text);
        setPrefWidth(156);
        setPrefHeight(48);
        setStyle(btn_press_style);

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
                setLayoutY(getLayoutY()+10);
            }
        });
        this.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                setLayoutY(getLayoutY()-10);
            }
        });
    }
}
