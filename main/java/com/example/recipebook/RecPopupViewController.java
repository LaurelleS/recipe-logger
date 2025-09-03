package com.example.recipebook;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RecPopupViewController {
    public TextField recName;
    public Label newRecList;
    public Button addRecIng;
    public Button saveRec;
    public Button cancelRec;
    public TextField recInstr;

    private RecipeBookController recBookController;
    private ArrayList<Ingredient> recList = new ArrayList<>();// list of ingredients for new recipe
    ArrayList<Ingredient> tempIng = new ArrayList<>();
    Stage ingStage;
    Recipe newRec;

    public void setRootController(RecipeBookController r) {
        this.recBookController = r;
    }

    public Recipe getNewRec() {
        return this.newRec;
    }

    @FXML
    public void addRecHandler() {
        recList = tempIng;
        newRec = new Recipe(recName.getText(), recList, recInstr.getText());
        tempIng = new ArrayList<>();
        recName.clear();
        newRecList.setText("");
        recInstr.clear();
        Stage stage = (Stage) this.cancelRec.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void cancel() {
        this.newRec = null;
        tempIng = new ArrayList<>();
        recName.clear();
        newRecList.setText("");
        recInstr.clear();
        Stage stage = (Stage) this.cancelRec.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void addRecIngHandler() {
        ingStage = recBookController.getIngStage();
        ingStage.showAndWait();
        IngPopupViewController ingPopupController = recBookController.getPopup();
        Ingredient g = ingPopupController.getNewIng();
        tempIng.add(g);
        newRecList.setText(newRecList.getText() + g.toString());
    }

}
