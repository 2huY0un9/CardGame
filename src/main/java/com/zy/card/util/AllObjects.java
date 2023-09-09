package com.zy.card.util;

import javafx.animation.AnimationTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AllObjects {

    private List<HandCards> HandCardsArray = new ArrayList<>();
    private List<Enemy> EnemyArray = new ArrayList<>();
    private List<HandCards> AllHoldingCards = new ArrayList<>();

    private Hero hero;
    private int Energy;
    private int Drawn = 3;
    private Random random = new Random();
    private Thread calculate = new Thread(new Runnable() {
        @Override
        public void run() {
            String type="";
            int value = 0;
            for(HandCards hc:HandCardsArray){
                if(hc.isChosen()) {
                    value = hc.getValue();
                    type = hc.getType();
                    break;
                }
            }
            for (Enemy Em:EnemyArray){
                if (Em.isChosen()){
                    switch (type){
                        case "Attack":{
                            if(Em.getShield()-value<0)
                            {
                                Em.setHP(Em.getHP()+Em.getShield()-value);
                                if(Em.getHP()<0)
                                {
                                    Em.dead();
                                    EnemyArray.remove(Em);
                                    Em = null;
                                }
                            }else{
                                Em.setShield(Em.getShield()-value);
                            }
                            break;
                        }
                    }
                }
            }

        }
    });

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public AllObjects() {
        //创建新线程
        calculate.run();
        //添加初始牌组
        for (int i = 0; i < 5; i++) {
            AllHoldingCards.add(new HandCards("会心一击",1,10));
        }
        for (int i = 0; i < 5; i++) {
            AllHoldingCards.add(new HandCards("防御",1,5));
        }
    }

    public List<HandCards> getHandCardsArray() {
        return HandCardsArray;
    }

    public List<Enemy> getEnemyArray() {
        return EnemyArray;
    }

    public List<HandCards> getAllHoldingCards() {
        return AllHoldingCards;
    }

    public void DrawCards(){

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(AllHoldingCards.size());
            HandCardsArray.add(AllHoldingCards.get(index));
        }
    }


    private void RoundEnd(){
        HandCardsArray.clear();
        Energy = 3;
    }

}
