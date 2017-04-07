package com.unq.tip;

/**
 * Created by Leonardo on 6/4/2017.
 */
public class ItemBuilder {

    Item item;

    String name = "";

    int ammount = 0;

    String currency = "";

    Price price = new Price(0);

    ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    ItemBuilder withPrice(int price) {
        this.price = new Price(price);
        return this;
    }

    ItemBuilder withCurrency(String currency){
        this.currency = currency;
        return this;
    }


    Item build() {
        return new Item(name, ammount, price,currency);
    }


}
