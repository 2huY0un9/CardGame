package com.zy.card;

import com.zy.card.util.AllObjects;
import com.zy.card.util.Hero;
import com.zy.card.util.Map;
import com.zy.card.util.MyImageView;
import javafx.animation.AnimationTimer;
import javafx.animation.ScaleTransition;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static com.zy.card.Obj.*;

public class MapController implements Initializable {

    private List<List<Integer>> graph;
    private Map map = new Map();
    private List<MyImageView> IconArray = new ArrayList<>();
    private boolean isFirstTime = true;
    private Button BackToBattle;

    private AnimationTimer animationTimer;
    private Image RestPointImage = new Image(getClass().getResourceAsStream("res/Image/RestPointIcon.png"));
    private Image MonsterImage = new Image(getClass().getResourceAsStream("res/Image/Monster.png"));
    private Image QuestionImage = new Image(getClass().getResourceAsStream("res/Image/Question.png"));
    private Image BossIconImage = new Image(getClass().getResourceAsStream("res/Image/Boss.png"));
    private Image MapBackground = new Image(getClass().getResourceAsStream("res/Image/MapBackground.jpg"),680,1200,false,true);
    private Image InfoBarImage = new Image(getClass().getResourceAsStream("res/Image/InfoBar.png"),960,40,false,true);
    private Image SettingImage = new Image(getClass().getResourceAsStream("res/Image/SettingInMap.png"),30,30,false,true);
    private Image IdentiCardImage = new Image(getClass().getResourceAsStream("res/Image/IdentiCard.png"));
    private Image CoinFrameImage = new Image(getClass().getResourceAsStream("res/Image/CoinFrame.png"),80,30,false,true);
    private Image CoinImage = new Image(getClass().getResourceAsStream("res/Image/Coin.png"),30,30,false,true);
    private Image BackToBtImg = new Image(getClass().getResourceAsStream("res/Image/BackToBattleButton.png"),120,40,false,true);
    private EventHandler<MouseEvent> ClickMonsterEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("a monster has been clicked");
            MyImageView BeClicked = (MyImageView) event.getSource();
            List<Integer> to = graph.get(BeClicked.getMark());
            for (MyImageView i : IconArray) {
                if(to.contains(i.getMark()))
                {
                    i.setMouseTransparent(false);
                    i.setOpacity(1);
                }
                if(BeClicked.getFloor()==i.getFloor())
                {
                    i.setMouseTransparent(true);
                    if(i!=BeClicked)
                        i.setOpacity(0.5);
                }
            }
            floor = BeClicked.getFloor();
            if(!isInBattle){
                isInBattle = true;
                FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("fight-view.fxml"));

