package com.unq.tip.model.builder;

import com.unq.tip.model.Currency;

/**
 * Created by Leonardo on 7/4/2017.
 */
public class CurrencyBuilder {

    private String codeFrom;

    private String codeTo;

    private String date;

    public CurrencyBuilder withDate(String date) {
        this.date = date;
        return this;
    }

    public CurrencyBuilder withCodeFrom(String codeFrom) {
        this.codeFrom = codeFrom;
        return this;
    }

    public CurrencyBuilder withCodeTo(String codeTo) {
        this.codeTo = codeTo;
        return this;
    }


    public Currency build() {
        return new Currency(codeFrom, codeTo, date);
    }




}
