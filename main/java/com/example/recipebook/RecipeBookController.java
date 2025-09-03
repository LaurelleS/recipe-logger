package com.example.recipebook;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RecipeBookController implements Initializable {
    @FXML public Button addIngButton;
    @FXML public Label listIngs;
    @FXML public Accordion accordion;
    @FXML public Button addRecButton;
    @FXML public Button makeRecButton;
    @FXML public Label recStatus = new Label();
    @FXML public Button seeCart;

    private Pantry pantry = new Pantry();
    private ShoppingCart cart = new ShoppingCart(pantry);
    Stage ingPopupStage;
    IngPopupViewController ingPopupController;
    Stage recPopupStage;
    RecPopupViewController recController;

    public void initialize(URL location, ResourceBundle resources) {
        FXMLLoader fxmlLoader = new FXMLLoader(RecipeBookController.class.getResource("ing-popup-view.fxml"));
        ingPopupStage = new Stage();
        FXMLLoader loader = new FXMLLoader(RecipeBookController.class.getResource("rec-popup-view.fxml"));
        recPopupStage = new Stage();
        try {
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);

            ingPopupController = fxmlLoader.getController();
            ingPopupController.setRootController(this);

            ingPopupStage.setTitle("Add New Ingredient");
            ingPopupStage.setScene(scene);
            ingPopupStage.initModality(Modality.APPLICATION_MODAL); // blocks input to other windows

            Scene scene1 = new Scene(loader.load());
            recController = loader.getController();
            recController.setRootController(this);
            recPopupStage.setTitle("Add new Recipe");
            recPopupStage.setScene(scene1);
            recPopupStage.initModality(Modality.APPLICATION_MODAL);

        } catch (IOException e) {
            System.out.println("IOException caught.");
        }
    }

    @FXML
    public void addIngredient() {
        ingPopupStage.showAndWait();
        Ingredient g =ingPopupController.getNewIng();
        if (g != null) {
            this.pantry.buyIng(g);
            listIngs.setText(pantry.toString());
        }
    }

    public Stage getIngStage() {
        return this.ingPopupStage;
    }
    public IngPopupViewController getPopup() {
        return this.ingPopupController;
    }
    public Accordion getAccordion() {
        return accordion;
    }

    @FXML
    public void addRecipe() {
        recPopupStage.showAndWait();
        Recipe r = recController.getNewRec();
        if (r != null) {
            makeRecButton.setDisable(false);
            Label recDetails = new Label();
            recDetails.setText(r.toString());
            TitledPane pane = new TitledPane(r.getName(), recDetails);
            accordion.getPanes().add(pane);
        }
    }

    @FXML
    public void makeRecipe() {
        this.cart.setPantry(pantry);
        TitledPane tp = this.accordion.getExpandedPane();
        Label l = (Label) tp.getContent();
        ArrayList<Ingredient> ings = new ArrayList<>();
        // separate text from label into line and then get Ingredients from lines
        String s = l.getText();
        String[] lines = s.split("\n");
        String ins = "";
        for (String line : lines) {
            if (line.startsWith("Instructions")) {
                ins = line;
                continue;
            }
            String[] parts = line.split("-");
            String name = parts[0].trim();
            String quantity = parts[1].trim().split(" ")[0];
            ings.add(new Ingredient(name, Integer.parseInt(quantity)));
        }
        Recipe selRec = new Recipe(tp.getText(), ings, ins);
       if (!this.cart.addRecipe(selRec)) {
           this.recStatus.setText("Whoops! Not enough ingredients to make this!");
       }
       else {
           this.recStatus.setText("Recipe added to cart.");
       }
    }
    @FXML
    public void showCart() {
        VBox vBox;
        StringBuilder str =  new StringBuilder();
        if (cart.getValidRecs() != null) {
            for (Recipe r : cart.getValidRecs()) {
                str.append(r.getName() + "\n");
                str.append(r.toString() + "\n\n");
            }
        }
        if (str != null) {
            vBox = new VBox(new Label(str.toString()));
        }
        else {
            vBox = new VBox(new Label("No recipes in cart."));
        }
        Scene scene = new Scene(vBox, 300.0, 100.0);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

}