                AnchorPane root = null;
                try {
                    root = fxmlLoader.load();
                    Scene scene = new Scene(root);
                    FightStage = new Stage();
                    FightStage.setScene(scene);
                    FightStage.setResizable(false);
                    FightStage.show();

                    Stage originalStage = (Stage) MainBack.getScene().getWindow();
                    originalStage.hide();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                MainBack.getChildren().add(BackToBattle);
            }
            System.out.println(isInBattle);

        }
    };
    private EventHandler<MouseEvent> ClickRestPointEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("a restpoint has been clicked");
            MyImageView BeClicked = (MyImageView) event.getSource();
            List<Integer> to = graph.get(BeClicked.getMark());
            for (MyImageView i : IconArray) {
                if(to.contains(i.getMark()))
                {
                    i.setMouseTransparent(false);
                    i.setOpacity(1);
                }
                if(BeClicked.getFloor()==i.getFloor())
                {
                    i.setMouseTransparent(true);
                    if(i!=BeClicked)
                        i.setOpacity(0.5);
                }
            }

            FXMLLoader restfxmlLoader = new FXMLLoader(LoginController.class.getResource("inhabit-view.fxml"));

            AnchorPane root = null;
            try {
                root = restfxmlLoader.load();
                Scene scene = new Scene(root);
                RestStage = new Stage();
                RestStage.setResizable(false);
                RestStage.setScene(scene);
                RestStage.show();

                Stage originalStage = (Stage) MainBack.getScene().getWindow();
                originalStage.hide();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };
    private EventHandler<MouseEvent> ClickQuestionEvent = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            System.out.println("a question has been clicked");
            MyImageView BeClicked = (MyImageView) event.getSource();
            List<Integer> to = graph.get(BeClicked.getMark());
            for (MyImageView i : IconArray) {
                if(to.contains(i.getMark()))
                {
                    i.setMouseTransparent(false);
                    i.setOpacity(1);
                }
                if(BeClicked.getFloor()==i.getFloor())
                {
                    i.setMouseTransparent(true);
                    if(i!=BeClicked)
                        i.setOpacity(0.5);
                }
            }

            FXMLLoader eventfxmlLoader = new FXMLLoader(LoginController.class.getResource("events-view.fxml"));

            AnchorPane root = null;
            try {
                root = eventfxmlLoader.load();
                Scene scene = new Scene(root);
                EventStage = new Stage();
                EventStage.setResizable(false);
                EventStage.setScene(scene);
                EventStage.show();

                Stage originalStage = (Stage) MainBack.getScene().getWindow();
                originalStage.hide();

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    };

    @FXML
    private ScrollPane ScrollMap;
    @FXML
    private AnchorPane InfoBar;
    @FXML
    private AnchorPane MapContainer;
    @FXML
    private AnchorPane MainBack;
    @FXML
    private ImageView ButtonSetting;
    @FXML
    private ImageView IdentiCard;
    @FXML
    private AnchorPane CoinFrame;
    @FXML
    private ImageView Coin;
    @FXML
    private Label CoinNumber;

