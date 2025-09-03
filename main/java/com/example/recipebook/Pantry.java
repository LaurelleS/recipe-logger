package com.example.recipebook;

import java.util.ArrayList;
import java.util.Objects;

public class Pantry {
    private ArrayList<Ingredient> pantry = new ArrayList<>();

    public ArrayList<Ingredient> getPantry() {
        return this.pantry;
    }
    public Boolean checkRecipe(Recipe r) {
        for (Ingredient i : r.getList()) {
            if (!pantry.contains(i)) {
                r.setStatus(false);
                return false;
            }
            for (Ingredient g : pantry) {
                if (g.getName().equalsIgnoreCase(i.getName()) && i.getAmt() > g.getAmt()) {
                    r.setStatus(false);
                    return false;
                }
            }
        }
        r.setStatus(true);
        return true;
    }
    public void buyIng(Ingredient i) {
        this.pantry.add(i);
        i.setStatus(true);
    }
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Ingredient i : this.pantry) {
            str.append(i.toString());
        }
        return str.toString();
    }

}
