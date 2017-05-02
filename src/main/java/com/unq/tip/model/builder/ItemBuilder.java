package com.unq.tip.model.builder;

import com.unq.tip.model.Item;
import com.unq.tip.model.Price;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;


/**
 * Created by Leonardo on 6/4/2017.
 */
public class ItemBuilder {

    String name = "bassename";
    int ammount = 0;
    String currency = "";
    String category = "";
    String user = "";
    int groupSize = 0;

    //LocalDateTime date = LocalDate.now().atStartOfDay();

    LocalDate date = LocalDate.now();


    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withGroupSize(int groupSize) {
        this.groupSize = groupSize;
        return this;
    }


    public ItemBuilder withCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    public ItemBuilder withCategory(String category) {
        this.category = category;
        return this;
    }

    public ItemBuilder withDate(LocalDate date) {
        this.date = date;
        return this;
    }

    public ItemBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public Item build() {
        return new Item(name, ammount, currency, category, date, user, groupSize);
    }


}
