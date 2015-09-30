package com.waylonbrown.stockpileapp;

import java.util.List;

/**
 * Created by waylon.brown on 9/30/15.
 */
public class CustomerObject {
    String name;
    int id;
    int age;
    List<String> purchases;

    public CustomerObject(){}

    public CustomerObject(String name, int id, int age, List<String> purchases){
        this.name = name;
        this.id = id;
        this.age = age;
        this.purchases = purchases;
    }
}
