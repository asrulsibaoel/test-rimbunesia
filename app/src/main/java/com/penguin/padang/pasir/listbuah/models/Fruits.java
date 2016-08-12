package com.penguin.padang.pasir.listbuah.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jowy on 8/11/16.
 */
public class Fruits {


    @SerializedName("Fruit")
    @Expose
    private List<Fruit> fruit = new ArrayList<Fruit>();

    /**
     * @return The fruit
     */
    public List<Fruit> getFruit() {
        return fruit;
    }

    /**
     * @param fruit The Fruit
     */
    public void setFruit(List<Fruit> fruit) {
        this.fruit = fruit;
    }
}
