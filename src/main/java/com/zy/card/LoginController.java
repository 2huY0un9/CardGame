package com.zy.card;

import com.zy.card.util.MusicPlayer;
import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private List<Stage> openStages = new ArrayList<>();
    private Image BossIconImage = new Image(getClass().getResourceAsStream("res/Image/Ttile_1.png"));
    private Image MainBackImage = new Image(getClass().getResourceAsStream("res/Image/end.jpg"),960,540,false,true);
    // 在你的类中添加一个成员变量来追踪当前的 Scene
    private Scene currentScene = null;
    private Stage codexStage = null; // 用于跟踪"Codex"窗口的引用
    private Stage settingStage = null; // 用于跟踪"Codex"窗口的引用
    boolean settingOpen = false;

    @FXML
    private ImageView title;
    @FXML
    private AnchorPane anchorPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //加载背景
        BackgroundImage MainBack = new BackgroundImage(MainBackImage, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
        anchorPane.setBackground(new Background(MainBack));

        // 加载字体文件
        String fontPath = "/com/zy/card/res/Font/spider.ttf"; // 注意路径格式
        title.setImage(BossIconImage);


        MusicPlayer.playMusic(MusicPlayer.BGM);

        CardGameButton codex = new CardGameButton("Codex");
        // 添加cardGameButton到AnchorPane
        anchorPane.getChildren().add(codex);
        AnchorPane.setRightAnchor(codex, 10.0);
        AnchorPane.setTopAnchor(codex, 10.0);

        CardGameButton setting = new CardGameButton("Setting");
        anchorPane.getChildren().add(setting);
        AnchorPane.setRightAnchor(setting, 10.0);
        AnchorPane.setTopAnchor(setting, 80.0);

        CardGameButton enter = new CardGameButton("ENTER");
        anchorPane.getChildren().add(enter);
        AnchorPane.setRightAnchor(enter, 410.0);
        AnchorPane.setTopAnchor(enter, 300.0);


        CardGameButton exit = new CardGameButton("EXIT");
        anchorPane.getChildren().add(exit);
        AnchorPane.setRightAnchor(exit, 410.0);
        AnchorPane.setTopAnchor(exit, 380.0);

        codex.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (codexStage != null && codexStage.isShowing()) {
                    // 如果 codex 窗口存在，关闭它
                    codexStage.close();
                    codexStage = null; // 将 codexStage 设置为 null，表示不存在
                } else {
                    // 如果 codex 窗口不存在，创建一个新的 codex 窗口
                    openCodex();
                }
            }
        });
        setting.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (currentScene != null) {
                    // 如果当前存在 Scene，关闭它并设置为 null
                    closeScene(currentScene);
                    currentScene = null;
                } else {
                    // 如果当前没有 Scene，创建一个新的 Scene 并打开它
                    openScene();
                }
            }
        });

        exit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // 获取按钮所在的 Stage
                Stage stage1 = (Stage) exit.getScene().getWindow();
                openStages.add(stage1);
                // 关闭窗口
                for (Stage stage : openStages) {
                    stage.close();
                }
                // 清空集合
                openStages.clear();

            }
        });

        enter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OpenHeroChoose();
                Stage stage1 = (Stage) exit.getScene().getWindow();
                openStages.add(stage1);
                // 关闭窗口
                for (Stage stage : openStages) {
                    stage.close();
                }
                // 清空集合
                openStages.clear();
            }
        });


    }

    private void openScene() {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("setting-view.fxml"));
        AnchorPane settingPane = null;
        try {
            settingPane = fxmlLoader.load();
            settingPane.setScaleX(0);
            settingPane.setScaleY(0);

            ScaleTransition scaleIn = new ScaleTransition(Duration.seconds(0.5), settingPane);
            scaleIn.setToX(1);
            scaleIn.setToY(1);
            scaleIn.play();

            // 创建一个透明的场景，包含缩放后的内容
            Scene scene = new Scene(settingPane, Color.TRANSPARENT);

            // 创建一个透明的窗口
            Stage dialogStage = new Stage(StageStyle.TRANSPARENT);
            dialogStage.setScene(scene);
            dialogStage.setTitle("Setting");

            // 设置关闭按钮的处理程序，关闭 Scene 并将 currentScene 设置为 null
            dialogStage.setOnCloseRequest(event -> {
                closeScene(scene);
                currentScene = null;
            });

            dialogStage.show();
            currentScene = scene;
            openStages.add(dialogStage);
            dialogStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void closeScene(Scene scene) {
        if (scene != null) {
            Stage stage = (Stage) scene.getWindow();
            stage.close();
        }
    }

    private void openCodex() {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("codex-view.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            AnchorPane anchorPane = (AnchorPane) scene.getRoot();
            codexStage = new Stage();
            codexStage.setScene(scene);
            codexStage.setTitle("Codex");
            codexStage.initStyle(StageStyle.UNDECORATED);

            // 设置关闭按钮的处理程序，关闭 codex 窗口并将 codexStage 设置为 null
            codexStage.setOnCloseRequest(event -> {
                codexStage = null;
            });

            codexStage.show();
            settingOpen = true;
            openStages.add(codexStage);
            codexStage.setResizable(false);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void OpenHeroChoose(){
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("herochoose-view.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage HeroChooseStage = new Stage();
            HeroChooseStage.setScene(scene);
            HeroChooseStage.setTitle("HeroChoose");
            HeroChooseStage.setResizable(false);

            HeroChooseStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}

