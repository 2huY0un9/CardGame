package com.zy.card.util;

import com.zy.card.Obj;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Enemy extends AnchorPane {

    public Image HpBar = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/HPbar.png"),122,18,false,true);
    public ArrayList<String> buffList = new ArrayList<>();

    private String intention = "Attack";
    private  int HP;
    private boolean chosen = false;
    private boolean dead = false;
    //  private String Enemy="/com/zy/card/res/Image/Enemy_.png";

    private  int MAX_HP;

    private int strength;//力量，用于攻击附加伤害

    private int max_strength;

    private int attack;

    private int shield;
    Canvas canvas_e = new Canvas();
    AnimationTimer animationTimer_e;
    GraphicsContext gc_e;


    public Enemy(String name,int maxhp){
//        String imagePath = "/com/zy/card/res/Image/Character/monster_"+name+".png";
//        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath));
//        ImageView imageView = new ImageView(backgroundImage);
//        imageView.setFitWidth(200); // 设置宽度
//        imageView.setFitHeight(200); // 设置高度
        MAX_HP = maxhp;
        HP = MAX_HP;
        attack = MAX_HP/8;


        this.gc_e = canvas_e.getGraphicsContext2D();
        canvas_e.setWidth(122);
        canvas_e.setHeight(8);
        canvas_e.setLayoutX(5);
        canvas_e.setLayoutY(-15);
        canvas_e.toFront();
        this.gc_e.setFill(Color.TRANSPARENT);
        this.gc_e.fillRect(0,0,122,18);

        this.getChildren().add(canvas_e);

        animationTimer_e = new AnimationTimer() {
            @Override
            public void handle(long l) {
                draw_hpbar();
            }
        };

        animationTimer_e.start();

        this.setPrefHeight(380);
        this.setPrefWidth(300);


        String imagepath = "/com/zy/card/res/Image/Character/monster_"+name+".png";
        Image BackgroundImage = new Image(getClass().getResourceAsStream(imagepath),150,150,false,true);
        BackgroundImage backgroundimage = new BackgroundImage(BackgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        this.setBackground(new Background(backgroundimage));

        String HPGaugePath = "/com/zy/card/res/Image/HPGauge.png";
        Image HPGaugeImage = new Image(getClass().getResourceAsStream(HPGaugePath),150,20,false,true);
        ImageView HPGImageview = new ImageView(HPGaugeImage);
        HPGImageview.setLayoutX(-10.0);
        HPGImageview.setLayoutY(-20.0);
        HPGImageview.toBack();
        HPGImageview.setBlendMode(BlendMode.OVERLAY);

        getChildren().add(HPGImageview);

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(!chosen)
                {
                    chosen = true;
                    setEffect(new DropShadow());
                }else {
                    chosen = false;
                    setEffect(null);
                }
            }
        });

    }

    public void draw_hpbar(){
        double ratio = (double) HP / MAX_HP;
        double barWidth = ratio * HpBar.getWidth();
        gc_e.drawImage(HpBar, 0, 0, barWidth, HpBar.getHeight());
        canvas_e.setWidth(barWidth);
    }
    public int getHP() {
        return HP;
    }

    public int getAttack() {
        return attack;
    }

    public int getShield() {
        return shield;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void setHP(int num){
        HP = num;
    }
    public void setMAX_HP(int num){
        MAX_HP = num;
    }
    public void setStrength(int num){
        strength = num;
    }
    public void setmax_strength(int num){
        max_strength = num;
    }
    public void setAttack(int num){
        attack = num;
    }

    public void setShield(int shield) { this.shield = shield; }

    public boolean isChosen() {
        return chosen;
    }

    public void fatigue(){
        this.strength=strength/2;
    }//乏力
    public void maul_heavily(){
        HP=HP-MAX_HP/2;
    }//重创
    public void abundant(){//力量充沛
        strength=max_strength;
    }



    public double attack_the_player_1(double attack){
        return attack+this.attack+this.strength;
    }//普通攻击，力量，基于白值以及你的参数

    public double attack_the_player_2(double attack){//回血加普通攻击
        HP=this.HP+4;
        return attack+this.attack;
    }
    public double attack_the_player(double attack){
        return this.attack;
    }
    public void Maximum_bleeding(){
        HP=this.HP-this.HP/2;
    }//最大扣血


    public void addbufflsit(String buff)
    {
        buffList.add(buff);
    }
    public void finalbuff(){
        for (String element : buffList) {
            switch (element) {
                case "乏力"://力量减半
                    fatigue();
                    break;
                case "重创"://扣除一半最大生命值
                    maul_heavily();
                    break;
                case "力量充沛":
                    abundant();//回复到最大力量值
                    break;
                case "重伤":
                    Maximum_bleeding();//扣一半血
                    break;
                default:
                    break;
            }
        }
        buffList.clear();
    }

    public void Action(){
        switch (intention){
            case "Attack":{
                intention = "Defence";
                Obj.allObjects.getHero().GotHit(attack);
                if(Obj.allObjects.getHero().getHP()<=0)
                    Obj.allObjects.getHero().dead();
                break;
            }
            case "Defence":{
                intention = "Attack";
                setShield(10);
                break;
            }
        }
    }

    public void GotHit(int value){
        if(shield-value > 0)
        {
            setShield(shield-value);
        }else{
            setHP(HP+(shield-value));
        }
    }

    public void dead(){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        // 启动动画
        fadeOut.play();
    }
}
