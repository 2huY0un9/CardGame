package com.zy.card.util;

import com.zy.card.Obj;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class HandCards extends AnchorPane {

    private String name;//唯一标识
    private int value;
    private int cost;//1，2，3费
    private String type = "";//攻击attack，防御defence，能力ability

    private boolean chosen = false;

    private boolean BeUsed = false;

    private double orgSceneX, orgSceneY;

    private Label text;
    private Label title;
    private String description;

    private Image CardFrameImg;
    private Image CardBackImg;
    private Image ContentImage;
    private Image CostImage;

    private ImageView Content;
    private ImageView CardFrame;
    private ImageView CostView;

    public HandCards() {
        super();
        name = "null";
        cost = 0;
        setPrefSize(100,130);

        //设置卡片标题
        title = new Label();
        title.setText(name);
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.WHITE);
        title.setPrefSize(50,20);
        title.setLayoutX(25);
        title.setLayoutY(23);

        //设置描述文字
        text = new Label() ;
        setDescription();
        text.setText(description);
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.WHITE);
        text.setWrapText(true);
        text.setPrefSize(70,70);
        text.setLayoutX(15);
        text.setLayoutY(95);
        text.setFont(new Font(10));

        //设置内容图片
        ContentImage = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/"+name+".png"));
        Content = new ImageView();
        Content.setImage(ContentImage);
        Content.setFitHeight(60);
        Content.setFitWidth(80);
        Content.setLayoutX(10);
        Content.setLayoutY(50);

        //设置CostView
        CostImage = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/Cost"+cost+".png"),20,20,false,true);
        CostView = new ImageView();

        CostView.setImage(CostImage);
        CostView.setLayoutX(0);
        CostView.setLayoutY(0);
        CostView.setFitWidth(30);
        CostView.setFitHeight(30);

        //设置卡牌背景
        CardBackImg = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/CardBack.png"),100,150,false,true);
        BackgroundImage back = new BackgroundImage(CardBackImg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(back));

        //设置卡牌框架
        CardFrameImg = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/CardFrame.png"),120,160,false,true);
        CardFrame = new ImageView();
        CardFrame.setImage(CardFrameImg);
        CardFrame.setLayoutX(-10);
        CardFrame.setLayoutY(-10);

        getChildren().add(CardFrame);
        getChildren().add(CostView);
        getChildren().add(title);
        getChildren().add(text);
        getChildren().add(Content);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Obj.allObjects.CanCardBeClick();
                if(chosen ==false){
                    chosen =true;
                    // 创建一个缩放动画，从当前大小变为1.2倍大小，动画时长为200毫秒
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), HandCards.this);
                    scaleTransition.setToX(1.2);
                    scaleTransition.setToY(1.2);
                    scaleTransition.play();

                    // 创建一个向上移动动画，移动距离为-30像素，动画时长为200毫秒
                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), HandCards.this);
                    translateTransition.setToY(-30);
                    translateTransition.play();
                }else {
                    chosen =false;
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), HandCards.this);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.play();

                    // 创建一个向下移动动画，移动距离为0像素，动画时长为200毫秒
                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), HandCards.this);
                    translateTransition.setToY(0);
                    translateTransition.play();
                }
            }
        });






    }

    public HandCards(String name,int cost,int value) {
        super();
        this.name = name;
        this.cost = cost;
        this.value = value;
        setPrefSize(100,130);

        //设置卡片标题
        title = new Label();
        title.setText(name);
        title.setAlignment(Pos.CENTER);
        title.setTextFill(Color.WHITE);
        title.setPrefSize(50,20);
        title.setLayoutX(25);
        title.setLayoutY(23);

        //设置描述文字
        text = new Label() ;
        setDescription();
        text.setText(description);
        text.setAlignment(Pos.CENTER);
        text.setTextFill(Color.WHITE);
        text.setWrapText(true);
        text.setPrefSize(70,70);
        text.setLayoutX(15);
        text.setLayoutY(95);
        text.setFont(new Font(10));

        //设置内容图片
        ContentImage = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/"+name+".png"));
        Content = new ImageView();
        Content.setImage(ContentImage);
        Content.setFitHeight(60);
        Content.setFitWidth(80);
        Content.setLayoutX(10);
        Content.setLayoutY(50);

        //设置CostView
        CostImage = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/Cost"+cost+".png"),20,20,false,true);
        CostView = new ImageView();

        CostView.setImage(CostImage);
        CostView.setLayoutX(0);
        CostView.setLayoutY(0);
        CostView.setFitWidth(30);
        CostView.setFitHeight(30);

        //设置卡牌背景
        CardBackImg = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/CardBack.png"),100,150,false,true);
        BackgroundImage back = new BackgroundImage(CardBackImg, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        setBackground(new Background(back));

        //设置卡牌框架
        CardFrameImg = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/CardImage/CardFrame.png"),120,160,false,true);
        CardFrame = new ImageView();
        CardFrame.setImage(CardFrameImg);
        CardFrame.setLayoutX(-10);
        CardFrame.setLayoutY(-10);

        getChildren().add(CardFrame);
        getChildren().add(CostView);
        getChildren().add(title);
        getChildren().add(text);
        getChildren().add(Content);

        //鼠标事件

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(chosen ==false){
//                    toFront();
                    chosen =true;
                    // 创建一个缩放动画，从当前大小变为1.2倍大小，动画时长为200毫秒
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), HandCards.this);
                    scaleTransition.setToX(1.2);
                    scaleTransition.setToY(1.2);
                    scaleTransition.play();

                    // 创建一个向上移动动画，移动距离为-30像素，动画时长为200毫秒
                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), HandCards.this);
                    translateTransition.setToY(-30);
                    translateTransition.play();
                }else {
//                    toBack();
                    chosen =false;
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(200), HandCards.this);
                    scaleTransition.setToX(1);
                    scaleTransition.setToY(1);
                    scaleTransition.play();

                    // 创建一个向下移动动画，移动距离为0像素，动画时长为200毫秒
                    TranslateTransition translateTransition = new TranslateTransition(Duration.millis(200), HandCards.this);
                    translateTransition.setToY(0);
                    translateTransition.play();
                }
            }
        });






    }

    public boolean isChosen() {
        return chosen;
    }

    public boolean isBeUsed() {
        return BeUsed;
    }

    public void setBeUsed(boolean beUsed) {
        BeUsed = beUsed;
    }

    public int getValue() {
        return value;
    }

    public int getCost() {
        return cost;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    private void setDescription(){
        switch (name){
            case "null":{
                description = "测试";
                break;
            }
            case "会心一击":{
                description = "对敌人造成"+ value +"点伤害";
                type = "Attack";
                break;
            }
            case "防御":{
                description = "提供" + value + "点护盾";
                type = "Defence";
                break;
            }
        }
    }

}
