package com.zy.card.util;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Hero extends ImageView{

    private String name="Saber";
    public ArrayList<String> buffList = new ArrayList<>();
    private int HP=90;

    private String Herourl="/com/zy/card/res/Image/Hero_.png";
    private int MAX_HP=90;

    private int strength=4;//力量，用于攻击附加伤害

    private int max_strength=8;

    private int attack=8;

    private int shield=5;


    public Hero(){

        String imagePath = "/com/zy/card/res/Image/Character/Hero_"+name+".png";
        Image backgroundImage = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(backgroundImage);
        imageView.setFitWidth(150); // 设置宽度
        imageView.setFitHeight(130); // 设置高度
    }

    public void Heroattack(){
        Image Hero = new Image(getClass().getResourceAsStream(Herourl),80,80,false,true);
        ImageView hero = new ImageView();
        hero.setImage(Hero);
        TranslateTransition moveForward = new TranslateTransition(Duration.millis(500), hero);
        moveForward.setByX(200);
        moveForward.play();
        moveForward.setOnFinished(event -> {
            TranslateTransition moveBackward = new TranslateTransition(Duration.millis(500), hero);
            moveBackward.setByX(-200);
            moveBackward.play();
        });

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
}
