package com.zy.card.util;

import com.zy.card.LoserController;
import com.zy.card.Obj;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

import static com.zy.card.Obj.*;

public class Boss extends Enemy {

    Boss(String name,int maxhp)
    {
        super(name,maxhp);
    }
    @Override
    public void Action(){

    }

    public void dead(){
        System.out.println("Boss dead");
        FadeTransition fadeOut = new FadeTransition(Duration.seconds(1), this);
        fadeOut.setFromValue(1.0);  // 完全可见
        fadeOut.setToValue(0.0);    // 完全透明
        fadeOut.setCycleCount(1);   // 只播放一次
        // 启动动画
        fadeOut.play();

        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        // 在等待时间结束后执行逻辑代码
        delay.setOnFinished(e->{
            System.out.println("fightstage close");
            FightStage.close();
            FightStage = null;
            isInBattle = false;

            FXMLLoader fxmlLoader = new FXMLLoader(LoserController.class.getResource("win-view.fxml"));
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
        });
        delay.play();
    }
}
