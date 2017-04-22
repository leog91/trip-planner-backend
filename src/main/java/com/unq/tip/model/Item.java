package com.unq.tip.model;


import org.joda.time.LocalDate;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    private String user;

    private String category;

    private int groupSize;


    /*
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    private LocalDateTime date;

*/

    //@Type(type="org.joda.time.contrib.hibernate.PersistentDateTime")
    private LocalDate date;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


/*
    @ManyToOne
    private Price price;
*/
    public Item() {
    }


/*
    public Item(String name, int ammount, Price price, String currency, String category, LocalDate date) {
        this.name = name;
        this.ammount = ammount;
        this.price = price;
        this.currency = currency;
        this.category = category;
        this.date = date;
    }
*/

    public Item(String name, int ammount, String currency, String category, LocalDate date, String user,int groupSize) {
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

  //  public Price getPrice() {return price; }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    //public void setPrice(Price price) {this.price = price;}

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }


}
