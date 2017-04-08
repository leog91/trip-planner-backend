package com.unq.tip.model.builder;

import com.unq.tip.model.Item;
import com.unq.tip.model.Price;

/**
 * Created by Leonardo on 6/4/2017.
 */
public class ItemBuilder {

    String name = "";
    int ammount = 0;
    String currency = "";
    String category = "";
    Price price = new Price(0);

    ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    ItemBuilder withPrice(int price) {
        this.price = new Price(price);
        return this;
    }

    ItemBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    ItemBuilder withCategory(String category) {
        this.category = category;
        return this;
    }


    Item build() {
        return new Item(name, ammount, price, currency,category);
    }


}
