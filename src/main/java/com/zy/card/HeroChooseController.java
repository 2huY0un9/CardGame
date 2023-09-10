package com.zy.card;

import com.zy.card.util.MusicPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.zy.card.Obj.MapStage;



public class HeroChooseController implements Initializable {
    @FXML
    private Label HeroChooseText;
    @FXML
    private Label HeroName;
    @FXML
    private ImageView HeroChooseBackGround;
    @FXML
    private ImageView HeroChooseBG;
    @FXML
    private ImageView IntroductionBG;
    @FXML
    private ImageView ConfirmImage;
    @FXML
    private ImageView ReturnImage;
    @FXML
    private ImageView Frame;
    @FXML
    private ImageView Hero1;
    @FXML
    private ImageView Hero2;
    @FXML
    private ImageView Hero3;
    @FXML
    private ImageView Hero4;
    @FXML
    private ImageView HeroDeplay;
    @FXML
    private Text Life;
    @FXML
    private Text Group;
    @FXML
    private Text SkillIntroduction;
    @FXML
    private Button HeroChoosebtn1;
    @FXML
    private Button HeroChoosebtn2;
    @FXML
    private Button HeroChoosebtn3;
    @FXML
    private Button HeroChoosebtn4;
    @FXML
    private Button HeroChooseReturn;
    @FXML
    private Button HeroChooseConfirm;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image ChooseBackground = new Image(getClass().getResourceAsStream("res/Image/HeroChooseBG.png"),960,540,false,true);
        HeroChooseBackGround.setImage(ChooseBackground);
        Image FramePic = new Image(getClass().getResourceAsStream("res/Image/Frame.png"),225,225,false,true);
        Frame.setImage(FramePic);
        Image Herodeplay = new Image(getClass().getResourceAsStream("res/Image/SaberDeplay.png"),350,300,false,true);
        HeroDeplay.setImage(Herodeplay);

        //设置Button按钮背景图
            //左侧
        Image HeroChooseImage = new Image(getClass().getResourceAsStream("res/Image/HeroChoose.png"),295,390,false,true);
        HeroChooseBG.setImage(HeroChooseImage);
            //右侧
        Image IntroductionImage = new Image(getClass().getResourceAsStream("res/Image/IntroductionBG.png"),330,435,false,true);
        IntroductionBG.setImage(IntroductionImage);
            //返回和选择（继续）两个按钮
        Image returnImage = new Image(getClass().getResourceAsStream("res/Image/ButtonImageReturn.png"),125,43,false,true);
        Image confirmImage = new Image(getClass().getResourceAsStream("res/Image/ButtonImageConfirm.png"),125,43,false,true);

            //四个英雄选项
        Image Hero01 = new Image(getClass().getResourceAsStream("res/Image/Saber.png"),70,70,false,true);
        Image Hero02 = new Image(getClass().getResourceAsStream("res/Image/Unknown01.png"),83,92,false,true);
        Image Hero03 = new Image(getClass().getResourceAsStream("res/Image/Unknown02.png"),85,97,false,true);
        Image Hero04 = new Image(getClass().getResourceAsStream("res/Image/Unknown03.png"),85,100,false,true);
            //四个英雄的图片设置
        Hero1.setImage(Hero01);
        Hero2.setImage(Hero02);
        Hero3.setImage(Hero03);
        Hero4.setImage(Hero04);
        HeroChoosebtn1.setStyle("-fx-background-color: transparent;");
        HeroChoosebtn1.setGraphic(Hero1);
        HeroChoosebtn2.setStyle("-fx-background-color: transparent;");
        HeroChoosebtn2.setGraphic(Hero2);
        HeroChoosebtn3.setStyle("-fx-background-color: transparent;");
        HeroChoosebtn3.setGraphic(Hero3);
        HeroChoosebtn4.setStyle("-fx-background-color: transparent;");
        HeroChoosebtn4.setGraphic(Hero4);

        //返回和选择的图片设置
        ConfirmImage.setImage(confirmImage);
        ReturnImage.setImage(returnImage);
        HeroChooseReturn.setStyle("-fx-background-color: transparent;");
        HeroChooseConfirm.setStyle("-fx-background-color: transparent;");
        HeroChooseReturn.setGraphic(ReturnImage);
        HeroChooseConfirm.setGraphic(ConfirmImage);
        addListeners_HeroChoose();

