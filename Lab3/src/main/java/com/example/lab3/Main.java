package com.example.lab3;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        CookieClicker cookieClicker = new CookieClicker();
        cookieClicker.createStage().show();

    }

    public static void main(String[] args) {
        launch();
    }
}