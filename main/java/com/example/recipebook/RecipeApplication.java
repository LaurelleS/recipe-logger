package com.example.recipebook;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RecipeApplication extends Application {
    private RecipeBookController controller;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeApplication.class.getResource("book-view.fxml"));
        Parent root = fxmlLoader.load();
        controller = fxmlLoader.getController();
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Recipe Book");
        stage.setScene(scene);
        stage.show();
    }

    public RecipeBookController getController() {
        return this.controller;
    }

    public static void main(String[] args) {
        launch();
    }
}