        //按钮功能
        HeroChooseReturn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GoBack();
                Stage stage1 = (Stage) HeroChooseReturn.getScene().getWindow();
                stage1.close();
            }
        });
        HeroChooseConfirm.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                OpenMap();
                Stage stage1 = (Stage) HeroChooseReturn.getScene().getWindow();
                stage1.close();
            }
        });

    }

    public void addListeners_HeroChoose(){

    //返回按钮（HeroChooseReturn）的相关设置

        //鼠标悬浮样式
        HeroChooseReturn.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image returnImageEnter = new Image(getClass().getResourceAsStream("res/Image/ButtonHoverReturn.png"),140,48,false,true);
                ReturnImage.setImage(returnImageEnter);
                ReturnImage.setFitWidth(140.0);
                ReturnImage.setFitHeight(48.0);
                HeroChooseReturn.setGraphic(ReturnImage);
            }

        });

        //鼠标指针离开后样式
        HeroChooseReturn.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image returnImageExited = new Image(getClass().getResourceAsStream("res/Image/ButtonImageReturn.png"),125,43,false,true);
                ReturnImage.setImage(returnImageExited);
                ReturnImage.setFitWidth(125.0);
                ReturnImage.setFitHeight(43.0);
                HeroChooseReturn.setGraphic(ReturnImage);
            }
        });

        //单击按压（不放开）样式
        HeroChooseReturn.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image returnImagePressed = new Image(getClass().getResourceAsStream("res/Image/ButtonPressedReturn.png"),113,39,false,true);
                ReturnImage.setImage(returnImagePressed);
                ReturnImage.setFitWidth(113.0);
                ReturnImage.setFitHeight(39.0);
                HeroChooseReturn.setGraphic(ReturnImage);
            }
        });

        //单机释放样式
        HeroChooseReturn.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image returnImageReleased = new Image(getClass().getResourceAsStream("res/Image/ButtonImageReturn.png"),125,43,false,true);
                ReturnImage.setImage(returnImageReleased);
                ReturnImage.setFitWidth(125.0);
                ReturnImage.setFitHeight(43.0);
                HeroChooseReturn.setGraphic(ReturnImage);
            }
        });

//        //点击后作出反应
//        HeroChooseReturn.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });


    //选择（选择英雄后进行游戏的按钮）的相关设置：

        //鼠标悬浮样式
        HeroChooseConfirm.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image confirmmageEnter = new Image(getClass().getResourceAsStream("res/Image/ButtonHoverConfirm.png"),140,48,false,true);
                ConfirmImage.setImage(confirmmageEnter);
                ConfirmImage.setFitWidth(140.0);
                ConfirmImage.setFitHeight(48.0);
                HeroChooseConfirm.setGraphic(ConfirmImage);
            }

        });

        //鼠标指针离开后样式
        HeroChooseConfirm.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image confirmImageExited = new Image(getClass().getResourceAsStream("res/Image/ButtonImageConfirm.png"),125,43,false,true);
                ConfirmImage.setImage(confirmImageExited);
                ConfirmImage.setFitWidth(125.0);
                ConfirmImage.setFitHeight(43.0);
                HeroChooseConfirm.setGraphic(ConfirmImage);
            }
        });

        //单击按压（不放开）样式
        HeroChooseConfirm.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image confirmImagePressed = new Image(getClass().getResourceAsStream("res/Image/ButtonPressedConfirm.png"),113,39,false,true);
                ConfirmImage.setImage(confirmImagePressed);
                ConfirmImage.setFitWidth(113.0);
                ConfirmImage.setFitHeight(39.0);
                HeroChooseConfirm.setGraphic(ConfirmImage);
            }
        });

        //单击释放样式
        HeroChooseConfirm.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Image confirmImageReleased = new Image(getClass().getResourceAsStream("res/Image/ButtonImageConfirm.png"),125,43,false,true);
                ConfirmImage.setImage(confirmImageReleased);
                ConfirmImage.setFitWidth(125.0);
                ConfirmImage.setFitHeight(43.0);
                HeroChooseConfirm.setGraphic(ConfirmImage);
            }
        });
        //点击后作出反应
//        HeroChooseConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//
//            }
//        });

    }

    private void GoBack(){
        FXMLLoader fxmlLoader = new FXMLLoader(LoginController.class.getResource("hello-view.fxml"));
        AnchorPane root = null;
        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage LoginStage = new Stage();
            LoginStage.setScene(scene);
            MusicPlayer.stopMusic();
            LoginStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void OpenMap(){

        FXMLLoader mapfxmlLoader = new FXMLLoader(LoginController.class.getResource("map-view.fxml"));
        AnchorPane root = null;
        try {
            root = mapfxmlLoader.load();
            Scene scene = new Scene(root);
            MapStage = new Stage();
            MapStage.setScene(scene);
            MapStage.setResizable(false);


            MapStage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
