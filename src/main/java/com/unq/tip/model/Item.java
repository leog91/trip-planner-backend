package com.unq.tip.model;


import org.joda.time.LocalDate;

import javax.persistence.*;

/**
 * Created by leog on 01/04/17.
 */
@Entity
public class Item {

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;
    private int ammount;
    private String currency;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    private String category;

    private int groupSize;

    //@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public Item() {
    }


    public Item(String name, int ammount, String currency, String category, LocalDate date, String user, int groupSize) {
        this.name = name;
        this.ammount = ammount;
        this.currency = currency;
        this.user = user;
        this.category = category;
        this.date = date;
        this.groupSize = groupSize;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }


}
