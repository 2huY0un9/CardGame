package com.zy.card;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.util.Duration;

public class CardGameScene extends SubScene {

    private static String BACKGROUND_IMG =
            CardGameScene.class.getResource("/com/zy/card/res/Image/setting_background.png").toExternalForm();

    private  boolean isHidden = true;

    public CardGameScene(){
        super(new AnchorPane(),688,391);
        prefWidth(688);
        prefHeight(391);

        Image image = new Image(BACKGROUND_IMG, 688,391,false,true);

        BackgroundImage backgroundImage=
                new BackgroundImage(image,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        null);

        AnchorPane anchorPane =(AnchorPane) getRoot();
        anchorPane.setBackground(new Background(backgroundImage));

        setLayoutY(-900);
        setLayoutY(50);
    }
    //移动
    public void moveSubScene(){
        TranslateTransition translateTransition =new TranslateTransition();
        translateTransition.setDuration(Duration.seconds(0.4));
        translateTransition.setNode(this);

        if(isHidden){
            translateTransition.setToX(950);
            isHidden = false;
        }
        else {
            translateTransition.setToX(0);
            isHidden = true;
        }
        translateTransition.play();
    }
}
