package com.unq.tip.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Leonardo on 16/4/2017.
 */


@Entity
public class User {

    @Id
    private String email;

    private int groupSize;

    private String currentCurrency;

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

/*
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
*/

   /* @OneToMany()
    private Set<Item> items = new HashSet<>();
*/

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(String email, int groupSize, String currentCurrency) {
        this.email = email;
        this.groupSize = groupSize;
        this.currentCurrency = currentCurrency;
    }

    public User() {
    }

    //public void addItem(Item item){this.items.add(item);}


}
