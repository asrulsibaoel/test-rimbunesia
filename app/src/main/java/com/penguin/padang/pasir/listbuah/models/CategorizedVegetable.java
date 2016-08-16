package com.penguin.padang.pasir.listbuah.models;

import java.util.List;

/**
 * Created by jowy on 8/15/16.
 */
public class CategorizedVegetable {

    private String categoryName;

    private List<Fruit> fruits;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public List<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(List<Fruit> fruits) {
        this.fruits = fruits;
    }
}
