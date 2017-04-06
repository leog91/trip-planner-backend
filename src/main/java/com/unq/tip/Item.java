package com.unq.tip;

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
    @ManyToOne
    private Price price;

    public String getName() {
        return name;
    }

    public Price getPrice() {return price;}

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Price price){ this.price = price;}

    public int getAmmount() {
        return ammount;
    }

    public void setAmmount(int ammount) {
        this.ammount = ammount;
    }


}
