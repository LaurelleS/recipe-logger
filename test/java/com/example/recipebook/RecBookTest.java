package com.example.recipebook;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Accordion;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;


class RecBookTest extends ApplicationTest {
    RecipeBookController rbc;

    @Start
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("book-view.fxml"));
        RecipeApplication app = new RecipeApplication();
        app.start(stage);
        rbc = app.getController();
    }
    public void exampleRecipe() {
        clickOn("#addRecButton");
        clickOn("#recName").write("Bolognese");
        clickOn("#addRecIng");
        clickOn("#nameField").write("Pasta");
        clickOn("#amountField").write("100");
        clickOn("#addButton");

        clickOn("#addRecIng");
        clickOn("#nameField").write("Tomato");
        clickOn("#amountField").write("100");
        clickOn("#addButton");

        clickOn("#addRecIng");
        clickOn("#nameField").write("Ground beef");
        clickOn("#amountField").write("100");
        clickOn("#addButton");

        clickOn("#recInstr").write("Cook on high for 30 mins.");
        clickOn("#saveRec");
    }
    @Test
    public void addRecIngHandler() {
        exampleRecipe();
        // make sure the accordion now has one element
        Accordion a = rbc.getAccordion();
        assertEquals(1, a.getPanes().size());
    }
    @Test
    public void checkRecipe() {
        exampleRecipe();
        clickOn("#addIngButton");
        clickOn("#nameField").write("Tomato");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        clickOn("#addIngButton");
        clickOn("#nameField").write("Pasta");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        clickOn("#addIngButton");
        clickOn("#nameField").write("Ground Beef");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        Accordion a = rbc.getAccordion();
        TitledPane b = a.getPanes().getFirst();
        a.setExpandedPane(b);
        clickOn("#makeRecButton");
        verifyThat("#recStatus", hasText("Recipe added to cart."));
    }
    @Test
    public void addIngredient() {
        clickOn("#addIngButton");
        clickOn("#nameField").write("Tomato");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        clickOn("#addIngButton");
        clickOn("#nameField").write("Pasta");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        clickOn("#addIngButton");
        clickOn("#nameField").write("Ground Beef");
        clickOn("#amountField").write("200");
        clickOn("#addButton");

        Ingredient a = new Ingredient("Tomato", 200);
        Ingredient b = new Ingredient("Pasta", 200);
        Ingredient c = new Ingredient("Ground Beef", 200);
        String s = a.toString() + b.toString() + c.toString();
        verifyThat("#listIngs", hasText(s));
    }
}