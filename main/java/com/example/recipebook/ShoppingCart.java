package com.example.recipebook;

import java.lang.reflect.Array;
import java.util.*;

public class ShoppingCart extends Pantry {
    private Pantry curIngs; // keep track of user's pantry
    private ArrayList<Ingredient> combinedRecs; // combining all valid recipes for computation with pantry
    private ArrayList<Recipe> validRecipes; // list with Recipes that can be made

    public ShoppingCart(Pantry p) {
        this.validRecipes = new ArrayList<>();
        this.curIngs = p;
    }
    public void setPantry(Pantry p) {
        this.curIngs = p;
    }
    public ArrayList<Recipe> getValidRecs() {
        return this.validRecipes;
    }
    public Boolean addRecipe(Recipe r) {
        ArrayList<Ingredient> temp = this.combineRecipes(combinedRecs, r.getList());
        Recipe rec = new Recipe("combined", temp, "");
        if (this.curIngs.checkRecipe(rec)) {
            this.validRecipes.add(r);
            this.combinedRecs = temp;
            r.setStatus(true);
            return true;
        }
        return false;
    }

    private ArrayList<Ingredient> combineRecipes(ArrayList<Ingredient> l1, ArrayList<Ingredient> l2) {
        Map<String, Ingredient> ingredientMap = new HashMap<>();
        if (l1 == null) {
            return l2;
        }
        for (Ingredient ing : l1) {
            ingredientMap.put(ing.getName(), new Ingredient(ing.getName(), ing.getAmt()));
        }

        for (Ingredient ing : l2) {
            ingredientMap.merge(
                    ing.getName(),
                    new Ingredient(ing.getName(), ing.getAmt()),
                    (existing, incoming) -> new Ingredient(existing.getName(), existing.getAmt() + incoming.getAmt())
            );
        }

        return new ArrayList<>(ingredientMap.values());
    }
}
