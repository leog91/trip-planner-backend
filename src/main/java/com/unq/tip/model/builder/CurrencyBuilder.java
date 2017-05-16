package com.unq.tip.model.builder;

import com.unq.tip.model.Currency;
import org.joda.time.LocalDate;

/**
 * Created by Leonardo on 7/4/2017.
 */
public class CurrencyBuilder {

    private String codeFrom;

    private String codeTo;

    private LocalDate date;

    private Float ratio;

    public CurrencyBuilder withDate(LocalDate date) {
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

    public CurrencyBuilder withRatio(Float ratio) {
        this.ratio = ratio;
        return this;
    }

    public Currency build() {
        return new Currency(codeFrom, codeTo, date, ratio);
    }


}
