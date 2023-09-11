package com.zy.card;

import com.zy.card.util.AllObjects;
import com.zy.card.util.Hero;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;

public class Obj {
    public static AllObjects allObjects = new AllObjects();

    public static Hero hero = allObjects.getHero();
    public static int floor = 0;

    public static boolean isInBattle = false;

    public static Stage MapStage;

    public static Stage RestStage;

    public static Stage EventStage;
    public static Stage FightStage;

    public static Stage MenuStage;
}
