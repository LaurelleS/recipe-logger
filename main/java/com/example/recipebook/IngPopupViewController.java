package com.example.recipebook;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IngPopupViewController {

    public TextField nameField;
    public TextField amountField;
    public Button cancelButton;
    public Button addButton;
    private RecipeBookController recBookController;
    private Ingredient newlyAdded;

    @FXML
    private void cancel() {
        newlyAdded = null;
        nameField.clear();
        amountField.clear();
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void addIngHandler() {
        String iname = nameField.getText().trim();
        int iamt = Integer.parseInt(amountField.getText());
        Ingredient ing = new Ingredient(iname, iamt);
        this.cancel();
        this.newlyAdded = ing;
    }
    public void setRootController(RecipeBookController r) {
        this.recBookController = r;
    }
    public Ingredient getNewIng() {
        return this.newlyAdded;
    }

}
