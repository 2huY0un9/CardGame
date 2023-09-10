package com.zy.card.util;

import com.zy.card.Obj;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

public class Hero extends AnchorPane {

    public Image HpBar = new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/HPbar.png"),122,18,false,true);
    private String name="Saber";
    public ArrayList<String> buffList = new ArrayList<>();
    private  int HP=90;

    private String Herourl="/com/zy/card/res/Image/Character/Hero_Saber.png";
    private int MAX_HP=90;

    private int strength=4;//力量，用于攻击附加伤害

    private int max_strength=8;

    private int attack=8;

    private int shield=0;

    Canvas canvas = new Canvas();
    AnimationTimer animationTimer;
    GraphicsContext gc;
    public Hero(){
        this.gc = canvas.getGraphicsContext2D();
        canvas.setWidth(122);
        canvas.setHeight(8);
        canvas.setLayoutX(-22.0);
        canvas.setLayoutY(131);


        this.gc.setFill(Color.TRANSPARENT);
        this.gc.fillRect(0,0,122,18);

        this.getChildren().add(canvas);



        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                draw_hpbar();
                // canvas.setWidth(canvas.getWidth()-);
            }
        };

        animationTimer.start();

        this.setPrefHeight(250);
        this.setPrefWidth(180);
        this.setLayoutY(180);
        this.setLayoutX(100);
        String imagePath = "/com/zy/card/res/Image/Character/Hero_"+name+".png";
        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath),80,150,false,true);
        BackgroundImage backgroundimage = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        this.setBackground(new Background(backgroundimage));

        String HPGaugePath = "/com/zy/card/res/Image/HPGauge.png";
        Image HPGaugeImage = new Image(getClass().getResourceAsStream(HPGaugePath),150,20,false,true);
        ImageView HPGImageview = new ImageView(HPGaugeImage);
        HPGImageview.setLayoutX(-35.0);
        HPGImageview.setLayoutY(125.0);
        HPGImageview.toBack();
        HPGImageview.setBlendMode(BlendMode.OVERLAY);

        getChildren().add(HPGImageview);
    }

    public void Heroattack(){

        TranslateTransition moveForward = new TranslateTransition(Duration.millis(200), this);
        moveForward.setByX(200);
        moveForward.play();
        moveForward.setOnFinished(event -> {
            TranslateTransition moveBackward = new TranslateTransition(Duration.millis(500), this);
            moveBackward.setByX(-200);
            moveBackward.play();
        });

    }
    public void draw_hpbar(){
        double ratio = (double) HP / MAX_HP;
        double barWidth = ratio * HpBar.getWidth();
        gc.drawImage(HpBar, 0, 0, barWidth, HpBar.getHeight());
        canvas.setWidth(barWidth);
    }
    public void max_recover(){//新生，回复最大生命
        HP=MAX_HP;
    }
    public void upliftment(){//鼓舞，攻击力加20%
        attack=attack+3;
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
    public void full(){//丰盈，回20点血不超过最大生名值
        if(HP+20<=MAX_HP)
        {
            HP=HP+20;
        }
        else
        {
            HP=MAX_HP;
        }
    }
    public double attack_the_enemy_1(double attack){
        return attack+this.attack+this.strength;
    }//普通攻击，力量，基于白值以及你的参数

    public double attack_the_enemy_2(double attack){//回血加普通攻击
        HP=this.HP+6;
        return attack+this.attack;
    }
    public double attack_the_player(double attack){
        return this.attack;
    }
    public void Maximum_bleeding(){
        HP=this.HP-this.HP/2;
    }//最大扣血,重伤

    public void setHP(int num){
        HP = num;
    }

    public void setShield(int shield) {
        this.shield = shield;
    }

    public int getShield() {
        return shield;
    }

    public  int getHP(){return HP;}
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
                case "新生"://回最大血
                    max_recover();
                    break;
                case "丰盈"://回20血，不超过最大生命
                    full();
                    break;
                case "鼓舞"://攻击力加20%
                    upliftment();
                    break;
                default:
                    break;
            }
        }
        buffList.clear();
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
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        // 在等待时间结束后执行逻辑代码
        delay.setOnFinished(e->{
            Obj.FightStage.close();

        });


    }
}