//    private

    private MyImageView BossIcon = new MyImageView();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


            BackgroundImage MapBack = new BackgroundImage(MapBackground, BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
            Background mapback = new Background(MapBack);
            MapContainer.setBackground(mapback);

            BackgroundImage InfoBarBackImage = new BackgroundImage(InfoBarImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
            Background InfoBarBack = new Background(InfoBarBackImage);
            InfoBar.setBackground(InfoBarBack);
            InfoBar.setEffect(new DropShadow());

            BackgroundImage CoinFrameBackImage = new BackgroundImage(CoinFrameImage,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
            CoinFrame.setBackground(new Background(CoinFrameBackImage));


            CoinNumber.setTextFill(Color.WHITE);
            CoinNumber.setAlignment(Pos.CENTER_RIGHT);

            Coin.setImage(CoinImage);

            ButtonSetting.setImage(SettingImage);
            IdentiCard.setImage(IdentiCardImage);

            BossIcon.setImage(BossIconImage);
            BossIcon.setMark(15);
            BossIcon.setLayoutX(210);
            BossIcon.setFitHeight(200);
            BossIcon.setFitWidth(240);
            BossIcon.setOnMouseClicked(ClickMonsterEvent);

            ButtonSetting.setOnMouseEntered(mouseEvent -> {
                ButtonSetting.setEffect(new DropShadow());
            });
            ButtonSetting.setOnMouseExited(mouseEvent -> {
                ButtonSetting.setEffect(null);
            });

            MapContainer.getChildren().add(BossIcon);

            MapInit();
            IconArray.add(BossIcon);

            MainBack.setStyle("-fx-background-color: #000000;");


            ScrollMap.setStyle("-fx-background-color: transparent;");
            ScrollMap.setVvalue(1.0);
            ScrollMap.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
            ScrollMap.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

            BackToBattle = new Button("返回战斗");
            BackgroundImage BackToB = new BackgroundImage(BackToBtImg,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,null);
            BackToBattle.setBackground(new Background(BackToB));
            BackToBattle.setStyle("-fx-text-fill: white;");
            BackToBattle.setLayoutX(30);
            BackToBattle.setLayoutY(470);
            BackToBattle.setPrefSize(120,40);
            BackToBattle.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),BackToBattle);
                    scaleTransition.setToX(0.8);
                    scaleTransition.setToY(0.8);
                    scaleTransition.play();
                }
            });
            BackToBattle.setOnMouseReleased(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),BackToBattle);
                    scaleTransition.setToX(1.0);
                    scaleTransition.setToY(1.0);
                    scaleTransition.play();
                }
            });
            BackToBattle.setOnAction(actionEvent -> {
                MapStage.hide();
                FightStage.show();
            });

            animationTimer = new AnimationTimer() {
                @Override
                public void handle(long l) {
                    if(isInBattle)
                    {
                        for (MyImageView i:IconArray)
                        {
                            i.Disable();
                        }
                    }
                }
            };

            animationTimer.start();

    }

    private void MapInit(){

        map.InitMapGraph();
        graph = map.getMapGraph();
        List<Integer> mp = map.getUsed();

        int[][] num = new int[3][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                num[j][i] = mp.get(j+i*3);
            }
        }
        for (int i = 0; i < 15; i++) {
            IconArray.add(new MyImageView());
            MapContainer.getChildren().add(IconArray.get(i));
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 3; j++) {
                int index = j+i*3;
                MyImageView icon = (MyImageView) IconArray.get(index);
                icon.setFloor(i);
                if (num[j][i]>=9&&num[j][i]<=14)
                {
                    icon.setImage(RestPointImage);
                    icon.setOnMouseClicked(ClickRestPointEvent);
                }
                else if (num[j][i]>=6&&num[j][i]<=8) {
                    icon.setImage(QuestionImage);
                    icon.setOnMouseClicked(ClickQuestionEvent);
                }
                else if (num[j][i]>=0&&num[j][i]<=5){
                    icon.setImage(MonsterImage);
                    icon.setOnMouseClicked(ClickMonsterEvent);
                }
                if (i==4) {
                    icon.setOpacity(1);
                    icon.setMouseTransparent(false);
                }
                icon.setMark(num[j][i]);
                icon.setFitHeight(40);
                icon.setFitWidth(40);
                icon.setLayoutX(160+j*150);
                icon.setLayoutY(250+200*i);

            }
        }

        for (MyImageView i:IconArray){
            int from = i.getMark();
            int n = graph.get(from).size();
            for (int j=0;j<n;j++)
            {
                for (MyImageView k:IconArray)
                {
                    if(graph.get(from).contains(k.getMark())){

                        double START_X = i.getLayoutX()+20;
                        double START_Y = i.getLayoutY()+20;
                        double END_X = k.getLayoutX()+20;
                        double END_Y = k.getLayoutY()+20;
                        double currentLength = Math.sqrt(Math.pow(END_X - START_X, 2) + Math.pow(END_Y - START_Y, 2));
                        double lengthDelta =-30.0;
                        // 计算长度增量在 x 轴和 y 轴上的分量
                        double deltaX = (lengthDelta * (END_X - START_X)) / currentLength;
                        double deltaY = (lengthDelta * (END_Y - START_Y)) / currentLength;

                        double newEndX = END_X + deltaX;
                        double newEndY = END_Y + deltaY;

                        double newStartX = START_X - deltaX;
                        double newStartY = START_Y - deltaY;

                        Line road = new Line();
                        road.setOpacity(0.5);
                        road.getStrokeDashArray().addAll(5.0, 5.0); // 设置虚线的线段长度
                        road.setStrokeType(StrokeType.CENTERED); // 线段居中绘制
                        road.setStrokeWidth(2); // 虚线的宽度
                        road.setStroke(Color.web("#000000")); // 虚线的颜色
                        road.setStartX(newStartX);
                        road.setStartY(newStartY);
                        road.setEndX(newEndX);
                        road.setEndY(newEndY);

                        MapContainer.getChildren().add(road);
                    }
                }
            }
        }

        ButtonSetting.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonSetting.setEffect(new DropShadow());
            }
        });
        ButtonSetting.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ButtonSetting.setEffect(null);
            }
        });
        ButtonSetting.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonSetting);
                scaleTransition.setToX(0.8);
                scaleTransition.setToY(0.8);
                scaleTransition.play();
            }
        });
        ButtonSetting.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                ScaleTransition scaleTransition = new ScaleTransition(Duration.millis(100),ButtonSetting);
                scaleTransition.setToX(1.0);
                scaleTransition.setToY(1.0);
                scaleTransition.play();
            }
        });


    }
}
