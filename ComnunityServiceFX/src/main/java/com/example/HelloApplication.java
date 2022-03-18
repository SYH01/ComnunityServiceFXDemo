package com.example;


import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private StageController stageController;

    @Override
    public void start(Stage stage) throws IOException {
        stageController=new StageController();
        stageController.loadStage("登录","Login-view.fxml");
        stageController.loadStage("注册","Register-view.fxml");
        stageController.loadStage("首页","Main-view.fxml");
        stageController.loadStage("管理","Admin-view.fxml");
        stageController.loadStage("聊天","Chat-view.fxml");
        stageController.setStage("登录");
    }

    public static void main(String[] args) {
        launch();
    }
}