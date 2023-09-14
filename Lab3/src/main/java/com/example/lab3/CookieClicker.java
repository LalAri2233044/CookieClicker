package com.example.lab3;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class CookieClicker {
    //Variables
    private int nbrDePoints;
    private int nbrDeClics;
    private int pointsParClic;
    private int coutsAmeliorations;
    private Label nbrDeClicsAffichage;
    private Label nbrDePointsAffichage;
    private Label coutsAmeliorationsAffichage;
    private ImageView cookie;
    private Button amelioration1;
    private Button amelioration2;
    private Button amelioration3;
    private Button amelioration4;
    private Button amelioration5;
    private boolean amelioration3Utilise;
    private boolean amelioration4Utilise;
    private boolean amelioration5Utilise;
    public CookieClicker(){
        nbrDePoints = 0;
        nbrDeClics = 0;
        pointsParClic = 1;
        coutsAmeliorations = 15;
        nbrDeClicsAffichage = new Label( + nbrDeClics + " clics");
        nbrDePointsAffichage = new Label( + nbrDePoints + " points");
        coutsAmeliorationsAffichage= new Label("   Cout d'une \namelioration : \n    " +
                coutsAmeliorations + " points");
        cookie = new ImageView(new Image("https://cdn-icons-png.flaticon.com/128/1047/1047711.png"));
        amelioration1 = new Button("+ Points / Clic");
        amelioration2 = new Button("Clics automatiques");
        amelioration3 = new Button("  Debloquer \nAmelioration");
        amelioration4 = new Button("Nouvel Affichage");
        amelioration5 = new Button("Reduire Cout");
        amelioration3Utilise = false;
        amelioration4Utilise = false;
        amelioration5Utilise = false;



    }
    //Methode pour creer stage
    public Stage createStage(){
        Stage stage = new Stage();
        //Group
        Group root = new Group(cookie,nbrDePointsAffichage,nbrDeClicsAffichage,amelioration1,amelioration2,
                amelioration3,coutsAmeliorationsAffichage);
        //Affichage
        //Cookie
        cookie.setPreserveRatio(true);
        cookie.setScaleX(1.5);
        cookie.setScaleY(1.5);
        cookie.setTranslateX(180);
        cookie.setTranslateY(150);

        //Points
        nbrDePointsAffichage.setScaleX(2);
        nbrDePointsAffichage.setScaleY(2);
        nbrDePointsAffichage.setTranslateX(30);
        nbrDePointsAffichage.setTranslateY(20);
        nbrDePointsAffichage.setTextFill(Color.web("#000000"));
        //Clics
        nbrDeClicsAffichage.setScaleX(2);
        nbrDeClicsAffichage.setScaleY(2);
        nbrDeClicsAffichage.setTranslateX(20);
        nbrDeClicsAffichage.setTranslateY(50);
        nbrDeClicsAffichage.setTextFill(Color.web("#000000"));
        //Affichage amelioration
        coutsAmeliorationsAffichage.setScaleX(1.75);
        coutsAmeliorationsAffichage.setScaleY(1.75);
        coutsAmeliorationsAffichage.setTranslateX(400);
        coutsAmeliorationsAffichage.setTranslateY(20);
        coutsAmeliorationsAffichage.setTextFill(Color.web("#000000"));
        coutsAmeliorationsAffichage.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 10));

        //Amelioration
        amelioration1.setScaleX(1.25);
        amelioration1.setScaleY(1.25);
        amelioration1.setTranslateX(45);
        amelioration1.setTranslateY(350);
        setCouleurGris(amelioration1);

        amelioration2.setScaleX(1.25);
        amelioration2.setScaleY(1.25);
        amelioration2.setTranslateX(190);
        amelioration2.setTranslateY(350);
        setCouleurGris(amelioration2);

        amelioration3.setScaleX(1.25);
        amelioration3.setScaleY(1.25);
        amelioration3.setTranslateX(350);
        amelioration3.setTranslateY(350);
        setCouleurGris(amelioration3);

        //Evenements

        cookie.setOnMouseClicked((e) -> {
            augmenter();
            if (coutsAmeliorations <= nbrDePoints) {
                setCouleurMauve(amelioration1);
                setCouleurMauve(amelioration2);
                setCouleurMauve(amelioration5);
                if(!amelioration4Utilise)
                    setCouleurMauve(amelioration4);
                if(!amelioration5Utilise)
                    setCouleurMauve(amelioration3);
            }
        });


        amelioration1.setOnAction((event) -> {
            if (nbrDePoints >= coutsAmeliorations) {
                pointsParClic *= 2;
                affichage();
            }
        });
        amelioration2.setOnAction((event) -> {
            if (nbrDePoints >= coutsAmeliorations) {
                affichage();
                nbrDePointsAffichage.setText(nbrDePoints + " points");
                //TimeLine
                final Timeline time = new Timeline();
                time.getKeyFrames().add(new KeyFrame(Duration.millis(1000),
                        (e) -> {
                            augmenter();
                            if (coutsAmeliorations <= nbrDePoints){
                                setCouleurMauve(amelioration1);
                                setCouleurMauve(amelioration2);
                                setCouleurMauve(amelioration5);
                                if(!amelioration4Utilise)
                                    setCouleurMauve(amelioration4);
                                if(!amelioration5Utilise)
                                    setCouleurMauve(amelioration3);
                            }
                        }));
                time.setCycleCount(Animation.INDEFINITE);
                time.play();
            }
        });
        amelioration3.setOnAction((event) -> {
            if (nbrDePoints >= coutsAmeliorations) {
                if (!amelioration3Utilise && !amelioration4Utilise){
                    root.getChildren().add(amelioration4);
                amelioration4.setScaleX(1.25);
                amelioration4.setScaleY(1.25);
                amelioration4.setTranslateX(45);
                amelioration4.setTranslateY(400);
                if (coutsAmeliorations <= nbrDePoints) {
                    setCouleurMauve(amelioration4);
                }
                amelioration3Utilise = true;
            }
                else if(!amelioration5Utilise) {
                    root.getChildren().add(amelioration5);
                    amelioration5.setScaleX(1.25);
                    amelioration5.setScaleY(1.25);
                    amelioration5.setTranslateX(200);
                    amelioration5.setTranslateY(400);
                    if (coutsAmeliorations <= nbrDePoints) {
                        setCouleurMauve(amelioration5);
                    }
                    amelioration5Utilise = true;
                    setCouleurGris(amelioration3);
                }
                affichage();
            }
        });
        amelioration4.setOnAction((event) -> {
            if (nbrDePoints >= coutsAmeliorations && !amelioration4Utilise) {
                //Image biscuit
                cookie.setTranslateX(-400);
                stage.getScene().setFill(Color.web("#20B2AA"));
                coutsAmeliorationsAffichage.setTextFill(Color.web("#000080"));
                ImageView biscuit = new ImageView(new Image("https://cdn-icons-png.flaticon.com/128/361/361446.png"));
                biscuit.setScaleX(1.5);
                biscuit.setScaleY(1.5);
                biscuit.setTranslateX(180);
                biscuit.setTranslateY(150);
                biscuit.setOnMouseClicked((e) -> {
                    augmenter();
                    if (coutsAmeliorations <= nbrDePoints) {
                        setCouleurMauve(amelioration1);
                        setCouleurMauve(amelioration2);
                        setCouleurMauve(amelioration5);
                        if(!amelioration5Utilise)
                            setCouleurMauve(amelioration3);
                    }
                });
                amelioration4Utilise = true;
                setCouleurGris(amelioration4);
                affichage();
                root.getChildren().add(biscuit);

            }
        });
        amelioration5.setOnAction((event) -> {
            if (nbrDePoints >= coutsAmeliorations) {
                coutsAmeliorations = (int)(coutsAmeliorations / 4);
                affichage();
            }
        });
        //Stage settings
        stage.setScene(new Scene(root,500,500));
        stage.getScene().setFill(Color.web("#48D1CC"));
        stage.setFullScreen(false);
        stage.setTitle("Cookie Clicker");
        stage.setResizable(false);
        return stage;
    }
    //Methode augmenter
    private void augmenter(){
        nbrDePoints += pointsParClic;
        nbrDeClics += 1;
        nbrDePointsAffichage.setText(nbrDePoints + " points");
        nbrDeClicsAffichage.setText(nbrDeClics + " clics");
    }
    private void setCouleurGris(Button amelioration){
        amelioration.setTextFill(Color.web("F8F8FF"));
        amelioration.setStyle("-fx-background-color: #827e7e; ");
    }
    private void setCouleurMauve(Button amelioration){
        amelioration.setTextFill(Color.web("#8A2BE2"));
        amelioration.setStyle("-fx-background-color: #bf7ad5; ");
    }
    private void affichage(){
        nbrDePoints -= coutsAmeliorations;
        coutsAmeliorations += 15;
        nbrDePointsAffichage.setText(nbrDePoints + " points");
        coutsAmeliorationsAffichage.setText("   Cout d'une \namelioration : \n    " +
                coutsAmeliorations + " points");;
        if(nbrDePoints < coutsAmeliorations){
            setCouleurGris(amelioration1);
            setCouleurGris(amelioration2);
            setCouleurGris(amelioration3);
            setCouleurGris(amelioration4);
            setCouleurGris(amelioration5);
        }
    }
}
