package com.unq.tip.model;

import javax.persistence.*;

/**
 * Created by leog on 01/04/17.
 */
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int ammount;
    private String currency;

    private String category;

    @ManyToOne
    private Price price;

    public Item() {
    }

    public Item(String name, int ammount, Price price, String currency, String category) {
        this.name = name;
        this.ammount = ammount;
        this.price = price;
        this.currency = currency;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCurrency() {
        return currency;
    }

    public Price getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }


}