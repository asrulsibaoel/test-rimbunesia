package com.penguin.padang.pasir.listbuah.models;

/**
 * Created by jowy on 8/11/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fruit implements Comparable<Fruit>{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("category")
    @Expose
    private String category;

    @Override
    public int compareTo(Fruit fruit) {
        return category.compareTo(fruit.category);
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The category
     */
    public String getCategory() {
        return category;
    }

    /**
     *
     * @param category
     * The category
     */
    public void setCategory(String category) {
        this.category = category;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Fruit fruit = (Fruit) o;
//
//        return name.equals(fruit.name);
//
//    }

//    @Override
//    public int hashCode() {
//        return name.hashCode();
//    }

//    @Override
//    public String toString() {
//        return name ;
//    }
}
