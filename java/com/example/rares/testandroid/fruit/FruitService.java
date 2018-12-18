package com.example.rares.testandroid.fruit;

import java.util.ArrayList;
import java.util.List;

public class FruitService {

    private static FruitService instance = new FruitService();

    public static FruitService getInstance() {
        return instance;
    }

    private List<Fruit> fruits;

    public List<Fruit> getFruits()
    {
        if(fruits==null) {
            fruits = getInitialList();
        }
        return fruits;
    }

    public List<Fruit> getInitialList() {
        fruits = new ArrayList<Fruit>();
        Fruit F1 = new Fruit(1, "banana", 2.5f, 10, false);
        Fruit F2 = new Fruit(2, "apple", 3.0f, 11, false);
        Fruit F3 = new Fruit(3, "orange", 1.5f, 15, true);
        fruits.add(F1); fruits.add(F2); fruits.add(F3);
        return fruits;
    }
}
