package com.zy.card.util;

import com.zy.card.LoserController;
import com.zy.card.WinController;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;

import static com.zy.card.Obj.allObjects;
import static com.zy.card.Obj.floor;

public class AllObjects {

    private List<HandCards> HandCardsArray = new ArrayList<>();
    private List<Enemy>     EnemyArray = new ArrayList<>();
    private List<HandCards> AllHoldingCards = new ArrayList<>();

    private Enemy enemy;
    private Hero hero;
    private int Energy;
    private int Drawn = 3;//每回合抽牌数

    private int ActionPoint = 3;

    private  int Max_ActionPoint = 3;

    private boolean isOneCardBeChosen;
    private Random random = new Random();


    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public AllObjects() {
        InitHandCards();
        InitEnemy();
        InitHero();
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

    public Hero getHero() { return hero; }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setMax_ActionPoint(int max_ActionPoint) {
        Max_ActionPoint = max_ActionPoint;
    }

    public void DrawCards(){

        HashSet<Integer> randnum = new HashSet<>();
        while (randnum.size() < 5)
        {
            randnum.add(random.nextInt(AllHoldingCards.size()));
        }

        for (int i :randnum) {
            HandCards c =AllHoldingCards.get(i);
            HandCardsArray.add(new HandCards(c.getName(),c.getCost(),c.getValue()));
        }
    }

    public void InitObj(){
        InitHandCards();
        InitEnemy();
        InitHero();
    }

    public void InitEnemy(){
        EnemyArray.add(new Enemy("1",80));
        EnemyArray.add(new Enemy("2",60));
        EnemyArray.add(new Enemy("3",70));
        EnemyArray.add(new Enemy("4",90));
    }

    public void DrawBoss(){
        enemy = new Enemy("110",100);
        enemy.setPrefWidth(400);
        enemy.setPrefHeight(580);
    }
    public void DrawEnemy(){
        int index = random.nextInt(4);
        enemy = new Enemy(String.valueOf(index+1),24);
    }
    public void InitHandCards(){
        //添加初始牌组
        for (int i = 0; i < 5; i++) {
            AllHoldingCards.add(new HandCards("会心一击",1,10));
        }
        for (int i = 0; i < 5; i++) {
            AllHoldingCards.add(new HandCards("防御",1,5));
        }
        AllHoldingCards.add(new HandCards("巫妖之祸",2,40));
        AllHoldingCards.add(new HandCards("点燃",2,5));
        AllHoldingCards.add(new HandCards("虚弱",1,2));
        AllHoldingCards.add(new HandCards("饮血剑",2,15));
    }

    public void InitHero(){
        hero = new Hero();
    }


    public void CanCardBeClick(){
        for(HandCards hc:HandCardsArray){
            if(hc.isChosen()) {
                isOneCardBeChosen = true;
                break;
            }
            isOneCardBeChosen = false;
        }
        if(isOneCardBeChosen)
        {
            for(HandCards i:HandCardsArray)
            {
                if(!i.isChosen())
                {
                    i.setMouseTransparent(true); //当一张手牌被选择时其他手牌被禁止点击
                }
            }
        }else {
            for(HandCards i:HandCardsArray)
            {
                if(!i.isChosen())
                {
                    i.setMouseTransparent(false);
                }
            }
        }
    }

    public int getActionPoint() {
        return ActionPoint;
    }

    public int getMax_ActionPoint() {
        return Max_ActionPoint;
    }

    public void setActionPoint(int actionPoint) {
        ActionPoint = actionPoint;
    }

    public void RoundEnd(){
        ActionPoint = Max_ActionPoint;
        PauseTransition delay = new PauseTransition(Duration.seconds(0.5));
        delay.setOnFinished(e-> {
            System.out.println("enemy action");
            enemy.Action();
        });
        delay.play();
        HandCardsArray.clear();
        DrawCards();
        Energy = 3;
    }

    public void GameWin(){
        FXMLLoader fxmlLoader = new FXMLLoader(WinController.class.getResource("win-view.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage WinStage= new Stage();
            WinStage.setResizable(false);
            WinStage.setScene(scene);
            WinStage.show();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}
