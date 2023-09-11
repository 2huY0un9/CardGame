package com.zy.card.util;

import com.zy.card.LoserController;
import com.zy.card.Obj;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import static com.zy.card.Obj.*;
import static com.zy.card.Obj.RestStage;

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

        ImageView hit = new ImageView(new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/EffectImg/attack_01.png"),200,200,false,true));
        this.getChildren().add(hit);
        hit.setLayoutX(10);
        hit.setLayoutY(-20);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), hit);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        fadeOut.setOnFinished(ex->{
            if(this.getChildren().contains(hit))
                this.getChildren().remove(hit);
        });
        // 启动动画
        fadeOut.play();
    }

    public void Heroattack2(){

        TranslateTransition moveForward = new TranslateTransition(Duration.millis(200), this);
        moveForward.setByX(200);
        moveForward.play();
        moveForward.setOnFinished(event -> {
            TranslateTransition moveBackward = new TranslateTransition(Duration.millis(500), this);
            moveBackward.setByX(-200);
            moveBackward.play();
        });

        ImageView hit2 = new ImageView(new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/EffectImg/attack_02.png"),200,200,false,true));
        this.getChildren().add(hit2);
        hit2.setLayoutX(10);
        hit2.setLayoutY(-20);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), hit2);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        fadeOut.setOnFinished(ex->{
            if(this.getChildren().contains(hit2))
                this.getChildren().remove(hit2);
        });
        // 启动动画
        fadeOut.play();
    }

    public void HeroDenfend(){
        //这里是防御的动画
        ImageView Def = new ImageView(new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/EffectImg/circle_02.png"),200,200,false,true));
        this.getChildren().add(Def);
        Def.setLayoutX(-60);
        Def.setLayoutY(-50);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), Def);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        fadeOut.setOnFinished(ex->{
            if(this.getChildren().contains(Def))
                this.getChildren().remove(Def);
        });
        // 启动动画
        fadeOut.play();
    }

    public void HeroFit(){
        //这里是治疗的动画
        ImageView fit = new ImageView(new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/EffectImg/fit.png"),200,200,false,true));
        this.getChildren().add(fit);
        fit.setLayoutX(-60);
        fit.setLayoutY(-40);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(2), fit);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        fadeOut.setOnFinished(ex->{
            if(this.getChildren().contains(fit))
                this.getChildren().remove(fit);
        });
        // 启动动画
        fadeOut.play();

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

    public int getMAX_HP() {
        return MAX_HP;
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
        //受到攻击的特效
        ImageView gothit = new ImageView(new Image(getClass().getResourceAsStream("/com/zy/card/res/Image/EffectImg/scratch_01.png"),200,200,false,true));
        this.getChildren().add(gothit);
        gothit.setLayoutX(-60);
        gothit.setLayoutY(-40);
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.5), gothit);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        fadeOut.setOnFinished(e->{
            if(this.getChildren().contains(gothit))
                this.getChildren().remove(gothit);
        });
        // 启动动画
        fadeOut.play();



        if(shield-value > 0)
        {
            setShield(shield-value);
        }else{
            setHP(HP+(shield-value));
        }
    }

    public void dead(){
        System.out.println("hero dead");
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        // 启动动画
        fadeOut.play();
        Obj.isInBattle = false;
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        // 在等待时间结束后执行逻辑代码
        delay.setOnFinished(e->{
            FightStage.close();
            System.out.println("fightstage close");
            MapStage.close();
            //打开战败页面
            FXMLLoader fxmlLoader = new FXMLLoader(LoserController.class.getResource("Lose-view.fxml"));
            AnchorPane root = null;
            try {
                root = fxmlLoader.load();
                Scene scene = new Scene(root);
                Stage LoseStage= new Stage();
                LoseStage.setResizable(false);
                LoseStage.setScene(scene);
                LoseStage.show();

            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        delay.play();

    }
}
