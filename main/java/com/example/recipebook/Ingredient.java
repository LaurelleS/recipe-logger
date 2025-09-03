package com.example.recipebook;

import java.util.Objects;

public class Ingredient implements Comparable<Ingredient> {

    private String name;
    private Integer amount;
    private Boolean inPantry;

    public Ingredient(String str, int amount) {
        this.name = str;
        this.amount = amount;
    }
    public String getName() {
        return this.name;
    }
    public void setStatus(Boolean val) {
        this.inPantry = val;
    }
    public String toString() {
        return this.name + " - " + this.amount + " grams\n";
    }
    public int getAmt(){
        return this.amount;
    }

    @Override
    public int compareTo(Ingredient o) {
        return this.name.toLowerCase().compareTo(o.name.toLowerCase());
    }
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Ingredient)) {
            return false;
        }
        return Objects.equals(this.getName(), ((Ingredient) o).getName());
    }
}
