package com.zy.card.util;

import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

import java.util.ArrayList;

public class Enemy extends ImageView {


    public ArrayList<String> buffList = new ArrayList<>();
    private int HP;
    private boolean chosen = false;
    private boolean dead = false;
    private String Enemy="/com/zy/card/res/Image/Enemy_.png";

    private int MAX_HP;

    private int strength;//力量，用于攻击附加伤害

    private int max_strength;

    private int attack;

    private int shield;
    public Enemy(String name,int width,int hength){
        String imagePath = "/com/zy/card/res/Image/Enemy_"+name+".png";
        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(width); // 设置宽度
        imageView.setFitHeight(hength); // 设置高度

    }

    public Enemy(String name){
        String imagePath = "/com/zy/card/res/Image/Enemy_"+name+".png";
        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(100); // 设置宽度
        imageView.setFitHeight(120); // 设置高度

        setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                chosen = !chosen;
            }
        });
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

    public void dead(){
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        // 启动动画
        fadeOut.play();
    }
}
