package com.unq.tip.model.builder;

import com.unq.tip.model.Trip;
import org.joda.time.LocalDate;

/**
 * Created by Leonardo on 9/6/2017.
 */
public class TripBuilder {

    private LocalDate dateFrom = LocalDate.now();

    private LocalDate dateTo = LocalDate.now();

    private String info = "";

    private String user = "";

    private String name = "";


    public TripBuilder withUser(String user) {
        this.user = user;
        return this;
    }

    public TripBuilder withInfo(String info) {
        this.info = info;
        return this;
    }

    public TripBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TripBuilder withDateFrom(LocalDate dateFrom) {
        this.dateFrom = dateFrom;
        return this;
    }

    public TripBuilder withDateTo(LocalDate dateTo) {
        this.dateTo = dateTo;
        return this;
    }

    public Trip build() {
        return new Trip(dateFrom, dateTo, info, user, name);
    }


}
