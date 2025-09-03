package com.example.recipebook;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;
    private String instructions;
    private Boolean canMake;

    public Recipe(String name, ArrayList<Ingredient> ing, String instructions) {
        if (ing != null) {
            ing.sort(null);
        }
        this.ingredients = ing;
        this.name = name;
        this.instructions = instructions;
    }
    public void addIngredient(Ingredient g) {
        this.ingredients.add(g);
        this.ingredients.sort(null);
    }
    public void deleteIngredient(Ingredient g) {this.ingredients.remove(g);}
    public boolean contains(Ingredient g) {return this.ingredients.contains(g);}
    public ArrayList<Ingredient> getList() {return this.ingredients;}
    public String getName() { return this.name; }

    public void setStatus(Boolean val) {
        this.canMake = val;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Ingredient i : this.ingredients) {
            str.append(i.toString());
        }
        str.append("Instructions: ");
        str.append(this.instructions);
        return str.toString();
    }

}
